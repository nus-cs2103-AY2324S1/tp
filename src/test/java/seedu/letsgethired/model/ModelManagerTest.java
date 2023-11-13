package seedu.letsgethired.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.model.application.InternApplicationComparator.COMPANY_COMPARATOR_DESCENDING;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.OPTIVER;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.model.application.CompanyContainsFieldKeywordsPredicate;
import seedu.letsgethired.testutil.InternTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternTracker(), new InternTracker(modelManager.getInternTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternTrackerFilePath(Paths.get("intern/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternTrackerFilePath(Paths.get("new/intern/book/file/path"));
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
    public void setInternTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternTrackerFilePath(null));
    }

    @Test
    public void setInternTrackerFilePath_validPath_setsInternTrackerFilePath() {
        Path path = Paths.get("intern/book/file/path");
        modelManager.setInternTrackerFilePath(path);
        assertEquals(path, modelManager.getInternTrackerFilePath());
    }

    @Test
    public void hasInternApplication_nullInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternApplication(null));
    }

    @Test
    public void hasInternApplication_internApplicationNotInInternTracker_returnsFalse() {
        assertFalse(modelManager.hasInternApplication(JANE_STREET));
    }

    @Test
    public void hasInternApplication_internApplicationInInternTracker_returnsTrue() {
        modelManager.addInternApplication(JANE_STREET);
        assertTrue(modelManager.hasInternApplication(JANE_STREET));
    }

    @Test
    public void getFilteredInternApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                modelManager.getFilteredInternApplicationList().remove(0));
    }

    @Test
    public void updateCurrentApplication_validInternApplication_success() {
        modelManager.addInternApplication(JANE_STREET);
        modelManager.setCurrentInternApplication(JANE_STREET);
        assertTrue(modelManager.getCurrentInternApplication().equals(JANE_STREET));
    }

    @Test
    public void getCurrentApplication_noCurrentInternApplication_success() {
        assertTrue(modelManager.getCurrentInternApplication() == null);
    }

    @Test
    public void equals() {
        InternTracker internTracker = new InternTrackerBuilder()
                .withInternApplication(JANE_STREET)
                .withInternApplication(OPTIVER)
                .build();
        InternTracker differentInternTracker = new InternTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different internBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternTracker, userPrefs)));

        // different filteredList -> returns false
        String searchString = JANE_STREET.getCompany().value;
        modelManager.updateFilteredInternApplicationList(new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, searchString))));
        assertNotEquals(modelManager, new ModelManager(internTracker, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.showAllInternApplications();

        // different filteredSortedList -> returns false
        modelManager.updateFilteredSortedInternApplicationList(COMPANY_COMPARATOR_DESCENDING);
        assertNotEquals(modelManager, new ModelManager(internTracker, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.showAllInternApplications();

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternTrackerFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(internTracker, differentUserPrefs));
    }
}
