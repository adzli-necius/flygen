package com.adzli.flygen;

import com.adzli.flygen.command.migration.MigrationCommand;
import picocli.CommandLine;

@CommandLine.Command(
        name = "flygen",
        mixinStandardHelpOptions = true,
        version = "Flygen v1.0.0",
        description = "Developer toolkit for FlyWay migrations",
        subcommands = {
                MigrationCommand.class
        }
)
public class FlygenCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Use --help to see available commands.");
    }
}
