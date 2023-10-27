package transact.model;

import java.util.List;
import java.util.function.Predicate;

import transact.commons.util.StringUtil;
import transact.commons.util.ToStringBuilder;

/**
 * Tests that a string matches any of the keywords given.
 */
public class StringContainsKeywordsPredicate implements Predicate<String> {
    private final List<String> keywords;

    public StringContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(String string) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(string, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StringContainsKeywordsPredicate)) {
            return false;
        }

        StringContainsKeywordsPredicate otherStringContainsKeywordsPredicate = (StringContainsKeywordsPredicate) other;
        return keywords.equals(otherStringContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
