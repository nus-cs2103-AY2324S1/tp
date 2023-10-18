package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_SESSION;

import seedu.address.logic.commands.RecordClassPartCommand;
import seedu.address.logic.commands.SetGradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentNumber;

/**
 * Parses input arguments and creates a new RecordClassPartCommand object
 */
public class RecordClassPartCommandParser implements Parser<RecordClassPartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RecordClassPartCommand
     * and returns an SetGradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecordClassPartCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NUMBER,
               PREFIX_TUTORIAL_SESSION, PREFIX_PARTICIPATION);

        if (!argMultimap.arePrefixesPresent(PREFIX_STUDENT_NUMBER, PREFIX_TUTORIAL_SESSION, PREFIX_PARTICIPATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordClassPartCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_NUMBER,
                PREFIX_TUTORIAL_SESSION, PREFIX_PARTICIPATION);
        StudentNumber studentNumber = ParserUtil.parseStudentNumber(
                argMultimap.getValue(PREFIX_STUDENT_NUMBER).get());
        int sessionNumber;
        boolean isParticipated;
        try {
            sessionNumber = Integer.parseInt(argMultimap.getValue(PREFIX_TUTORIAL_SESSION).get());
            isParticipated = Boolean.parseBoolean(argMultimap.getValue(PREFIX_PARTICIPATION).get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGradeCommand.MESSAGE_USAGE));
        }
        return new RecordClassPartCommand(studentNumber, sessionNumber, isParticipated);
    }

}
