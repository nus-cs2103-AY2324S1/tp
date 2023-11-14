package seedu.address.model.contact;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Recruiter in the address book.
 * Guarantees: Guarantees: name and id are present and not null,
 * field values are immutable and if present, are validated.
 */
public class Recruiter extends Contact {

    public static final String MESSAGE_INVALID_ORGANIZATION =
            "If a recruiter is linked to an organization, "
                    + "the linked organization should be present in the address book "
                    + "and have a valid id";

    /**
     * Name and id fields must be non-null.
     * Tags must be non-null but can be empty as well.
     * The other fields can be null.
     */
    public Recruiter(Name name, Id id, Phone phone, Email email, Url url, Address address, Set<Tag> tags,
                     Organization organization) {
        super(name, id, phone, email, url, address, tags, organization);
    }

    public Optional<Id> getOrganizationId() {
        return getOrganization().map(Contact::getId);
    }

    public Optional<Organization> getOrganization() {
        return super.getParent().map(contact -> (Organization) contact);
    }

    @Override
    public Type getType() {
        return Type.RECRUITER;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls implicitly
        if (!(other instanceof Recruiter)) {
            return false;
        }

        Recruiter otherContact = (Recruiter) other;
        return getId().equals(otherContact.getId())
                && getType().equals(otherContact.getType())
                && getName().equals(otherContact.getName())
                && getPhone().equals(otherContact.getPhone())
                && getEmail().equals(otherContact.getEmail())
                && getAddress().equals(otherContact.getAddress())
                && getUrl().equals(otherContact.getUrl())
                && getTags().equals(otherContact.getTags())
                && getOrganization().equals(otherContact.getOrganization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), getType(), getName(), getPhone(), getEmail(), getAddress(), getTags(), getOrganization()
        );
    }

    @Override
    public ToStringBuilder toStringBuilder() {
        return super.toStringBuilder()
                .add("organization", getOrganization());
    }
}
