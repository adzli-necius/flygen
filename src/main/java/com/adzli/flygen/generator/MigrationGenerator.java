package com.adzli.flygen.generator;

import com.adzli.flygen.writer.MigrationWriter;

import java.io.IOException;

public class MigrationGenerator {

    private final FlyWayVersionGenerator flyWayVersionGenerator = new FlyWayVersionGenerator();
    private final FilenameGenerator filenameGenerator = new FilenameGenerator();
    private final MigrationWriter migrationWriter = new MigrationWriter();

    public void generate(String migrationName) {

        try {
            String version = flyWayVersionGenerator.generate();

            String filename = filenameGenerator.generate(version,migrationName);

            migrationWriter.write(filename);

            System.out.println("Migration created: " + filename);

        } catch (IOException e) {
            System.err.println("Failed to create migration: " + e.getMessage());
        }

    }
}
