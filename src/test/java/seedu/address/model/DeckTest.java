package seedu.address.model;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Card;
import seedu.address.model.person.exceptions.CardNotFoundException;
import seedu.address.testutil.DeckBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.*;

public class DeckTest {

    @Test
    public void deckConstructor_nullDeckToCopy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deck(null));
    }

    @Test
    public void setCards_nullCards_throwsNullPointerException() {
        Deck deck = new Deck();
        assertThrows(NullPointerException.class, () -> deck.setCards(null));
    }

    @Test
    public void setCards_validCards_setsCards() {
        Deck deck = new Deck();
        Deck otherDeck = new DeckBuilder().withCard(CS2100).build();
        deck.setCards(otherDeck.getCardList());
        assertTrue(deck.equals(otherDeck));
    }

    @Test
    public void resetData_nullNewData_throwsNullPointerException() {
        Deck deck = new Deck();
        assertThrows(NullPointerException.class, () -> deck.resetData(null));
    }

    @Test
    public void resetData_withSameDeck_returnsSameDeck() {
        Deck deck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
        Deck otherDeck = new Deck();
        otherDeck.resetData(deck); // same deck
        assertTrue(deck.equals(otherDeck));
    }

    @Test
    public void resetData_withDiffDeck_returnsDiffDeck() {
        Deck deck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
        Deck otherDeck = new Deck();
        Deck diffDeck = new DeckBuilder().withCard(CS2100).withCard(CS1231S).build();
        otherDeck.resetData(diffDeck);
        assertTrue(otherDeck.equals(diffDeck));
        assertFalse(deck.equals(otherDeck));
    }

    @Test
    public void hasCard_nullCard_throwsNullPointerException() {
        Deck deck = new Deck();
        assertThrows(NullPointerException.class, () -> deck.hasCard(null));
    }

    @Test
    public void hasCard_cardInDeck_returnsTrue() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        assertTrue(deck.hasCard(CS2100));
    }

    @Test
    public void hasCard_cardNotInDeck_returnsFalse() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        assertFalse(deck.hasCard(CS1231S));
    }

    @Test
    public void addCard_nullCard_throwsNullPointerException() {
        Deck deck = new Deck();
        assertThrows(NullPointerException.class, () -> deck.addCard(null));
    }

    @Test
    public void addCard_validCard_addsCard() {
        Deck deck = new Deck();
        deck.addCard(CS2100);
        Deck sameDeck = new DeckBuilder().withCard(CS2100).build();
        assertTrue(deck.equals(sameDeck));
    }

    @Test
    public void setCard_nullEditedCard_throwsNullPointerException() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        assertThrows(NullPointerException.class, () -> deck.setCard(CS2100, null));
    }

    @Test
    public void setCard_targetCardNotInDeck_throwsCardNotFoundException() {
        Deck deck = new Deck();
        assertThrows(CardNotFoundException.class, () -> deck.setCard(CS2100, CS1231S));
    }

    @Test
    public void setCard_withEditedCard_setsCard() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        Deck targetDeck = new DeckBuilder().withCard(CS1231S).build();
        deck.setCard(CS2100, CS1231S);
        assertTrue(deck.equals(targetDeck));
    }

    @Test
    public void removeCard_nullCard_throwsNullPointerException() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        assertThrows(NullPointerException.class, () -> deck.removeCard(null));
    }

    @Test
    public void removeCard_cardInDeck_cardRemoved() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        deck.removeCard(CS2100);
        assertTrue(deck.equals(new Deck()));
    }

    @Test
    public void removeCard_cardNotInDeck_throwsCardNotFoundException() {
        Deck deck = new DeckBuilder().withCard(CS2100).build();
        assertThrows(CardNotFoundException.class, () -> deck.removeCard(CS1231S));
    }

    @Test
    public void equals() {
        Deck deck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
        Deck diffDeck = new Deck();

        // same values -> returns true
        Deck sameDeck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
        assertTrue(deck.equals(sameDeck));

        // same object -> returns true
        assertTrue(deck.equals(deck));

        // null -> returns false
        assertFalse(deck.equals(null));

        // different types -> returns false
        assertFalse(deck.equals(5));

        // different deck -> returns false
        assertFalse(deck.equals(diffDeck));
    }
}
