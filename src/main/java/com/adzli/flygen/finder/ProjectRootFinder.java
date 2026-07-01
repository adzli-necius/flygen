package com.adzli.flygen.finder;

import java.nio.file.Path;

public class ProjectRootFinder {

    public Path find() {

        Path currentDirectory = Path.of("").toAbsolutePath();

        System.out.println(currentDirectory);

        return currentDirectory;

    }
}
