package seedu.address.logic.prefixcompletion;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;

/**
 * Provide prefix-based completion recommendations.
 */
public class PrefixCompletion {
    private static final Logger logger = LogsCenter.getLogger(PrefixCompletion.class);

    /**
     * Given the current input, returns the next prefix and example for completion.
     *
     * @param currentInput The user's current input string.
     * @return The next prefix and example for completion.
     * @throws PrefixCompletionException If no prefix completion recommendation is found.
     */
    public String getNextCompletion(String currentInput) throws PrefixCompletionException {
        PrefixFinder prefixFinder;

        logger.log(Level.INFO, "Finding next prefix for: " + currentInput);
        checkFormat(currentInput);

        String commandWord = getCommandWord(currentInput);

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            prefixFinder = new AddPrefixFinder();
            break;
        default:
            logger.log(Level.WARNING, "Command not found");
            throw new PrefixCompletionException("No prefix completion recommendation found for the current input.");
        }

        return prefixFinder.getPrefix(currentInput);
    }

    /**
     * Retrieves the command word from the input string.
     *
     * @param input The user's input string.
     * @return The command word extracted from the input.
     */
    private String getCommandWord(String input) {
        return input.split("\\s+")[0];
    }

    /**
     * Checks if the input string ends with a single space, ensuring proper format for prefix completion.
     *
     * @param currentInput The user's current input string.
     * @throws PrefixCompletionException If the input does not end with a single space.
     */
    private void checkFormat(String currentInput) throws PrefixCompletionException {
        boolean isLastCharSingleSpace =
                currentInput.length() > 0 && currentInput.charAt(currentInput.length() - 1) == ' ';

        if (!isLastCharSingleSpace) {
            logger.log(Level.WARNING, "Prefix not found");
            throw new PrefixCompletionException("Add space to use prefix completion");
        }
    }
}
