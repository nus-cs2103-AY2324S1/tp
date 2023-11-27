package seedu.address.logic.commands;

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
 * Deletes one or more bookings identif ied using their displayed indices from the address book.
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
        assert targetIndices.length > 0 : "At least one index must be provided for deletion.";
        this.targetIndices = targetIndices;
    }

    /**
     * Executes the DeleteCommand to delete bookings by their indices.
     *
     * @param model The model containing the booking list.
     * @return A CommandResult indicating the success of the deletion operation.
     * @throws CommandException If there's an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null.";
        List<Booking> lastShownList = model.getFilteredBookingList();
        List<Booking> deleteList = new ArrayList<>();
        List<Integer> invalidIndices = new ArrayList<>(); // To keep track of invalid indices

        StringBuilder deletedBookings = new StringBuilder();
        boolean atLeastOneBookingDeleted = false;

        for (Index targetIndex : targetIndices) {
            int zeroBasedIndex = targetIndex.getZeroBased();
            if (zeroBasedIndex >= 0 && zeroBasedIndex < lastShownList.size()) {
                Booking bookingToDelete = lastShownList.get(zeroBasedIndex);
                deleteList.add(bookingToDelete);
                atLeastOneBookingDeleted = true;
            } else {
                invalidIndices.add(targetIndex.getOneBased()); // Record the invalid index
            }
        }

        if (!atLeastOneBookingDeleted) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        model.addToDeletedBookings(deleteList);

        for (Booking deletedBooking : deleteList) {
            model.deleteBooking(deletedBooking);
            deletedBookings.append(Messages.format(deletedBooking)).append(", ");
        }

        deletedBookings.setLength(deletedBookings.length() - 2);

        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_PERSONS);

        String successMessage = String.format(MESSAGE_DELETE_BOOKING_SUCCESS, deletedBookings);

        // If there are invalid indices, notify the user about them
        if (!invalidIndices.isEmpty()) {
            String invalidIndicesMsg = "Invalid index(es): " + invalidIndices.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            successMessage += "\n" + invalidIndicesMsg;
        }

        return new CommandResult(successMessage, false, false, true);
    }

    /**
     * Checks for equality between two DeleteCommand objects based on their target indices.
     *
     * @param other The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Provides a string representation of the DeleteCommand, including its target indices.
     *
     * @return A string representation of the command.
     */
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
