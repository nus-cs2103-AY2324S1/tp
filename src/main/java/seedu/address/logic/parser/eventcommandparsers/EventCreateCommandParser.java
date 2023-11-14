package seedu.address.logic.parser.eventcommandparsers;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE_PARAMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATERIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_VOLUNTEER_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.eventcommands.EventCreateCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Budget;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.Material;
import seedu.address.model.event.MaxVolunteerSize;
import seedu.address.model.event.Role;
import seedu.address.model.volunteer.Name;


/**
 * Parses input arguments and creates a new EventCreateCommand object
 */
public class EventCreateCommandParser implements Parser<EventCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventCreateCommand
     * and returns an EventCreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventCreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE, PREFIX_START_DATETIME,
                        PREFIX_END_DATETIME, PREFIX_LOCATION, PREFIX_DESCRIPTION, PREFIX_MATERIAL, PREFIX_BUDGET,
                        PREFIX_MAX_VOLUNTEER_SIZE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ROLE, PREFIX_START_DATETIME,
                PREFIX_LOCATION, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EventCreateCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_START_DATETIME, PREFIX_END_DATETIME,
                PREFIX_LOCATION, PREFIX_DESCRIPTION, PREFIX_BUDGET, PREFIX_MAX_VOLUNTEER_SIZE);
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Role> roleList = ParserUtil.parseRoles(argMultimap.getAllValues(PREFIX_ROLE));
        DateTime startDate = ParserUtil.parseDateAndTime(argMultimap.getValue(PREFIX_START_DATETIME).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Set<Material> materialList = ParserUtil.parseMaterials(argMultimap.getAllValues(PREFIX_MATERIAL));

        // Check if the command contains the optional end datetime field
        // default value is 3 hours after the start datetime
        DateTime endDate = new DateTime(startDate.dateAndTime.plusHours(3));
        if (args.contains(PREFIX_END_DATETIME.getPrefix())) {
            endDate = ParserUtil.parseDateAndTime(argMultimap.getValue(PREFIX_END_DATETIME).get());
        }

        // compare end datetime to ensure it is after/same as start datetime
        if (endDate.dateAndTime.isBefore(startDate.dateAndTime)) {
            throw new ParseException(MESSAGE_INVALID_DATE_PARAMS);
        }

        // Check if the command contains the optional budget field
        Budget budget = new Budget("");
        if (args.contains(PREFIX_BUDGET.getPrefix())) {
            budget = ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get());
        }

        // Check if the command contains the optional max volunteer size field
        MaxVolunteerSize maxVolunteerSize = new MaxVolunteerSize();
        if (args.contains(PREFIX_MAX_VOLUNTEER_SIZE.getPrefix())) {
            maxVolunteerSize = ParserUtil
                    .parseMaxVolunteerSize(argMultimap.getValue(PREFIX_MAX_VOLUNTEER_SIZE).get());
        }

        Event event = new Event(eventName, roleList, startDate, endDate, location, description, materialList, budget,
                new HashSet<Name>(), maxVolunteerSize);

        return new EventCreateCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
