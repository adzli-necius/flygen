/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.finder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectRootFinderTest {

    @TempDir
    Path directory;

    private final ProjectRootFinder finder = new ProjectRootFinder();

    @Test
    void acceptsMavenProjectRoot() throws Exception {
        Files.createFile(directory.resolve("pom.xml"));

        assertEquals(directory.toAbsolutePath(), finder.find(directory));
    }

    @Test
    void rejectsDirectoryWithoutPomFile() {
        IOException exception = assertThrows(IOException.class, () -> finder.find(directory));

        org.junit.jupiter.api.Assertions.assertTrue(exception.getMessage().contains("Unsupported project directory"));
    }
}
