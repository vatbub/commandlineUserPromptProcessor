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

public class ParsableEnum<E extends Enum<E>> implements Parsable<E> {
    private Class<E> eClass;
    private E value;
    private E defaultValue;

    /**
     * Instantiates a new {@link ParsableBoolean} with no {@code defaultValue}
     *
     * @param eClass The class of the enum
     */
    public ParsableEnum(Class<E> eClass) {
        this(eClass, null);
    }

    /**
     * Instantiates a new {@link ParsableBoolean} with the given {@code defaultValue}
     *
     * @param eClass       The class of the enum
     * @param defaultValue The default value to use if the user makes an invalid input
     */
    public ParsableEnum(Class<E> eClass, E defaultValue) {
        seteClass(eClass);
        setDefaultValue(defaultValue);
    }

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
    @Override
    public void fromString(String s) throws ParseException {
        try {
            value = E.valueOf(geteClass(), s);
        } catch (IllegalArgumentException e) {
            if (getDefaultValue() != null) {
                value = getDefaultValue();
            } else {
                throw new ParseException(e.getMessage(), e);
            }
        }
    }

    /**
     * This method should return a {@code String} that tells the user all valid inputs he can make.
     * Example: {@code "true/false"} for a boolean value. If this method returns {@code null}, it is assumed that any
     * input is valid and no information about valid inputs is displayed to the user.
     *
     * @return a {@code String} that tells the user all valid inputs he can make.
     */
    @Nullable
    @Override
    public String getOptionsString() {
        E[] enumConstants = geteClass().getEnumConstants();
        String[] enumStrings = new String[enumConstants.length];
        for (int i = 0; i < enumConstants.length; i++) {
            enumStrings[i] = enumConstants[i].toString();
        }
        return String.join("/", enumStrings);
    }

    /**
     * Returns the string representation if the default value or {@code null} if none is set.
     *
     * @return the string representation if the default value or {@code null} if none is set.
     * @see #setDefaultValue(Object)
     * @see #getDefaultValue()
     */
    @Nullable
    @Override
    public String getStringForDefaultValue() {
        if (getDefaultValue() == null) {
            return null;
        } else {
            return getDefaultValue().toString();
        }
    }

    /**
     * Returns the currently defined default value
     *
     * @return the currently defined default value
     */
    @Override
    public E getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value to be used if the user input cannot be parsed
     *
     * @param defaultValue The default value to set
     */
    @Override
    public void setDefaultValue(@Nullable E defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the parsed result of {@link #fromString(String)}
     *
     * @return Returns the parsed result of {@link #fromString(String)}
     */
    @Override
    public E toValue() {
        return value;
    }

    public Class<E> geteClass() {
        return eClass;
    }

    public void seteClass(Class<E> eClass) {
        this.eClass = eClass;
    }
}
