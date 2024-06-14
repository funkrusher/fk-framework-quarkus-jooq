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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fk.core.jooq.FkPojoFilePostProcessor.PojoProcessingEvent.*;
import static org.fk.core.jooq.FkPojoFilePostProcessor.PojoProcessingConfig.*;

/**
 * FkPojoFileProcessor
 * <p>
 * Helper-class that post-processes the pojo files that the jooq code-generator has created,
 * and makes some modifications that make the pojos ready to be used (as templates) for our DTOs.
 */
public class FkPojoFilePostProcessor {
    private static final Pattern DTO_CLAZZ_PATTERN = Pattern.compile("public class\\s+(\\w+)\\s+implements\\s+(\\w+)\\s*\\{");
    private static final Pattern DTO_FIELD_PATTERN = Pattern.compile("private\\s+(\\w+)\\s+(\\w+)\\s*;");
    private static final Pattern DTO_SETTER_PATTERN = Pattern.compile("public\\s+(\\w+)\\s+set(\\w+)\\(([^)]+)\\)\\s*\\{");
    private static final Pattern DTO_CONSTRUCTOR_PATTERN = Pattern.compile("public\\s+(\\w+)\\((.*)");
    private static final Pattern DTO_FIELD_INIT_PATTERN = Pattern.compile("this.(\\w+)\\s+(.*)");

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
            \s   protected transient BookKeeper keeper = new BookKeeper(this);
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
            \s   public %s() {}
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

    public enum PojoProcessingConfig {
        CLASS_NAME,
        INTERFACE_NAME,
        FIELD_NAME,
        FIELD_TYPE,
        SETTER_FIELD_NAME
    }

