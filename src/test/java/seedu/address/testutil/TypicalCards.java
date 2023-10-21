package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Deck;
import seedu.address.model.card.Card;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class TypicalCards {

    private static final LocalDateTime typicalDate =
            LocalDateTime.of(2018, 11, 3,12, 45, 30);
    public static final Card CS2100 = new CardBuilder().withQuestion("R-Format instruction opcode")
            .withAnswer("0").withNextPracticeDate(typicalDate).build();
    public static final Card CS1231S = new CardBuilder().withQuestion("Name the 3 relations")
            .withAnswer("Reflexive, Symmetric, Transitive").withNextPracticeDate(typicalDate).build();
    public static final Card CS1101S = new CardBuilder().withQuestion("What is the language used for this mod?")
            .withAnswer("Source").withNextPracticeDate(typicalDate).build();
    public static final Card HIGH = new CardBuilder().withQuestion("High Priority?")
            .withAnswer("True")
            .withNextPracticeDate(LocalDateTime.MIN).build();
    public static final Card LOW = new CardBuilder().withQuestion("Low Priority?")
            .withAnswer("True")
            .withNextPracticeDate(LocalDateTime.MAX).build();

    private TypicalCards() {} // prevents instantiation


    /**
     * Returns an {@code Deck} with all the typical card.
     */
    public static Deck getTypicalDeck() {
        Deck ab = new Deck();
        for (Card card : getTypicalCards()) {
            ab.addCard(card);
        }
        return ab;
    }
    public static List<Card> getTypicalCards() {
        return new ArrayList<>(Arrays.asList(CS2100, CS1101S, CS1231S, HIGH, LOW));
    }
}
