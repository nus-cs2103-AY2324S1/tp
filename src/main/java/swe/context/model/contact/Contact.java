package swe.context.model.contact;

import static swe.context.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import swe.context.commons.util.ToStringBuilder;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.tag.Tag;


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
    private Set<AlternateContact> alternates = new HashSet<>();

    /**
     * Constructs a Contact.
     */
    public Contact(
        Name _name,
        Phone _phone,
        Email _email,
        Note _note,
        Set<Tag> _tags,
        Set<AlternateContact> _alternates
    ) {
        requireAllNonNull(_name, _phone, _email, _note, _tags, _alternates);

        this.name = _name;
        this.phone = _phone;
        this.email = _email;
        this.note = _note;
        this.tags.addAll(_tags);
        this.alternates.addAll(_alternates);
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

    public Set<AlternateContact> getAlternates() {
        return Collections.unmodifiableSet(alternates);
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
     * Formats this for display to the user.
     */
    public static String format(Contact contact) {
        String tags = contact
                .getTags()
                .stream()
                .map(Tag::toString)
                .collect(Collectors.joining(", "));
        String alternates = contact
                .getAlternates()
                .stream()
                .map(AlternateContact::toString)
                .collect(Collectors.joining(", "));

        StringBuilder builder = new StringBuilder();
        builder.append(contact.getName())
                .append("; Phone: ")
                .append(contact.getPhone())
                .append("; Email: ")
                .append(contact.getEmail())
                .append("; Note: ")
                .append(contact.getNote())
                .append("; Tags: ")
                .append(tags)
                .append("; Alternate Contacts: ")
                .append(alternates);
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
                && tags.equals(otherContact.tags)
                && alternates.equals(otherContact.alternates);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, note, tags, alternates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("note", note)
                .add("tags", tags)
                .add("alternate contacts", alternates)
                .toString();
    }

}
