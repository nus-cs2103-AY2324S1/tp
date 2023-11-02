package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.Model;

/**
 * Clears the LoveBook.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "LoveBook has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLoveBook(new LoveBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
