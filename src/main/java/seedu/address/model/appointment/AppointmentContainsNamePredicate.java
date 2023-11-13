package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Appointment}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentContainsNamePredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentContainsNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.size() == 0 ? false : keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(appointment.getName().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsNamePredicate)) {
            return false;
        }

        AppointmentContainsNamePredicate otherPredicate = (AppointmentContainsNamePredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
