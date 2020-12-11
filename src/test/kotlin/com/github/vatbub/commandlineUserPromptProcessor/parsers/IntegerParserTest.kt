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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test


class IntegerParserTest : ParserTest<IntegerParser, Int>() {
    override val legalDefaultValues = mapOf(
        10 to "10"
    )
    override val illegalInputs = listOf("not a number", "", "10.4", "10.0")
    override val expectedOptionsString: String? = null
    override val legalInputs = mapOf(
        "10" to 10,
    )

    override fun newObjectUnderTest() = IntegerParser()

    @Test
    fun integerInputOutsideOfAllowedRangeTest() {
        val minValue = -10
        val maxValue = 10
        val parser = IntegerParser(minValue, maxValue)
        assertThrows(ParseException::class.java) {
            parser.parse("100", null)
        }
    }

    @Test
    fun integerInputInsideOfAllowedRangeTest() {
        val minValue = -10
        val maxValue = 10
        val parser = IntegerParser(minValue, maxValue)
        assertEquals(5, parser.parse("5", null))

    }

    @Test
    fun bothBoundsSetOptionsStringTest(){
        val minValue = -10
        val maxValue = 10
        val parser = IntegerParser(minValue, maxValue)
        assertEquals("$minValue <= x <= $maxValue", parser.optionsString)
    }

    @Test
    fun lowerBoundSetOptionsStringTest(){
        val minValue = -10
        val maxValue = Int.MAX_VALUE
        val parser = IntegerParser(minValue, maxValue)
        assertEquals("$minValue <= x", parser.optionsString)
    }

    @Test
    fun higherBoundSetOptionsStringTest(){
        val minValue = Int.MIN_VALUE
        val maxValue = 10
        val parser = IntegerParser(minValue, maxValue)
        assertEquals("x <= $maxValue", parser.optionsString)
    }

    @Test
    fun illegalMinMaxIntervalTest() {
        assertThrows(IllegalArgumentException::class.java) {
            IntegerParser(10, -10)
        }
    }
}
