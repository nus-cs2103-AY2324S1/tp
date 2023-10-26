package seedu.address.logic.prefixcompletion;

import seedu.address.logic.prefixcompletion.exceptions.PrefixCompletionException;

/**
 * Represents an interface for classes that provide prefix completion functionality.
 */
public interface PrefixFinder {
    String getPrefix(String currentInput) throws PrefixCompletionException;
}
