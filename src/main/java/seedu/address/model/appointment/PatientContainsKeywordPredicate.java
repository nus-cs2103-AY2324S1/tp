package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that an {@code Appointment}'s patient name matches any of the keywords given.
 */
public class PatientContainsKeywordPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    public PatientContainsKeywordPredicate(List<String> keywords) {
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

        if (other instanceof PatientContainsKeywordPredicate) {
            PatientContainsKeywordPredicate otherFilter = (PatientContainsKeywordPredicate) other;
            return this.keywords.equals(otherFilter.keywords);
        }

        return false;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
