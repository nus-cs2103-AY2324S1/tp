package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        LocalDateTime startTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_STARTTIME).get(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_ENDTIME).get(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Event event = new Event(index, description, startTime, endTime);
        return new EventCommand(event);
    }
}
