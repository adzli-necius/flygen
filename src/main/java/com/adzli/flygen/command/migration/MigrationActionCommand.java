/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import com.adzli.flygen.generator.MigrationGenerator;
import com.adzli.flygen.generator.MigrationTemplateGenerator;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

abstract class MigrationActionCommand implements Callable<Integer> {

    private static final String DEFAULT_MIGRATION_LOCATION = "src/main/resources/db/migration";

    private final MigrationAction action;
    private final MigrationGenerator migrationGenerator;
    private final MigrationTemplateGenerator templateGenerator;

    @CommandLine.Parameters(index = "0", paramLabel = "TARGET")
    private String target;

    @CommandLine.Option(
            names = {"-l", "--location"},
            defaultValue = DEFAULT_MIGRATION_LOCATION,
            paramLabel = "PATH",
            description = "Project-relative Flyway migration directory (default: ${DEFAULT-VALUE})."
    )
    private String migrationLocation;

    protected MigrationActionCommand(MigrationAction action) {
        this(action, new MigrationGenerator(), new MigrationTemplateGenerator());
    }

    MigrationActionCommand(
            MigrationAction action,
            MigrationGenerator migrationGenerator,
            MigrationTemplateGenerator templateGenerator
    ) {
        this.action = action;
        this.migrationGenerator = migrationGenerator;
        this.templateGenerator = templateGenerator;
    }

    @Override
    public Integer call() {
        try {
            String migrationName = action.normalizeMigrationName(target);
            String template = templateGenerator.generate(action, target);
            Path migrationFile = migrationGenerator.generate(migrationName, migrationLocation, template);
            System.out.println("Migration created: " + migrationFile);
            return CommandLine.ExitCode.OK;
        } catch (IllegalArgumentException e) {
            throw new CommandLine.ParameterException(new CommandLine(this), e.getMessage(), e);
        } catch (IOException e) {
            throw new CommandLine.ExecutionException(new CommandLine(this), "Failed to create migration: " + e.getMessage(), e);
        }
    }
}
