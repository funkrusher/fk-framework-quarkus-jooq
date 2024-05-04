package org.fk.core.jooq;

import org.fk.core.exception.MappingException;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class FkGeneratedFilesPostProcessor {
    private final FkInterfaceFilePostProcessor fkInterfaceFilePostProcessor = new FkInterfaceFilePostProcessor();
    private final FkPojoFilePostProcessor fkPojoFilePostProcessor= new FkPojoFilePostProcessor();
    private static final String JAVA_FILE_ENDING = ".java";

    public void processFiles(String targetDirectory) throws IOException {
        Files.walkFileTree(Paths.get(targetDirectory), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                File inputFile = file.toFile();
                if (file.toString().contains(File.separator + "interfaces" + File.separator)) {
                    // Process files within "interfaces" folders
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                        File tempFile = File.createTempFile("temp", JAVA_FILE_ENDING, inputFile.getParentFile());
                        try (FileWriter writer = new FileWriter(tempFile)) {
                            fkInterfaceFilePostProcessor.processInterfaceFile(reader, writer);
                        }
                        // Replace the original file with the temporary modified file
                        Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    } catch (IOException e) {
                        throw new MappingException(e);
                    }

                } else if (file.toString().contains(File.separator + "pojos" + File.separator)) {
                    // Process files within "dtos" folders
                    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                        String targetDirectory = file.getParent().getParent().toString();
                        File targetFolder = new File(targetDirectory + File.separator + "dtos");
                        targetFolder.mkdirs(); // Ensure the target folder exists

                        String outputFileName = targetFolder.getAbsolutePath() + File.separator +
                                inputFile.getName().replace(JAVA_FILE_ENDING, FkPojoFilePostProcessor.DTO_NAME + JAVA_FILE_ENDING);

                        try (FileWriter writer = new FileWriter(outputFileName)) {
                            fkPojoFilePostProcessor.processPojoFile(reader, writer);
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

