/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import picocli.CommandLine;

@CommandLine.Command(
        name = "create",
        description = "Create a FlyWay migration."
)
public class CreateCommand extends MigrationActionCommand {

    public CreateCommand() {
        super(MigrationAction.CREATE);
    }
}
