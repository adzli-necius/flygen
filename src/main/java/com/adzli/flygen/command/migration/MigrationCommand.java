/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import picocli.CommandLine;

@CommandLine.Command(
        name = "migration",
        description = "Migration commands",
        subcommands = {
                CreateCommand.class,
                AlterCommand.class,
                DropCommand.class,
                DataCommand.class
        }
)
public class MigrationCommand implements Runnable{

    @Override
    public void run() {
        System.out.println("Migration command.");
    }
}
