/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.finder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectRootFinder {

    public Path find() throws IOException {
        return find(Path.of(""));
    }

    public Path find(Path currentDirectory) throws IOException {
        Path normalizedCurrentDirectory = currentDirectory.toAbsolutePath().normalize();

        if (!Files.isRegularFile(normalizedCurrentDirectory.resolve("pom.xml"))) {
            throw new IOException(
                    "Unsupported project directory: " + normalizedCurrentDirectory + ". Run FlyGen from a Maven project root containing pom.xml."
            );
        }

        return normalizedCurrentDirectory;
    }
}
