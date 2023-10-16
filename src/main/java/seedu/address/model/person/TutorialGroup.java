package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's TutorialGroup.
 * Guarantees: immutable; is valid as declared in {@link #isValidTG(String)}
 */
public class TutorialGroup {

    public static final String MESSAGE_CONSTRAINTS = "Tutorial group should only contain alphanumeric "
            + "characters, with no special characters or space, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]*$";

    public final String value;
    /**
     * Constructs an {@code Tutorial Group}.
     *
     * @param group A valid Tutorial Group.
     */
    public TutorialGroup(String group) {
        checkArgument(isValidTG(group), MESSAGE_CONSTRAINTS);
        value = group;
    }

    /**
     * Returns true if a given string is a valid ID.
     */
    public static boolean isValidTG(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroup // instanceof handles nulls
                && value.equals(((TutorialGroup) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
