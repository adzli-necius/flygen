package com.adzli.flygen.generator;

public class FilenameGenerator {

    public String generate(String version, String migrationName) {
        return "V" + version + "__" + migrationName + ".sql";
    }
}
