package seedu.address.logic.newcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;

import java.util.Date;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.NewCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NewModel;
import seedu.address.model.flashcard.FlashCard;

/**
 * Adds a person to the address book.
 */
public class YesCommand extends NewCommand {

    public static final String COMMAND_WORD = "yes";

    // For help function
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicates user has successfully memorized the word.\n"
      + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS = "Great Job! You have indicated that you have memorized the word!";
    private final String isMemorized;

    /**
     * Creates an YesCommand.
     */
    public YesCommand(String input) {
        this.isMemorized = input;
    }

    @Override
    public CommandResult execute(NewModel model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
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
          .add("Yes", isMemorized)
          .toString();
    }
}
