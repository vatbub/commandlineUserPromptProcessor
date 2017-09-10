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


import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableBoolean;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParsableYesNoBoolean;
import com.github.vatbub.commandlineUserPromptProcessor.parsables.ParseException;
import org.hamcrest.core.StringContains;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Frederik on 04.09.2017.
 */
public class BooleanTest extends PromptTest {
    @Test
    public void booleanInputTest() throws IOException, InterruptedException, ParseException {
        String promptText = "Sample boolean prompt";

        ParsableBoolean returnValue = (ParsableBoolean) promptTestCore(promptText, "true", new ParsableBoolean());
        Assert.assertEquals(true, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "yes", new ParsableBoolean());
        Assert.assertEquals(true, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "t", new ParsableBoolean());
        Assert.assertEquals(true, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "y", new ParsableBoolean());
        Assert.assertEquals(true, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "false", new ParsableBoolean());
        Assert.assertEquals(false, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "no", new ParsableBoolean());
        Assert.assertEquals(false, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "f", new ParsableBoolean());
        Assert.assertEquals(false, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "n", new ParsableBoolean());
        Assert.assertEquals(false, returnValue.toValue());
    }

    @Test
    public void booleanWithDefaultValueTest() throws ParseException {
        String promptText = "Sample boolean prompt with default value";

        ParsableBoolean returnValue = (ParsableBoolean) promptTestCore(promptText, "", new ParsableBoolean(true));
        Assert.assertEquals(true, returnValue.toValue());

        returnValue = (ParsableBoolean) promptTestCore(promptText, "", new ParsableBoolean(false));
        Assert.assertEquals(false, returnValue.toValue());
    }

    @Test
    public void illegalInputTest() {
        String promptText = "Sample boolean prompt";

        try {
            promptTestCore(promptText, "", new ParsableBoolean());
            Assert.fail("ParseException expected");
        } catch (ParseException e) {
            System.out.println("Test passed");
        }
    }

    @Test
    public void yesNoBooleanTest() {
        ParsableYesNoBoolean returnValue = new ParsableYesNoBoolean();
        Assert.assertThat(returnValue.getOptionsString(), new StringContains("yes"));
        Assert.assertThat(returnValue.getOptionsString(), new StringContains("no"));

        Assert.assertEquals(null, returnValue.getStringForDefaultValue());
        returnValue.setDefaultValue(true);
        Assert.assertThat(returnValue.getStringForDefaultValue(), new IsEqualIgnoringCase("yes"));
        returnValue.setDefaultValue(false);
        Assert.assertThat(returnValue.getStringForDefaultValue(), new IsEqualIgnoringCase("no"));
    }
}
