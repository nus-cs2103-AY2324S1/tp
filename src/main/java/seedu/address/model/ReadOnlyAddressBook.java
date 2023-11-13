package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the tutorials list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Tutorial> getTutorialList();

    ObservableList<Assignment> getAssignmentList();

    List<Tag> getAttendanceTagsList();
}
