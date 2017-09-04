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


import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableDouble;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableInteger;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Frederik on 04.09.2017.
 */
public class DoubleTest extends PromptTest {
    @Test
    public void doubleTestWithDefaultValue() throws ParseException {
        String promptText = "Sample double prompt with default value";

        ParsableDouble returnValue = (ParsableDouble) promptTestCore(promptText, "", new ParsableDouble(10.0));
        Assert.assertEquals(10, returnValue.toValue(), 0);
    }

    @Test
    public void doubleInputTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample double prompt";

        ParsableDouble returnValue = (ParsableDouble) promptTestCore(promptText, "10", new ParsableDouble());
        Assert.assertEquals(10.0, returnValue.toValue(), 0);
    }

    @Test
    public void doubleInputOutsideOfAllowedRangeTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample double prompt";

        try {
            //noinspection unused
            ParsableDouble returnValue = (ParsableDouble) promptTestCore(promptText, "100", new ParsableDouble(null, -10, 10));
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Test passed");
        }
    }

    @Test
    public void doubleInputInsideOfAllowedRangeTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample double prompt";

        ParsableDouble returnValue = (ParsableDouble) promptTestCore(promptText, "5", new ParsableDouble(null, -10, 10));
        Assert.assertEquals(5.0,  returnValue.toValue(), 0);
    }
}
