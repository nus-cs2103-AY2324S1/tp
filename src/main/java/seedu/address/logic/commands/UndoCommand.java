package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Reverts the last deletion of bookings.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Last deletion has been undone.";
    public static final String MESSAGE_NO_UNDO = "No deletions to undo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null;
        List<Booking> lastDeletion = model.undoDeletion();

        if (lastDeletion.isEmpty()) {
            throw new CommandException(MESSAGE_NO_UNDO);
        } else {
            for (Booking booking : lastDeletion) {
                model.addBooking(booking);
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof UndoCommand);
    }
}
