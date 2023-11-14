package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import connectify.model.InvalidEntityException;
import connectify.model.Model;
import connectify.model.person.Person;
import connectify.model.person.PersonNameComparator;

/**
 * Lists all entities (both persons and companies) in the address book to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons and companies";

    public static final String EMPTY_LIST_MESSAGE = "There are no entities in Connectify";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        Comparator<Person> comparator = new PersonNameComparator();
        model.updateSortedPersonList(comparator);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        try {
            model.setCurrEntity("all");
        } catch (InvalidEntityException e) {
            throw new AssertionError("An exception was thrown when setting the current entity to 'all'");
        }
        if (model.isEmpty()) {
            return new CommandResult(EMPTY_LIST_MESSAGE);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof ListAllCommand) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "ListAllCommand";
    }
}
