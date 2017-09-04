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


import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableInteger;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Frederik on 04.09.2017.
 */
public class IntegerTest extends PromptTest {
    @Test
    public void integerTestWithDefaultValue() throws ParseException {
        String promptText = "Sample integer prompt with default value";

        ParsableInteger returnValue = (ParsableInteger) promptTestCore(promptText, "", new ParsableInteger(10));
        Assert.assertEquals(10, (int) returnValue.toValue());
    }

    @Test
    public void integerInputTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample integer prompt";

        ParsableInteger returnValue = (ParsableInteger) promptTestCore(promptText, "10", new ParsableInteger());
        Assert.assertEquals(10, (int) returnValue.toValue());
    }

    @Test
    public void integerInputOutsideOfAllowedRangeTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample integer prompt";

        try {
            //noinspection unused
            ParsableInteger returnValue = (ParsableInteger) promptTestCore(promptText, "100", new ParsableInteger(null, -10, 10));
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Test passed");
        }
    }

    @Test
    public void integerInputInsideOfAllowedRangeTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample integer prompt";

        ParsableInteger returnValue = (ParsableInteger) promptTestCore(promptText, "5", new ParsableInteger(null, -10, 10));
        Assert.assertEquals(5, (int) returnValue.toValue());
    }
}
