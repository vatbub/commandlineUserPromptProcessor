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
 * Created by Frederik on 03.09.2017.
 */
public class ParsableBoolean implements Parsable<Boolean> {
    private boolean value;
    private Boolean defaultValue = null;

    public ParsableBoolean() {
        this(null);
    }

    public ParsableBoolean(Boolean defaultValue) {
        setDefaultValue(defaultValue);
    }

    @Override
    public void fromString(String s) throws ParseException {
        switch (s) {
            case "true":
            case "t":
            case "y":
            case "yes":
                value = true;
                break;

            case "false":
            case "f":
            case "n":
            case "no":
                value = false;
                break;

            default:
                if (defaultValue != null) {
                    value = defaultValue;
                } else {
                    throw new ParseException();
                }
        }
    }

    @Override
    public String getOptionsString() {
        return "true/false";
    }

    @Nullable
    @Override
    public String getStringForDefaultValue() {
        if (getDefaultValue() == null) {
            return null;
        } else {
            return getDefaultValue().toString();
        }
    }

    @Override
    public Boolean getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(@Nullable Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean toValue() {
        return value;
    }
}
