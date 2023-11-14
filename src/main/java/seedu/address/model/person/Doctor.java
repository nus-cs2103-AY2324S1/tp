package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Extends abstract class {@code Person}
 */
public class Doctor extends Person {

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Address address, Remark remark, Gender gender,
                  Ic ic, Set<Appointment> appointments, Set<Tag> tags) {
        super(name, phone, email, address, remark, gender, ic, appointments, tags);
    }

    /**
     * Returns true if person is a {@code Doctor}.
     */
    @Override
    public boolean isDoctor() {
        return true;
    }

    /**
     * Returns true if person is a {@code Patient}.
     */
    @Override
    public boolean isPatient() {
        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, ic, appointments, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("gender", gender)
                .add("nric", ic)
                .add("appointments", appointments)
                .add("tags", tags)
                .toString();
    }

}

