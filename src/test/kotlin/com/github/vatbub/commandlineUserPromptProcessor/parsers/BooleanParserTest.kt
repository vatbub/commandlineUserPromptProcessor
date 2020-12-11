package com.github.vatbub.commandlineUserPromptProcessor.parsers

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

/**
 * Created by Frederik on 04.09.2017.
 */
open class BooleanParserTest : ParserTest<BooleanParser, Boolean>() {
    override val expectedOptionsString = "true/false"
    override val illegalInputs = listOf("", "10.0")
    override val legalDefaultValues = mapOf(true to "true", false to "false")
    override val legalInputs = mapOf(
        "true" to true,
        "yes" to true,
        "t" to true,
        "y" to true,
        "false" to false,
        "no" to false,
        "f" to false,
        "n" to false
    )

    override fun newObjectUnderTest(): BooleanParser = BooleanParser()
}