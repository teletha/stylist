/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StyleDSLBuilder {

    public static void main(String[] args) throws IOException {
        String source = Files.readString(Path.of("src/main/java/stylist/StyleDSL.java"));
        source = source.replaceAll("public static final", "protected static final");
        source = source.replaceAll("public interface StyleDSL extends", "public abstract class AbstractStyleDSL implements");
        Files.writeString(Path.of("src/main/java/stylist/AbstractStyleDSL.java"), source, StandardCharsets.UTF_8);
    }
}