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

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Frederik on 03.09.2017.
 */
@SuppressWarnings("WeakerAccess")
public class Prompt {
    private String promptText;
    private Parsable returnValue;

    public Prompt(String promptText, Parsable returnValue) {
        setPromptText(promptText);
        setReturnValue(returnValue);
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public Parsable getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Parsable returnValue) {
        this.returnValue = returnValue;
    }

    public Parsable doPrompt() throws ParseException {
        return doPrompt(System.out, System.in);
    }

    public Parsable doPrompt(PrintStream out, InputStream in) throws ParseException {
        StringBuilder finalPromptText = new StringBuilder(getPromptText());
        String optionsString = getReturnValue().getOptionsString();
        String defaultValue = getReturnValue().getStringForDefaultValue();

        if (optionsString != null) {
            finalPromptText.append(" [");
            finalPromptText.append(optionsString);
            finalPromptText.append("]");
        }

        if (defaultValue != null) {
            finalPromptText.append(" [default: ");
            finalPromptText.append(defaultValue);
            finalPromptText.append("]");
        }

        finalPromptText.append(": ");
        out.println(finalPromptText.toString());
        Scanner scanner = new Scanner(in);
        getReturnValue().fromString(scanner.nextLine());

        return getReturnValue();
    }
}
