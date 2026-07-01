package com.adzli.flygen.generator;

public class MigrationGenerator {

    private final FlyWayVersionGenerator flyWayVersionGenerator = new FlyWayVersionGenerator();
    private final FilenameGenerator filenameGenerator = new FilenameGenerator();

    public void generate(String migrationName) {

        String version = flyWayVersionGenerator.generate();
        String filename = filenameGenerator.generate(
                version,
                migrationName
        );
        System.out.println(filename);
    }
}
