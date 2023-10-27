package seedu.ccacommander.testutil;

import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.ccacommander.logic.commands.CreateEventCommand;
import seedu.ccacommander.logic.commands.EditEventCommand;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.tag.Tag;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns a create command string for adding the {@code event}.
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

    public static String getEditEventDescriptorDetails(EditEventCommand.EditEventDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.name).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getDate().ifPresent(eventDate -> sb.append(PREFIX_DATE).append(eventDate.date).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
