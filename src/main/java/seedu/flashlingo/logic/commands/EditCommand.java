package seedu.flashlingo.logic.commands;

import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = " ";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
