package seedu.address.logic.prefixcompletion;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;
import seedu.address.model.booking.Booking;

/**
 * A utility class for finding prefixes in the EditCommand context.
 * This class helps in suggesting the next possible prefix for user input
 * based on the EditCommand's expected prefixes.
 */
public class EditPrefixFinder implements PrefixFinder {
    private static final List<Prefix> PREFIXES = EditCommand.PREFIXES;
    private static List<String> examples = EditCommand.EXAMPLES;

    /**
     * Updates the examples list based on the booking data of the given index.
     *
     * @param index The index of the booking to fetch data from.
     * @throws PrefixCompletionException If an invalid index is provided.
     */
    private void updateExample(int index) throws PrefixCompletionException {
        Booking booking;

        try {
            booking = PrefixCompletion.bookingBook.getPersonList().get(index);
        } catch (NullPointerException e) {
            throw new PrefixCompletionException("Invalid index provided");
        } catch (IndexOutOfBoundsException e) {
            throw new PrefixCompletionException("Invalid index provided");
        }

        List<String> updatedExamples = Arrays.asList(
                Integer.toString(booking.getRoom().getRoomNumber()),
                booking.getBookingPeriod().toString(),
                booking.getName().toString(),
                booking.getPhone().toString(),
                booking.getEmail().toString(),
                booking.getRemark().toString()
        );

        examples = updatedExamples;
    }

    @Override
    public String getPrefix(String currentInput) throws PrefixCompletionException {
        // Extract the index from currentInput
        String[] splitInput = currentInput.split(" ", 3);

        if (splitInput.length < 2 || !splitInput[0].equals("edit")) {
            throw new PrefixCompletionException("Invalid command format.");
        }

        int index;

        try {
            index = Integer.parseInt(splitInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new PrefixCompletionException("Invalid index provided in the current input.");
        }

        updateExample(index);

        // Determine the next prefix and example.
        for (int i = 0; i < PREFIXES.size(); i++) {
            if (!currentInput.contains(PREFIXES.get(i).toString())) {
                return PREFIXES.get(i) + examples.get(i);
            }
        }
        throw new PrefixCompletionException("No prefix completion recommendation found for the current input.");
    }
}
