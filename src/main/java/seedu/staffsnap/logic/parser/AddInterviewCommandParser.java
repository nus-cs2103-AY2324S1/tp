package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.staffsnap.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.commands.AddInterviewCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.interview.Interview;

/**
 * Parses input arguments and creates a new AddInterviewCommand object
 */
public class AddInterviewCommandParser implements Parser<AddInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterviewCommand
     * and returns an AddInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE);

        String type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Interview interview = new Interview(type);

        return new AddInterviewCommand(index, interview);
    }
}
