package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignmentIndivCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;


/**
 * Parses input arguments and creates a new AssignmentIndivCommand object
 */
public class AssignmentIndivCommandParser implements Parser<AssignmentIndivCommand> {

    /**
     * Parses the given arguments and constructs an AssignmentIndivCommand object for execution.
     *
     * @param args A string of arguments representing the assignment command.
     * @return An AssignmentIndivCommand for execution.
     * @throws ParseException If the user input does not conform to the expected format
     *     or if maxScore is not a positive integer.
     */
    public AssignmentIndivCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MAX_SCORE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignmentIndivCommand.MESSAGE_USAGE), pe);
        }

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MAX_SCORE)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignmentIndivCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_MAX_SCORE);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        int maxScore;
        try {
            maxScore = ParserUtil.parseInt(argMultimap.getValue(PREFIX_MAX_SCORE).get());
        } catch (ParseException e) {
            throw new ParseException(Assignment.MESSAGE_INVALID_MAX_SCORE);
        }

        return new AssignmentIndivCommand(index, name, maxScore);
    }
}
