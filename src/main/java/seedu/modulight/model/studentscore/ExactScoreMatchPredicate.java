package seedu.modulight.model.studentscore;

import java.util.List;
import java.util.function.Predicate;

import seedu.modulight.model.gradedcomponent.GcName;

/**
 * Tests that a {@code Student}'s attributes matches any of the attributes given.
 */
public class ExactScoreMatchPredicate implements Predicate<StudentScore> {
    private final List<GcName> gcNames;

    /**
     * Constructs a predicate to search for certain students.
     *
     * @param gcNames The keywords for Component Name
     */
    public ExactScoreMatchPredicate(List<GcName> gcNames) {
        this.gcNames = gcNames;
    }

    @Override
    public boolean test(StudentScore studentscore) {
        return gcNames.isEmpty() || gcNames.stream()
                .anyMatch(keyword -> studentscore.getGcName().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExactScoreMatchPredicate)) {
            return false;
        }

        ExactScoreMatchPredicate otherStudentIdMatchPredicate =
                (ExactScoreMatchPredicate) other;
        return gcNames.equals(otherStudentIdMatchPredicate.gcNames);
    }

    @Override
    public String toString() {
        return "Find students by given criteria";
    }
}




