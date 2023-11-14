package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.DeassignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;


/**
 * Parses input arguments and creates a new DeassignmentCommand object
 */
public class DeassignmentCommandParser implements Parser<DeassignmentCommand> {

    /**
     * Parses the given arguments and constructs an DeassignmentCommand object for execution.
     *
     * @param args A string of arguments representing the deassignment command.
     * @return An DeassignmentCommand for execution.
     * @throws ParseException If the user input does not conform to the expected format
     *     or if maxScore is not a positive integer.
     */
    public DeassignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeassignmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new DeassignmentCommand(name);
    }
}
