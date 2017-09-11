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
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableEnum;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class EnumTest extends PromptTest {
    @Test
    public void enumInputTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample enum prompt";

        Parsable returnValue = promptTestCore(promptText, "enumValue1", new ParsableEnum<>(TestEnum.class));
        Assert.assertEquals(TestEnum.enumValue1, returnValue.toValue());

        returnValue = promptTestCore(promptText, "enumValue2", new ParsableEnum<>(TestEnum.class));
        Assert.assertEquals(TestEnum.enumValue2, returnValue.toValue());
    }

    @Test
    public void enumWithDefaultValueTest() throws ParseException {
        String promptText = "Sample enum prompt with default value";

        Parsable returnValue = promptTestCore(promptText, "", new ParsableEnum<>(TestEnum.class, TestEnum.enumValue1));
        Assert.assertEquals(TestEnum.enumValue1, returnValue.toValue());
    }

    @Test
    public void illegalInputTest() {
        String promptText = "Sample enum prompt";

        try {
            promptTestCore(promptText, "", new ParsableEnum<>(TestEnum.class));
            Assert.fail("ParseException expected");
        } catch (ParseException e) {
            System.out.println("Test passed");
        }
    }

    private enum TestEnum {
        enumValue1, enumValue2
    }
}
