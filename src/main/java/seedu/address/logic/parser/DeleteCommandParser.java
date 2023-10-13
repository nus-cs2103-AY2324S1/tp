package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTNUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentNumber;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_STUDENTNUMBER);

        StudentNumber studentNumber;
        try {
            studentNumber = ParserUtil.parseStudentNumber(argMultimap.getValue(PREFIX_STUDENTNUMBER).get());;
            return new DeleteCommand(studentNumber);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE), pe);
        }

//        try {
//            Index index = ParserUtil.parseIndex(args);
//            return new DeleteCommand(index);
//        } catch (ParseException pe) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
//        }
    }

}
