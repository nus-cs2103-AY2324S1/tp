package seedu.address.model.musician;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Musician in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Musician {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Tag> instruments = new HashSet<>();
    private final Set<Tag> genres = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Musician(Name name, Phone phone, Email email, Set<Tag> tags, Set<Tag> instrumentTags,
                    Set<Tag> genreTags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.instruments.addAll(instrumentTags);
        this.genres.addAll(genreTags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Set<Tag> getInstruments() {
        return Collections.unmodifiableSet(instruments);
    }

    public Set<Tag> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameMusician(Musician otherMusician) {
        if (otherMusician == this) {
            return true;
        }

        return otherMusician != null
                && otherMusician.getName().equals(getName());
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
        if (!(other instanceof Musician)) {
            return false;
        }

        Musician otherMusician = (Musician) other;
        return name.equals(otherMusician.name)
                && phone.equals(otherMusician.phone)
                && email.equals(otherMusician.email)
                && tags.equals(otherMusician.tags)
                && instruments.equals(otherMusician.instruments)
                && genres.equals(otherMusician.genres);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags, instruments, genres);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("tags", tags)
                .add("instruments", instruments)
                .add("genres", genres)
                .toString();
    }

}
