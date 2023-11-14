package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Tutorial} matches any of the keywords given.
 */
public class TutorialContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TutorialContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getTutorials().stream()
                .map(Tutorial::getFullTutorialString)
                .anyMatch(tutorial -> keywords.stream()
                        .anyMatch(keyword -> keyword.equalsIgnoreCase(tutorial)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialContainsKeywordsPredicate)) {
            return false;
        }

        TutorialContainsKeywordsPredicate otherTutorialContainsKeywordsPredicate =
                (TutorialContainsKeywordsPredicate) other;
        return keywords.equals(otherTutorialContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
