package com.github.vatbub.commandlineUserPromptProcessor.parsables;

/*-
 * #%L
 * Vatbub Commandline User Prompt Processor
 * %%
 * Copyright (C) 2017 Frederik Kammel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.jetbrains.annotations.Nullable;

/**
 * Same as {@link ParsableBoolean} but outputs {@code yes/no} instead of {@code true/false} as the options string and uses {@code yes} and {@code no} to describe the default value.
 *
 * @see ParsableBoolean
 */
public class ParsableYesNoBoolean extends ParsableBoolean {
    @Override
    public String getOptionsString() {
        return "yes/no";
    }

    @Override
    public @Nullable String getStringForDefaultValue() {
        if (getDefaultValue() == null) {
            return null;
        } else if (getDefaultValue()) {
            return "yes";
        } else {
            return "no";
        }
    }
}
