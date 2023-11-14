package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.shared.DateTime;
import seedu.address.model.shared.Name;
import seedu.address.model.shared.Nric;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Appointment {

    // Identity fields
    private final Name name;
    private final DateTime dateTime;
    private final Nric nric;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Name name, DateTime dateTime, Nric nric) {
        requireAllNonNull(name, dateTime);
        this.name = name;
        this.dateTime = dateTime;
        this.nric = nric;
    }

    public Name getName() {
        return name;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Nric getNric() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return name.equals(otherAppointment.name)
                && dateTime.equals(otherAppointment.dateTime)
                && ((nric == null && otherAppointment.nric == null) || nric.equals(otherAppointment.nric));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, dateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("dateTime", dateTime)
                .add("nric", nric)
                .toString();
    }
}
