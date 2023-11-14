package seedu.address.model.university.comparator;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

/**
 * Comparator class to compare two Universities by UniversityName.
 */
public class UniversityComparatorByUniversityName implements Comparator<University> {
    @Override
    public int compare(University university, University otherUniversity) {
        requireAllNonNull(university, otherUniversity);
        UniversityName universityName = university.getUniversityName();
        UniversityName otherUniversityName = otherUniversity.getUniversityName();
        int diff = universityName.getValue().compareToIgnoreCase(otherUniversityName.getValue());
        return Integer.compare(diff, 0);
    }

    @Override
    public String toString() {
        return "universityname";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniversityComparatorByUniversityName)) {
            return false;
        }

        UniversityComparatorByUniversityName otherUniversityComparatorByUniversityName =
                (UniversityComparatorByUniversityName) other;
        return this.toString().equals(otherUniversityComparatorByUniversityName.toString());
    }
}
