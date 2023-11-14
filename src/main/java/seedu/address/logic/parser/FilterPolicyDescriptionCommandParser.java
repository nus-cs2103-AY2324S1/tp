package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterPolicyDescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FilterPolicyDescriptionPredicate;

/**
 * Parses input arguments and creates a new FilterPolicyDescriptionCommand
 * object
 */
public class FilterPolicyDescriptionCommandParser implements Parser<FilterPolicyDescriptionCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected
     *                        format
     */
    @Override
    public FilterPolicyDescriptionCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPolicyDescriptionCommand.MESSAGE_USAGE));
        }

        String nameKeywords = trimmedArgs;

        return new FilterPolicyDescriptionCommand(new FilterPolicyDescriptionPredicate(nameKeywords));
    }
}
