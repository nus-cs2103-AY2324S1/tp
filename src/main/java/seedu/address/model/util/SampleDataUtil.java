package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import seedu.address.model.Deck;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Difficulty;
import seedu.address.model.card.Hint;
import seedu.address.model.card.PracticeDate;
import seedu.address.model.card.Question;
import seedu.address.model.card.SolveCount;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Deck} with sample data.
 */
public class SampleDataUtil {

    /**
     * Provides sample Cards in an array
     * @return an array of sample Cards
     */
    public static Card[] getSampleCard() {
        return new Card[] {
            new Card(new Question("What is 1 + 1"),
                    new Answer("2"),
                    Difficulty.EASY,
                    new ArrayList<>(Collections.singleton(new Tag("Math"))),
                    new PracticeDate(LocalDateTime.now()), null,
                    new SolveCount(),
                    new Hint.EmptyHint()),
            new Card(new Question("What is 1 + 3"),
                    new Answer("4"),
                    Difficulty.EASY,
                    new ArrayList<>(Collections.singleton(new Tag("Math"))),
                    new PracticeDate(LocalDateTime.now()), null,
                    new SolveCount(),
                    new Hint.EmptyHint())
        };
    }

    /**
     * Provides a sample Deck with sample Cards
     * @return a sample Deck
     */
    public static Deck getSampleDeck() {
        Deck sampleDeck = new Deck();
        for (Card sampleCard : getSampleCard()) {
            sampleDeck.addCard(sampleCard);
        }
        return sampleDeck;
    }
}
