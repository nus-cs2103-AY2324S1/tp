package seedu.address.model.applicant.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;

/**
 * Tests that an {@code Applicant}'s {@code Set<Tag>} matches any of the keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;
    public TagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return applicant.getTags().stream()
                .reduce(false, (acc, tag) -> {
                    return acc || keywords.stream().anyMatch(
                            keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword));
                }, (x, y) -> x || y);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainKeywordsPredicate)) {
            return false;
        }

        TagsContainKeywordsPredicate otherNameContainsKeywordsPredicate = (TagsContainKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
