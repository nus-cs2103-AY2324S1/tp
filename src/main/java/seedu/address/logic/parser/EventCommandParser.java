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

public class EventCommandParser implements Parser<EventCommand> {

    public EventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_STARTTIME, PREFIX_ENDTIME);
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        System.out.println(description);
        LocalDateTime start_time = LocalDateTime.parse(argMultimap.getValue(PREFIX_STARTTIME).get(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime end_time = LocalDateTime.parse(argMultimap.getValue(PREFIX_ENDTIME).get(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        Event event = new Event(index, description, start_time, end_time);
        return new EventCommand(event);
    }
}
