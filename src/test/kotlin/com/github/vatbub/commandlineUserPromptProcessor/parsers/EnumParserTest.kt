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

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test


class EnumParserTest : ParserTest<EnumParser<DummyEnum>, DummyEnum>() {
    override val legalDefaultValues = DummyEnum.values().associateWith { it.toString() }
    override val legalInputs = DummyEnum.values().associateBy { it.toString() }
    override val illegalInputs = listOf("", "10.0")
    override val expectedOptionsString = "EnumValue1/EnumValue2"

    override fun newObjectUnderTest() = EnumParser<DummyEnum>(DummyEnum::class.java)


    @Test
    fun illegalClassTest() {
        assertThrows(IllegalArgumentException::class.java) {
            EnumParser(String::class.java)
        }
    }
}

enum class DummyEnum {
    EnumValue1, EnumValue2
}
