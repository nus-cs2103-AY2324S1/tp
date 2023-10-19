package seedu.address.model.person;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Compares a {@code Person}'s {@code Appointment} to another {@code Person}'s {@code Appointment} to determine
 * appointment ordering.
 */
public class SortByAppointmentComparator implements Comparator<Person> {


    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAppointment().compareTo(o2.getAppointment());
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
