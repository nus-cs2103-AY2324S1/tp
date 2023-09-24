package seedu.address.cardslist;

import java.util.ArrayList;

import seedu.address.pojo.FlashCard;

/**
 * Stores flashcards
 *
 * @author Nathanael M. Tan
 * @author Wang Cheng
 * @version 1.0
 * @since 1.0
 */
public class CardList {
    private static ArrayList<FlashCard> ls;

    public void add(FlashCard flashcard) {
        ls.add(flashcard);
    }

    public int size() {
        return ls.size();
    }

    public FlashCard get(int index) {
        return ls.get(index);
    }

    public void delete(int index) {
        ls.remove(index);
    }

    /**
     * To be used for the main CardList parsed from the text file
     *
     * @return A CardList of all flashcards that need to be reviewed
     */
    public CardList toReview() {
        CardList review = new CardList();
        for (FlashCard fc : ls) {
            if (fc.isOverdue()) {
                review.add(fc);
            }
        }
        return review;
    }

    public boolean hasCard(FlashCard card) {
        for (FlashCard fc : ls) {
            if (fc.equals(card)) {
                return true;
            }
        }
        return false;
    }

}
