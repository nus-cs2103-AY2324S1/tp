package seedu.address.logic.prefixcompletion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;

/**
 * Represents a utility class to find the appropriate prefix completion for the AddCommand.
 */
public class AddPrefixFinder implements PrefixFinder {
    private static final List<Prefix> PREFIXES = AddCommand.PREFIXES;
    private static List<String> examples = AddCommand.EXAMPLES;

    /**
     * Updates the example list with the current date and time.
     */
    private void updateExample() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String currentDate = now.format(formatter);
        LocalDateTime nextDayAt11am = now.plusDays(1).withHour(11)
                .withMinute(0).withSecond(0).withNano(0);
        String nextDate = nextDayAt11am.format(formatter);

        // Create a new list with the updated date
        List<String> updatedExamples = new ArrayList<>(examples);
        updatedExamples.set(1, currentDate + " to " + nextDate);

        // Assign the updated list to examples
        examples = updatedExamples;
    }

    /**
     * Retrieves the appropriate prefix for the current input in the AddCommand.
     *
     * @param currentInput The current input string.
     * @return The recommended prefix completion.
     * @throws PrefixCompletionException If no prefix completion recommendation is found.
     */
    public String getPrefix(String currentInput) throws PrefixCompletionException {
        updateExample();
        // Determine the next prefix and example.
        for (int i = 0; i < PREFIXES.size(); i++) {
            if (!currentInput.contains(PREFIXES.get(i).toString())) {
                return PREFIXES.get(i) + examples.get(i);
            }
        }
        throw new PrefixCompletionException("No prefix completion recommendation found for the current input.");
    }
}
