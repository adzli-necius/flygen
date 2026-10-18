/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

public enum MigrationAction {
    CREATE("create", "Creates"),
    ALTER("alter", "Alters"),
    DROP("drop", "Drops"),
    DATA("data", "Adds or updates data for");

    private final String commandName;
    private final String templateVerb;

    MigrationAction(String commandName, String templateVerb) {
        this.commandName = commandName;
        this.templateVerb = templateVerb;
    }

    public String commandName() {
        return commandName;
    }

    public String templateVerb() {
        return templateVerb;
    }

    public String normalizeMigrationName(String target) {
        String prefix = commandName + "_";
        return target.startsWith(prefix) ? target : prefix + target;
    }
}
