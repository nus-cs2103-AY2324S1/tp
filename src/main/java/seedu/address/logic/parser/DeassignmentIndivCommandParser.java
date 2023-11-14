package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeassignmentIndivCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeassignmentIndivCommandParser object
 */
public class DeassignmentIndivCommandParser implements Parser<DeassignmentIndivCommand> {

    /**
     * Parses the given arguments and constructs an DeassignmentIndivCommand object for execution.
     *
     * @param args A string of arguments representing the assignment command.
     * @return An DeassignmentIndivCommand for execution.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public DeassignmentIndivCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeassignmentIndivCommand.MESSAGE_USAGE), pe);
        }

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeassignmentIndivCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new DeassignmentIndivCommand(index, name);
    }
}
