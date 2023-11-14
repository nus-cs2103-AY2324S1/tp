package seedu.address.model.localcourse.comparator;

import java.util.Comparator;

import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalName;

/**
 * Comparator class to compare two LocalCourses by LocalName.
 */
public class LocalCourseComparatorByLocalName implements Comparator<LocalCourse> {
    @Override
    public int compare(LocalCourse localCourse, LocalCourse otherLocalCourse) {
        LocalName localName = localCourse.getLocalName();
        LocalName otherLocalName = otherLocalCourse.getLocalName();
        int diff = localName.getValue().compareToIgnoreCase(otherLocalName.getValue());
        return Integer.compare(diff, 0);
    }

    @Override
    public String toString() {
        return "localname";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LocalCourseComparatorByLocalName)) {
            return false;
        }

        LocalCourseComparatorByLocalName otherLocalCourseComparatorByLocalName =
                (LocalCourseComparatorByLocalName) other;
        return this.toString().equals(otherLocalCourseComparatorByLocalName.toString());
    }
}
