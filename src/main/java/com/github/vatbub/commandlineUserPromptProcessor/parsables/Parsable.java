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


import com.github.vatbub.commandlineUserPromptProcessor.Prompt;
import org.jetbrains.annotations.Nullable;

/**
 * Implementing this interface enables a type to be requested from a user using the {@link Prompt} class.
 */
public interface Parsable<T> {
    /**
     * This method is called after the user has entered his input and pressed the enter key.
     * This method should parse the user input and store it in a private variable which will be returned by the{@link #toValue()}-method.
     *
     * @param s The text entered by the user.
     * @throws ParseException Should be thrown if the user input cannot be parsed.
     *                        If a default value has been defined using {@link #setDefaultValue(Object)},
     *                        no exception shall be thrown and the default value shall be used instead.
     * @see #setDefaultValue(Object)
     */
    void fromString(String s) throws ParseException;

    /**
     * This method should return a {@code String} that tells the user all valid inputs he can make.
     * Example: {@code "true/false"} for a boolean value. If this method returns {@code null}, it is assumed that any
     * input is valid and no information about valid inputs is displayed to the user.
     *
     * @return a {@code String} that tells the user all valid inputs he can make.
     */
    @Nullable String getOptionsString();

    /**
     * Returns the string representation if the default value or {@code null} if none is set.
     *
     * @return the string representation if the default value or {@code null} if none is set.
     * @see #setDefaultValue(Object)
     * @see #getDefaultValue()
     */
    @Nullable String getStringForDefaultValue();

    /**
     * Returns the currently defined default value
     * @return the currently defined default value
     */
    T getDefaultValue();

    /**
     * Sets the default value to be used if the user input cannot be parsed
     * @param defaultValue The default value to set
     */
    @SuppressWarnings("unused")
    void setDefaultValue(@Nullable T defaultValue);

    /**
     * Returns the parsed result of {@link #fromString(String)}
     * @return Returns the parsed result of {@link #fromString(String)}
     */
    T toValue();
}
