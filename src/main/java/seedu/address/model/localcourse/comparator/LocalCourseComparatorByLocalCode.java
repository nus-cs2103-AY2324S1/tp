package seedu.address.model.localcourse.comparator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;

/**
 * Comparator class to compare two LocalCourses by LocalCode.
 */
public class LocalCourseComparatorByLocalCode implements Comparator<LocalCourse> {

    @Override
    public int compare(LocalCourse localCourse, LocalCourse otherLocalCourse) {
        requireAllNonNull(localCourse, otherLocalCourse);
        LocalCode localCode = localCourse.getLocalCode();
        LocalCode otherLocalCode = otherLocalCourse.getLocalCode();
        int diff = localCode.getValue().compareToIgnoreCase(otherLocalCode.getValue());
        return Integer.compare(diff, 0);
    }

    @Override
    public String toString() {
        return "localcode";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LocalCourseComparatorByLocalCode)) {
            return false;
        }

        LocalCourseComparatorByLocalCode otherLocalCourseComparatorByLocalCode =
                (LocalCourseComparatorByLocalCode) other;
        return this.toString().equals(otherLocalCourseComparatorByLocalCode.toString());
    }
}
