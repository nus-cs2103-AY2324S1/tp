package seedu.address.model.appointment;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Checks if an {@code Appointment} has a patient or doctor, matching
 * the query Ic {@code keywords}.
 */
public class AppointmentIcPredicate implements Predicate<Appointment> {
    private final String keywords;

    public AppointmentIcPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        if (keywords.equalsIgnoreCase(appointment.getPatient().toString())) {
            return true;
        }
        return keywords.equalsIgnoreCase(appointment.getDoctor().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentIcPredicate)) {
            return false;
        }

        AppointmentIcPredicate otherAppointmentIcPredicate = (AppointmentIcPredicate) other;
        return keywords.equals(otherAppointmentIcPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

