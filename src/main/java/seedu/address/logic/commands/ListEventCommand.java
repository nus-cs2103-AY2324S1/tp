package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTime;

/**
 * The command handler for {@code list events} command
 */
public class ListEventCommand extends ListCommand {
    public static final String SECONDARY_COMMAND_WORD = "events";
    public static final String MESSAGE_ALL = "Here are all the events in this address book ";
    public static final String MESSAGE_FILTERED = "Here are the events in this address book within the time interval ";
    public static final String MESSAGE_ASCENDING = "(in ascending order):\n";
    public static final String MESSAGE_DESCENDING = "(in descending order):\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECONDARY_COMMAND_WORD
            + ": Shows a list of all events or events within a specified time interval.\n"
            + "Usage: " + COMMAND_WORD + " " + SECONDARY_COMMAND_WORD
            + " [-descending] [-st filter_start_time] [-et filter_end_time]"
            + " (-st and -et must either both present or both not present)";

    private LocalDateTime filterStartTime;
    private LocalDateTime filterEndTime;
    private boolean sortAscending;

    /**
     * Constructor for {@code ListEventCommand} class
     *
     * @param filterStartTime The start time for filter
     * @param filterEndTime   The end time for filter
     * @param sortAscending   Set to {@code true} to sort the events by start time
     *                        in ascending order,
     *                        {@code false} in descending order.
     */
    public ListEventCommand(EventTime filterStartTime, EventTime filterEndTime, boolean sortAscending) {
        this.filterStartTime = filterStartTime != null ? filterStartTime.getTime() : null;
        this.filterEndTime = filterEndTime != null ? filterEndTime.getTime() : null;
        this.sortAscending = sortAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert (filterStartTime == null && filterEndTime == null) || (filterStartTime != null && filterEndTime != null);
        String result = "";
        if (filterStartTime == null) {
            model.updateFilteredEventList(evt -> true); // Remove the filter to get the full event list
            result = MESSAGE_ALL;
        } else {
            model.updateFilteredEventList(
                    evt -> DateTimeUtil.withinTimeInterval(this.filterStartTime, this.filterEndTime,
                            evt.getStartTime()));
            result = MESSAGE_FILTERED;
        }
        result += this.sortAscending ? MESSAGE_ASCENDING : MESSAGE_DESCENDING;
        List<Event> eventList = model.generateSortedFilteredEventList((o1, o2) -> {
            LocalDateTime startTime1 = o1.getStartTime();
            LocalDateTime startTime2 = o2.getStartTime();
            return (startTime1.isBefore(startTime2)
                    ? 1
                    : startTime1.equals(startTime2)
                            ? 0
                            : -1)
                    * (sortAscending ? -1 : 1);
        });
        result += StringUtil.eventListToString(eventList);
        return new CommandResult(result, false, true);
    }
}
