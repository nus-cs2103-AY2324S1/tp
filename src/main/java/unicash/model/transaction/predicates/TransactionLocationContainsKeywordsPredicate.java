package unicash.model.transaction.predicates;

import unicash.commons.util.StringUtil;
import unicash.commons.util.ToStringBuilder;
import unicash.model.transaction.Transaction;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Transactions}'s {@code Location} matches any of the keywords given.
 */
public class TransactionLocationContainsKeywordsPredicate
        implements Predicate<Transaction> {
    private final List<String> keywords;

    public TransactionLocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Transaction transaction) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(
                        transaction.getLocation().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionLocationContainsKeywordsPredicate)) {
            return false;
        }

        TransactionLocationContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (TransactionLocationContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
