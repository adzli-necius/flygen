/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import picocli.CommandLine;

@CommandLine.Command(name = "drop", description = "Create a drop migration.")
public class DropCommand extends MigrationActionCommand {

    public DropCommand() {
        super(MigrationAction.DROP);
    }
}
