package org.fk.core.jooq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fk.core.jooq.FkPojoFilePostProcessor.PojoProcessingEvent.*;
import static org.fk.core.jooq.FkPojoFilePostProcessor.PojoProcessingParam.*;

public class FkPojoFilePostProcessor {
    private static final Pattern DTO_CLAZZ_PATTERN = Pattern.compile("public class\\s+(\\w+)\\s+implements\\s+(\\w+)\\s*\\{");
    private static final Pattern DTO_FIELD_PATTERN = Pattern.compile("private\\s+(\\w+)\\s+(\\w+)\\s*;");
    private static final Pattern DTO_SETTER_PATTERN = Pattern.compile("public\\s+(\\w+)\\s+set(\\w+)\\(([^)]+)\\)\\s*\\{");

    public static final String DTO_NAME = "Dto";
    private static final String PACKAGE = "package";
    private static final String PUBLIC = "public";
    private static final String PRIVATE = "private";
    private static final String OVERRIDE = "@Override";
    private static final String SERIAL_VERSION_UID = "serialVersionUID";
    private static final String LOCAL_DATE_TIME = "LocalDateTime";
    private static final String CURLY_CLOSE = "}";
    private static final String EMPTY_STRING = "";
    private static final String EOL = "\n";

    @SuppressWarnings({ "all"})
    private static final String HEADER_DATABASE_FIELDS = """
            \s   // -------------------------------------------------------------------------
            \s   // Database-Fields (must exist in the associated database table)
            \s   // -------------------------------------------------------------------------
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String HEADER_NON_DATABASE_FIELDS = """
            \s   // -------------------------------------------------------------------------
            \s   // Non-Database-Fields (please define your additional fields here)
            \s   // -------------------------------------------------------------------------
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String HEADER_CONSTRUCTOR = """
            \s   // -------------------------------------------------------------------------
            \s   // Constructor(s)
            \s   // -------------------------------------------------------------------------
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String HEADER_DATABASE_FIELDS_GETTERS_SETTERS = """
            \s   // -------------------------------------------------------------------------
            \s   // Database-Fields Setters/Getters
            \s   // -------------------------------------------------------------------------
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String HEADER_NON_DATABASE_FIELDS_GETTERS_SETTERS = """
            \s   // -------------------------------------------------------------------------
            \s   // Non-Database-Fields Setters/Getters (please define here)
            \s   // -------------------------------------------------------------------------
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String HEADER_TOSTRING_EQUALS_HASHCODE = """
            \s   // -------------------------------------------------------------------------
            \s   // ToString, Equals, HashCode
            \s   // -------------------------------------------------------------------------
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String HEADER_BOOK_KEEPER = """
            \s   // -------------------------------------------------------------------------
            \s   // BookKeeper (Patching Updates Support)
            \s   // -------------------------------------------------------------------------
            \s   """;

    @SuppressWarnings({ "all"})
    private static final String BLOCK_TOSTRING = """
            \s   @Override
            \s   public String toString() {
            \s       return keeper.touchedToString();
            \s   }
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_EQUALS = """
            \s   @Override
            \s   public boolean equals(Object obj) {
            \s       if (this == obj)
            \s           return true;
            \s       if (obj == null)
            \s           return false;
            \s       if (getClass() != obj.getClass())
            \s           return false;
            \s       final DTO other = (DTO) obj;
            \s       return this.keeper.touchedEquals(other);
            \s   }
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_HASHCODE = """
            \s   @Override
            \s   public int hashCode() {
            \s       return this.keeper.touchedHashCode();
            \s   }
            \s   """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_BOOK_KEEPER = """
            \s   @JsonIgnore
            \s   @XmlTransient
            \s   protected BookKeeper keeper;
            \s   
            \s   @JsonIgnore
            \s   @XmlTransient
            \s   public BookKeeper getBookKeeper() {
            \s       return keeper;
            \s   }
            """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_IMPORT_DEFINITION = """
            import %s;
            """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_SETTER_DEFINITION = """
            \s   public %s set%s(%s) {
            """;
    @SuppressWarnings({ "all"})
    private static final String ANNOTATION_SCHEMA_LOCALDATETIME = """
            \s   @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
            """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_CLAZZ_DEFINITION = """
            public class %s implements %s, %s {""";
    @SuppressWarnings({ "all"})
    private static final String BLOCK_CONSTRUCTOR_DEFINITION = """
            \s   public %s() { this.keeper = new BookKeeper(this); }
            """;
    @SuppressWarnings({ "all"})
    private static final String BLOCK_TOUCH = """
            \s       this.keeper.touch("%s");
            """;

