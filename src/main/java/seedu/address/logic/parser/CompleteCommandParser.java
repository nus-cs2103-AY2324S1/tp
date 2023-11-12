package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;

import java.time.LocalDate;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompleteByDate;
import seedu.address.logic.commands.CompleteByIndex;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new CompleteCommand object
 */
public class CompleteCommandParser implements Parser<CompleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CompleteCommand
     * and returns an CompleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CompleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_DATE);

        String strIndex = argMultimap.getPreamble().trim();
        Optional<String> strDate = argMultimap.getValue(PREFIX_APPOINTMENT_DATE);

        if (!isValidArguments(strIndex, strDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CompleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT_DATE);

        if (strDate.isPresent()) {
            LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
            return new CompleteByDate(date);
        } else {
            try {
                Index index = ParserUtil.parseIndex(strIndex);
                return new CompleteByIndex(index);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CompleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    public static boolean isValidArguments(String strIndex, Optional<String> strDate) {
        //if no input
        if (strIndex.isEmpty() && strDate.isEmpty()) {
            return false;
        }
        //if both date and index given
        if (!strIndex.isEmpty() && strDate.isPresent()) {
            return false;
        }

        return true;
    }
}
