package seedu.address.model.plan;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PlanNameContainsKeywordsPredicate implements Predicate<Plan> {
    private final List<String> keywords;

    public PlanNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Plan plan) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(plan.getPlanName().planName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.plan.PlanNameContainsKeywordsPredicate)) {
            return false;
        }

        seedu.address.model.plan.PlanNameContainsKeywordsPredicate planNameContainsKeywordsPredicate =
                (seedu.address.model.plan.PlanNameContainsKeywordsPredicate) other;
        return keywords.equals(planNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

