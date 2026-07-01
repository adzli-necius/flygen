package com.adzli.flygen.command.migration;

import com.adzli.flygen.service.MigrationService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "create",
        description = "Create a FlyWay migration."
)
public class CreateCommand implements Runnable {

    private final MigrationService migrationService = new MigrationService();

    @CommandLine.Parameters(index = "0")
    private String migrationName;

    @Override
    public void run() {

        migrationService.createMigration(migrationName);
    }
}
