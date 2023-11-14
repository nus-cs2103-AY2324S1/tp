package seedu.address.logic.parser.events;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Duration;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIME_START, PREFIX_TIME_END, PREFIX_CLIENT,
                        PREFIX_LOCATION, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME, PREFIX_TIME_START, PREFIX_TIME_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TIME_START, PREFIX_TIME_END,
                PREFIX_LOCATION, PREFIX_DESCRIPTION);
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        Duration duration = DateTimeParser.parseDateTimeDuration(argMultimap.getValue(PREFIX_TIME_START).get(),
                argMultimap.getValue(PREFIX_TIME_END).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(""));
        EventDescription eventDescription = ParserUtil.parseEventDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                .orElse(""));
        Set<Person> clients = ParserUtil.parseClients(argMultimap.getAllValues(PREFIX_CLIENT));

        Event event = new Event(eventName, duration, clients, location, eventDescription);

        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
