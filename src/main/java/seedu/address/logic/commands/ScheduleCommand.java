package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Format full help instructions for every command for display.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows events.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SCHEDULE_MESSAGE = "Opened events window.";

    @Override
    public CommandResult execute(Model model) {
        model.sortEventList(Comparator.comparing(Event::getStart_time));
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(SHOWING_SCHEDULE_MESSAGE, false, false, false, true, false);
    }
}
