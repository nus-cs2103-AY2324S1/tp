package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object.
 */
public class DeleteEventCommandParser implements Parser<DeleteEventCommand> {
    @Override
    public DeleteEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_START_DATE_TIME);

        if (!arePrefixesPresent(argMultiMap, PREFIX_EVENT_START_DATE_TIME) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE));
        }

        argMultiMap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_START_DATE_TIME);
        LocalDateTime eventTime = LocalDateTime.parse(argMultiMap.getValue(PREFIX_EVENT_START_DATE_TIME).get(),
                DATE_TIME_STRING_FORMATTER);
        return new DeleteEventCommand(eventTime);
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
