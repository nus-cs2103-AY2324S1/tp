package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns the list containing the selected student.
     * The list only has one selected student.
     */
    ObservableList<Student> getSelectedStudent();

}
