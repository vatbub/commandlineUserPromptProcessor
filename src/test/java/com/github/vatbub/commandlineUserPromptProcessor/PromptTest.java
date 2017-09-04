package com.github.vatbub.commandlineUserPromptProcessor;

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


import com.github.vatbub.commandlineUserPromptProcessor.parsables.Parsable;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParseException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

/**
 * Created by Frederik on 03.09.2017.
 */
public abstract class PromptTest {
    @Rule
    public final TextFromStandardInputStream systemInMock
            = TextFromStandardInputStream.emptyStandardInputStream();
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    protected Parsable promptTestCore(String promptText, String input, Parsable returnValue) throws ParseException {
        systemInMock.provideLines(input);
        Prompt prompt = new Prompt(promptText, returnValue);
        returnValue = prompt.doPrompt();

        StringBuilder expectedPromptText = new StringBuilder(promptText);
        String optionsString = returnValue.getOptionsString();
        String defaultValueString = returnValue.getStringForDefaultValue();

        if (returnValue.getDefaultValue() == null || defaultValueString == null) {
            Assert.assertEquals(returnValue.getDefaultValue(), defaultValueString);
        }

        if (optionsString != null) {
            expectedPromptText.append(" [");
            expectedPromptText.append(optionsString);
            expectedPromptText.append("]");
        }

        if (defaultValueString != null) {
            expectedPromptText.append(" [default: ");
            expectedPromptText.append(defaultValueString);
            expectedPromptText.append("]");
        }

        expectedPromptText.append(": ");

        Assert.assertEquals(expectedPromptText.toString(), systemOutRule.getLog().replace("\n", "").replace("\r", ""));
        systemOutRule.clearLog();

        return returnValue;
    }
}
