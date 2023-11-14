package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_START_TIME_AFTER_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DESCENDING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTime;


/**
 * The parser for {@code list event} command
 */
public class ListEventCommandParser implements Parser<ListEventCommand> {

    @Override
    public ListEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_SORT_DESCENDING);

        if ((
                ParserUtil.arePrefixesPresent(argMultimap, PREFIX_START_TIME)
                        && !ParserUtil.arePrefixesPresent(argMultimap, PREFIX_END_TIME))
                || (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_START_TIME)
                && ParserUtil.arePrefixesPresent(argMultimap, PREFIX_END_TIME))
                || !argMultimap.getPreamble().isEmpty()
                || !argMultimap.getValue(PREFIX_SORT_DESCENDING).orElseGet(()->"").isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_SORT_DESCENDING);

        EventTime filterStartTime = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_START_TIME)
                ? ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_START_TIME).get())
                : null;
        EventTime filterEndTime = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_END_TIME)
                ? ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME).get())
                : null;

        boolean useAscendingOrder = !ParserUtil.arePrefixesPresent(argMultimap, PREFIX_SORT_DESCENDING);
        if (filterStartTime != null) {
            if (filterStartTime.isAfter(filterEndTime)) {
                throw new ParseException(String.format(MESSAGE_START_TIME_AFTER_END_TIME,
                        filterStartTime, filterEndTime));
            }
        }
        return new ListEventCommand(filterStartTime, filterEndTime, useAscendingOrder);
    }
}
