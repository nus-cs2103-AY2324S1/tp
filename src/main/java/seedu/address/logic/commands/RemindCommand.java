package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Reminds the user of the upcoming birthdays and events in the next n number of days.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reminds the user of the upcoming birthdays and events in the next n number of days.\n"
            + "If no index is given, the default number of days is 7.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMIND_SUCCESS = "Here are the birthdays and events happening in the next %1$s days: \n\n"
            + "Birthdays: \n" + "%2$s\n" + "Events: \n" + "%3$s";

    private final int days;

    public RemindCommand(int days) {
        this.days = days;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (days < 0) {
            throw new CommandException("Number of days cannot be negative.");
        }
        Set<Person> persons = model.findPersonsWithUpcomingBirthdays(days);
        Set<Event> events = model.findEventsWithUpcomingDates(days);
        String birthdaysStrings = getBirthdays(persons);
        String eventsStrings = getEvents(events);
        return new CommandResult(String.format(MESSAGE_REMIND_SUCCESS, days, birthdaysStrings, eventsStrings));
    }

    private String getBirthdays(Set<Person> persons) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Person person : persons) {
            sb.append(count + ". ");
            sb.append(person.getName() + ": ");
            sb.append(person.getBirthday().toString() + "\n");
            count++;
        }
        return sb.toString();
    }

    private String getEvents(Set<Event> events) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Event event : events) {
            sb.append(count + ". ");
            sb.append(event.getName().toString() + ": ");
            sb.append(event.getStartDate().toString() + "\n");
            count++;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemindCommand)) {
            return false;
        }

        RemindCommand otherRemindCommand = (RemindCommand) other;
        return days == otherRemindCommand.days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("days", days)
                .toString();
    }

}
