package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Deletes one or more bookings identified using their displayed indices from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the bookings identified by the index numbers used in the displayed booking list.\n"
            + "Parameters: INDEX [INDEX]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Booking(s): %1$s";

    private final Index[] targetIndices;

    /**
     * Constructs a {@code DeleteCommand} to delete bookings at the specified indices.
     *
     * @param targetIndices The indices of the bookings to be deleted.
     */
    public DeleteCommand(Index... targetIndices) {
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();
        List<Booking> deleteList = new ArrayList<>();

        StringBuilder deletedBookings = new StringBuilder();
        boolean atLeastOneBookingDeleted = false;

        for (Index targetIndex : targetIndices) {
            int zeroBasedIndex = targetIndex.getZeroBased();
            if (zeroBasedIndex >= 0 && zeroBasedIndex < lastShownList.size()) {
                Booking bookingToDelete = lastShownList.get(zeroBasedIndex);
                deleteList.add(bookingToDelete);
                atLeastOneBookingDeleted = true;
            }
        }

        if (!atLeastOneBookingDeleted) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        for (int i = 0; i < targetIndices.length; i++) {
            Booking deletedBooking = deleteList.get(i);
            model.deleteBooking(deletedBooking);
            deletedBookings.append(Messages.format(deletedBooking)).append(", ");
        }

        deletedBookings.setLength(deletedBookings.length() - 2); // Remove the trailing comma and space

        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, deletedBookings),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return Arrays.equals(targetIndices, otherDeleteCommand.targetIndices);
    }

    @Override
    public String toString() {
        // Create a list of index strings
        List<String> indexStrings = Arrays.stream(targetIndices)
                .map(Index::getOneBased)
                .map(String::valueOf)
                .collect(Collectors.toList());

        // Join the index strings into a single string
        String indicesString = String.join(" ", indexStrings);

        return getClass().getCanonicalName() + "{targetIndices=" + indicesString + "}";
    }


}
