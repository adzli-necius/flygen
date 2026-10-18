/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import picocli.CommandLine;

@CommandLine.Command(name = "alter", description = "Create an alter migration.")
public class AlterCommand extends MigrationActionCommand {

    public AlterCommand() {
        super(MigrationAction.ALTER);
    }
}
