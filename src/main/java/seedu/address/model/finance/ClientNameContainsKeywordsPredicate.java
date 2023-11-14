package seedu.address.model.finance;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code Finance}'s {@code Name} matches any of the keywords given.
 */
public class ClientNameContainsKeywordsPredicate implements Predicate<Finance> {
    private final List<String> keywords;

    public ClientNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Finance finance) {
        if (finance.getClient() == null) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(finance.getClient()
                        .getName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientNameContainsKeywordsPredicate)) {
            return false;
        }
        ClientNameContainsKeywordsPredicate otherClientNameContainsKeywordsPredicate =
                (ClientNameContainsKeywordsPredicate) other;
        return keywords.equals(otherClientNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
