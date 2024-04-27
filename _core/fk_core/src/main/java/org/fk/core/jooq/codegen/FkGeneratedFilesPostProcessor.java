package org.fk.core.jooq.codegen;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FkGeneratedFilesPostProcessor {

    private Pattern setterPattern = Pattern.compile("public\\s+(\\w+)\\s+set(\\w+)\\(([^)]+)\\)\\s*\\{");
    private Pattern interfaceSetterPattern = Pattern.compile("public\\s+(\\w+)\\s+set(\\w+)\\(([^)]+)\\);");

    private Pattern clazzPattern = Pattern.compile("public class\\s+(\\w+)\\s+implements\\s+(\\w+)\\s*\\{");

    private Pattern privateVarPattern = Pattern.compile("private\\s+(\\w+)\\s+(\\w+)\\s*;");


    public void processPojoFiles(String targetDirectory) {
        try {
            Files.walkFileTree(Paths.get(targetDirectory), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().contains(File.separator + "pojos" + File.separator)) {
                        // Process files within "dtos" folders
                        processPojoFile(file.toFile(), file.getParent().getParent().toString());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processPojoFile(File inputFile, String targetDirectory) {

        String DTO_NAME = "Dto";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            File targetFolder = new File(targetDirectory + File.separator + "dtos");
            targetFolder.mkdirs(); // Ensure the target folder exists

            String outputFileName = targetFolder.getAbsolutePath() + File.separator +
                    inputFile.getName().replace(".java", DTO_NAME + ".java");

            try (FileWriter writer = new FileWriter(outputFileName)) {
                String line;
                String setterFieldName = null;
                boolean inSetter = false;
                boolean writeAdditionalImports = false;
                String clazzName = null;
                boolean constructorFinished = false;
                boolean openConstructor = false;
                boolean openToString = false;
                while ((line = reader.readLine()) != null) {
                    // Check if we are inside a setter
                    if (writeAdditionalImports) {
                        writer.write("import " + DTO.class.getName() + ";\n");
                        writer.write("import " + BookKeeper.class.getName() + ";\n");
                        writer.write("import " + Schema.class.getName() + ";\n");
                        writer.write("import " + SchemaType.class.getName() + ";\n");
                        writer.write("import " + XmlTransient.class.getName() + ";\n");
                        writer.write("import " + JsonIgnore.class.getName() + ";\n");
                        writeAdditionalImports = false;
                    } else if (line.contains("package ")) {
                        writeAdditionalImports = true;
                    } else if (line.contains("private ")) {
                        Matcher privateVarMatcher = privateVarPattern.matcher(line);
                        if (privateVarMatcher.find()) {
                            // we arrived at a private variable.
                            String varType = privateVarMatcher.group(1);
                            String varName = privateVarMatcher.group(2);
                            if (varType.contains("LocalDateTime")) {
                                writer.write("    @Schema(example = \"1618312800000\", type = SchemaType.NUMBER, format = \"date-time\", description = \"Timestamp in milliseconds since 1970-01-01T00:00:00Z\")\n");
                            }
                        }
                    } else if (line.contains("public ")){
                        if (line.contains("public class")) {
                            Matcher clazzMatcher = clazzPattern.matcher(line);
                            if (clazzMatcher.find()) {
                                // we arrived at a setter-method.
                                clazzName = clazzMatcher.group(1);
                                String interfaceName = clazzMatcher.group(2);
                                line = "public class " + clazzName + DTO_NAME + " implements " + interfaceName + ", " + DTO.class.getSimpleName() + " {";
                            }
                        } else if (clazzName != null) {
                            if (line.contains("public " + clazzName + "(")) {
                                if (!constructorFinished) {
                                    writer.write("    public " + clazzName + DTO_NAME + "() { this.keeper = new BookKeeper(this); }\n");
                                    constructorFinished = true;
                                }
                                openConstructor = true;
                            } else if (line.contains("public String toString() {")) {
                                writer.write("    public String toString() {\n");
                                writer.write("        return keeper.touchedToString();\n");
                                writer.write("    }\n");
                                writer.write("    \n");
                                writer.write("    @Override\n");
                                writer.write("    public boolean equals(Object obj) {\n");
                                writer.write("        if (this == obj)\n");
                                writer.write("            return true;\n");
                                writer.write("        if (obj == null)\n");
                                writer.write("            return false;\n");
                                writer.write("        if (getClass() != obj.getClass())\n");
                                writer.write("            return false;\n");
                                writer.write("        final DTO other = (DTO) obj;\n");
                                writer.write("        return this.keeper.touchedEquals(other);\n");
                                writer.write("    }\n");
                                writer.write("    @Override\n");
                                writer.write("    public int hashCode() {\n");
                                writer.write("        return this.keeper.touchedHashCode();\n");
                                writer.write("    }\n");
                                writer.write("\n");

                                openToString = true;

                            } else {
                                Matcher setterMatcher = setterPattern.matcher(line);
                                if (setterMatcher.find()) {
                                    // we arrived at a setter-method.
                                    String fluentSetterReturnType = setterMatcher.group(1);
                                    String capitalFieldName = setterMatcher.group(2);
                                    String setterParameterType = setterMatcher.group(3);

                                    setterFieldName = Character.toLowerCase(capitalFieldName.charAt(0)) + capitalFieldName.substring(1);
                                    inSetter = true;
                                    line = "    public " + fluentSetterReturnType + DTO_NAME + " set" + capitalFieldName + "(" + setterParameterType + ") {";
                                }
                            }
                        }
                    } else if (inSetter && line.contains("return ")) {
                        writer.write("        this.keeper.touch(\"" + setterFieldName + "\");\n");
                        inSetter = false;
                        setterFieldName = null;
                    } else if (line.equals("}")) {
                        writer.write("    // -------------------------------------------------------------------------\n");
                        writer.write("    // BookKeeper (Patching Updates Support)\n");
                        writer.write("    // -------------------------------------------------------------------------\n");
                        writer.write("    \n");
                        writer.write("    @JsonIgnore\n");
                        writer.write("    @XmlTransient\n");
                        writer.write("    protected BookKeeper keeper;\n");
                        writer.write("    \n");
                        writer.write("    @JsonIgnore\n");
                        writer.write("    @XmlTransient\n");
                        writer.write("    @Override\n");
                        writer.write("    public BookKeeper getBookKeeper() {\n");
                        writer.write("        return keeper;\n");
                        writer.write("    }\n");
                    }
                    if (!openConstructor && !openToString) {
                        writer.write(line + "\n");
                    }
                    if (openConstructor && line.equals("    }")) {
                        openConstructor = false;
                    }
                    if (openToString && line.equals("    }")) {
                        openToString = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void processInterfaceFiles(String targetDirectory) {
        try {
            Files.walkFileTree(Paths.get(targetDirectory), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().contains(File.separator + "interfaces" + File.separator)) {
                        // Process files within "interfaces" folders
                        processInterfaceFile(file.toFile());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processInterfaceFile(File inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            File tempFile = File.createTempFile("temp", ".java", inputFile.getParentFile());
            try (FileWriter writer = new FileWriter(tempFile)) {
                String line;
                boolean writeAdditionalImports = false;
                while ((line = reader.readLine()) != null) {
                    if (writeAdditionalImports) {
                        writer.write("import " + Schema.class.getName() + ";\n");
                        writer.write("import " + SchemaType.class.getName() + ";\n");
                        writeAdditionalImports = false;
                    } else if (line.contains("package ")) {
                        writeAdditionalImports = true;
                    } else if (line.contains("public ")){
                        Matcher setterMatcher = interfaceSetterPattern.matcher(line);
                        if (setterMatcher.find()) {
                            // we arrived at a setter-method.
                            String fluentSetterReturnType = setterMatcher.group(1);
                            String capitalFieldName = setterMatcher.group(2);
                            String setterParameterType = setterMatcher.group(3);
                            if (setterParameterType.contains("LocalDateTime")) {
                                String fieldName = Character.toLowerCase(capitalFieldName.charAt(0)) + capitalFieldName.substring(1);
                                // writer.write("    @Schema(name = \"" + fieldName + "\", example = \"1618312800000\", type = SchemaType.NUMBER, format = \"date-time\", description = \"Timestamp in milliseconds since 1970-01-01T00:00:00Z\")\n");
                            }
                        }
                    }
                    writer.write(line + "\n");
                }
            }
            // Replace the original file with the temporary modified file
            Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

