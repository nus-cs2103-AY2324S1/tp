package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Unflags a booking identified using its displayed indices from the bookings book.
 */
public class UnflagCommand extends Command {

    public static final String COMMAND_WORD = "unflag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": unflags a booking identified using its displayed indices from the bookings book.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FLAG_SUCCESS = "Flagged Booking: %1$s";
    public static final String MESSAGE_NOT_FLAGGED = "Booking not flagged!";

    private final Index targetIndex;

    /**
     * Constructs an {@code UnflagCommand} to unflag a booking at the specified index.
     *
     * @param targetIndex The index of the booking to be unflagged.
     */
    public UnflagCommand(Index targetIndex) {
        assert targetIndex != null : "Index cannot be null";
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the UnflagCommand to unflag a booking using its index.
     *
     * @param model The model containing the booking list.
     * @return A CommandResult indicating the success of the unflag operation.
     * @throws CommandException If there's an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking booking = lastShownList.get(targetIndex.getZeroBased());
        if (!booking.isFlagged()) {
            throw new CommandException(MESSAGE_NOT_FLAGGED);
        }
        booking.unflag();
        model.updateFilteredBookingList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_FLAG_SUCCESS, booking), false, false,
                false);
    }

    /**
     * Checks for equality between two UnflagCommand objects based on their target indices.
     *
     * @param other The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnflagCommand)) {
            return false;
        }

        UnflagCommand otherUnflagCommand = (UnflagCommand) other;
        return this.targetIndex.equals(otherUnflagCommand.targetIndex);
    }
}
