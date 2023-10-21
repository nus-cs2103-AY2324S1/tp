package seedu.flashlingo.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;

/**
 * Indicates user has not yet memorized the word.
 */
public class NoCommand extends Command {

    public static final String COMMAND_WORD = "no";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicates user hasn't memorized the word.\n"
        + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Great Job! You have indicated that you have memorized the word!";

    public static final String MESSAGE_NOT_START_REVIEW = "Haven't start review!";

    /**
     * Creates an NoCommand.
     */
    public NoCommand() {};

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.rememberWord(false);
        String response = model.nextReviewWord();
        return new CommandResult(MESSAGE_SUCCESS + "\n" + response);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .toString();
    }
}
