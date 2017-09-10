package com.github.vatbub.commandlineUserPromptProcessor.parsables;

import org.jetbrains.annotations.Nullable;

/**
 * Same as {@link ParsableBoolean} but outputs {@code yes/no} instead of {@code true/false} as the options string and uses {@code yes} and {@code no} to describe the default value.
 *
 * @see ParsableBoolean
 */
public class ParsableYesNoBoolean extends ParsableBoolean {
    @Override
    public String getOptionsString() {
        return "yes/no";
    }

    @Override
    public @Nullable String getStringForDefaultValue() {
        if (getDefaultValue() == null) {
            return null;
        } else if (getDefaultValue()) {
            return "yes";
        } else {
            return "no";
        }
    }
}
