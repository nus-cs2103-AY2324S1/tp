package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    // TODO: Implement usage message
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public final String operation;
    public final String type;
    public final String value;

    public FilterCommand(String operation, String type, String value) {
        this.operation = operation;
        this.type = type;
        this.value = value;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format("%s, %s, %s", operation, type, value));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand e = (FilterCommand) other;
        return operation.equals(e.operation)
                && type.equals(e.type)
                && value.equals(e.value);
    }
}
