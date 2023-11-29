package seedu.address.model.person.dentist;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a dentist's Years of Expetience (YoE) in ToothTracker address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYoe(String)}
 */
public class Yoe {
    public static final String FULL_CLASS_NAME = "Years of Experience";
    public static final String MESSAGE_CONSTRAINTS =
            "Years of Experience (YOE) should only contain numbers, and it should be at most 2 digits long";
    // Solution on VALIDATION_REGEX inspired and adapted from
    // https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/model/person/doctor/Yoe.java
    private static final String VALIDATION_REGEX = "^0*[0-9]{0,2}$";
    private final String value;

    /**
     * Constructs a {@code YOE}.
     *
     * @param yearsOfExperience A valid number of years.
     */
    public Yoe(String yearsOfExperience) {
        requireNonNull(yearsOfExperience);
        checkArgument(isValidYoe(yearsOfExperience), MESSAGE_CONSTRAINTS);
        if (yearsOfExperience.equals("0") || yearsOfExperience.equals("00")) {
            value = "0";
        } else {
            //Check for cases like "0000000000", Trim then check if it is empty string, if yes -> ERROR
            value = yearsOfExperience.replaceFirst("^0+(?!S)", "");
        }
    }

    /**
     * Returns true if a given string is a valid format for Years of Experience.
     * @param test The input of years of experience to be tested.
     */
    public static boolean isValidYoe(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        if (test.equals("0") || test.equals("00")) {
            return true;
        } else {
            String trimmedTest = test.replaceFirst("^0+(?!S)", "");
            return !trimmedTest.equals("");
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // Short Circuit
                || (other instanceof Yoe)
                && value.equals(((Yoe) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }


}
