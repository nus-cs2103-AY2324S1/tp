package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindAllTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsAllTagsPredicate;
import seedu.address.model.task.TaskContainsAllTagsPredicate;

/**
 * Parses input arguments and creates a new FindAllTagCommand object.
 */
public class FindAllTagCommandParser implements Parser<FindAllTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAllTagCommand
     * and returns a FindAllTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAllTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllTagCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new FindAllTagCommand(new PersonContainsAllTagsPredicate(Arrays.asList(tagKeywords)),
                new TaskContainsAllTagsPredicate(Arrays.asList(tagKeywords))
        );
    }
}
