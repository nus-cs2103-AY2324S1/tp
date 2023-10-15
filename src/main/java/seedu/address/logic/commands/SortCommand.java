package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of employees from the last listing by the specified attribute. "
            + "Parameters: by/ [ATTRIBUTE]\n"
            + "Example: " + COMMAND_WORD + " by/ salary";
//            + "by the index number used in the last person listing. "
//            + "Existing remark will be overwritten by the input.\n"
//            + "Parameters: INDEX (must be a positive integer) "
//            + "r/ [REMARK]\n"
//            + "Example: " + COMMAND_WORD + " 1 "
//            + "r/ Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Attribute: %1$s";

    private final String attribute;

    public SortCommand(String attribute) {
        requireAllNonNull(attribute);

        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, attribute));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand e = (SortCommand) other;
        return attribute.equals(e.attribute);
    }
}
