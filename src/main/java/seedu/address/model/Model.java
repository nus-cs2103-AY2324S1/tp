package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.card.Card;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Card> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' deck file path.
     */
    Path getDeckFilePath();

    /**
     * Sets the user prefs' deck file path.
     */
    void setDeckFilePath(Path cardFilePath);

    /**
     * Returns the Deck
     */
    ReadOnlyDeck getDeck();

    /**
     * Returns true if a Card with the same identity as {@code person} exists in the Deck.
     */
    boolean hasCard(Card card);

    /**
     * Replaces the given Card {@code target} with {@code editedCard}.
     * {@code target} must exist in the Deck.
     * The card identity of {@code editedCard} must not be the same as another existing card in the Deck.
     */
    void setCard(Card target, Card editedCard);

    /** Returns an unmodifiable view of the filtered Card list */
    ObservableList<Card> getFilteredCardList();

    /**
     * Updates the filter of the filtered Card list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCardList(Predicate<Card> predicate);
    /**
     * Adds the given Card.
     * {@code card} must not already exist in the Deck.
     */
    void addCard(Card card);
    /**
     * Deletes the given Card.
     * {@code card} must exist in the Deck.
     */
    void deleteCard(Card card);

    /**
     * Replaces Deck data with the data in {@code deck}.
     */
    public void setDeck(ReadOnlyDeck deck);

}
