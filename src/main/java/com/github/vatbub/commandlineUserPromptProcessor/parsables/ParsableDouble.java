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
 * Created by Frederik on 04.09.2017.
 */
public class ParsableDouble implements Parsable<Double> {
    private double minValue;
    private double maxValue;
    private double value;
    private @Nullable Double defaultValue;

    public ParsableDouble() {
        this(null);
    }

    public ParsableDouble(Double defaultValue) {
        this(defaultValue, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public ParsableDouble(Double defaultValue, double minValue, double maxValue) {
        setDefaultValue(defaultValue);
        setAcceptedValues(minValue, maxValue);
    }

    public void setAcceptedValues(double minValue, double maxValue) {
        if (maxValue < minValue) {
            throw new IllegalArgumentException("maxValue must be bigger than or equal to minValue");
        }

        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    @Override
    public void fromString(String s) throws ParseException {
        try {
            value = Double.parseDouble(s);
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
        if (minValue == Double.NEGATIVE_INFINITY && maxValue == Double.POSITIVE_INFINITY) {
            return null;
        } else if (minValue != Double.NEGATIVE_INFINITY && maxValue != Double.POSITIVE_INFINITY) {
            return minValue + " <= x <= " + maxValue;
        } else if (minValue != Double.NEGATIVE_INFINITY) {
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
    public Double getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(@Nullable Double defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Double toValue() {
        return value;
    }
}
