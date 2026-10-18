/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrationActionTest {

    @Test
    void prefixesTargetWithActionWhenNeeded() {
        assertEquals("alter_users_table", MigrationAction.ALTER.normalizeMigrationName("users_table"));
        assertEquals("data_seed_roles", MigrationAction.DATA.normalizeMigrationName("seed_roles"));
    }

    @Test
    void preservesExistingActionPrefixForBackwardCompatibility() {
        assertEquals("create_users_table", MigrationAction.CREATE.normalizeMigrationName("create_users_table"));
    }
}
