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
 * Implements the {@link Parsable} interface for integer values
 */
public class ParsableInteger implements Parsable<Integer> {
    private int minValue;
    private int maxValue;
    private int value;
    private @Nullable Integer defaultValue;

    /**
     * Instantiates a new {@link ParsableInteger} with no {@code defaultValue}
     */
    public ParsableInteger() {
        this(null);
    }

    /**
     * Instantiates a new {@link ParsableInteger} with the given {@code defaultValue}
     *
     * @param defaultValue The default value to use if the user makes an invalid input
     */
    public ParsableInteger(Integer defaultValue) {
        this(defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Instantiates a new {@link ParsableInteger} with the given {@code defaultValue}, min- and max values.
     * {@code minValue} must be lower than or equal to {@code maxValue}
     *
     * @param defaultValue The default value to use if the user makes an invalid input
     * @param minValue     The minimal allowed value
     * @param maxValue     The maximal allowed value
     */
    public ParsableInteger(Integer defaultValue, int minValue, int maxValue) {
        setDefaultValue(defaultValue);
        setAcceptedValues(minValue, maxValue);
    }

    /**
     * Sets the set of allowed inputs. {@code minValue} must be lower than or equal to {@code maxValue}
     * @param minValue The minimal allowed value
     * @param maxValue The maximal allowed value
     */
    public void setAcceptedValues(int minValue, int maxValue) {
        if (maxValue < minValue) {
            throw new IllegalArgumentException("maxValue must be bigger than or equal to minValue");
        }

        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    /**
     * Returns the current maximal allowed value.
     * @return the current maximal allowed value.
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Returns the current minimal allowed value.
     * @return the current minimal allowed value.
     */
    public int getMinValue() {
        return minValue;
    }

    @Override
    public void fromString(String s) throws ParseException {
        try {
            value = Integer.parseInt(s);
            if (value < minValue || value > maxValue) {
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) {
            if (defaultValue != null) {
                value = defaultValue;
            } else {
                throw new ParseException();
            }
        }
    }

    @Nullable
    @Override
    public String getOptionsString() {
        if (minValue == Integer.MIN_VALUE && maxValue == Integer.MAX_VALUE) {
            return null;
        } else if (minValue != Integer.MIN_VALUE && maxValue != Integer.MAX_VALUE) {
            return minValue + " <= x <= " + maxValue;
        } else if (minValue != Integer.MIN_VALUE) {
            return minValue + " <= x";
        } else {
            return "x <= " + maxValue;
        }
    }

    @Nullable
    @Override
    public String getStringForDefaultValue() {
        if (defaultValue == null) {
            return null;
        } else {
            return defaultValue.toString();
        }
    }

    @Nullable
    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(@Nullable Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer toValue() {
        return value;
    }
}
