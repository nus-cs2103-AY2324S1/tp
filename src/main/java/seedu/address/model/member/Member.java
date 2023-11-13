package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Member in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidName(String)}
 */
public class Member {

    public static final String MESSAGE_CONSTRAINTS = "Member names should not be blank or contain /";
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[^/]*$";

    public final String memberName;

    /**
     * Constructs a {@code Member}.
     *
     * @param memberName A valid Member name.
     */
    public Member(String memberName) {
        requireNonNull(memberName);
        checkArgument(isValidName(memberName), MESSAGE_CONSTRAINTS);
        this.memberName = memberName;
    }

    /**
     * Checks if a given string is a valid member name.
     * @param test String to be tested for validity.
     * @return True if the string is a valid member name, false otherwise.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;
        return memberName.equals(otherMember.memberName);
    }

    @Override
    public int hashCode() {
        return memberName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + memberName + ']';
    }

}
