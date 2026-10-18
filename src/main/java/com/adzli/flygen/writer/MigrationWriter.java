/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MigrationWriter {

    public Path write(Path migrationDirectory, String filename) throws IOException {
        return write(migrationDirectory, filename, "");
    }

    public Path write(Path migrationDirectory, String filename, String contents) throws IOException {
        Files.createDirectories(migrationDirectory);

        Path migrationFile = migrationDirectory.resolve(filename);
        return Files.writeString(
                migrationFile,
                contents,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE_NEW,
                StandardOpenOption.WRITE
        );
    }
}
