/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import picocli.CommandLine;

@CommandLine.Command(name = "data", description = "Create a data migration.")
public class DataCommand extends MigrationActionCommand {

    public DataCommand() {
        super(MigrationAction.DATA);
    }
}
