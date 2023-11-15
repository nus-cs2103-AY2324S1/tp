package seedu.address.testutil;

import seedu.address.logic.CommandWarnings;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a function that takes in a warning and can possibly throw a ParseException.
 */
@FunctionalInterface
public interface FunctionWithWarnings {
    void apply(CommandWarnings warnings) throws ParseException;
}
