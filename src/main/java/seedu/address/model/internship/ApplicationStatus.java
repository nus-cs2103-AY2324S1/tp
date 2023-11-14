package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents an Internship's application status in Flagship.
 * Guarantees: immutable; is valid as declared in {@link #isValidApplicationStatus(String)}
 */
public class ApplicationStatus implements Comparable<ApplicationStatus> {

    public static final String MESSAGE_CONSTRAINTS =
            "The application status should fulfill all the following conditions:\n"
            + "1. Contains one of the following values: Yet to apply, Applied, In progress, Accepted, Rejected\n";

    private static final List<String> VALID_STATUSES = Arrays.asList(
            "Yet to apply", "Applied", "In progress", "Accepted", "Rejected"
    );

    private final String applicationStatus;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param applicationStatus A valid application status.
     */
    public ApplicationStatus(String applicationStatus) {
        requireNonNull(applicationStatus);
        checkArgument(isValidApplicationStatus(applicationStatus), MESSAGE_CONSTRAINTS);
        this.applicationStatus = applicationStatus;
    }

    /**
     * Verifies whether the given string constitutes a valid application status enum. The given string is also stripped
     * to defensively guard against instances where leading or trailing spaces are inserted when user directly modifies
     * the internship.json file. This is important so that the given string does not fail the regex check.
     *
     * @param test The given string to be tested.
     * @return A boolean representing whether the string input is valid.
     */
    public static boolean isValidApplicationStatus(String test) {
        String strippedTest = test.strip();
        return VALID_STATUSES.contains(strippedTest);
    }

    @Override
    public String toString() {
        return this.applicationStatus;
    }

    /**
     * Returns the raw string value of the Internship's application status. This method is used exclusively by the
     * filter command. We have both a toString() method and a getApplicationStatus() method to guard against any
     * potential changes to the toString() string output that will regress the functionalities of filter.
     *
     * @return The raw string value of the Internship's application status.
     */
    public String getApplicationStatus() {
        return this.applicationStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }

        ApplicationStatus otherApplicationStatus = (ApplicationStatus) other;
        return this.applicationStatus.equals(otherApplicationStatus.applicationStatus);
    }

    @Override
    public int hashCode() {
        return this.applicationStatus.hashCode();
    }

    @Override
    public int compareTo(ApplicationStatus other) {
        return this.applicationStatus.compareTo(other.applicationStatus);
    }
}
