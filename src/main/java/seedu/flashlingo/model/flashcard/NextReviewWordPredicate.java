package seedu.flashlingo.model.flashcard;

import java.util.function.Predicate;

import seedu.flashlingo.commons.util.ToStringBuilder;

/**
 * Gets the first flashcard.
 */
public class NextReviewWordPredicate implements Predicate<FlashCard> {

    private final FlashCard flashCard;
    public NextReviewWordPredicate(FlashCard flashCard) {
        this.flashCard = flashCard;
    }

    @Override
    public boolean test(FlashCard flashCard) {
        return this.flashCard.equals(flashCard);
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
        return new ToStringBuilder(this).add("to be reviewed", "flashcard").toString();
    }
}
