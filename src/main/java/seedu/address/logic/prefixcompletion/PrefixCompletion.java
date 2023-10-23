package seedu.address.logic.prefixcompletion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.Prefix;
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
     */
    public String getNextCompletion(String currentInput) throws PrefixCompletionException {
        logger.log(Level.INFO, "Finding next prefix for: " + currentInput);
        boolean isLastCharSingleSpace =
                currentInput.length() > 0 && currentInput.charAt(currentInput.length() - 1) == ' ';

        // To ensure correct format.
        if (!isLastCharSingleSpace) {
            logger.log(Level.WARNING, "Prefix not found");
            throw new PrefixCompletionException("Add space to use prefix completion");
        }

        String commandWord = getCommandWord(currentInput);

        List<Prefix> prefixes;
        List<String> examples;

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            prefixes = AddCommand.PREFIXES;
            examples = AddCommand.EXAMPLES;
            break;
        default:
            logger.log(Level.WARNING, "Command not found");
            throw new PrefixCompletionException("No prefix completion recommendation found for the current input.");
        }

        // Determine the next prefix and example.
        for (int i = 0; i < prefixes.size(); i++) {
            if (!currentInput.contains(prefixes.get(i).toString())) {
                logger.log(Level.INFO, "Prefix found: " + prefixes.get(i));
                return prefixes.get(i) + examples.get(i);
            }
        }
        logger.log(Level.WARNING, "Prefix not found");
        throw new PrefixCompletionException("No prefix completion recommendation found for the current input.");
    }

    private String getCommandWord(String input) {
        return input.split("\\s+")[0];
    }
}
