package seedu.address.model.schedule;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class StatusPredicate implements Predicate<Schedule> {
    private final List<String> status;
    private final List<String> tutorName;

    /**
     * Default constructor for StatusPredicate.
     *
     * @param status  status value
     * @param tutorName name of tutor
     */
    public StatusPredicate(List<String> status, List<String> tutorName) {
        this.status = status;
        this.tutorName = tutorName;
    }

    @Override
    public boolean test(Schedule schedule) {
        if (tutorName != null) {
            return status.stream()
                .allMatch(status ->
                    StringUtil.containsWordIgnoreCase(schedule.getStatus().toString(), status))
                && tutorName.stream()
                .allMatch(keywords ->
                    StringUtil.containsWordIgnoreCase(schedule.getTutor().getName().toString(), keywords));
        } else {
            return status.stream()
                .allMatch(status ->
                    StringUtil.containsWordIgnoreCase(schedule.getStatus().toString(), status));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusPredicate)) {
            return false;
        }

        StatusPredicate otherStatusPredicate =
            (StatusPredicate) other;
        return status.equals(otherStatusPredicate.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("status", status).toString();
    }

}
