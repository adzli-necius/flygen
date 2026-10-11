package com.adzli.flygen.generator;

public class FilenameGenerator {

    private static final String VALID_MIGRATION_NAME = "[A-Za-z0-9][A-Za-z0-9_-]*";

    public String generate(String version, String migrationName) {
        if (migrationName == null || !migrationName.matches(VALID_MIGRATION_NAME)) {
            throw new IllegalArgumentException(
                    "Migration name must contain only letters, numbers, underscores, and hyphens, and must start with a letter or number."
            );
        }

        return "V" + version + "__" + migrationName + ".sql";
    }
}
