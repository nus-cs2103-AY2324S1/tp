package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;

/**
 * Reminds the user of the upcoming birthdays and events in the next n number of days.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reminds the user of the upcoming birthdays and events in the next n number of days. "
            + "If no NUM_OF_DAYS is given, the default number of days is 7.\n"
            + "Parameters: NUM_OF_DAYS (must be a positive integer and maximum of 999999999)\n"
            + "Example: " + COMMAND_WORD + " 1";



    public static final String MESSAGE_REMIND_SUCCESS =
            "Showing all birthdays and events happening in the next %1$s days:";

    private static final int DEFAULT_DAYS = 7;


    private final BirthdayWithinDaysPredicate birthdayPredicate;

    private final EventWithinDaysPredicate eventPredicate;

    private final int days;



    /**
     * Creates a RemindCommand to remind the user of the upcoming birthdays and events in the next n number of days.
     */
    public RemindCommand(BirthdayWithinDaysPredicate birthdayPredicate,
                         EventWithinDaysPredicate eventPredicate, int days) {
        this.birthdayPredicate = birthdayPredicate;
        this.eventPredicate = eventPredicate;
        if (days == 7) {
            this.days = DEFAULT_DAYS;
        } else {
            this.days = days;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Show everything first before filtering
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        // Events must be filtered before persons, if not persons will be filtered out of events
        model.updateFilteredEventList(eventPredicate);
        model.updateFilteredPersonList(birthdayPredicate);
        return new CommandResult(String.format(MESSAGE_REMIND_SUCCESS, days));
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
        return birthdayPredicate.equals(otherRemindCommand.birthdayPredicate)
                && eventPredicate.equals(otherRemindCommand.eventPredicate)
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
