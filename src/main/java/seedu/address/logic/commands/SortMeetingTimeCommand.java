package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists persons in the address book sorted by their meeting times.
 */
public class SortMeetingTimeCommand extends Command {

    public static final String COMMAND_WORD = "sortmeeting";

    public static final String MESSAGE_SUCCESS = "Sorted all meeting times chronologically";

    public static final Predicate<Person> PREDICATE_HAS_MEETING_TIME = person -> person.getMeetingTime().isPresent();
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
