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

open class BooleanParser : Parser<Boolean> {
    override fun parse(input: String, defaultValue: Boolean?): Boolean = when (input) {
        "true", "t", "y", "yes" -> true
        "false", "f", "n", "no" -> false
        else -> defaultValue ?: throw ParseException()
    }

    override val optionsString = "true/false"

}
