package seedu.lovebook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;
import static seedu.lovebook.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.model.person.NameContainsKeywordsPredicate;
import seedu.lovebook.testutil.LoveBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new LoveBook(), new LoveBook(modelManager.getLoveBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLoveBookFilePath(Paths.get("lovebook/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLoveBookFilePath(Paths.get("new/lovebook/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setLoveBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLoveBookFilePath(null));
    }

    @Test
    public void setLoveBookFilePath_validPath_setsLoveBookFilePath() {
        Path path = Paths.get("lovebook/book/file/path");
        modelManager.setLoveBookFilePath(path);
        assertEquals(path, modelManager.getLoveBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInLoveBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInLoveBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void executeWelcomeMessage() {
        String expected = "Hey there, fabulous single!" + "\n"
                + "Get ready to embark on an exciting journey with LoveBook to find your perfect match ❤︎₊ ⊹";
        assertEquals(expected, modelManager.getWelcomeMessage());
    }

    @Test
    public void executeRandomPerson() {
        modelManager.addPerson(ALICE);
        modelManager.getRandomPerson();
        assertTrue(modelManager.getFilteredPersonList().equals(Arrays.asList(ALICE)));
    }

    @Test
    public void equals() {
        LoveBook loveBook = new LoveBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        LoveBook differentLoveBook = new LoveBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(loveBook, userPrefs, new DatePrefs());
        ModelManager modelManagerCopy = new ModelManager(loveBook, userPrefs, new DatePrefs());
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different LoveBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentLoveBook, userPrefs, new DatePrefs())));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(loveBook, userPrefs, new DatePrefs())));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLoveBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(loveBook, differentUserPrefs, new DatePrefs())));
    }
}
