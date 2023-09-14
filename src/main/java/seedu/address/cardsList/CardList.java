package seedu.address.cardsList;

import seedu.address.pojo.Flashcard;

import java.util.ArrayList;

public class CardList {
    private static ArrayList<Flashcard> ls;

    public void add(Flashcard flashcard) {
        ls.add(flashcard);
    }

    public int size() {
        return ls.size();
    }

    public Flashcard get(int index) {
        return ls.get(index);
    }

    public void delete(int index) {
        ls.remove(index);
    }

    /**
     * To be used for the main CardList parsed from the text file
     * @return A CardList of all flashcards that need to be reviewed
     */
    public CardList toReview() {
        CardList review = new CardList();
        for (Flashcard fc : ls) {
            if (fc.isOverdue()) {
                review.add(fc);
            }
        }
        return review;
    }

}
