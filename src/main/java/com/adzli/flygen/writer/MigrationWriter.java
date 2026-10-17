/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MigrationWriter {

    public Path write(Path migrationDirectory, String filename) throws IOException {
        Files.createDirectories(migrationDirectory);

        Path migrationFile = migrationDirectory.resolve(filename);
        return Files.createFile(migrationFile);
    }
}
