package seedu.address.logic;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INTEGER_OVERFLOW =
            "Provided index is greater than 2147483647. Please use a smaller integer.";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Event index is out of bounds!";
    public static final String MESSAGE_DATE_CHANGE_SUCCESSFUL = "Successfully changed!";
    private static final String MESSAGE_EVENT_ADDED_SUCCESSFULLY = "Event added successfully!";
    private static final String MESSAGE_EVENT_TIMING_CONFLICT = "Conflicting timing!";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the event for display to the user.
     *
     * @param event event to be displayed.
     * @return String of appropriate format.
     */
    public static String format(Event event) {
        final StringBuilder builder = new StringBuilder();
        builder.append(event.getDescription())
                .append("; ")
                .append(event.getEventPeriod());
        return builder.toString();
    }

    /**
     * Formats a task for display to the user.
     *
     * @param task task to be displayed
     * @return String of appropriate format.
     */
    public static String format(Task task) {
        final StringBuilder builder = new StringBuilder();
        builder.append(task.getDescriptionString())
                .append("\nDeadline: ")
                .append(task.getDeadlineString());
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String formatCalendar(Person person) {
        final StringBuilder builder = new StringBuilder();
        ObservableList<Event> eventList = person.getCalendar().getEventList();
        builder.append(person.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (int i = 0; i < eventList.size(); i++) {
            Event event = eventList.get(i);
            String eventDescription = event.getDescription().getDescription();
            String startTime = formatter.format(event.getEventPeriod().getStart());
            String endTime = formatter.format(event.getEventPeriod().getEnd());
            builder.append(";\n")
                    .append(eventDescription)
                    .append(" ")
                    .append(startTime)
                    .append(" ")
                    .append(endTime);
        }
        return builder.toString();
    }
}
