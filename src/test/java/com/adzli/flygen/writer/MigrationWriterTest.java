/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.writer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MigrationWriterTest {

    @TempDir
    Path temporaryDirectory;

    @Test
    void createsMissingMigrationDirectoriesAndFile() throws Exception {
        Path migrationDirectory = temporaryDirectory.resolve("src/main/resources/db/migration");
        Path migrationFile = new MigrationWriter().write(migrationDirectory, "V1__create_users.sql");

        assertEquals(migrationDirectory.resolve("V1__create_users.sql"), migrationFile);
        assertTrue(Files.isRegularFile(migrationFile));
    }

    @Test
    void failsWithoutOverwritingWhenMigrationAlreadyExists() throws Exception {
        Path migrationDirectory = temporaryDirectory.resolve("migration");
        MigrationWriter writer = new MigrationWriter();
        writer.write(migrationDirectory, "V1__create_users.sql");

        assertThrows(
                FileAlreadyExistsException.class,
                () -> writer.write(migrationDirectory, "V1__create_users.sql")
        );
    }
}
