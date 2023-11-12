package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.calendar.UniMateCalendar;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Command to compare calendars of user with people in the calendar with some collection of tags.
 */
public class CompareCalendarByTagCommand extends Command {
    public static final String COMMAND_WORD = "compareGroupCalendars";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": compare calendars within people with specified tags. "
            + "Parameters: "
            + "NONE/ONE/MULTIPLE VALID TAGS \n"
            + "Example: " + COMMAND_WORD + " "
            + "class friend";

    public static final String MESSAGE_SUCCESS = "Displaying available time periods";

    private final List<Tag> tagList;

    public CompareCalendarByTagCommand(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        ReadOnlyCalendar comparisonCalendar = lastShownList.stream().filter(person -> {
            return tagList.stream().anyMatch(person::hasTag);
        }).map(Person::getCalendar).reduce(model.getUnderlyingCalendar(), UniMateCalendar::combineCalendar);

        model.setComparisonCalendar(comparisonCalendar);

        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
