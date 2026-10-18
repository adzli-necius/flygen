/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.command.migration;

import com.adzli.flygen.FlygenCommand;
import com.adzli.flygen.generator.MigrationGenerator;
import com.adzli.flygen.generator.MigrationTemplateGenerator;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCommandTest {

    @Test
    void returnsUsageExitCodeForInvalidMigrationName() {
        int exitCode = new CommandLine(new FlygenCommand())
                .execute("migration", "create", "../outside");

        assertEquals(CommandLine.ExitCode.USAGE, exitCode);
    }

    @Test
    void returnsSoftwareExitCodeWhenMigrationCreationFails() {
        MigrationActionCommand command = new MigrationActionCommand(
                MigrationAction.CREATE,
                new MigrationGenerator() {
            @Override
            public Path generate(String migrationName, String migrationLocation, String contents) throws IOException {
                throw new IOException("disk is unavailable");
            }
        },
                new MigrationTemplateGenerator()
        ) {
        };

        CommandLine commandLine = new CommandLine(command);
        commandLine.setExecutionExceptionHandler((exception, parsedCommand, parseResult) -> CommandLine.ExitCode.SOFTWARE);

        int exitCode = commandLine.execute("create_users_table");

        assertEquals(CommandLine.ExitCode.SOFTWARE, exitCode);
    }
}
