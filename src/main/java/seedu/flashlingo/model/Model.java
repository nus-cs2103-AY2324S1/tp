package seedu.flashlingo.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.flashlingo.commons.core.GuiSettings;
import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<FlashCard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;
    Predicate<FlashCard> PREDICATE_SHOW_NONE_FLASHCARDS = unused -> false;

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
     * Returns the user prefs' Flashlingo file path.
     */
    Path getFlashlingoFilePath();

    /**
     * Sets the user prefs' Flashlingo file path.
     */
    void setFlashlingoFilePath(Path flashlingoFilePath);

    /**
     * Replaces Flashlingo data with the data in {@code flashlingo}.
     */
    void setFlashlingo(ReadOnlyFlashlingo flashlingo);

    /** Returns the Flashlingo */
    ReadOnlyFlashlingo getFlashlingo();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the Flashlingo.
     */
    boolean hasFlashCard(FlashCard flashCard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the Flashlingo.
     */
    void deleteFlashCard(FlashCard target);

    /**
     * Adds the given FlashCard.
     * {@code flashCard} must not already exist in the Flashlingo.
     */
    void addFlashCard(FlashCard flashCard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashCard}.
     * {@code target} must exist in the Flashlingo .
     * The flashcard identity of {@code editedFlashCard} must not be the same as another existing
     * FlashCard in the Flashlingo.
     */
    void setFlashCard(FlashCard target, FlashCard editedFlashCard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<FlashCard> getFilteredFlashCardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashCardList(Predicate<FlashCard> predicate);
    void setReviewWord(Predicate<FlashCard> predicate, FlashCard flashCard);
    boolean isReviewSession();
    void updateReviewSessionState();
    int getNumberOfFlashCards();
    int getNumberOfRememberedWords();
    void incrementRememberedWords();
    void nextReviewWord() throws CommandException;
    void rememberWord();
}
