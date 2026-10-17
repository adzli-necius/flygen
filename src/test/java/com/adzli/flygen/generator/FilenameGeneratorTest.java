/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilenameGeneratorTest {

    private final FilenameGenerator generator = new FilenameGenerator();

    @Test
    void generatesFlywayFilenameForValidMigrationName() {
        assertEquals(
                "V20261011120000000__create_users_table.sql",
                generator.generate("20261011120000000", "create_users_table")
        );
    }

    @Test
    void rejectsUnsafeMigrationNames() {
        assertThrows(IllegalArgumentException.class, () -> generator.generate("1", "../outside"));
        assertThrows(IllegalArgumentException.class, () -> generator.generate("1", "create users"));
        assertThrows(IllegalArgumentException.class, () -> generator.generate("1", "_starts_with_underscore"));
    }
}
