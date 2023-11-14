package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's student number in the applicant list.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentNumber(String)}
 */
public class StudentNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Student numbers should look like A0123456G. 1 letter, 7 digits, then 1 more letter.";
    public static final String VALIDATION_REGEX = "[A-Z]\\d{7}[A-Z]";

    public final String value;

    /**
     * Constructs a {@code StudentNumber}.
     *
     * @param studentNo A valid student number.
     */
    public StudentNumber(String studentNo) {
        requireNonNull(studentNo);
        checkArgument(isValidStudentNumber(studentNo), MESSAGE_CONSTRAINTS);
        value = studentNo;
    }

    /**
     * Returns true if a given string is a valid student number.
     */
    public static boolean isValidStudentNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentNumber)) {
            return false;
        }

        StudentNumber otherStudentNo = (StudentNumber) other;
        return value.equals(otherStudentNo.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
