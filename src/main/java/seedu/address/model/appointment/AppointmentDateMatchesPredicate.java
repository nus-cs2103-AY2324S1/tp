package seedu.address.model.appointment;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Appointment}'s date matches the given date.
 */
public class AppointmentDateMatchesPredicate implements Predicate<Appointment> {
    private final String date;

    public AppointmentDateMatchesPredicate(String date) {
        this.date = date;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getDate().getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentDateMatchesPredicate)) {
            return false;
        }

        AppointmentDateMatchesPredicate otherPredicate = (AppointmentDateMatchesPredicate) other;
        return date.equals(otherPredicate.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("date", date).toString();
    }
}
