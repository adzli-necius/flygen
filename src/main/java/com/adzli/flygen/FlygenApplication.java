/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen;

import picocli.CommandLine;

public class FlygenApplication {

    public static void main(String[] args) {

        CommandLine commandLine = new CommandLine(new FlygenCommand());
        commandLine.setExecutionExceptionHandler((exception, command, parseResult) -> {
            command.getErr().println(exception.getMessage());
            return command.getCommandSpec().exitCodeOnExecutionException();
        });

        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
