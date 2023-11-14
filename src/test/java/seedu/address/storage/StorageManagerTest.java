package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.PartnerCourseCatalogue;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.UniversityCatalogue;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonLocalCourseCatalogueStorage localCourseCatalogueStorage = new JsonLocalCourseCatalogueStorage(
            getTempFilePath("localcourse"));
        JsonPartnerCourseCatalogueStorage partnerCourseCatalogueStorage = new JsonPartnerCourseCatalogueStorage(
            getTempFilePath("partnercourse"));
        JsonUniversityCatalogueStorage universityCatalogueStorage = new JsonUniversityCatalogueStorage(getTempFilePath(
            "university"));
        JsonNoteCatalogueStorage noteCatalogueStorage = new JsonNoteCatalogueStorage(getTempFilePath("note"));
        JsonMappingCatalogueStorage mappingCatalogueStorage =
            new JsonMappingCatalogueStorage(getTempFilePath("mapping"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));

        storageManager = new StorageManager(userPrefsStorage,
                                            localCourseCatalogueStorage,
                                            partnerCourseCatalogueStorage,
                                            universityCatalogueStorage,
                                            mappingCatalogueStorage,
                                            noteCatalogueStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void localCourseCatalogueReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonLocalCourseCatalogueStorage} class.
         */
        LocalCourseCatalogue original = getTypicalLocalCourseCatalogue();
        storageManager.saveLocalCourseCatalogue(original);
        ReadOnlyLocalCourseCatalogue retrieved = storageManager.readLocalCourseCatalogue().get();
        assertEquals(original, new LocalCourseCatalogue(retrieved));
    }

    @Test
    public void getLocalCourseCatalogueFilePath() {
        assertNotNull(storageManager.getLocalCourseCatalogueFilePath());
    }

    @Test
    public void partnerCourseCatalogueReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPartnerCourseCatalogueStorage} class.
         */
        PartnerCourseCatalogue original = getTypicalPartnerCourseCatalogue();
        storageManager.savePartnerCourseCatalogue(original);
        ReadOnlyPartnerCourseCatalogue retrieved = storageManager.readPartnerCourseCatalogue().get();
        assertEquals(original, new PartnerCourseCatalogue(retrieved));
    }

    @Test
    public void getPartnerCourseCatalogueFilePath() {
        assertNotNull(storageManager.getPartnerCourseCatalogueFilePath());
    }

    @Test
    public void universityCatalogueReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUniversityCatalogueStorage} class.
         */
        UniversityCatalogue original = getTypicalUniversityCatalogue();
        storageManager.saveUniversityCatalogue(original);
        ReadOnlyUniversityCatalogue retrieved = storageManager.readUniversityCatalogue().get();
        assertEquals(original, new UniversityCatalogue(retrieved));
    }

    @Test
    public void getUniversityCatalogueFilePath() {
        assertNotNull(storageManager.getUniversityCatalogueFilePath());
    }

}
