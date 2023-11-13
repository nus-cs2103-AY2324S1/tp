package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.card.Card;
import seedu.address.model.card.UniqueCardList;

/**
 * Wraps all data at the Deck level
 * Duplicates are not allowed (by .isSameCard comparison)
 */
public class Deck implements ReadOnlyDeck {

    private final UniqueCardList cards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        cards = new UniqueCardList();
    }

    public Deck() {}

    /**
     * Creates a Deck using the Cards in the {@code toBeCopied}
     */
    public Deck(ReadOnlyDeck toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the card list with {@code Cards}.
     * {@code Cards} must not contain duplicate cards.
     */
    public void setCards(List<Card> cards) {
        this.cards.setCards(cards);
    }

    /**
     * Resets the existing data of this {@code Deck} with {@code newData}.
     */
    public void resetData(ReadOnlyDeck newData) {
        requireNonNull(newData);

        setCards(newData.getCardList());
    }

    //// card-level operations

    /**
     * Returns true if a Card with the same identity as {@code Card} exists in the Deck.
     */
    public boolean hasCard(Card card) {
        requireNonNull(card);

        return cards.contains(card);
    }

    /**
     * Adds a Card to the Deck.
     * The Card must not already exist in the Deck.
     */
    public void addCard(Card p) {
        cards.add(p);
    }

    /**
     * Replaces the given Card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the Deck.
     * The card identity of {@code editedCard} must not be the same as another existing Card in the Deck.
     */
    public void setCard(Card target, Card editedCard) {
        requireNonNull(editedCard);

        cards.setCard(target, editedCard);
    }

    /**
     * Removes {@code key} from this {@code Deck}.
     * {@code key} must exist in the Deck.
     */
    public void removeCard(Card key) {
        cards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("card", cards)
                .toString();
    }

    @Override
    public ObservableList<Card> getCardList() {
        return cards.asUnmodifiableObservableList();
    }

    @Override
    public void sort() {
        cards.sort();
    }

    public int getNumberOfCards() {
        return cards.count();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deck)) {
            return false;
        }

        // compare UniqueCardList equality
        Deck otherDeck = (Deck) other;
        return cards.equals(otherDeck.cards);
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }
}
