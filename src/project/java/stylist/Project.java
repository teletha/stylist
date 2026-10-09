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

import javax.lang.model.SourceVersion;

public class Project extends bee.api.Project {

    {
        product("io.github.teletha", "stylist", ref("version.txt"));
        require(SourceVersion.latest(), SourceVersion.RELEASE_21);

        require("io.github.teletha", "sinobu");
        require("io.github.teletha", "antibug").atTest();

        versionControlSystem("https://github.com/teletha/stylist");
    }
}