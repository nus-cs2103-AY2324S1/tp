package seedu.address.testutil;

import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;
/**
 * A utility class to help with building University objects.
 */
public class UniversityBuilder {
    public static final String DEFAULT_UNIVERSITY_NAME = "Seoul National University";

    private UniversityName universityName;

    /**
     * Creates a {@code UniversityBuilder} with the default details
     */
    public UniversityBuilder() {
        universityName = new UniversityName(DEFAULT_UNIVERSITY_NAME);
    }

    /**
     * Initialises the UniversityBuilder with the data of {@code universityToCopy}.
     */
    public UniversityBuilder(University universityToCopy) {
        universityName = universityToCopy.getUniversityName();
    }

    /**
     * Sets the {@code UniversityName} of the {@code University} that we are building.
     */
    public UniversityBuilder withUniversityName(String universityName) {
        this.universityName = new UniversityName(universityName);
        return this;
    }

    public University build() {
        return new University(universityName);
    }
}
