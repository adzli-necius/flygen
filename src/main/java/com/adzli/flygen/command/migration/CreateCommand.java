package com.adzli.flygen.command.migration;

import com.adzli.flygen.generator.MigrationGenerator;
import picocli.CommandLine;

@CommandLine.Command(
        name = "create",
        description = "Create a FlyWay migration."
)
public class CreateCommand implements Runnable {

    private final MigrationGenerator migrationGenerator = new MigrationGenerator();

    @CommandLine.Parameters(index = "0")
    private String migrationName;

    @Override
    public void run() {

        migrationGenerator.generate(migrationName);
    }
}
