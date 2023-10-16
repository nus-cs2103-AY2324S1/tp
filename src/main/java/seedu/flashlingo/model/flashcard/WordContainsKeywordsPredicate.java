package seedu.flashlingo.model.flashcard;

import java.util.List;
import java.util.function.Predicate;

import seedu.flashlingo.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class WordContainsKeywordsPredicate implements Predicate<FlashCard> {
    private final List<String> keywords;

    public WordContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(FlashCard flashCard) {
        return keywords.stream()
          .anyMatch(flashCard::hasKeyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WordContainsKeywordsPredicate)) {
            return false;
        }

        WordContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (WordContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
