package com.adzli.flygen.command.migration;

import picocli.CommandLine;

@CommandLine.Command(
        name = "migration",
        description = "Migration commands",
        subcommands = {
                CreateCommand.class
        }
)
public class MigrationCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("Migration command.");
    }
}
