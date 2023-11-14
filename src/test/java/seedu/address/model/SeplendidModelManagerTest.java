package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.SeplendidModel.PREDICATE_SHOW_ALL_LOCAL_COURSES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP2000;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS3230;
import static seedu.address.testutil.TypicalObjects.MA2001;
import static seedu.address.testutil.TypicalObjects.NTU;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.University;
import seedu.address.testutil.LocalCourseCatalogueBuilder;
import seedu.address.testutil.PartnerCourseCatalogueBuilder;

public class SeplendidModelManagerTest {

    private SeplendidModelManager modelManager = new SeplendidModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new LocalCourseCatalogue(), new LocalCourseCatalogue(modelManager.getLocalCourseCatalogue()));
        assertEquals(new PartnerCourseCatalogue(),
                new PartnerCourseCatalogue(modelManager.getPartnerCourseCatalogue()));
        assertEquals(new UniversityCatalogue(), new UniversityCatalogue(modelManager.getUniversityCatalogue()));
        assertEquals(new MappingCatalogue(), new MappingCatalogue(modelManager.getMappingCatalogue()));
        assertEquals(new NoteCatalogue(), new NoteCatalogue(modelManager.getNoteCatalogue()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLocalCourseCatalogueFilePath(Paths.get("local/course/catalogue/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLocalCourseCatalogueFilePath(Paths.get("local/course/catalogue/file/path"));
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
    public void setLocalCourseCatalogueFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLocalCourseCatalogueFilePath(null));
    }

    @Test
    public void setLocalCourseCatalogueFilePath_validPath_setsLocalCourseCatalogueFilePath() {
        Path path = Paths.get("local/course/catalogue/file/path");
        modelManager.setLocalCourseCatalogueFilePath(path);
        assertEquals(path, modelManager.getLocalCourseCatalogueFilePath());
    }

    @Test
    public void hasLocalCourse_nullLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasLocalCourse((LocalCourse) null));
    }

    @Test
    public void hasLocalCourse_localCourseNotInLocalCourseCatalogue_returnsFalse() {
        assertFalse(modelManager.hasLocalCourse(CS3230));
    }

    @Test
    public void hasLocalCourse_localCourseInLocalCourseCatalogue_returnsTrue() {
        modelManager.addLocalCourse(CS3230);
        assertTrue(modelManager.hasLocalCourse(CS3230));
    }

    @Test
    public void getLocalCourseIfExists_localCourseInLocalCourseCatalogue_returnsLocalCourse() {
        modelManager.addLocalCourse(MA2001);
        assertEquals(MA2001, modelManager.getLocalCourseIfExists(MA2001.getLocalCode()).get());
    }

    @Test
    public void getLocalCourseIfExists_localCourseNotInLocalCourseCatalogue_returnsEmpty() {
        assertEquals(Optional.empty(), modelManager.getLocalCourseIfExists(MA2001.getLocalCode()));
    }

    @Test
    public void getFilteredLocalCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredLocalCourseList().remove(0));
    }


    //partner courses
    @Test
    public void setPartnerCourseCatalogueFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPartnerCourseCatalogueFilePath(null));
    }

    @Test
    public void setPartnerCourseCatalogueFilePath_validPath_setsPartnerCourseCatalogueFilePath() {
        Path path = Paths.get("partner/course/catalogue/file/path");
        modelManager.setPartnerCourseCatalogueFilePath(path);
        assertEquals(path, modelManager.getPartnerCourseCatalogueFilePath());
    }

    @Test
    public void hasPartnerCourse_nullPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPartnerCourse((PartnerCourse) null));
    }

    @Test
    public void hasPartnerCourse_partnerCourseNotInPartnerCourseCatalogue_returnsFalse() {
        assertFalse(modelManager.hasPartnerCourse(COMP1000));
    }

    @Test
    public void hasPartnerCourse_partnerCourseInPartnerCourseCatalogue_returnsTrue() {
        modelManager.addPartnerCourse(COMP1000);
        assertTrue(modelManager.hasPartnerCourse(COMP1000));
    }

    @Test
    public void hasPartnerCourse_partnerCourseWithSamePartnerCodeDifferentUniversityName_returnsFalse() {
        modelManager.addPartnerCourse(COMP1000);
        assertFalse(modelManager.hasPartnerCourse(COMP1000.getPartnerCode(),
                COMP2000.getPartnerUniversity().getUniversityName()));
    }

    @Test
    public void hasPartnerCourse_partnerCourseWithDifferentPartnerCodeSameUniversityName_returnsFalse() {
        modelManager.addPartnerCourse(COMP1000);
        assertFalse(modelManager.hasPartnerCourse(COMP2000.getPartnerCode(),
                COMP1000.getPartnerUniversity().getUniversityName()));
    }

    @Test
    public void hasPartnerCourse_partnerCourseWithSameKeyValues_returnsTrue() {
        modelManager.addPartnerCourse(COMP1000);
        assertTrue(modelManager.hasPartnerCourse(COMP1000.getPartnerCode(),
                COMP1000.getPartnerUniversity().getUniversityName()));
    }

    @Test
    public void getPartnerCourseIfExists_partnerCourseInPartnerCourseCatalogueWithSameKeyValues_returnsPartnerCourse() {
        modelManager.addPartnerCourse(COMP2000);
        assertEquals(COMP2000, modelManager.getPartnerCourseIfExists(COMP2000.getPartnerCode(),
                COMP2000.getPartnerUniversity().getUniversityName()).get());
    }

    @Test
    public void getPartnerCourseIfExists_partnerCourseWithSamePartnerCodeDifferentUniversityName_returnsEmpty() {
        modelManager.addPartnerCourse(COMP2000);
        assertEquals(Optional.empty(), modelManager.getPartnerCourseIfExists(COMP2000.getPartnerCode(),
                COMP1000.getPartnerUniversity().getUniversityName()));
    }

    @Test
    public void getPartnerCourseIfExists_partnerCourseWithDifferentPartnerCodeSameUniversityName_returnsEmpty() {
        modelManager.addPartnerCourse(COMP2000);
        assertEquals(Optional.empty(), modelManager.getPartnerCourseIfExists(COMP1000.getPartnerCode(),
                COMP2000.getPartnerUniversity().getUniversityName()));
    }


    @Test
    public void getPartnerCourseIfExists_partnerCourseNotInPartnerCourseCatalogue_returnsEmpty() {
        assertEquals(Optional.empty(), modelManager.getPartnerCourseIfExists(COMP2000.getPartnerCode(),
                COMP2000.getPartnerUniversity().getUniversityName()));
    }

    @Test
    public void getFilteredPartnerCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPartnerCourseList().remove(0));
    }

    //university
    @Test
    public void setUniversityCatalogueFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUniversityCatalogueFilePath(null));
    }

    @Test
    public void setUniversityCatalogueFilePath_validPath_setsUniversityCatalogueFilePath() {
        Path path = Paths.get("university/catalogue/file/path");
        modelManager.setUniversityCatalogueFilePath(path);
        assertEquals(path, modelManager.getUniversityCatalogueFilePath());
    }

    @Test
    public void hasUniversity_nullUniversityCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasUniversity((University) null));
    }

    @Test
    public void hasUniversity_universityNotInUniversityCourseCatalogue_returnsFalse() {
        assertFalse(modelManager.hasUniversity(NTU));
    }

    @Test
    public void hasUniversity_universityInUniversityCatalogue_returnsTrue() {
        modelManager.addUniversity(NTU);
        assertTrue(modelManager.hasUniversity(NTU));
    }

    @Test
    public void getFilteredUniversityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredUniversityList().remove(0));
    }

    @Test
    public void equals() {
        LocalCourseCatalogue localCourseCatalogue =
                new LocalCourseCatalogueBuilder().withLocalCourse(CS2030S).withLocalCourse(CS2040S).build();
        LocalCourseCatalogue differentLocalCourseCatalogue = new LocalCourseCatalogue();
        UserPrefs userPrefs = new UserPrefs();
        PartnerCourseCatalogue partnerCourseCatalogue =
                new PartnerCourseCatalogueBuilder().withPartnerCourse(COMP1000).withPartnerCourse(COMP2000).build();
        UniversityCatalogue universityCatalogue = getTypicalUniversityCatalogue();
        NoteCatalogue noteCatalogue = getTypicalNoteCatalogue();
        MappingCatalogue mappingCatalogue = getTypicalMappingCatalogue();


        // same values -> returns true
        modelManager = new SeplendidModelManager(
                userPrefs,
                localCourseCatalogue,
                partnerCourseCatalogue,
                universityCatalogue,
                mappingCatalogue,
                noteCatalogue);
        SeplendidModelManager modelManagerCopy = new SeplendidModelManager(
                userPrefs,
                localCourseCatalogue,
                partnerCourseCatalogue,
                universityCatalogue,
                mappingCatalogue,
                noteCatalogue);

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different localCourseCatalogue -> returns false
        assertFalse(modelManager.equals(new SeplendidModelManager(
                userPrefs,
                differentLocalCourseCatalogue,
                partnerCourseCatalogue,
                universityCatalogue,
                mappingCatalogue,
                noteCatalogue)));

        // different filteredList -> returns false
        modelManager.updateFilteredLocalCourseList(unused -> false);
        assertFalse(modelManager.equals(new SeplendidModelManager(
                userPrefs,
                localCourseCatalogue,
                partnerCourseCatalogue,
                universityCatalogue,
                mappingCatalogue,
                noteCatalogue)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredLocalCourseList(PREDICATE_SHOW_ALL_LOCAL_COURSES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLocalCourseCatalogueFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new SeplendidModelManager(
                differentUserPrefs,
                localCourseCatalogue,
                partnerCourseCatalogue,
                universityCatalogue,
                mappingCatalogue,
                noteCatalogue)));
    }
}
