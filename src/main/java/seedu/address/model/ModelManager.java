package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.card.Card;
import seedu.address.model.exceptions.RandomIndexNotInitialisedException;
import seedu.address.model.goal.Goal;

/**
 * Represents the in-memory model of the Deck data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Deck deck;
    private final UserPrefs userPrefs;
    private final FilteredList<Card> filteredCards;
    private final Goal goal;

    private Index randomIndex;

    /**
     * Initializes a ModelManager with the given Deck and userPrefs.
     */
    public ModelManager(ReadOnlyDeck deck, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(deck, userPrefs);

        logger.fine("Initializing with Deck: " + deck + " and user prefs " + userPrefs);

        this.deck = new Deck(deck);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCards = new FilteredList<>(this.deck.getCardList());
        this.goal = new Goal(this.deck);
    }

    public ModelManager() {
        this(new Deck(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDeckFilePath() {
        return userPrefs.getDeckFilePath();
    }

    @Override
    public void setDeckFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setDeckFilePath(addressBookFilePath);
    }

    //=========== Deck ================================================================================

    @Override
    public void setDeck(ReadOnlyDeck deck) {
        this.deck.resetData(deck);
    }

    @Override
    public ReadOnlyDeck getDeck() {
        return deck;
    }

    @Override
    public int getDeckSize() {
        return deck.getNumberOfCards();
    }

    @Override
    public boolean hasCard(Card card) {
        requireNonNull(card);
        return deck.hasCard(card);
    }

    @Override
    public void deleteCard(Card target) {
        deck.removeCard(target);
    }

    @Override
    public void addCard(Card card) {
        deck.addCard(card);
    }

    @Override
    public void setCard(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);

        deck.setCard(target, editedCard);
    }

    //=========== Filtered Card List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list of
     * {@code versionedDeck}
     */
    @Override
    public ObservableList<Card> getFilteredCardList() {
        return filteredCards;
    }

    @Override
    public void updateFilteredCardList(Predicate<Card> predicate) {
        requireNonNull(predicate);
        filteredCards.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        // compare Deck, UserPrefs and FilteredList equality
        ModelManager otherModelManager = (ModelManager) other;
        return deck.equals(otherModelManager.deck)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCards.equals(otherModelManager.filteredCards);
    }

    //=========== Random Index =============================================================
    @Override
    public void setRandomIndex(Index randomIndex) {
        this.randomIndex = randomIndex;
    }

    @Override
    public Index getRandomIndex() throws RandomIndexNotInitialisedException {
        if (this.randomIndex == null) {
            throw new RandomIndexNotInitialisedException();
        }
        return this.randomIndex;
    }

    @Override
    public void resetRandomIndex() {
        this.randomIndex = null;
    }
    //=========== Goal ==================================================================================

    @Override
    public void setGoal(int target) {
        this.goal.setTarget(target);
    }

    @Override
    public Goal getGoal() {
        return goal;
    }

}
