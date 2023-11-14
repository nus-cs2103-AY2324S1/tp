package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSIGNMENT_NAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;


/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {
    /**
     * Parses the given arguments and constructs a GradeCommand object for execution.
     *
     * @param args A string of arguments representing the grade command.
     * @return An GradeCommand for execution.
     * @throws ParseException If the user input does not conform to the expected format
     *     or if score is a negative integer.
     */
    public GradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GRADE);
        Index index;
        Name name = null;
        int score = -1;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), ive);
        }

        if (!argMultimap.getValue(PREFIX_NAME).isPresent() || !argMultimap.getValue(PREFIX_GRADE).isPresent()
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_GRADE);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            try {
                name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            } catch (ParseException e) {
                throw new ParseException(MESSAGE_INVALID_ASSIGNMENT_NAME);
            }
        }

        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            try {
                score = ParserUtil.parseInt(argMultimap.getValue(PREFIX_GRADE).get());
            } catch (ParseException e) {
                throw new ParseException(MESSAGE_INVALID_ASSIGNMENT_SCORE);
            }
        }

        if (score < 0) {
            throw new ParseException(MESSAGE_INVALID_ASSIGNMENT_SCORE);
        }

        return new GradeCommand(index, name, score);
    }

}
