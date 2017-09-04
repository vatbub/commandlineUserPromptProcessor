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


import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableString;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Frederik on 04.09.2017.
 */
public class StringTest extends PromptTest {
    @Test
    public void stringTestWithDefaultValue() throws ParseException {
        String promptText = "Sample string prompt with default value";

        ParsableString returnValue = (ParsableString) promptTestCore(promptText, "", new ParsableString("default Value"));
        Assert.assertEquals("default Value", returnValue.toValue());
    }

    @Test
    public void stringInputTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample string prompt";

        ParsableString returnValue = (ParsableString) promptTestCore(promptText, "true", new ParsableString());
        Assert.assertEquals("true", returnValue.toValue());
    }
}
