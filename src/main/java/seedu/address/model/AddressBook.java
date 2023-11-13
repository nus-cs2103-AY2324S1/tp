package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.UniqueAssignmentList;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.UniqueTutorialList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueModuleList modules;
    private final UniqueTutorialList tutorials;
    private final UniqueAssignmentList assignments;
    private List<Tag> attendanceTags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        modules = new UniqueModuleList();
        tutorials = new UniqueTutorialList();
        assignments = new UniqueAssignmentList();
        attendanceTags = new ArrayList<>();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with {@code modules}.
     * {@code modules} must not contain duplicate persons.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Replaces the contents of the person list with {@code tutorials}.
     * {@code tutorials} must not contain duplicate persons.
     */
    public void setTutorials(List<Tutorial> tutorials) {
        this.tutorials.setTutorials(tutorials);
    }

    /**
     * Replaces the contents of the person list with {@code assignments}.
     * {@code assignments} must not contain duplicate persons.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Replaces the contents of the person list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAttendanceTags(List<Tag> attendanceTags) {
        for (Tag tag : attendanceTags) {
            this.attendanceTags.add(tag);
        }
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setModules(newData.getModuleList());
        setTutorials(newData.getTutorialList());
        setAssignments(newData.getAssignmentList());
        setAttendanceTags(newData.getAttendanceTagsList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// module-level operations

    /**
     * Adds a {@code module} to the address book.
     * The module must not already exist in the address book.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Delete a {@code module} to the address book.
     * The module must already exist in the address book.
     */
    public void removeModule(Module module) {
        removeTutorialWithDeletedModule(module.getModuleCode());
        modules.remove(module);
    }

    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    //// tutorial-level operations

    /**
     * Adds a {@code tutorial} to the address book.
     * The tutorial must not already exist in the address book.
     */
    public void addTutorial(Tutorial tutorial) {
        tutorials.add(tutorial);
    }

    /**
     * Delete a {@code tutorial} from the address book.
     * The tutorial must already exist in the address book.
     */
    public void removeTutorial(Tutorial tutorial) {
        tutorials.remove(tutorial);
    }

    /**
     * Delete a tutorial that contains a deleted Module from the address book.
     */
    public void removeTutorialWithDeletedModule(String deletedModuleCode) {
        List<Tutorial> toBeRemoved = new ArrayList<>();

        // Stores the tutorials which are to be removed in another list
        for (Tutorial tutorial : tutorials) {
            if (tutorial.getModuleCode().equals(deletedModuleCode)) {
                toBeRemoved.add(tutorial);
            }
        }

        for (Tutorial tutorial : toBeRemoved) {
            removeTutorial(tutorial);
        }
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.contains(tutorial);
    }

    //// assignment-level operations

    /**
     * Adds a assignment to the address book.
     * The assignment must not already exist in the address book.
     */
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Delete a assignment from the address book.
     * The assignment must already exist in the address book.
     */
    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    //// attendance tags operations

    /**
     * Adds a attendance tag to the address book.
     * The tag must not already exist in the address book.
     */
    public void addAttendanceTag(Tag tag) {
        attendanceTags.add(tag);
    }

    /**
     * Delete a attendance tag from the address book.
     * The tag must already exist in the address book.
     */
    public void deleteAttendanceTag(Tag tag) {
        attendanceTags.remove(tag);
    }

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the address book.
     */
    public boolean hasAttendanceTag(Tag tag) {
        requireNonNull(tag);
        return attendanceTags.contains(tag);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tutorial> getTutorialList() {
        return tutorials.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    @Override
    public List<Tag> getAttendanceTagsList() {
        return attendanceTags;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
