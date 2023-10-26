package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Group's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class GroupRemark {
    public final String value;

    public GroupRemark(String groupRemark) {
        requireNonNull(groupRemark);
        value = groupRemark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupRemark // instanceof handles nulls
                && value.equals(((GroupRemark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}