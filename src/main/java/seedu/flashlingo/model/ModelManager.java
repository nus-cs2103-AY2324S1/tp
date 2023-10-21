package seedu.flashlingo.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * Represents the in-memory model of the flashlingo data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Flashlingo flashlingo;
    private final UserPrefs userPrefs;
    private final FilteredList<FlashCard> filteredFlashCards;
    private int numberOfFlashCards;
    private int numberOfRememberedWords;

    /**
     * Initializes a ModelManager with the given flashlingo and userPrefs.
     */
    public ModelManager(ReadOnlyFlashlingo flashlingo, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(flashlingo, userPrefs);

        logger.fine("Initializing with Flashlingo: " + flashlingo + " and user prefs " + userPrefs);

        this.flashlingo = new Flashlingo(flashlingo);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashCards = new FilteredList<>(this.flashlingo.getFlashCardList());
    }

    public ModelManager() {
        this(new Flashlingo(), new UserPrefs());
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
    public Path getFlashlingoFilePath() {
        return userPrefs.getFlashlingoFilePath();
    }

    @Override
    public void setFlashlingoFilePath(Path flashlingoFilePath) {
        requireNonNull(flashlingoFilePath);
        userPrefs.setFlashlingoFilePath(flashlingoFilePath);
    }

    //=========== Flashlingo ================================================================================

    @Override
    public void setFlashlingo(ReadOnlyFlashlingo flashlingo) {
        this.flashlingo.resetData(flashlingo);
    }

    @Override
    public ReadOnlyFlashlingo getFlashlingo() {
        return flashlingo;
    }

    @Override
    public boolean hasFlashCard(FlashCard flashCard) {
        requireNonNull(flashCard);
        return flashlingo.hasFlashCard(flashCard);
    }

    @Override
    public void deleteFlashCard(FlashCard target) {
        flashlingo.removeFlashCard(target);
        this.numberOfFlashCards--;
    }

    @Override
    public void addFlashCard(FlashCard flashCard) {
        flashlingo.addFlashCard(flashCard);
        this.numberOfFlashCards++;
        updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireAllNonNull(target, editedFlashCard);

        flashlingo.setFlashCard(target, editedFlashCard);
    }
    @Override
    public int getNumberOfFlashCards() {
        return this.numberOfFlashCards;
    }
    @Override
    public int getNumberOfRememberedWords() {
        return this.numberOfRememberedWords;
    }
    @Override
    public void incrementRememberedWords() {
        this.numberOfRememberedWords++;
    }
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code FlashCard} backed by the internal list of
     * {@code versionedFlashlingo}
     */
    @Override
    public ObservableList<FlashCard> getFilteredFlashCardList() {
        return filteredFlashCards;
    }

    @Override
    public void updateFilteredFlashCardList(Predicate<FlashCard> predicate) {
        requireNonNull(predicate);
        filteredFlashCards.setPredicate(predicate);
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

        ModelManager otherModelManager = (ModelManager) other;
        return flashlingo.equals(otherModelManager.flashlingo)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredFlashCards.equals(otherModelManager.filteredFlashCards);
    }
}
