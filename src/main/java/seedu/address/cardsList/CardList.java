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
}
