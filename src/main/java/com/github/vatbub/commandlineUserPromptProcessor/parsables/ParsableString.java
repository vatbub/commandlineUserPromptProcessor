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
 * Implements the {@link Parsable} interface for String values
 */
public class ParsableString implements Parsable<String> {
    private String value;
    private String defaultValue;

    /**
     * Instantiates a new {@link ParsableString} with no {@code defaultValue}
     */
    public ParsableString() {
        this(null);
    }

    /**
     * Instantiates a new {@link ParsableString} with the given {@code defaultValue}
     *
     * @param defaultValue The default value to use if the user makes an invalid input
     */
    public ParsableString(String defaultValue) {
        setDefaultValue(defaultValue);
    }

    @Override
    public void fromString(String s) throws ParseException {
        if (s.equals("")) {
            if (defaultValue==null){
                throw new ParseException();
            }
            value = defaultValue;
        } else {
            value = s;
        }
    }

    @Override
    public String getOptionsString() {
        return null;
    }

    @Nullable
    @Override
    public String getStringForDefaultValue() {
        return getDefaultValue();
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(@Nullable String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toValue() {
        return value;
    }
}
