package seedu.address.model.appointment.appointmentfilters;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;


/**
 * Represents a filter for finding appointments based on patient names.
 * This filter is used to match appointments with patient names that contain
 * one or more of the provided keywords in a case-insensitive manner.
 */
public class FindPatientFilter implements Predicate<Appointment> {

    private final List<String> keywords;

    public FindPatientFilter(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param appointment the input argument
     * @return {@code true} if the input argument matches the predicate,
     *     otherwise {@code false}
     */
    @Override
    public boolean test(Appointment appointment) {
        String patientName = appointment.getPatientName().toString();
        return keywords.stream()
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

            return this.keywords.equals(otherFilter.keywords);
        }

        return false;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
