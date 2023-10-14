package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Member in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member extends Person {

    // Identity Fields
    private final Telegram telegram;

    /**
     * Every field must be present and not null.
     */
    public Member(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags) {
        super(name, phone, email, new Address("sample"), tags);
        requireAllNonNull(telegram);
        this.telegram = telegram;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    /**
     * Returns true if both members have the same telegram handle.
     * This defines a weaker notion of equality between two members.
     */
    public boolean isSameMember(Member otherMember) {
        if (otherMember == this) {
            return true;
        }

        return otherMember != null
                && otherMember.getTelegram().equals(getTelegram());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Member)) {
            return false;
        }

        Member otherPerson = (Member) other;
        return getName().equals(otherPerson.getName())
                && getPhone().equals(otherPerson.getPhone())
                && getEmail().equals(otherPerson.getEmail())
                && getAddress().equals(otherPerson.getAddress())
                && getTags().equals(otherPerson.getTags())
                && getTelegram().equals(otherPerson.getTelegram());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getTelegram(), getTags());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("telegram", getTelegram())
                .add("tags", getTags())
                .toString();
    }
}
