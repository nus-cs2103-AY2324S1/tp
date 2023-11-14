package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.localcourse.LocalCourse;

/**
 * Unmodifiable view of local course catalogue.
 */
public interface ReadOnlyLocalCourseCatalogue {

    /**
     * Returns an unmodifiable view of the local course list.
     * This list will not contain any duplicate local courses.
     * ObservableList will aid interoperability.
     */
    ObservableList<LocalCourse> getLocalCourseList();

}
