/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.generator;

import com.adzli.flygen.finder.MigrationDirectoryFinder;
import com.adzli.flygen.finder.ProjectRootFinder;
import com.adzli.flygen.writer.MigrationWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class MigrationGenerator {

    private final FlyWayVersionGenerator flyWayVersionGenerator;
    private final FilenameGenerator filenameGenerator;
    private final MigrationWriter migrationWriter;
    private final ProjectRootSupplier projectRootSupplier;
    private final MigrationDirectoryFinder migrationDirectoryFinder;

    public MigrationGenerator() {
        this(
                new FlyWayVersionGenerator(),
                new FilenameGenerator(),
                new MigrationWriter(),
                new ProjectRootFinder()::find,
                new MigrationDirectoryFinder()
        );
    }

    MigrationGenerator(
            FlyWayVersionGenerator flyWayVersionGenerator,
            FilenameGenerator filenameGenerator,
            MigrationWriter migrationWriter,
            ProjectRootSupplier projectRootSupplier,
            MigrationDirectoryFinder migrationDirectoryFinder
    ) {
        this.flyWayVersionGenerator = Objects.requireNonNull(flyWayVersionGenerator);
        this.filenameGenerator = Objects.requireNonNull(filenameGenerator);
        this.migrationWriter = Objects.requireNonNull(migrationWriter);
        this.projectRootSupplier = Objects.requireNonNull(projectRootSupplier);
        this.migrationDirectoryFinder = Objects.requireNonNull(migrationDirectoryFinder);
    }

    public Path generate(String migrationName, String migrationLocation) throws IOException {
        String version = flyWayVersionGenerator.generate();
        String filename = filenameGenerator.generate(version, migrationName);

        Path projectRoot = projectRootSupplier.get();
        Path migrationDirectory = migrationDirectoryFinder.find(projectRoot, migrationLocation);

        try {
            return migrationWriter.write(migrationDirectory, filename);
        } catch (java.nio.file.FileAlreadyExistsException e) {
            throw new IOException("Migration already exists: " + e.getFile(), e);
        } catch (java.nio.file.AccessDeniedException e) {
            throw new IOException("Cannot write to migration location (access denied): " + e.getFile(), e);
        }
    }

    @FunctionalInterface
    interface ProjectRootSupplier {
        Path get() throws IOException;
    }
}
