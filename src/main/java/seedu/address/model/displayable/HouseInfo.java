package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.validateArgument;

/**
 * Info relating to a house.
 * Guarantees: immutable; is valid as declared in {@link #isValidHouseInfo(String)}
 */
public class HouseInfo {
    public static final String MESSAGE_CONSTRAINTS =
        "House information can take any values, and it should not be blank";

    public static final String MESSAGE_RECOMMENDATIONS = MESSAGE_CONSTRAINTS;

    /*
     * The first character of the info must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String AFFIRMATION_REGEX = VALIDATION_REGEX;
    public static final String DEFAULT_HOUSE_INFO = "Placeholder house";
    public final String info;

    /**
     * Creates a valid {@code HouseInfo}.
     * @param info a valid info text.
     */
    public HouseInfo(String info) {
        requireNonNull(info);
        validateArgument(isValidHouseInfo(info), MESSAGE_CONSTRAINTS);
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HouseInfo)) {
            return false;
        }

        HouseInfo otherHouseInfo = (HouseInfo) other;
        return info.equals(otherHouseInfo.info);
    }
    public static boolean isValidHouseInfo(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public static boolean isAppropriateHouseInfo(String test) {
        return test.matches(AFFIRMATION_REGEX);
    }
    @Override
    public int hashCode() {
        return info.hashCode();
    }
}
