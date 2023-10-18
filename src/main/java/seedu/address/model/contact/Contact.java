package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;



/**
 * Represents a Contact.
 *
 * Guarantees that all details are present and validated.
 */
public class Contact {
    // Identity fields
    private Name name;
    private Phone phone;
    private Email email;

    // Data fields
    private Note note;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a Contact.
     */
    public Contact(
        Name _name,
        Phone _phone,
        Email _email,
        Note _note,
        Set<Tag> _tags
    ) {
        requireAllNonNull(_name, _phone, _email, _note, _tags);

        this.name = _name;
        this.phone = _phone;
        this.email = _email;
        this.note = _note;

        this.tags.addAll(_tags);
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Email getEmail() {
        return this.email;
    }

    public Note getNote() {
        return this.note;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both contacts have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getName().equals(getName());
    }

    /**
     * Formats the {@code contact} for display to the user.
     */
    public static String format(Contact contact) {
        final StringBuilder builder = new StringBuilder();
        builder.append(contact.getName())
                .append("; Phone: ")
                .append(contact.getPhone())
                .append("; Email: ")
                .append(contact.getEmail())
                .append("; Note: ")
                .append(contact.getNote())
                .append("; Tags: ");
        contact.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return name.equals(otherContact.name)
                && phone.equals(otherContact.phone)
                && email.equals(otherContact.email)
                && note.equals(otherContact.note)
                && tags.equals(otherContact.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, note, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("note", note)
                .add("tags", tags)
                .toString();
    }

}
