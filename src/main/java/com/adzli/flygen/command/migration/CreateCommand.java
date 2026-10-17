/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import com.adzli.flygen.generator.MigrationGenerator;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "create",
        description = "Create a FlyWay migration."
)
public class CreateCommand implements Callable<Integer> {

    private static final String DEFAULT_MIGRATION_LOCATION = "src/main/resources/db/migration";

    private final MigrationGenerator migrationGenerator;

    CreateCommand(MigrationGenerator migrationGenerator) {
        this.migrationGenerator = migrationGenerator;
    }

    public CreateCommand() {
        this(new MigrationGenerator());
    }

    @CommandLine.Parameters(index = "0")
    private String migrationName;

    @CommandLine.Option(
            names = {"-l", "--location"},
            defaultValue = DEFAULT_MIGRATION_LOCATION,
            paramLabel = "PATH",
            description = "Project-relative Flyway migration directory (default: ${DEFAULT-VALUE})."
    )
    private String migrationLocation;

    @Override
    public Integer call() {
        try {
            Path migrationFile = migrationGenerator.generate(migrationName, migrationLocation);
            System.out.println("Migration created: " + migrationFile);
            return CommandLine.ExitCode.OK;
        } catch (IllegalArgumentException e) {
            throw new CommandLine.ParameterException(new CommandLine(this), e.getMessage(), e);
        } catch (IOException e) {
            throw new CommandLine.ExecutionException(new CommandLine(this), "Failed to create migration: " + e.getMessage(), e);
        }
    }
}
