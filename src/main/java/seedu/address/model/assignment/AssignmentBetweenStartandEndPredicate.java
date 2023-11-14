package seedu.address.model.assignment;

import static seedu.address.model.fields.IsoDate.isDateBefore;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Assignment}'s {@code endDate} is between a given start and end date.
 */
public class AssignmentBetweenStartandEndPredicate implements Predicate<Assignment> {
    private final List<String> dates;

    public AssignmentBetweenStartandEndPredicate(List<String> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Assignment assignment) {
        String compareDate = assignment.getEnd().toParseString();
        String startDate = dates.get(0);
        String endDate = dates.get(1);

        boolean isStartBeforeCompare = !isDateBefore(compareDate, startDate); // True if valid
        boolean isEndAfterCompare = !isDateBefore(endDate, compareDate); // True if valid

        return isStartBeforeCompare && isEndAfterCompare;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentBetweenStartandEndPredicate)) {
            return false;
        }

        AssignmentBetweenStartandEndPredicate otherAssignmentToCompare =
                (AssignmentBetweenStartandEndPredicate) other;
        return dates.equals(otherAssignmentToCompare.dates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Start Date", dates.get(0))
                .add("End Date", dates.get(1)).toString();
    }
}
