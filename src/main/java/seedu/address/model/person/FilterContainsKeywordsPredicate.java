package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FilterContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FilterContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean allKeywordsMatched = true;
        for (String keyword : keywords) {
            boolean keywordMatched = false;
            keyword = keyword.trim();
            for (Tag tag : person.getTags()) {
                String tagName = tag.getTagName();
                if (keyword.equalsIgnoreCase(tagName)) {
                    keywordMatched = true;
                    break;
                }
            }
            if (!keywordMatched) {
                allKeywordsMatched = false;
                break;
            }
        }
        return allKeywordsMatched;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterContainsKeywordsPredicate)) {
            return false;
        }

        FilterContainsKeywordsPredicate otherFilterContainsKeywordsPredicate = (FilterContainsKeywordsPredicate) other;
        return keywords.equals(otherFilterContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
