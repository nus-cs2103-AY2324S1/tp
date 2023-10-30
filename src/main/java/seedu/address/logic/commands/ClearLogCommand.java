package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.LogBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearLogCommand extends Command {

    public static final String COMMAND_WORD = "clog";

    public static final String COMMAND_WORD_ALIAS = "cl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "or" + COMMAND_WORD_ALIAS
            + ": Clears the entire logger tab.\n"
            + "Example: " + COMMAND_WORD + "or" + COMMAND_WORD_ALIAS;

    public static final String MESSAGE_SUCCESS = "Logger tab has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLogBook(new LogBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
