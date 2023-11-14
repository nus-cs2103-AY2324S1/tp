package seedu.address.model.partnercourse.comparator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * Comparator class to compare two PartnerCourses by PartnerCode.
 */
public class PartnerCourseComparatorByPartnerCode implements Comparator<PartnerCourse> {
    @Override
    public int compare(PartnerCourse partnerCourse, PartnerCourse otherPartnerCourse) {
        requireAllNonNull(partnerCourse, otherPartnerCourse);
        PartnerCode partnerCode = partnerCourse.getPartnerCode();
        PartnerCode otherPartnerCode = otherPartnerCourse.getPartnerCode();
        int diff = partnerCode.getValue().compareToIgnoreCase(otherPartnerCode.getValue());
        return Integer.compare(diff, 0);
    }

    @Override
    public String toString() {
        return "partnercode";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseComparatorByPartnerCode)) {
            return false;
        }

        PartnerCourseComparatorByPartnerCode otherPartnerCourseComparatorByPartnerCode =
                (PartnerCourseComparatorByPartnerCode) other;
        return this.toString().equals(otherPartnerCourseComparatorByPartnerCode.toString());
    }
}
