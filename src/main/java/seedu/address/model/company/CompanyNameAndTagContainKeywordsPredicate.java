
package seedu.address.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Company}'s {@code Name} or {@code Tags} matches any of the keywords given.
 */
public class CompanyNameAndTagContainKeywordsPredicate implements Predicate<Company> {
    private final List<String> companyNameKeywords;
    private final List<String> tagKeywords;

    /**
     * Constructor for NameAndTagContainKeywordPredicate.
     *
     * @param companyNameKeywords List of keywords to search for in the companyName.
     * @param tagKeywords List of keywords to search for in the tags.
     */
    public CompanyNameAndTagContainKeywordsPredicate(List<String> companyNameKeywords, List<String> tagKeywords) {
        this.companyNameKeywords = companyNameKeywords;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Company company) {
        return companyNameKeywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(company.getCompanyName().fullName, keyword))
            // Previously, the method was checking for keyword matches using company.getTags().toString(),
            // which converted all tags of a company into a single string representation.
            // This approach might not correctly identify individual tags if the toString() method for tags
            // includes additional characters or formatting.
            //
            // The updated approach streams through each individual tag of a Company and checks
            // if any of them match the keywords. This ensures that we accurately check
            // each tag against each keyword, providing a more precise match.
            || company.getTags().stream()
            .anyMatch(tag -> tagKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyNameAndTagContainKeywordsPredicate)) {
            return false;
        }

        CompanyNameAndTagContainKeywordsPredicate otherCompanyNameAndTagContainKeywordsPredicate =
            (CompanyNameAndTagContainKeywordsPredicate) other;
        return companyNameKeywords.equals(otherCompanyNameAndTagContainKeywordsPredicate.companyNameKeywords)
            && tagKeywords.equals(otherCompanyNameAndTagContainKeywordsPredicate.tagKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("companyNameKeywords", companyNameKeywords)
            .add("tagKeywords", tagKeywords).toString();
    }
}
