/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.generator;

import com.adzli.flygen.command.migration.MigrationAction;

public class MigrationTemplateGenerator {

    public String generate(MigrationAction action, String target) {
        return "-- " + action.templateVerb() + " " + target + "." + System.lineSeparator();
    }
}
