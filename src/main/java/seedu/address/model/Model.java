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
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasCard(Card card);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setCard(Card target, Card editedCard);

    /** Returns an unmodifiable view of the filtered card list */
    ObservableList<Card> getFilteredCardList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCardList(Predicate<Card> predicate);
    /**
     * Adds the given card.
     * {@code card} must not already exist in the deck.
     */
    void addCard(Card card);
    /**
     * Deletes the given card.
     * {@code card} must exist in the deck.
     */
    void deleteCard(Card card);

    /**
     * Replaces deck data with the data in {@code deck}.
     */
    public void setDeck(ReadOnlyDeck deck);

}
