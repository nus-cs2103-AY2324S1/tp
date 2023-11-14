package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import connectify.model.Model;
import connectify.model.person.Person;
import connectify.model.person.PersonPriorityComparator;

/**
 * Lists all persons in the address book to the user according to their priority.
 */
public class RankPersonCommand extends Command {

    public static final String COMMAND_WORD = "rank";

    public static final String MESSAGE_SUCCESS = "Ranked all persons";

    public static final String EMPTY_LIST_MESSAGE = "There are no persons in Connectify";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        Comparator<Person> comparator = new PersonPriorityComparator();
        model.updateSortedPersonList(comparator);
        Integer size = model.getNumberOfPeople();
        if (size == 0) {
            return new CommandResult(EMPTY_LIST_MESSAGE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof RankPersonCommand) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "RankPersonCommand";
    }

}
