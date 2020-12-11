/*-
 * #%L
 * Vatbub Commandline User Prompt Processor
 * %%
 * Copyright (C) 2017 - 2020 Frederik Kammel
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
package com.github.vatbub.commandlineUserPromptProcessor.parsers

import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class DoubleParser(
    private val minValue: Double = NEGATIVE_INFINITY,
    private val maxValue: Double = POSITIVE_INFINITY
) : Parser<Double> {
    init {
        require(maxValue >= minValue) { "maxValue must be bigger than or equal to minValue" }
    }

    override fun parse(input: String, defaultValue: Double?): Double {
        val value = input.toDoubleOrNull() ?: defaultValue ?: throw ParseException()
        if (value !in minValue..maxValue)
            throw ParseException("Double was out of range: $input, minValue: $minValue, maxValue: $maxValue")
        return value
    }

    override val optionsString: String? by lazy {
        if (minValue == NEGATIVE_INFINITY && maxValue == POSITIVE_INFINITY)
            return@lazy null
        val result = StringBuilder()
        if (minValue != NEGATIVE_INFINITY)
            result.append("$minValue <= ")
        result.append("x")
        if (maxValue != POSITIVE_INFINITY)
            result.append(" <= $maxValue")
        return@lazy result.toString()
    }
}
