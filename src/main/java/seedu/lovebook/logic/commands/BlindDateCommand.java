package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.Model;
/**
 * Generates a random Date
 * @author lynnlow175
 */
public class BlindDateCommand extends Command {
    public static final String COMMAND_WORD = "blindDate";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a blind Date"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.getBlindDate();
        return new CommandResult(Messages.MESSAGE_RANDOM_PERSON_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        return other instanceof BlindDateCommand;
    }
}
