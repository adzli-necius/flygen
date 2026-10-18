/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.generator;

import com.adzli.flygen.command.migration.MigrationAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrationTemplateGeneratorTest {

    private final MigrationTemplateGenerator generator = new MigrationTemplateGenerator();

    @Test
    void generatesActionSpecificTemplate() {
        assertEquals("-- Alters users_table." + System.lineSeparator(), generator.generate(MigrationAction.ALTER, "users_table"));
        assertEquals("-- Adds or updates data for roles." + System.lineSeparator(), generator.generate(MigrationAction.DATA, "roles"));
    }
}
