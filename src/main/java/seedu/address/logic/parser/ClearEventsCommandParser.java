package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONFIRMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import seedu.address.logic.commands.ClearEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventPeriod;

/**
 * Parses the input arguments and creates a new ClearEventsCommand object.
 */
public class ClearEventsCommandParser implements Parser<ClearEventsCommand> {
    private static final String CONFIRMATION = "CONFIRMED";
    @Override
    public ClearEventsCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_EVENT_START_DATE_TIME, PREFIX_EVENT_END_DATE_TIME,
                        PREFIX_CONFIRMATION);

        if (!isTimePresent(argMultiMap) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearEventsCommand.MESSAGE_USAGE));
        }

        argMultiMap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_START_DATE_TIME, PREFIX_EVENT_END_DATE_TIME,
                PREFIX_CONFIRMATION);

        boolean isConfirmed = isCommandConfirmed(argMultiMap);
        EventPeriod clearPeriod = ParserUtil.parseEventPeriod(argMultiMap.getValue(PREFIX_EVENT_START_DATE_TIME).get(),
                argMultiMap.getValue(PREFIX_EVENT_END_DATE_TIME).get());
        return new ClearEventsCommand(clearPeriod, isConfirmed);
    }

    private static boolean isCommandConfirmed(ArgumentMultimap argumentMultimap) {
        if (argumentMultimap.getValue(PREFIX_CONFIRMATION).isPresent()) {
            return argumentMultimap.getValue(PREFIX_CONFIRMATION).get().equals(CONFIRMATION);
        }
        return false;
    }

    private static boolean isTimePresent(ArgumentMultimap argumentMultimap) {
        boolean isStartTimePresent = argumentMultimap.getValue(PREFIX_EVENT_START_DATE_TIME).isPresent();
        boolean isEndTimePresent = argumentMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).isPresent();
        return isStartTimePresent && isEndTimePresent;
    }
}
