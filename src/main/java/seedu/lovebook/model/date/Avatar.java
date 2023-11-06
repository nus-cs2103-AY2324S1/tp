package seedu.lovebook.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date's avatar in the lovebook.
 * Guarantees: immutable; is always valid
 */
public class Avatar {
    public static final String MESSAGE_CONSTRAINTS = "Avatar should only contain numbers from 1 to 9.";
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 10;

    public final String value;

    /**
     * Constructor for the avatar class
     * @param value the avatar number
     */
    public Avatar(String value) {
        requireNonNull(value);
        checkArgument(isValidAvatar(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Constructor for the avatar class with a random number
     */
    public Avatar() {
        value = String.valueOf((int) (Math.random() * (UPPER_BOUND - LOWER_BOUND)) + LOWER_BOUND);
    }

    /**
     * Returns the avatar number
     * @return the avatar number
     */
    public String getAvatarNumber() {
        return value;
    }

    /**
     * Returns true if the avatar number is valid.
     */
    public static boolean isValidAvatar(String avatar) {
        requireNonNull(avatar);

        int avatarNumber = Integer.parseInt(avatar);
        return avatarNumber >= LOWER_BOUND && avatarNumber < UPPER_BOUND;
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
        if (!(other instanceof Avatar)) {
            return false;
        }

        Avatar otherAvatar = (Avatar) other;
        return value.equals(otherAvatar.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
