package seedu.address.model.partnercourse.comparator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.University;

/**
 * Comparator class to compare two PartnerCourses by University.
 */
public class PartnerCourseComparatorByUniversity implements Comparator<PartnerCourse> {
    @Override
    public int compare(PartnerCourse partnerCourse, PartnerCourse otherPartnerCourse) {
        requireAllNonNull(partnerCourse, otherPartnerCourse);
        University university = partnerCourse.getPartnerUniversity();
        University otherUniversity = otherPartnerCourse.getPartnerUniversity();
        int diff = university.getUniversityName().getValue()
                .compareToIgnoreCase(otherUniversity.getUniversityName().getValue());
        return Integer.compare(diff, 0);
    }

    @Override
    public String toString() {
        return "university";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PartnerCourseComparatorByUniversity)) {
            return false;
        }

        PartnerCourseComparatorByUniversity otherPartnerCourseComparatorByUniversity =
                (PartnerCourseComparatorByUniversity) other;
        return this.toString().equals(otherPartnerCourseComparatorByUniversity.toString());
    }
}
