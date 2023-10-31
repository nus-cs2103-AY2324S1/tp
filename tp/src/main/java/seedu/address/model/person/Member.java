package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Represents a Member in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member extends Person {

    // Identity Fields
    private final Phone phone;
    private final Email email;
    private final Telegram telegram;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Member(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags) {
        super(name);
        requireAllNonNull(telegram);
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both members have the same name, phone, email and telegram handle.
     * This defines a weaker notion of equality between two members.
     */
    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;
        // members are considered the same if they have the same name, phone, email or telegram handle
        return getName().equals(otherMember.getName())
                || this.phone.equals(otherMember.phone)
                || this.email.equals(otherMember.email)
                || this.telegram.equals(otherMember.telegram);
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

        Member otherMember = (Member) other;
        return getName().equals(otherMember.getName())
                && getPhone().equals(otherMember.getPhone())
                && getEmail().equals(otherMember.getEmail())
                && getTags().equals(otherMember.getTags())
                && getTelegram().equals(otherMember.getTelegram());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTelegram(), getTags());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("telegram", getTelegram())
                .add("tags", getTags())
                .toString();
    }
}
