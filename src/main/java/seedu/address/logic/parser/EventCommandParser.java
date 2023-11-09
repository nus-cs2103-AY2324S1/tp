package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;

/**
 * Parses input arguments and returns an EventCommand object.
 */
public class EventCommandParser implements Parser<EventCommand> {

    /**
     * Parses the given {@code String} in the context of an EventCommand
     * and returns an EventCommand object for execution.
     */
    public EventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION,
                PREFIX_STARTTIME, PREFIX_ENDTIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_STARTTIME, PREFIX_ENDTIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.MESSAGE_USAGE));
        }
        LocalDateTime startTime;
        LocalDateTime endTime;

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        try {
            startTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_STARTTIME).get(),
                    DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT));
            endTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_ENDTIME).get(),
                    DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeException e) {
            String s = e.getMessage();
            if (s.contains("Invalid")) {
                throw new ParseException(MESSAGE_INVALID_DATE);
            } else {
                throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
            }
        }

        if (!(endTime.isAfter(startTime))) {
            throw new ParseException(MESSAGE_INVALID_START);
        }
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.MESSAGE_USAGE), pe);
        }
        index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Event event = new Event(index, description, startTime, endTime);
        return new EventCommand(event);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
