package com.github.vatbub.commandlineUserPromptProcessor
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
import com.github.vatbub.commandlineUserPromptProcessor.parsers.Parser
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by Frederik on 03.09.2017.
 */
class PromptTest {

    private fun <T> promptTest(
        promptText: String,
        expectedInput: String,
        expectedDefaultValue: T?,
        mockParsingResult: T,
        expectedOptionsString: String?
    ) {
        var parserToStringCalled = false

        val parser = object : Parser<T> {
            override fun parse(input: String, defaultValue: T?): T {
                assertEquals(expectedInput, input)
                assertEquals(expectedDefaultValue, defaultValue)
                return mockParsingResult
            }

            override fun toString(obj: T): String {
                parserToStringCalled = true
                assertEquals(expectedDefaultValue, obj)
                return super.toString(obj)
            }

            override val optionsString = expectedOptionsString

        }

        val inputStream = ByteArrayInputStream("$expectedInput\n".toByteArray(Charset.forName("UTF-8")))
        val byteArrayOutputStream = ByteArrayOutputStream()
        val printStream = PrintStream(byteArrayOutputStream)

        val prompt = Prompt(promptText, parser)
        assertEquals(
            mockParsingResult,
            prompt.doPrompt(defaultValue = expectedDefaultValue, outStream = printStream, inStream = inputStream)
        )
        if (expectedDefaultValue != null)
            await().atMost(5, SECONDS).until { parserToStringCalled }

        val expectedPromptText = StringBuilder(promptText)
        expectedOptionsString?.let { expectedPromptText.append(" [$it]") }
        expectedDefaultValue?.let { expectedPromptText.append(" [default: $it]") }
        expectedPromptText.append(": ")

        val printedLog = byteArrayOutputStream.toByteArray().toString(Charset.forName("UTF-8"))
        assertEquals(expectedPromptText.toString(), printedLog.replace("\n", "").replace("\r", ""))
    }

    @Test
    fun promptWithDefaultValueTest() = promptTest(
        promptText = "I am a prompt",
        expectedInput = "dummyInput",
        expectedDefaultValue = "I am a default value",
        mockParsingResult = "Parse result",
        expectedOptionsString = "Dummy options"
    )

    @Test
    fun promptNoDefaultValueTest() = promptTest(
        promptText = "I am another prompt",
        expectedInput = "hello world",
        expectedDefaultValue = null,
        mockParsingResult = "Another parse result",
        expectedOptionsString = "And more options"
    )
}