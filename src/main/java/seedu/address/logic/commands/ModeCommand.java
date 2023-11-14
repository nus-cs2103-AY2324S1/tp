package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles between light and dark mode for the application.
 */
public class ModeCommand extends Command {

    public static final String COMMAND_WORD = "mode";

    public static final String MESSAGE_TOGGLED = "Toggled dark/light mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles dark/light mode for the application.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_TOGGLED, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof ModeCommand;
    }
}
