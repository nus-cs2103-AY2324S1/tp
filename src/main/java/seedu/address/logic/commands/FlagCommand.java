package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Flags a booking identified using its displayed indices from the bookings book.
 */
public class FlagCommand extends Command {

    public static final String COMMAND_WORD = "flag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Flags a booking identified using its displayed indices from the bookings book.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FLAG_SUCCESS = "Flagged Booking: %1$s";
    public static final String MESSAGE_ALREADY_FLAGGED = "Booking already flagged!";

    private final Index targetIndex;

    public FlagCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking booking = lastShownList.get(targetIndex.getZeroBased());
        if (booking.isFlagged()) {
            throw new CommandException(MESSAGE_ALREADY_FLAGGED);
        }
        booking.flag();
        model.updateFilteredBookingList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_FLAG_SUCCESS, booking), false, false,
                false);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FlagCommand)) {
            return false;
        }

        FlagCommand otherFlagCommand = (FlagCommand) other;
        return this.targetIndex.equals(otherFlagCommand.targetIndex);
    }
}
