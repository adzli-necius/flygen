package com.adzli.flygen;

import picocli.CommandLine;
import java.util.Arrays;

public class FlygenApplication {

    public static void main(String[] args) {

        int exitCode = new CommandLine(new FlygenCommand()).execute(args);
        System.exit(exitCode);
    }
}