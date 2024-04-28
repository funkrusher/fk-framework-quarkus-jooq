package org.fk.core.jooq;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fk.core.jooq.FkInterfaceFilePostProcessor.InterfaceProcessingEvent.AT_START_OF_PACKAGE_DEFINITION;
import static org.fk.core.jooq.FkInterfaceFilePostProcessor.InterfaceProcessingEvent.AT_START_OF_SETTER_DEFINITION;
import static org.fk.core.jooq.FkInterfaceFilePostProcessor.InterfaceProcessingParam.*;

public class FkInterfaceFilePostProcessor {
    private static final Pattern INTERFACE_SETTERN_PATTERN = Pattern.compile("public\\s+(\\w+)\\s+set(\\w+)\\(([^)]+)\\);");

    private static final String PACKAGE = "package";
    private static final String PUBLIC = "public";
    private static final String LOCAL_DATE_TIME = "LocalDateTime";
    private static final String EMPTY_STRING = "";
    private static final String EOL = "\n";

    private static final String BLOCK_IMPORT_DEFINITION = """
            import %s;
            """;
    @SuppressWarnings({ "all"})

    public enum InterfaceProcessingEvent {
        AT_START_OF_PACKAGE_DEFINITION,
        AT_START_OF_SETTER_DEFINITION,
    }

    public enum InterfaceProcessingParam {
        FLUENT_SETTER_RETURN_TYPE,
        FLUENT_SETTER_FIELD_NAME,
        FLUENT_SETTER_PARAMETER_TYPE,
    }

    private void rewriteAtStartOfPackageDefinition(final List<String> linesCollected, final FileWriter writer,  final Map<InterfaceProcessingParam, String> params) throws IOException {
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
        writer.write(EOL);
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(Schema.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(SchemaType.class.getName()));
    }

    private void rewriteAtStartOfSetterDefinition(final List<String> linesCollected, final FileWriter writer,  final Map<InterfaceProcessingParam, String> params) throws IOException {
        String fluentSetterParameterType = params.get(FLUENT_SETTER_PARAMETER_TYPE);
        // if (fluentSetterParameterType.contains(LOCAL_DATE_TIME)) {
        // writer.write("    @Schema(name = \"" + fieldName + "\", example = \"1618312800000\", type = SchemaType.NUMBER, format = \"date-time\", description = \"Timestamp in milliseconds since 1970-01-01T00:00:00Z\")\n");
        // }
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
    }

    public void processInterfaceFile(final BufferedReader reader, final FileWriter writer) throws IOException {
        String line;
        String lastLine = null;

        // Event-Processing
        // (we collect parts of the file to process it in a bundled way)
        InterfaceProcessingEvent currentEvent = null;
        List<String> linesCollected = new ArrayList<String>();
        Map<InterfaceProcessingParam, String> params = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            if (currentEvent == null) {
                if (line.startsWith(PACKAGE)) {
                    currentEvent = AT_START_OF_PACKAGE_DEFINITION;
                } else if (line.startsWith(PUBLIC)) {
                    Matcher setterMatcher = INTERFACE_SETTERN_PATTERN.matcher(line);
                    if (setterMatcher.find()) {
                        currentEvent = AT_START_OF_SETTER_DEFINITION;
                        String fluentSetterReturnType = setterMatcher.group(1);
                        String capitalFieldName = setterMatcher.group(2);
                        String setterParameterType = setterMatcher.group(3);
                        params.put(FLUENT_SETTER_RETURN_TYPE, fluentSetterReturnType);
                        params.put(FLUENT_SETTER_FIELD_NAME, Character.toLowerCase(capitalFieldName.charAt(0)) + capitalFieldName.substring(1));
                        params.put(FLUENT_SETTER_PARAMETER_TYPE, setterParameterType);
                    } else {
                        writer.write(line + EOL);
                    }
                } else {
                    if (lastLine == null || !(lastLine.equals(EMPTY_STRING) && line.equals(EMPTY_STRING))) {
                        // ignore double empty lines, but write everything else.
                        writer.write(line + EOL);
                        lastLine = line;
                    }
                }
            }

            if (currentEvent != null) {
                linesCollected.add(line + EOL);
                boolean eventFinished = false;
                if (currentEvent == AT_START_OF_PACKAGE_DEFINITION) {
                    rewriteAtStartOfPackageDefinition(linesCollected, writer, params);
                    eventFinished = true;
                } else if (currentEvent == AT_START_OF_SETTER_DEFINITION) {
                    rewriteAtStartOfSetterDefinition(linesCollected, writer, params);
                    eventFinished = true;
                }
                if (eventFinished) {
                    currentEvent = null;
                    linesCollected = new ArrayList<>();
                }
            }
        }
    }
}