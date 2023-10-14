package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTNUMBER;

import java.util.stream.Stream;

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

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENTNUMBER) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_STUDENTNUMBER, PREFIX_CLASSNUMBER);

        StudentNumber studentNumber;
        studentNumber = ParserUtil.parseStudentNumber(argMultimap.getValue(PREFIX_STUDENTNUMBER).get());
        return new DeleteCommand(studentNumber);
    }


    //@@ngeeyonglim ChangruHenryQian-reused
    //This part of code is reused from my teammate ngeeyonglim

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    //@@ngeeyonglim
}
