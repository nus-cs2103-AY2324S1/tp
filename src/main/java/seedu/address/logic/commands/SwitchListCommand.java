package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches the bottom list between the task list and the event list.
 */
public class SwitchListCommand extends Command {

    public static final String COMMAND_WORD = "switchList";

    public static final String MESSAGE_SWITCH_ACKNOWLEDGEMENT = "Switching list.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SWITCH_ACKNOWLEDGEMENT, false, false, false, true);
    }
}
