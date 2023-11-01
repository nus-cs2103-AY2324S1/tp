package seedu.address.logic.prefixcompletion;

import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;

/**
 * Represents an interface for classes that provide prefix completion functionality.
 */
public interface PrefixFinder {
    /**
     * Retrieves the next prefix and example for completion based on the current user input.
     *
     * @param currentInput The current user input string.
     * @return The next recommended prefix and example for completion.
     * @throws PrefixCompletionException If there's an issue determining the prefix completion.
     */
    String getPrefix(String currentInput) throws PrefixCompletionException;
}
