package seedu.classmanager.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.classmanager.model.student.Student;

/**
 * Unmodifiable view of a Class Manager
 */
public interface ReadOnlyClassManager extends Observable {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns the list containing the selected student.
     * The list only has one selected student.
     */
    ObservableList<Student> getObservableSelectedStudent();

    /**
     * Returns the selected student.
     */
    Student getSelectedStudent();
}
