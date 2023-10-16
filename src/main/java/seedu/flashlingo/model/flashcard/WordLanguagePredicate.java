package seedu.flashlingo.model.flashcard;

import seedu.flashlingo.commons.util.ToStringBuilder;

import java.util.function.Predicate;

/**
 * Tests that a {@code FlashCard} is the language given.
 */
public class WordLanguagePredicate implements Predicate<FlashCard> {
    private final String language;

    public WordLanguagePredicate(String language) {
        this.language = language;
    }

    @Override
    public boolean test(FlashCard flashCard) {
        return flashCard.isSameLanguage(language);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof WordLanguagePredicate)) {
            return false;
        }

        WordLanguagePredicate otherWordLanguagePredicate = (WordLanguagePredicate) other;
        return language.equals(otherWordLanguagePredicate.language);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", language).toString();
    }
}
