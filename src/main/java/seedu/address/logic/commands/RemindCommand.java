package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;
import seedu.address.model.person.Person;

/**
 * Reminds the user of the upcoming birthdays and events in the next n number of days.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reminds the user of the upcoming birthdays and events in the next n number of days. "
            + "If no index is given, the default number of days is 7.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMIND_SUCCESS = "Showing all birthdays and events happening in the next %1$s days:";

    private final BirthdayWithinDaysPredicate birthdayPredicate;
    private final EventWithinDaysPredicate eventPredicate;
    private final int days;

    public RemindCommand(BirthdayWithinDaysPredicate birthdayPredicate,
                         EventWithinDaysPredicate eventPredicate, int days) {
        this.birthdayPredicate = birthdayPredicate;
        this.eventPredicate  = eventPredicate;
        this.days = days;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(birthdayPredicate);
        model.updateFilteredEventList(eventPredicate);
        return new CommandResult(String.format(MESSAGE_REMIND_SUCCESS, days));
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
        return birthdayPredicate == otherRemindCommand.birthdayPredicate
                && eventPredicate == otherRemindCommand.eventPredicate
                && days == otherRemindCommand.days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("birthdayPredicate", birthdayPredicate)
                .add("eventPredicate", eventPredicate)
                .add("days", days)
                .toString();
    }

}
