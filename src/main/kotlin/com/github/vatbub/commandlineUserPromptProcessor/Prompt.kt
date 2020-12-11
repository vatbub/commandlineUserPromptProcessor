package com.github.vatbub.commandlineUserPromptProcessor

import com.github.vatbub.commandlineUserPromptProcessor.parsers.Parser
import java.io.InputStream
import java.io.PrintStream
import java.util.*

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
 */ /**
 * Use this class to create prompts for user input with Standard In and Standard Out.<br></br>
 * <br></br>
 * Example:<br></br>
 * <blockquote><pre>
 * boolean defaultValue = true;
 * Prompt prompt = new Prompt("Are you a human?", new ParsableBoolean(defaultValue));
 * ParsableBoolean returnValue = (ParsableBoolean) prompt.doPrompt();
 * boolean result = returnValue.toValue();
</pre></blockquote> *
 */
class Prompt<T>(private val promptText: String, private val parser: Parser<T>) {
    @JvmOverloads
    fun doPrompt(
        defaultValue: T? = null,
        outStream: PrintStream = System.out,
        inStream: InputStream = System.`in`
    ): T {
        val finalPromptText = StringBuilder(promptText)
        parser.optionsString?.let { finalPromptText.append(" [$it]") }
        defaultValue?.let { finalPromptText.append(" [default: ${parser.toString(it)}]") }
        finalPromptText.append(": ")
        outStream.println(finalPromptText.toString())
        val scanner = Scanner(inStream)
        return parser.parse(scanner.nextLine(), defaultValue)
    }
}