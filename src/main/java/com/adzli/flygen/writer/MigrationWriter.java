package com.adzli.flygen.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MigrationWriter {

    public void write(String filename) throws IOException {
        Files.createFile(Path.of(filename));
    }
}
