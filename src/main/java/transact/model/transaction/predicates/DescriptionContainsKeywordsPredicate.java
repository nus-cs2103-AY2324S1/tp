package transact.model.transaction.predicates;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import transact.commons.util.ToStringBuilder;
import transact.model.StringContainsKeywordsPredicate;
import transact.model.transaction.Transaction;

/**
 * Tests that a {@code Transaction}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Transaction> {
    private final StringContainsKeywordsPredicate predicate;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        predicate = new StringContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean test(Transaction transaction) {
        return predicate.test(transaction.getDescription().getValue());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DescriptionContainsKeywordsPredicate)) {
            return false;
        }

        DescriptionContainsKeywordsPredicate otherDescriptionContainsKeywordsPredicate = (DescriptionContainsKeywordsPredicate) other;
        return Objects.equals(otherDescriptionContainsKeywordsPredicate.predicate, predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("predicate", predicate).toString();
    }
}
