package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;

/**
 * Represents an Applicant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant extends Person {

    private final Phone phone;

    /**
     * Every field must be present and not null.
     *
     * @param name  The name of the applicant.
     * @param phone The phone number of the applicant.
     */
    public Applicant(Name name, Phone phone) {
        super(name);
        this.phone = phone;
    }

    /**
     * Returns true if both applicants have the same name and phone.
     * This defines a weaker notion of equality between two applicants.
     */
    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        // applicants are considered the same if they have the same name or phone
        return getName().equals(otherApplicant.getName())
                || this.phone.equals(otherApplicant.phone);
    }

    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return getName().equals(otherApplicant.getName())
                && this.phone.equals(otherApplicant.phone);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), phone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", phone)
                .toString();
    }

    @Override
    public String detailsToCopy() {
        return "Name: " + getName() + "\n"
                + "Phone: " + phone;
    }
}
