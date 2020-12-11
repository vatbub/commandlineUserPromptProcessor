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


abstract class ParserTest<TParser : Parser<TValue>, TValue> {
    abstract val legalDefaultValues: Map<TValue, String>
    abstract val legalInputs: Map<String, TValue>
    abstract val illegalInputs: List<String>
    abstract val expectedOptionsString: String?

    abstract fun newObjectUnderTest(): TParser

    @Test
    fun testLegalValues() {
        legalInputs.forEach { (input, expectedParseResult) ->
            assertEquals(expectedParseResult, newObjectUnderTest().parse(input, null))
        }
    }

    @Test
    fun defaultValuesTest() {
        legalDefaultValues.forEach { (legalDefaultValue, stringRepresentation) ->
            assertEquals(stringRepresentation, newObjectUnderTest().toString(legalDefaultValue))
            illegalInputs.forEach { illegalInput ->
                assertEquals(legalDefaultValue, newObjectUnderTest().parse(illegalInput, legalDefaultValue))
            }
        }
    }

    @Test
    fun illegalInputWithoutDefaultValueTest() {
        illegalInputs.forEach { illegalInput ->
            assertThrows(ParseException::class.java) { newObjectUnderTest().parse(illegalInput, null) }
        }
    }

    @Test
    fun optionsValueTest() {
        assertEquals(expectedOptionsString, newObjectUnderTest().optionsString)
    }
}
