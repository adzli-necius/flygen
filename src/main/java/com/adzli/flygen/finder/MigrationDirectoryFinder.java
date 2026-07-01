package com.adzli.flygen.finder;

import java.nio.file.Path;

public class MigrationDirectoryFinder {

    public Path find(Path projectRoot) {
        return projectRoot
                .resolve("src")
                .resolve("main")
                .resolve("resources")
                .resolve("db")
                .resolve("migration");
    }
}
