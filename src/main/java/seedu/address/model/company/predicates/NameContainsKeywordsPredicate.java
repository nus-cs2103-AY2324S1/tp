package seedu.address.model.company.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.company.Company;

/**
 * Represents a predicate to test if a {@code Company}'s {@code Name} contains
 * any of the specified keywords. The matching is case-insensitive.
 */
public class NameContainsKeywordsPredicate implements Predicate<Company> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the {@code Company}'s name contains any of the keywords as substrings.
     *
     * <p>For instance, if the keyword is "tech", then companies with names "Tech Corp",
     * "Biotech Industries", and "Infotech Solutions" would all be matched.</p>
     *
     * @param company The company to be tested against the keywords.
     * @return True if the company's name contains any keyword as a substring, false otherwise.
     */
    @Override
    public boolean test(Company company) {
        return keywords.stream()
                .anyMatch(keyword -> company.getName().toString().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
