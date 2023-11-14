package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Grade Point Average (Gpa) in the applicant list.
 * Guarantees: immutable; is valid as declared in {@link #isValidGpa(double)}
 */
public class Gpa implements Comparable<Gpa> {

    public static final String MESSAGE_CONSTRAINTS = "Gpa can take values from 0.00 to 5.00, and cannot be blank";

    public final double value;

    /**
     * Constructs a {@code Gpa}.
     *
     * @param gpa A possible gpa of a person.
     */
    public Gpa(double gpa) {
        requireNonNull(gpa);
        checkArgument(isValidGpa(gpa), MESSAGE_CONSTRAINTS);
        value = gpa;
    }

    /**
     * Returns true if a given double is a valid Gpa.
     */
    public static boolean isValidGpa(double test) {
        return test >= 0.0 && test <= 5.0;
    }

    /**
     * Compares this GPA with another GPA and returns an integer representing the comparison.
     *
     * @param other The other Gpa object to compare with.
     * @return A negative value if this GPA is less than the other GPA, zero if they are equal,
     *         and a positive value if this GPA is greater than the other GPA.
     */
    @Override
    public int compareTo(Gpa other) {
        return Double.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Gpa)) {
            return false;
        }

        Gpa otherGpa = (Gpa) other;
        return value == otherGpa.value;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
