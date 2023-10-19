package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.CreateEventCommand;
import seedu.address.model.event.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an create command string for adding the {@code event}.
     */
    public static String getCreateEventCommand(Event event) {
        return CreateEventCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().name + " ");
        sb.append(PREFIX_LOCATION + event.getLocation().value + " ");
        sb.append(PREFIX_DATE + event.getDate().toString() + " ");
        return sb.toString();
    }

}
