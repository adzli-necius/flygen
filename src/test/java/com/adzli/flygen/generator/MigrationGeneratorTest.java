package com.adzli.flygen.generator;

import com.adzli.flygen.finder.MigrationDirectoryFinder;
import com.adzli.flygen.writer.MigrationWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MigrationGeneratorTest {

    @TempDir
    Path projectRoot;

    @Test
    void writesMigrationIntoTheFlywayMigrationDirectory() throws Exception {
        MigrationGenerator generator = new MigrationGenerator(
                new FlyWayVersionGenerator() {
                    @Override
                    public String generate() {
                        return "20261011120000000";
                    }
                },
                new FilenameGenerator(),
                new MigrationWriter(),
                () -> projectRoot,
                new MigrationDirectoryFinder()
        );

        Path migrationFile = generator.generate("create_users_table");

        assertEquals(
                projectRoot.resolve("src/main/resources/db/migration/V20261011120000000__create_users_table.sql"),
                migrationFile
        );
        assertTrue(Files.isRegularFile(migrationFile));
    }
}
