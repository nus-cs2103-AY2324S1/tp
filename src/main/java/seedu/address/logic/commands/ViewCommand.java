package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View which data you want to see\n"
            + "Parameters: data category (must be 'students' or 'appointments') "
            + "g/ [DATA_CATEGORY]\n"
            + "Example: " + COMMAND_WORD + " g/appointments ";

    public static final String MESSAGE_ARGUMENTS = "Data chosen: %1$s";

    private final String category;

    public ViewCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, category));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand e = (ViewCommand) other;
        return category.equals(e.category);
    }
}
