package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.BookingsBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Booking book has been cleared!";

    /**
     * Executes the ClearCommand to clear the booking book.
     *
     * @param model The model from which the booking book is cleared.
     * @return A CommandResult indicating the success of the command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBookingsBook(new BookingsBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
