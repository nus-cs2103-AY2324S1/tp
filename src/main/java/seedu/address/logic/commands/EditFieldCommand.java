package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents and EditFieldCommand in the profile view page which is executed
 * when the name of the field is entered into the command box.
 */
public class EditFieldCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Editing fosterer field";

    public EditFieldCommand() {
        super();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS, CommandType.EDIT_FIELD);
    }
}
