/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.finder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MigrationDirectoryFinderTest {

    @TempDir
    Path projectRoot;

    private final MigrationDirectoryFinder finder = new MigrationDirectoryFinder();

    @Test
    void resolvesProjectRelativeLocation() {
        assertEquals(
                projectRoot.resolve("database/migrations"),
                finder.find(projectRoot, "database/migrations")
        );
    }

    @Test
    void rejectsLocationOutsideProjectRoot() {
        assertThrows(IllegalArgumentException.class, () -> finder.find(projectRoot, "../outside"));
    }
}
