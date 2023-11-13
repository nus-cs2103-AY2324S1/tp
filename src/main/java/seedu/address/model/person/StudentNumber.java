package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's student number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentNumber(String)}
 */
public class StudentNumber {


    public static final String MESSAGE_CONSTRAINTS =
            "Student number should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+$";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param studentNumber A valid student number.
     */
    public StudentNumber(String studentNumber) {
        requireNonNull(studentNumber);
        checkArgument(isValidStudentNumber(studentNumber), MESSAGE_CONSTRAINTS);
        value = studentNumber;
    }

    /**
     * Returns true if a given string is a valid Student number.
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

        StudentNumber otherStudentNumber = (StudentNumber) other;
        return value.equals(otherStudentNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

