/* SPDX-License-Identifier: Apache-2.0
 * Copyright 2026 adzli-necius
 */
package com.adzli.flygen.generator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlyWayVersionGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public String generate() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
