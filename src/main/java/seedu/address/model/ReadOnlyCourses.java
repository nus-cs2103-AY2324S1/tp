package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.course.Course;

/**
 * Unmodifiable view of courses
 */
public interface ReadOnlyCourses {

    /**
     * Returns an unmodifiable view of the courses list.
     * This list will not contain any duplicate courses.
     */
    ObservableList<Course> getCourseList();

}
