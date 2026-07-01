package com.adzli.flygen.generator;

import com.adzli.flygen.finder.MigrationDirectoryFinder;
import com.adzli.flygen.finder.ProjectRootFinder;
import com.adzli.flygen.writer.MigrationWriter;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.find;

public class MigrationGenerator {

    private final FlyWayVersionGenerator flyWayVersionGenerator = new FlyWayVersionGenerator();
    private final FilenameGenerator filenameGenerator = new FilenameGenerator();
    private final MigrationWriter migrationWriter = new MigrationWriter();
    private final ProjectRootFinder projectRootFinder = new ProjectRootFinder();
    private final MigrationDirectoryFinder migrationDirectoryFinder = new MigrationDirectoryFinder();


    public void generate(String migrationName) {

        try {
            String version = flyWayVersionGenerator.generate();

            String filename = filenameGenerator.generate(version,migrationName);

            Path projectRoot = projectRootFinder.find();
            Path migrationDirectory = migrationDirectoryFinder.find(projectRoot);

            migrationWriter.write(filename);

            System.out.println("Project Root :" + projectRoot);
            System.out.println("Migration Directory :" + migrationDirectory);
            System.out.println("Migration created: " + filename);


        } catch (IOException e) {
            System.err.println("Failed to create migration: " + e.getMessage());
        }

    }
}