    /**
     * Rewrite the At Start Of Package Definition
     * <p>
     * - add Packages we may need
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @throws IOException internal-error during io.
     */
    private void rewriteAtStartOfPackageDefinition(final List<String> linesCollected, final FileWriter writer) throws IOException {
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine.replaceAll(".pojos", ".dtos"));
        }
        // TODO: try to resolve only those packages, that are really! needed in the DTO (unused-imports problem)
        // TODO: afterwards remove the SuppressWarnings Annotation, as we want to have Warnings when we copy the DTOs into our project.
        writer.write(EOL);
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(DTO.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(BookKeeper.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(Schema.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(SchemaType.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(XmlTransient.class.getName()));
        writer.write(BLOCK_IMPORT_DEFINITION.formatted(JsonIgnore.class.getName()));
    }

    /**
     * Rewrite the At Start Of Class Definition
     * <p>
     * - add DTO interface to Class-Implements.
     *
     * @param writer writer
     * @param configs configs
     * @throws IOException internal-error during io.
     */
    private void rewriteAtStartOfClassDefinition(final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {
        String clazzName = configs.get(CLASS_NAME);
        String interfaceName = configs.get(INTERFACE_NAME);
        writer.write(BLOCK_CLAZZ_DEFINITION.formatted(
                clazzName + DTO_NAME,
                interfaceName,
                DTO.class.getSimpleName()
        ));
        writer.write(EOL);
    }

    /**
     * Rewrite the At Start Of SerialVersionUuid Definition
     * <p>
     * - add comment
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @throws IOException internal-error during io.
     */
    private void rewriteAtStartOfSerialVersionUid(final List<String> linesCollected, final FileWriter writer) throws IOException {
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
        writer.write(EOL);
        writer.write(HEADER_DATABASE_FIELDS);
        writer.write(EOL);
    }


    /**
     * Rewrite the At Start Of Field Definition
     * <p>
     * - add LocalDateTime Annotations
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @param configs configs
     * @throws IOException internal-error during io.
     */
    private void rewriteAtStartOfFieldDefinition(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {
        String fieldType = configs.get(FIELD_TYPE);
        if (fieldType.contains(LOCAL_DATE_TIME)) {
            writer.write(ANNOTATION_SCHEMA_LOCALDATETIME);
        }
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
        configs.put(FIELD_NAME, null);
        configs.put(FIELD_TYPE, null);
    }


    /**
     * Add Code to the End Of The Class.
     * <p>
     * - add bookkeeper support
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @throws IOException internal-error during io.
     */
    private void rewriteAtEndOfClassDefinition(final List<String> linesCollected, final FileWriter writer) throws IOException {
        writer.write(EOL);
        writer.write(HEADER_BOOK_KEEPER);
        writer.write("    " + EOL);
        writer.write(BLOCK_BOOK_KEEPER);
        for (String collectedLine : linesCollected) {
            writer.write(collectedLine);
        }
    }

    /**
     * Rewrite the Default Constructor
     *
     * @param writer writer
     * @param configs configs
     * @throws IOException internal-error during io.
     */
    private void rewriteCollectedDefaultConstructorBlock(final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {
        // we override the default constructor generated by jOOQ.
        String clazzName = configs.get(CLASS_NAME);
        writer.write(EOL);
        writer.write(HEADER_NON_DATABASE_FIELDS);
        writer.write(EOL);
        writer.write(HEADER_CONSTRUCTOR);
        writer.write(EOL);
        writer.write(BLOCK_CONSTRUCTOR_DEFINITION.formatted(clazzName + DTO_NAME));
        writer.write(EOL);
    }

    /**
     * Rewrite the From-Interface Constructor
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @param configs configs
     * @throws IOException internal-error during io.
     */
    private void rewriteCollectedInterfaceConstructorBlock(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {
        // we don't need that one for now.
    }

    /**
     * Rewrite the From-Fields Constructor
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @param configs configs
     * @throws IOException internal-error during io.
     */
    private void rewriteCollectedFieldsConstructorBlock(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {
        String constructorName = null;

        List<String> openMethodPart = new ArrayList<>();
        List<String> setterCalls = new ArrayList<>();
        List<String> closingMethodPart = new ArrayList<>();

        int step = 0;
        for (String collectedLine : linesCollected) {
            Matcher constructorMatcher = DTO_CONSTRUCTOR_PATTERN.matcher(collectedLine);
            if (constructorMatcher.find()) {
                // we arrived at a constructor-method.
                constructorName = constructorMatcher.group(1);
                openMethodPart.add(collectedLine.replaceFirst(constructorName, "static " + constructorName + DTO_NAME + " create"));
            } else if (collectedLine.contains("{")) {
                openMethodPart.add(collectedLine);
                openMethodPart.add("        return new " + constructorName + DTO_NAME + "()");
                openMethodPart.add(EOL);
            } else {
                Matcher fieldInitMatcher = DTO_FIELD_INIT_PATTERN.matcher(collectedLine);
                if (fieldInitMatcher.find()) {
                    step = 1;
                    // we arrived at a field-init
                    String fieldInitName = fieldInitMatcher.group(1);
                    String fieldInitNameCC = Character.toUpperCase(fieldInitName.charAt(0)) + fieldInitName.substring(1);
                    String setterName = "            .set" + fieldInitNameCC + "(" + fieldInitName + ")";
                    setterCalls.add(setterName);
                } else {
                    if (step == 1) {
                        step = 2;
                    }
                    if (step == 0) {
                        openMethodPart.add(collectedLine);
                    } else if (step == 2) {
                        closingMethodPart.add(collectedLine);
                    }
                }
            }
        }

        for (String line : openMethodPart) {
            writer.write(line);
        }
        for (int i=0; i < setterCalls.size(); i++) {
            String setterCall = setterCalls.get(i);
            writer.write(setterCall);
            if ((i+1) == setterCalls.size()) {
                writer.write(";");
            }
            writer.write(EOL);
        }
        for (String line : closingMethodPart) {
            writer.write(line);
        }

        writer.write(EOL);
        writer.write(HEADER_DATABASE_FIELDS_GETTERS_SETTERS);
        writer.write(EOL);
    }

    /**
     * Rewrite the toString() function
     *
     * @param writer writer
     * @throws IOException internal-error during io.
     */
    private void rewriteCollectedToStringBlock(final FileWriter writer) throws IOException {
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
    }

    /**
     * Rewrite the Setter
     * <p>
     * - add touch() functionally for bookkeeping support
     * - fluent-setters
     * - correct setter-name for all cases (even when field-name is UPPERCASE)
     *
     * @param linesCollected linesCollected
     * @param writer writer
     * @param configs configs
     * @throws IOException internal-error during io.
     */
    private void rewriteCollectedSetterBlock(final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {
        for (String collectedLine : linesCollected) {
            String setterFieldName = configs.get(SETTER_FIELD_NAME);
            if (setterFieldName != null && collectedLine.contains("return ")) {
                // add touch() call for book-keeping support.
                writer.write(BLOCK_TOUCH.formatted(setterFieldName));
                writer.write(collectedLine);
                configs.put(SETTER_FIELD_NAME, null);
            } else {
                Matcher setterMatcher = DTO_SETTER_PATTERN.matcher(collectedLine);
                if (setterMatcher.find()) {
                    // we arrived at a setter-method.
                    String fluentSetterReturnType = setterMatcher.group(1);
                    String capitalFieldName = setterMatcher.group(2);
                    String setterParameterType = setterMatcher.group(3);

                    boolean isAllUppercase = capitalFieldName.chars().noneMatch(Character::isLowerCase);
                    if (isAllUppercase) {
                        configs.put(SETTER_FIELD_NAME, capitalFieldName);
                    } else {
                        configs.put(SETTER_FIELD_NAME, Character.toLowerCase(capitalFieldName.charAt(0)) + capitalFieldName.substring(1));
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

    /**
     * Check the given line and configs and resolve the next-event that we can match for those criteria.
     * We also set configs here by analyzing the line/configs and save some infos into our configs,
     * that we can use later on during processing of the events.
     *
     * @param line line
     * @param configs configs
     * @return next event
     */
    private PojoProcessingEvent getNextEvent(final String line, final Map<PojoProcessingConfig, String> configs) {
        if (line.startsWith(PACKAGE)) {
            // we ignore everything before the package starts (which is: the jooq-generated info)
            return AT_START_OF_PACKAGE_DEFINITION;
        } else if (line.startsWith(PUBLIC)) {
            Matcher clazzMatcher = DTO_CLAZZ_PATTERN.matcher(line);
            if (clazzMatcher.find()) {
                String interfaceName = clazzMatcher.group(2);
                configs.put(CLASS_NAME, clazzMatcher.group(1));
                configs.put(INTERFACE_NAME, interfaceName);
                return AT_START_OF_CLASS_DEFINITION;
            }
        } else if (line.contains(OVERRIDE)) {
            return COLLECTED_OVERRIDE_BLOCK;
        } else if (line.contains("public " + configs.get(CLASS_NAME) + "(")) {
            return COLLECTED_CONSTRUCTOR_BLOCK;
        } else if (line.contains(PRIVATE)) {
            if (line.contains(SERIAL_VERSION_UID)) {
                return AT_START_OF_SERIAL_VERSION_UID;
            } else {
                Matcher privateVarMatcher = DTO_FIELD_PATTERN.matcher(line);
                if (privateVarMatcher.find()) {
                    configs.put(FIELD_NAME, privateVarMatcher.group(2));
                    configs.put(FIELD_TYPE, privateVarMatcher.group(1));
                    return AT_START_OF_FIELD_DEFINITION;
                }
            }
        } else if (line.equals(CURLY_CLOSE)) {
            return AT_END_OF_CLASS_DEFINITION;
        }
        return null;
    }

    /**
     * Process given ongoing event.
     * <p>
     * As soon as an event is processed here, it is finished.
     * Some events may not be processed here, as they are still ongoing, they will only be processed when a specific
     * criteria is also true
     *
     * @param event event
     * @param line line
     * @param linesCollected linesCollected during ongoing event
     * @param writer writer
     * @param configs configs
     * @return current-event (if still ongoing) or NULL if event finished here.
     * @throws IOException internal-error during io
     */
    private PojoProcessingEvent processOngoingEvent(final PojoProcessingEvent event, final String line, final List<String> linesCollected, final FileWriter writer, final Map<PojoProcessingConfig, String> configs) throws IOException {

        linesCollected.add(line + EOL);

        boolean eventFinished = false;
        if (event == AT_START_OF_PACKAGE_DEFINITION) {
            rewriteAtStartOfPackageDefinition(linesCollected, writer);
            eventFinished = true;
        } else if (event == AT_START_OF_CLASS_DEFINITION) {
            rewriteAtStartOfClassDefinition(writer, configs);
            eventFinished = true;
        } else if (event == AT_START_OF_SERIAL_VERSION_UID) {
            rewriteAtStartOfSerialVersionUid(linesCollected, writer);
            eventFinished = true;
        } else if (event == AT_START_OF_FIELD_DEFINITION) {
            rewriteAtStartOfFieldDefinition(linesCollected, writer, configs);
            eventFinished = true;
        } else if (event == AT_END_OF_CLASS_DEFINITION) {
            rewriteAtEndOfClassDefinition(linesCollected, writer);
            eventFinished = true;
        } else if (event == COLLECTED_CONSTRUCTOR_BLOCK && line.contains(CURLY_CLOSE)) {
            final String firstLine = linesCollected.getFirst();
            if (firstLine.contains("() {}")) {
                rewriteCollectedDefaultConstructorBlock(writer, configs);
            } else if (firstLine.contains("{")){
                rewriteCollectedInterfaceConstructorBlock(linesCollected, writer, configs);
            } else {
                rewriteCollectedFieldsConstructorBlock(linesCollected, writer, configs);
            }
            eventFinished = true;
        } else if (event == COLLECTED_OVERRIDE_BLOCK && line.contains(CURLY_CLOSE)) {
            final String secondLine = linesCollected.get(1);
            if (secondLine.contains("public String toString() {")) {
                rewriteCollectedToStringBlock(writer);
            } else if (secondLine.contains("from(")) {
                for (String collectedLine : linesCollected) {
                    writer.write(collectedLine);
                }
                writer.write(EOL);
            } else if (secondLine.contains("into(")) {
                for (String collectedLine : linesCollected) {
                    writer.write(collectedLine);
                }
            } else {
                // we assume that the other case here is the setter-block.
                rewriteCollectedSetterBlock(linesCollected, writer, configs);
            }
            eventFinished = true;
        }
        if (eventFinished) {
            linesCollected.clear();
            return null;
        }
        return event;
    }


    /**
     * Process the given pojo file, and convert it into a DTO-file.
     * The resulting DTO-file can be used as-is, but it is recommended to use this resulting DTO-file
     * like a Template for copying it into your project and manually adding additional non-database fields if needed.
     *
     * @param reader reader for pojo-file (source)
     * @param writer writer for dto-file (target)
     * @throws IOException internal-error during io
     */
    public void processPojoFile(final BufferedReader reader, final FileWriter writer) throws IOException {
        String line;
        String lastLine = null;
        boolean writeEnabled = false;
        PojoProcessingEvent event = null;
        List<String> linesCollected = new ArrayList<>();
        EnumMap<PojoProcessingConfig, String> configs = new EnumMap<>(PojoProcessingConfig.class);

        while ((line = reader.readLine()) != null) {
            if (!writeEnabled && line.startsWith(PACKAGE)) {
                // we ignore everything before the package starts (which is: the jooq-generated info)
                writeEnabled = true;
            }
            if (event == null) {
                event = getNextEvent(line, configs);
            }
            if (event == null) {
                // no event, just use the regular write-logic.
                if (writeEnabled && (lastLine == null || !(lastLine.equals(EMPTY_STRING) && line.equals(EMPTY_STRING)))) {
                    // ignore double empty lines, but write everything else.
                    writer.write(line + EOL);
                }
                lastLine = line;
            } else {
                // event given, use the custom write-logic.
                event = processOngoingEvent(event, line, linesCollected, writer, configs);
            }
        }
    }
}
