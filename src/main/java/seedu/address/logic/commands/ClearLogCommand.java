package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.LogBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearLogCommand extends Command {

    public static final String COMMAND_WORD = "clog";
    public static final String MESSAGE_SUCCESS = "Logbook has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLogBook(new LogBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