    public enum PojoProcessingEvent {
        COLLECTED_OVERRIDE_BLOCK,
        COLLECTED_CONSTRUCTOR_BLOCK,
        AT_START_OF_PACKAGE_DEFINITION,
        AT_START_OF_CLASS_DEFINITION,
        AT_START_OF_FIELD_DEFINITION,
        AT_START_OF_SERIAL_VERSION_UID,
        AT_END_OF_CLASS_DEFINITION
    }

    public enum PojoProcessingParam {
        CLASS_NAME,
        INTERFACE_NAME,
        FIELD_NAME,
        FIELD_TYPE,
        SETTER_FIELD_NAME
    }

    private void rewriteAtStartOfPackageDefinition(final List<String> linesCollected, final FileWriter writer,  final Map<PojoProcessingParam, String> params) throws IOException {
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine.replaceAll(".pojos", ".dtos"));
        }
        writer.write(EOL);
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(DTO.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(BookKeeper.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(Schema.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(SchemaType.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(XmlTransient.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(JsonIgnore.class.getName()));
    }

    private void rewriteAtStartOfClassDefinition(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingParam, String> params) throws IOException {
        String clazzName = params.get(CLASS_NAME);
        String interfaceName = params.get(INTERFACE_NAME);
        writer.write(BLOCK_CLAZZ_DEFINITION.formatted(
                clazzName + DTO_NAME,
                interfaceName,
                DTO.class.getSimpleName()
        ));
        writer.write(EOL);
    }

    private void rewriteAtStartOfSerialVersionUid(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingParam, String> params) throws IOException {
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
        writer.write(EOL);
        writer.write(HEADER_DATABASE_FIELDS);
        writer.write(EOL);
    }

    private void rewriteAtStartOfFieldDefinition(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingParam, String> params) throws IOException {
        String fieldName = params.get(FIELD_NAME);
        String fieldType = params.get(FIELD_TYPE);
        if (fieldType.contains(LOCAL_DATE_TIME)) {
            writer.write(ANNOTATION_SCHEMA_LOCALDATETIME);
        }
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
        params.put(FIELD_NAME, null);
        params.put(FIELD_TYPE, null);
    }

    private void rewriteAtEndOfClassDefinition(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingParam, String> params) throws IOException {
        writer.write(EOL);
        writer.write(HEADER_BOOK_KEEPER);
        writer.write("    " + EOL);
        writer.write(BLOCK_BOOK_KEEPER);
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
    }


    private void rewriteCollectedConstructorBlock(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingParam, String> params) throws IOException {
        boolean isDefaultConstructor = linesCollected
                .stream()
                .anyMatch(x -> x.contains("() {}"));
        if (isDefaultConstructor) {
            // we dont need the other jOOQ generated constructors, just swallow them.
            // we only overwrite the default constructor here.
            String clazzName = params.get(CLASS_NAME);

            // we override the default constructor generated by jOOQ.
            writer.write(EOL);
            writer.write(HEADER_NON_DATABASE_FIELDS);
            writer.write(EOL);
            writer.write(HEADER_CONSTRUCTOR);
            writer.write(EOL);
            writer.write(BLOCK_CONSTRUCTOR_DEFINITION.formatted(clazzName + DTO_NAME));
            writer.write(EOL);
            writer.write(HEADER_DATABASE_FIELDS_GETTERS_SETTERS);
            writer.write(EOL);
        }
    }

    private void rewriteCollectedOverrideBlock(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingParam, String> params) throws IOException {
        boolean isToStringMethod = linesCollected
                .stream()
                .anyMatch(x -> x.contains("public String toString() {"));

        if (isToStringMethod) {
            // we override the default toString method generated by jOOQ,
            // and also add an equals and hashCode method in this place.
            writer.write(HEADER_NON_DATABASE_FIELDS_GETTERS_SETTERS);
            writer.write(EOL);
            writer.write(HEADER_TOSTRING_EQUALS_HASHCODE);
            writer.write(EOL);
            writer.write(BLOCK_TOSTRING);
            writer.write(EOL);
            writer.write(BLOCK_EQUALS);
            writer.write(EOL);
            writer.write(BLOCK_HASHCODE);
            writer.write(EOL);
        } else {
            for (String collectedLine : linesCollected) {
                String setterFieldName = params.get(SETTER_FIELD_NAME);
                if (setterFieldName != null && collectedLine.contains("return ")) {
                    writer.write(BLOCK_TOUCH.formatted(setterFieldName));
                    writer.write(collectedLine);
                    params.put(SETTER_FIELD_NAME, null);
                } else {
                    Matcher setterMatcher = DTO_SETTER_PATTERN.matcher(collectedLine);
                    if (setterMatcher.find()) {
                        // we arrived at a setter-method.
                        String fluentSetterReturnType = setterMatcher.group(1);
                        String capitalFieldName = setterMatcher.group(2);
                        String setterParameterType = setterMatcher.group(3);

                        boolean isAllUppercase = capitalFieldName.chars().noneMatch(Character::isLowerCase);
                        if (isAllUppercase) {
                            params.put(SETTER_FIELD_NAME, capitalFieldName);
                        } else {
                            params.put(SETTER_FIELD_NAME, Character.toLowerCase(capitalFieldName.charAt(0)) + capitalFieldName.substring(1));
                        }
                        writer.write(BLOCK_SETTER_DEFINITION.formatted(
                                fluentSetterReturnType + DTO_NAME,
                                capitalFieldName,
                                setterParameterType
                        ));
                    } else {
                        writer.write(collectedLine);
                    }
                }
            }
        }
    }

    public void processPojoFile(final BufferedReader reader, final FileWriter writer) throws IOException {
        String line;
        String lastLine = null;
        String clazzName = null;
        boolean writeEnabled = false;

        // Event-Processing
        // (we collect parts of the file to process it in a bundled way)
        PojoProcessingEvent currentEvent = null;
        List<String> linesCollected = new ArrayList<String>();
        Map<PojoProcessingParam, String> params = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            if (currentEvent == null) {
                if (line.startsWith(PACKAGE)) {
                    // we ignore everything before the package starts (which is: the jooq-generated info)
                    writeEnabled = true;
                    currentEvent = AT_START_OF_PACKAGE_DEFINITION;
                } else if (line.startsWith(PUBLIC)) {
                    Matcher clazzMatcher = DTO_CLAZZ_PATTERN.matcher(line);
                    if (clazzMatcher.find()) {
                        currentEvent = AT_START_OF_CLASS_DEFINITION;
                        clazzName = clazzMatcher.group(1);
                        String interfaceName = clazzMatcher.group(2);
                        params.put(CLASS_NAME, clazzName);
                        params.put(INTERFACE_NAME, interfaceName);
                    }
                } else if (line.contains(OVERRIDE)) {
                    currentEvent = COLLECTED_OVERRIDE_BLOCK;
                } else if (line.contains("public " + clazzName + "(")) {
                    currentEvent = COLLECTED_CONSTRUCTOR_BLOCK;
                } else if (line.contains(PRIVATE)) {
                    if (line.contains(SERIAL_VERSION_UID)) {
                        currentEvent = AT_START_OF_SERIAL_VERSION_UID;
                    } else {
                        Matcher privateVarMatcher = DTO_FIELD_PATTERN.matcher(line);
                        if (privateVarMatcher.find()) {
                            currentEvent = AT_START_OF_FIELD_DEFINITION;
                            params.put(FIELD_NAME, privateVarMatcher.group(2));
                            params.put(FIELD_TYPE, privateVarMatcher.group(1));
                        } else {
                            writer.write(line + EOL);
                        }
                    }
                } else if (line.equals(CURLY_CLOSE)) {
                    currentEvent = AT_END_OF_CLASS_DEFINITION;
                } else if (writeEnabled) {
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
                } else if (currentEvent == AT_START_OF_CLASS_DEFINITION) {
                    rewriteAtStartOfClassDefinition(linesCollected, writer, params);
                    eventFinished = true;
                } else if (currentEvent == AT_START_OF_SERIAL_VERSION_UID) {
                    rewriteAtStartOfSerialVersionUid(linesCollected, writer, params);
                    eventFinished = true;
                } else if (currentEvent == AT_START_OF_FIELD_DEFINITION) {
                    rewriteAtStartOfFieldDefinition(linesCollected, writer, params);
                    eventFinished = true;
                } else if (currentEvent == AT_END_OF_CLASS_DEFINITION) {
                    rewriteAtEndOfClassDefinition(linesCollected, writer, params);
                    eventFinished = true;
                } else if (currentEvent == COLLECTED_CONSTRUCTOR_BLOCK && line.contains(CURLY_CLOSE)) {
                    rewriteCollectedConstructorBlock(linesCollected, writer, params);
                    eventFinished = true;
                } else if (currentEvent == COLLECTED_OVERRIDE_BLOCK && line.contains(CURLY_CLOSE)) {
                    rewriteCollectedOverrideBlock(linesCollected, writer, params);
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
