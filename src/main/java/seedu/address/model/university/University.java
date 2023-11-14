package seedu.address.model.university;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.SeplendidDataType;

/**
 * Represents a University in SEPlendid.
 * Guarantees: details are present and not null, field values are validated and immutable.
 */
public class University implements SeplendidDataType {
    private final UniversityName universityName;

    /**
     * Every field must be present and not null
     */
    public University(UniversityName uN) {
        requireAllNonNull(uN);
        universityName = uN;
    }

    //Getters
    public UniversityName getUniversityName() {
        return universityName;
    }

    @Override
    public String toString() {
        return universityName.toString();
    }

    /**
     * Returns true if both Universities have the same universities.
     * Assumption: university is the primary key i.e. there exists
     * no two distinct University with the same university name.
     *
     * @param otherUniversity This is the other university.
     */
    public boolean isSameUniversity(University otherUniversity) {
        if (otherUniversity == this) {
            return true;
        }
        return otherUniversity != null
                && otherUniversity.getUniversityName().equals(getUniversityName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof University)) {
            return false;
        }

        University otherUniversity = (University) other;
        return universityName.equals(otherUniversity.universityName);
    }
}
