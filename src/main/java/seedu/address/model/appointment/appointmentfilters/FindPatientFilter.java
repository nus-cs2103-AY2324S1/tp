package seedu.address.model.appointment.appointmentfilters;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.appointment.Appointment;

import java.util.List;
import java.util.function.Predicate;

public class FindPatientFilter implements Predicate<Appointment> {

    private final List<String> KEYWORDS;

    public FindPatientFilter(List<String> keywords) {
        this.KEYWORDS = keywords;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param appointment the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Appointment appointment) {
        String patientName = appointment.getPatientName().toString();
        return KEYWORDS.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(patientName, keyword));
    }

    @Override
    public boolean equals(Object other) {

        // Check whether they are the same object
        if (this == other) {
            return true;
        }

        if (other instanceof FindPatientFilter) {
            FindPatientFilter otherFilter = (FindPatientFilter) other;

            return this.KEYWORDS.equals(otherFilter.KEYWORDS);
        }

        return false;
    }
}
