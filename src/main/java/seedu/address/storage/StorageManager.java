package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.ReadOnlyNoteCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final UserPrefsStorage userPrefsStorage;
    private final LocalCourseCatalogueStorage localCourseCatalogueStorage;
    private final PartnerCourseCatalogueStorage partnerCourseCatalogueStorage;
    private final UniversityCatalogueStorage universityCatalogueStorage;
    private final MappingCatalogueStorage mappingCatalogueStorage;
    private final NoteCatalogueStorage noteCatalogueStorage;


    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UserPrefsStorage userPrefsStorage,
                          LocalCourseCatalogueStorage localCourseCatalogueStorage,
                          PartnerCourseCatalogueStorage partnerCourseCatalogueStorage,
                          UniversityCatalogueStorage universityCatalogueStorage,
                          MappingCatalogueStorage mappingCatalogueStorage,
                          NoteCatalogueStorage noteCatalogueStorage) {
        this.userPrefsStorage = userPrefsStorage;
        this.localCourseCatalogueStorage = localCourseCatalogueStorage;
        this.partnerCourseCatalogueStorage = partnerCourseCatalogueStorage;
        this.universityCatalogueStorage = universityCatalogueStorage;
        this.mappingCatalogueStorage = mappingCatalogueStorage;
        this.noteCatalogueStorage = noteCatalogueStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ LocalCourseCatalogue methods ==============================

    @Override
    public Path getLocalCourseCatalogueFilePath() {
        return localCourseCatalogueStorage.getLocalCourseCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue() throws DataLoadingException {
        return readLocalCourseCatalogue(localCourseCatalogueStorage.getLocalCourseCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return localCourseCatalogueStorage.readLocalCourseCatalogue(filePath);
    }

    @Override
    public void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue) throws IOException {
        saveLocalCourseCatalogue(localCourseCatalogue, localCourseCatalogueStorage.getLocalCourseCatalogueFilePath());
    }

    @Override
    public void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue,
                                         Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        localCourseCatalogueStorage.saveLocalCourseCatalogue(localCourseCatalogue, filePath);
    }

    // ================ University methods ==============================
    @Override
    public Path getUniversityCatalogueFilePath() {
        return universityCatalogueStorage.getUniversityCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue() throws DataLoadingException {
        return readUniversityCatalogue(universityCatalogueStorage.getUniversityCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return universityCatalogueStorage.readUniversityCatalogue(filePath);
    }

    @Override
    public void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue) throws IOException {
        saveUniversityCatalogue(universityCatalogue, universityCatalogueStorage.getUniversityCatalogueFilePath());
    }

    @Override
    public void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue,
                                        Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        universityCatalogueStorage.saveUniversityCatalogue(universityCatalogue, filePath);
    }

    // ================ PartnerCourseCatalogue methods ==============================
    @Override
    public Path getPartnerCourseCatalogueFilePath() {
        return partnerCourseCatalogueStorage.getPartnerCourseCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue() throws DataLoadingException {
        return readPartnerCourseCatalogue(partnerCourseCatalogueStorage.getPartnerCourseCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue(Path filePath)
            throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return partnerCourseCatalogueStorage.readPartnerCourseCatalogue(filePath);
    }

    @Override
    public void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) throws IOException {
        savePartnerCourseCatalogue(partnerCourseCatalogue, partnerCourseCatalogueStorage
                .getPartnerCourseCatalogueFilePath());
    }

    @Override
    public void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue,
                                           Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        partnerCourseCatalogueStorage.savePartnerCourseCatalogue(partnerCourseCatalogue, filePath);
    }
    // ================ NoteCatalogue methods ==============================

    @Override
    public Path getNoteCatalogueFilePath() {
        return noteCatalogueStorage.getNoteCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyNoteCatalogue> readNoteCatalogue() throws DataLoadingException {
        return readNoteCatalogue(noteCatalogueStorage.getNoteCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyNoteCatalogue> readNoteCatalogue(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return noteCatalogueStorage.readNoteCatalogue(filePath);
    }

    @Override
    public void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) throws IOException {
        saveNoteCatalogue(noteCatalogue, noteCatalogueStorage.getNoteCatalogueFilePath());
    }

    @Override
    public void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue,
                                  Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        noteCatalogueStorage.saveNoteCatalogue(noteCatalogue, filePath);
    }

    // ================ MappingCatalogue methods ==============================
    @Override
    public Path getMappingCatalogueFilePath() {
        return mappingCatalogueStorage.getMappingCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyMappingCatalogue> readMappingCatalogue() throws DataLoadingException {
        return readMappingCatalogue(mappingCatalogueStorage.getMappingCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyMappingCatalogue> readMappingCatalogue(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mappingCatalogueStorage.readMappingCatalogue(filePath);
    }

    @Override
    public void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue) throws IOException {
        saveMappingCatalogue(mappingCatalogue, mappingCatalogueStorage.getMappingCatalogueFilePath());
    }

    @Override
    public void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue,
                                     Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mappingCatalogueStorage.saveMappingCatalogue(mappingCatalogue, filePath);
    }

}
