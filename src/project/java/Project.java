/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
public class Project extends bee.api.Project {

    {
        product("com.github.teletha", "stylist", "0.2");

        require("com.github.teletha", "sinobu", "LATEST");
        require("com.github.teletha", "antibug", "LATEST").atTest();

        versionControlSystem("https://github.com/teletha/stylist");
    }
}
