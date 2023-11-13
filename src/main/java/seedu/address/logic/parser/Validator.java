package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Functional interface for validating input strings.
 */
@FunctionalInterface
public interface Validator {
    /**
     * Validates the given string.
     *
     * @param string value to validate.
     * @throws ParseException if the string does not meet the requirements.
     */
    void validate(String string) throws ParseException;
}
