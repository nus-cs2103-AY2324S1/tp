package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.ReadOnlyNoteCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends LocalCourseCatalogueStorage, UserPrefsStorage,
        PartnerCourseCatalogueStorage, UniversityCatalogueStorage,
        NoteCatalogueStorage, MappingCatalogueStorage {

    // ================ UserPrefs methods ==============================
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    // ================ LocalCourseCatalogue methods ==============================
    @Override
    Path getLocalCourseCatalogueFilePath();

    @Override
    Optional<ReadOnlyLocalCourseCatalogue> readLocalCourseCatalogue() throws DataLoadingException;

    @Override
    Optional<ReadOnlyUniversityCatalogue> readUniversityCatalogue() throws DataLoadingException;

    @Override
    void saveLocalCourseCatalogue(ReadOnlyLocalCourseCatalogue localCourseCatalogue) throws IOException;

    // ================ PartnerCourseCatalogue methods ==============================
    @Override
    Path getPartnerCourseCatalogueFilePath();

    @Override
    Optional<ReadOnlyPartnerCourseCatalogue> readPartnerCourseCatalogue() throws DataLoadingException;

    @Override
    void savePartnerCourseCatalogue(ReadOnlyPartnerCourseCatalogue partnerCourseCatalogue) throws IOException;

    // ================ UniversityCatalogue methods ==============================
    @Override
    void saveUniversityCatalogue(ReadOnlyUniversityCatalogue universityCatalogue) throws IOException;

    // ================ NoteCatalogue methods ==============================
    @Override
    Path getNoteCatalogueFilePath();

    @Override
    Optional<ReadOnlyNoteCatalogue> readNoteCatalogue() throws DataLoadingException;

    @Override
    void saveNoteCatalogue(ReadOnlyNoteCatalogue noteCatalogue) throws IOException;

    // ================ MappingCatalogue methods ==============================
    @Override
    Path getMappingCatalogueFilePath();

    @Override
    Optional<ReadOnlyMappingCatalogue> readMappingCatalogue() throws DataLoadingException;

    @Override
    void saveMappingCatalogue(ReadOnlyMappingCatalogue mappingCatalogue) throws IOException;
}
