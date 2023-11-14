package seedu.address.model.partnercourse.comparator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerName;

/**
 * Comparator class to compare two PartnerCourses by PartnerName.
 */
public class PartnerCourseComparatorByPartnerName implements Comparator<PartnerCourse> {
    @Override
    public int compare(PartnerCourse partnerCourse, PartnerCourse otherPartnerCourse) {
        requireAllNonNull(partnerCourse, otherPartnerCourse);
        PartnerName partnerName = partnerCourse.getPartnerName();
        PartnerName otherPartnerName = otherPartnerCourse.getPartnerName();
        int diff = partnerName.getValue().compareToIgnoreCase(otherPartnerName.getValue());
        return Integer.compare(diff, 0);
    }

    @Override
    public String toString() {
        return "partnername";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseComparatorByPartnerName)) {
            return false;
        }

        PartnerCourseComparatorByPartnerName otherPartnerCourseComparatorByPartnerName =
                (PartnerCourseComparatorByPartnerName) other;
        return this.toString().equals(otherPartnerCourseComparatorByPartnerName.toString());
    }
}
