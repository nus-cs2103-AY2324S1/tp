package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.model.Model.PREDICATE_SHOW_ALL_JOBS;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.GuiSettings;
import seedu.application.model.job.FieldContainsKeywordsPredicate;
import seedu.application.testutil.ApplicationBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ApplicationBook(), new ApplicationBook(modelManager.getApplicationBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setApplicationBookFilePath(Paths.get("application/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setApplicationBookFilePath(Paths.get("new/application/book/file/path"));
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
    public void setApplicationBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setApplicationBookFilePath(null));
    }

    @Test
    public void setApplicationBookFilePath_validPath_setsApplicationBookFilePath() {
        Path path = Paths.get("application/book/file/path");
        modelManager.setApplicationBookFilePath(path);
        assertEquals(path, modelManager.getApplicationBookFilePath());
    }

    @Test
    public void hasJob_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasJob(null));
    }

    @Test
    public void hasJob_jobNotInApplicationBook_returnsFalse() {
        assertFalse(modelManager.hasJob(CHEF));
    }

    @Test
    public void hasJob_jobInApplicationBook_returnsTrue() {
        modelManager.addJob(CHEF);
        assertTrue(modelManager.hasJob(CHEF));
    }

    @Test
    public void getFilteredJobList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredJobList().remove(0));
    }

    @Test
    public void equals() {
        ApplicationBook applicationBook = new ApplicationBookBuilder().withJob(CHEF).withJob(CLEANER).build();
        ApplicationBook differentApplicationBook = new ApplicationBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(applicationBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(applicationBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different applicationBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentApplicationBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = CHEF.getRole().description.split("\\s+");
        modelManager.updateFilteredJobList(
                new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setApplicationBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(applicationBook, differentUserPrefs)));
    }
}
