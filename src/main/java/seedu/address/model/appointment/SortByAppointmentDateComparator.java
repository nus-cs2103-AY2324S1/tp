package seedu.address.model.appointment;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Compares an {@code Appointment} to another {@code Appointment} to determine
 * appointment ordering.
 */
public class SortByAppointmentDateComparator implements Comparator<Appointment> {


    @Override
    public int compare(Appointment apt1, Appointment apt2) {
        if (apt1.getDateTime().isBefore(apt2.getDateTime())) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortByAppointmentDateComparator)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
