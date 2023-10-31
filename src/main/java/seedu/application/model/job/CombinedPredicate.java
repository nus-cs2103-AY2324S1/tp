package seedu.application.model.job;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a predicate that is the logical AND of all {@code FieldContainsKeyWordsPredicate} objects supplied to it.
 */
public class CombinedPredicate implements Predicate<Job> {
    private List<FieldContainsKeywordsPredicate> predicateList;

    public CombinedPredicate(List<FieldContainsKeywordsPredicate> predicateList) {
        this.predicateList = new ArrayList<>(predicateList);
    }

    @Override
    public boolean test(Job job) {
        Predicate<Job> predicate = x -> true;
        for (Predicate<Job> p : predicateList) {
            predicate = predicate.and(p);
        }
        return predicate.test(job);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CombinedPredicate)) {
            return false;
        }

        CombinedPredicate otherCombinedPredicate = (CombinedPredicate) other;

        if (otherCombinedPredicate.predicateList.size() != predicateList.size()) {
            return false;
        }

        for (FieldContainsKeywordsPredicate p : predicateList) {
            if (!otherCombinedPredicate.predicateList.contains(p)) {
                return false;
            }
        }
        return true;
    }

}
