package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.unique.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueList<Assignment> assignments;

    private final UniqueList<InternshipRole> internshipRoles;

    private final UniqueList<InternshipTask> internshipTasks;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        assignments = new UniqueList<>();
        internshipRoles = new UniqueList<>();
        internshipTasks = new UniqueList<>();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the assignments list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setList(assignments);
    }

    public void setInternshipRoles(List<InternshipRole> internRoles) {
        this.internshipRoles.setList(internRoles);
    }

    public void setInternshipTasks(List<InternshipTask> internshipTasks) {
        this.internshipTasks.setList(internshipTasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setAssignments(newData.getAssignmentList());
        setInternshipRoles(newData.getInternshipRoleList());
        setInternshipTasks(newData.getInternshipTaskList());
    }

    /**
     * @param assignment to be checked
     * @return true if assignment is present, false otherwise
     */
    // Assignments
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void removeAssignment(Assignment key) {
        assignments.remove(key);
    }


    /**
     * Sort assignments by endDate
     */
    public void sortAssignments() {
        assignments.sort(Comparator.comparing(Assignment::getEnd));
    }


    /**
     * Edits the target assignment's description
     *
     * @param newAssignment to replace target
     */
    public void editAssignment(Assignment target, Assignment newAssignment) {
        requireNonNull(target);
        assignments.set(target, newAssignment);
    }

    // ================ Internship Roles =====================================================================

    /**
     * Add an internship role
     * @param role to be added
     */
    public void addInternshipRole(InternshipRole role) {
        requireNonNull(role);
        internshipRoles.add(role);
    }

    public void removeInternshipRole(InternshipRole key) {
        internshipRoles.remove(key);
    }

    /**
     * Verify if the given role is in the list
     * @param role to be checked
     * @return true if role is present
     */
    public boolean hasInternshipRole(InternshipRole role) {
        requireNonNull(role);
        return internshipRoles.contains(role);
    }

    @Override
    public ObservableList<InternshipRole> getInternshipRoleList() {
        return internshipRoles.asUnmodifiableObservableList();
    }
    // ================ Internship Tasks =====================================================================

    /**
     * @param internshipTask to be checked
     * @return true if internship task is present, false otherwise
     */
    public boolean hasInternshipTask(InternshipTask internshipTask) {
        requireNonNull(internshipTask);
        return internshipTasks.contains(internshipTask);
    }

    @Override
    public ObservableList<InternshipTask> getInternshipTaskList() {
        return internshipTasks.asUnmodifiableObservableList();
    }

    /**
     * Adds an internship task to the list of internship tasks, checking if the role exists first
     * @param internshipTask
     */
    public void addInternshipTask(InternshipTask internshipTask) {
        assert internshipRoles.contains(internshipTask.getInternshipRole());
        internshipTasks.add(internshipTask);
    }

    public void removeInternshipTask(InternshipTask key) {
        internshipTasks.remove(key);
    }

    /**
     * Sort assignments by endDate
     */
    public void sortInternshipTasks() {
        internshipTasks.sort(Comparator.comparing(InternshipTask::getDeadline));
    }

    /**
     * Replaces task with newTask as InternshipTasks are immutable, and as such any changes made will have to be
     * reflected in the internshipTasks list through replaing the old InternshipTask
     *
     * @param newTask to replace target
     */
    public void setInternshipTask(InternshipTask task, InternshipTask newTask) {
        requireNonNull(task);
        internshipTasks.set(task, newTask);
    }

    public void setInternshipRole(InternshipRole role, InternshipRole newRole) {
        requireNonNull(role);
        internshipRoles.set(role, newRole);
    }

    //util methods
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("assignments", assignments)
                .add("internshipRoles", internshipRoles)
                .add("internshipTasks", internshipTasks)
                .toString();
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
        return assignments.equals(otherAddressBook.assignments)
                && internshipRoles.equals(otherAddressBook.internshipRoles)
                && internshipTasks.equals(otherAddressBook.internshipTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignments, internshipRoles, internshipTasks);
    }
}
