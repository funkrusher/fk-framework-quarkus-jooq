package org.fk.core.jooq.codegen;


import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FkGeneratedFilesPostProcessor {

    public static void processDTOFiles(String targetDirectory) {
        try {
            Files.walkFileTree(Paths.get(targetDirectory), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().contains(File.separator + "dtos" + File.separator)) {
                        // Process files within "dtos" folders
                        processDTOFile(file.toFile());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDTOFile(File inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            File tempFile = File.createTempFile("temp", ".java", inputFile.getParentFile());
            try (FileWriter writer = new FileWriter(tempFile)) {
                String line;
                boolean inSetter = false;
                while ((line = reader.readLine()) != null) {
                    // Check if we are inside a setter
                    if (line.contains("public void set")) {
                        inSetter = true;
                    } else if (inSetter && line.contains("}")) {
                        // Check if the setter is ending
                        inSetter = false;
                        // Add this.touch() before the closing curly brace
                        writer.write("        this.touch();\n");
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


    public static void processInterfaceFiles(String targetDirectory) {
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

    private static void processInterfaceFile(File inputFile) {
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
                    } else if (line.contains("public void set") && line.contains("LocalDateTime")) {
                        String withoutSet = StringUtils.substringBetween(line, "public void set", "(");
                        String fieldName = Character.toLowerCase(withoutSet.charAt(0)) + withoutSet.substring(1);
                        writer.write("    @Schema(name = \"" + fieldName + "\", example = \"1618312800000\", type = SchemaType.STRING, format = \"date-time\", description = \"Timestamp in milliseconds since 1970-01-01T00:00:00Z\")\n");
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

