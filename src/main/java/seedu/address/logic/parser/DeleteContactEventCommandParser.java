package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INTEGER_OVERFLOW;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INDEX_TOO_LARGE;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteContactEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteContactEventCommand object
 */
public class DeleteContactEventCommandParser implements Parser<DeleteContactEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteContactEventCommand
     * and returns an DeleteContactEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteContactEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_START_DATE_TIME);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INDEX_TOO_LARGE)) {
                throw new ParseException(MESSAGE_INTEGER_OVERFLOW);
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactEventCommand.MESSAGE_USAGE), pe);
            }
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_START_DATE_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteContactEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_START_DATE_TIME);
        try {
            LocalDateTime eventTime = LocalDateTime.parse(
                    argMultimap.getValue(PREFIX_EVENT_START_DATE_TIME).get(),
                    DATE_TIME_STRING_FORMATTER);
            return new DeleteContactEventCommand(index, eventTime);
        } catch (Exception pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactEventCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks if all the given prefix fields are non-empty.
     *
     * @param argumentMultimap argumentMultimap managing arguments for this command.
     * @param prefixes prefixes to be tested.
     * @return true if all fields are non-empty, false if any field contains empty value.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
