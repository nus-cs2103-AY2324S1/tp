package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Sets the user prefs' address book file path.
     */
    void setFlashLingoFilePath(Path addressBookFilePath);

    /**
     * Returns true if a word with the same identity as {@code flashcard} exists in Flashlingo.
     */
    boolean hasWord(FlashCard flashCard);

    /**
     * Deletes the given word.
     * The word must exist in the address book.
     */
    void deleteWord(FlashCard flashCard);

    /**
     * Adds the given word.
     * {@code flashcard} must not already exist in Flashlingo.
     */
    void addWord(Flashcard flashcard);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setCard(Flashcard target, FlashCard editedCard);
}
