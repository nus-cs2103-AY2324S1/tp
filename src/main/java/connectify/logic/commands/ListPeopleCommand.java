package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import connectify.model.InvalidEntityException;
import connectify.model.Model;
import connectify.model.person.Person;
import connectify.model.person.PersonNameComparator;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPeopleCommand extends Command {
    public static final String COMMAND_WORD = "people";
    public static final String MESSAGE_SUCCESS = "Listed all persons.";
    public static final String EMPTY_LIST_MESSAGE = "There are no people in Connectify.";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        Comparator<Person> comparator = new PersonNameComparator();
        model.updateSortedPersonList(comparator);
        Integer size = model.getNumberOfPeople();
        try {
            model.setCurrEntity("people");
        } catch (InvalidEntityException e) {
            throw new AssertionError("An exception was thrown when setting the current entity to 'people'");
        }

        if (size == 0) {
            return new CommandResult(EMPTY_LIST_MESSAGE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof ListPeopleCommand)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "ListPeopleCommand";
    }

}
