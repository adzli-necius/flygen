/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.finder;

import java.nio.file.Path;

public class MigrationDirectoryFinder {

    public Path find(Path projectRoot, String location) {
        Path normalizedProjectRoot = projectRoot.toAbsolutePath().normalize();
        Path configuredLocation = Path.of(location);

        if (configuredLocation.isAbsolute()) {
            throw new IllegalArgumentException("Migration location must be relative to the project root.");
        }

        Path migrationDirectory = normalizedProjectRoot.resolve(configuredLocation).normalize();
        if (!migrationDirectory.startsWith(normalizedProjectRoot)) {
            throw new IllegalArgumentException("Migration location must stay within the project root.");
        }

        return migrationDirectory;
    }
}
