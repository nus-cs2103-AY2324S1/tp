package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Title;

/**
 * Parses input arguments and creates a new AddAssignmentCommand object
 */
public class AddAssignmentCommandParser implements Parser<AddAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAssignmentCommand
     * and returns an AddAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT);

        // check if "assgn/" prefix exists and additional erroneous inputs such as add ppm/CS2100
        if (!isPrefixPresent(argMultimap, PREFIX_ASSIGNMENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ASSIGNMENT);

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_ASSIGNMENT).get());

        Assignment assignment = new Assignment(title);
        return new AddAssignmentCommand(assignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
