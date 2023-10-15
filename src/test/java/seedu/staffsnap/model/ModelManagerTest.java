package seedu.staffsnap.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.commons.core.GuiSettings;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Descriptor;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;
import seedu.staffsnap.testutil.ApplicantBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ApplicantBook(), new ApplicantBook(modelManager.getApplicantBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setApplicantBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setApplicantBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setApplicantBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setApplicantBookFilePath(null));
    }

    @Test
    public void setApplicantBookFilePath_validPath_setsApplicantBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setApplicantBookFilePath(path);
        assertEquals(path, modelManager.getApplicantBookFilePath());
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInApplicantBook_returnsFalse() {
        assertFalse(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInApplicantBook_returnsTrue() {
        modelManager.addApplicant(ALICE);
        assertTrue(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void getFilteredApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicantList().remove(0));
    }

    @Test
    public void updateSortedApplicantList_setDescriptor_changesDescriptor() {
        modelManager.updateSortedApplicantList(Descriptor.PHONE);
        assert(Applicant.getDescriptor() == Descriptor.PHONE);
    }
    @Test
    public void equals() {
        ApplicantBook applicantBook = new ApplicantBookBuilder().withApplicant(ALICE).withApplicant(BENSON).build();
        ApplicantBook differentApplicantBook = new ApplicantBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(applicantBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(applicantBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different applicantBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentApplicantBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(applicantBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setApplicantBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(applicantBook, differentUserPrefs)));
    }
}
