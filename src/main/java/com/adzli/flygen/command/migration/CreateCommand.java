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

    private final MigrationGenerator migrationGenerator;

    CreateCommand(MigrationGenerator migrationGenerator) {
        this.migrationGenerator = migrationGenerator;
    }

    public CreateCommand() {
        this(new MigrationGenerator());
    }

    @CommandLine.Parameters(index = "0")
    private String migrationName;

    @Override
    public Integer call() {
        try {
            Path migrationFile = migrationGenerator.generate(migrationName);
            System.out.println("Migration created: " + migrationFile);
            return CommandLine.ExitCode.OK;
        } catch (IllegalArgumentException e) {
            throw new CommandLine.ParameterException(new CommandLine(this), e.getMessage(), e);
        } catch (IOException e) {
            throw new CommandLine.ExecutionException(new CommandLine(this), "Failed to create migration: " + e.getMessage(), e);
        }
    }
}
