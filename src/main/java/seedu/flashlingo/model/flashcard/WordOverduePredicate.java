package seedu.flashlingo.model.flashcard;

import java.util.function.Predicate;

import seedu.flashlingo.commons.util.ToStringBuilder;

/**
 * Tests that a {@code FlashCard} is overdue or not.
 */
public class WordOverduePredicate implements Predicate<FlashCard> {

    public WordOverduePredicate() {
    }

    @Override
    public boolean test(FlashCard flashCard) {
        return flashCard.isOverdue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof WordOverduePredicate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", "overdue").toString();
    }
}
