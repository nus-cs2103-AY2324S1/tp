package seedu.address;

import static seedu.address.model.util.SampleDataUtil.getSampleLocalCourseCatalogue;
import static seedu.address.model.util.SampleDataUtil.getSampleMappingCatalogue;
import static seedu.address.model.util.SampleDataUtil.getSampleNoteCatalogue;
import static seedu.address.model.util.SampleDataUtil.getSamplePartnerCourseCatalogue;
import static seedu.address.model.util.SampleDataUtil.getSampleUniversityCatalogue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.SeplendidLogic;
import seedu.address.logic.SeplendidLogicManager;
import seedu.address.model.ReadOnlyLocalCourseCatalogue;
import seedu.address.model.ReadOnlyMappingCatalogue;
import seedu.address.model.ReadOnlyNoteCatalogue;
import seedu.address.model.ReadOnlyPartnerCourseCatalogue;
import seedu.address.model.ReadOnlyUniversityCatalogue;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.seplendidui.Ui;
import seedu.address.seplendidui.UiManager;
import seedu.address.storage.JsonLocalCourseCatalogueStorage;
import seedu.address.storage.JsonMappingCatalogueStorage;
import seedu.address.storage.JsonNoteCatalogueStorage;
import seedu.address.storage.JsonPartnerCourseCatalogueStorage;
import seedu.address.storage.JsonUniversityCatalogueStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.LocalCourseCatalogueStorage;
import seedu.address.storage.MappingCatalogueStorage;
import seedu.address.storage.NoteCatalogueStorage;
import seedu.address.storage.PartnerCourseCatalogueStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UniversityCatalogueStorage;
import seedu.address.storage.UserPrefsStorage;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;

    protected SeplendidLogic seplendidLogic;

    protected Storage storage;

    protected SeplendidModel seplendidModel;
    protected Config config;

    protected Font font;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        LocalCourseCatalogueStorage localCourseCatalogueStorage =
                new JsonLocalCourseCatalogueStorage(userPrefs.getLocalCourseCatalogueFilePath());
        PartnerCourseCatalogueStorage partnerCourseCatalogue =
                new JsonPartnerCourseCatalogueStorage(userPrefs.getPartnerCourseCatalogueFilePath());
        UniversityCatalogueStorage universityCatalogueStorage =
                new JsonUniversityCatalogueStorage(userPrefs.getUniversityCatalogueFilePath());
        MappingCatalogueStorage mappingCatalogueStorage =
                new JsonMappingCatalogueStorage(userPrefs.getMappingCatalogueFilePath());
        NoteCatalogueStorage noteCatalogueStorage =
                new JsonNoteCatalogueStorage(userPrefs.getNoteCatalogueFilePath());

        storage = new StorageManager(
                userPrefsStorage,
                localCourseCatalogueStorage,
                partnerCourseCatalogue,
                universityCatalogueStorage,
                mappingCatalogueStorage,
                noteCatalogueStorage);

        seplendidModel = initSeplendidModelManager(storage, userPrefs);
        seplendidLogic = new SeplendidLogicManager(seplendidModel, storage);

        ui = new UiManager(seplendidLogic);
        seplendidLogic.setUi(ui);
    }

    /**
     * Returns a {@code SeplendidModelManager} with the data from {@code storage, LocalCourseCatalogue,
     * PartnerCourseCatalogue, UniversityCatalogue, NoteCatalogue, userPrefs}.
     * <br>
     * Sample data will be used instead if {@code storage}'s data is not found,
     * or an empty dataset will be used instead if errors occur when reading {@code storage}'s data.
     */
    private SeplendidModelManager initSeplendidModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        // The below needs to be repeated several times for the different data types

        Optional<ReadOnlyLocalCourseCatalogue> localCourseCatalogueOptional;
        Optional<ReadOnlyPartnerCourseCatalogue> partnerCourseCatalogueOptional;
        Optional<ReadOnlyUniversityCatalogue> universityCatalogueOptional;
        Optional<ReadOnlyMappingCatalogue> mappingCatalogueOptional;
        Optional<ReadOnlyNoteCatalogue> noteCatalogueOptional;

        ReadOnlyPartnerCourseCatalogue initialPartnerCourseCatalogue;
        ReadOnlyLocalCourseCatalogue initialLocalCourseCatalogue;
        ReadOnlyUniversityCatalogue initialUniversityCatalogue;
        ReadOnlyMappingCatalogue initialMappingCatalogue;
        ReadOnlyNoteCatalogue initialNoteCatalogue;

        logger.info("Using data file : " + storage.getLocalCourseCatalogueFilePath());
        logger.info("Using data file : " + storage.getPartnerCourseCatalogueFilePath());
        logger.info("Using data file : " + storage.getUniversityCatalogueFilePath());
        logger.info("Using data file : " + storage.getMappingCatalogueFilePath());
        logger.info("Using data file : " + storage.getNoteCatalogueFilePath());

        try {
            localCourseCatalogueOptional = storage.readLocalCourseCatalogue();
            partnerCourseCatalogueOptional = storage.readPartnerCourseCatalogue();
            universityCatalogueOptional = storage.readUniversityCatalogue();
            mappingCatalogueOptional = storage.readMappingCatalogue();
            noteCatalogueOptional = storage.readNoteCatalogue();

            if (localCourseCatalogueOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getLocalCourseCatalogueFilePath()
                        + " populated with a sample LocalCourseCatalogue.");
            }
            initialLocalCourseCatalogue = localCourseCatalogueOptional.orElseGet(
                    SampleDataUtil::getSampleLocalCourseCatalogue);

            if (partnerCourseCatalogueOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getPartnerCourseCatalogueFilePath()
                        + " populated with a sample PartnerCourseCatalogue.");
            }
            initialPartnerCourseCatalogue = partnerCourseCatalogueOptional.orElseGet(
                    SampleDataUtil::getSamplePartnerCourseCatalogue);

            if (universityCatalogueOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getUniversityCatalogueFilePath()
                        + " populated with a sample UniversityCatalogue.");
            }
            initialUniversityCatalogue = universityCatalogueOptional.orElseGet(
                    SampleDataUtil::getSampleUniversityCatalogue);

            if (noteCatalogueOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getNoteCatalogueFilePath()
                        + " populated with a sample NoteCatalogue.");
            }
            initialNoteCatalogue = noteCatalogueOptional.orElseGet(
                    SampleDataUtil::getSampleNoteCatalogue);

            if (mappingCatalogueOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getMappingCatalogueFilePath()
                        + " populated with a sample MappingCatalogue.");
            }
            initialMappingCatalogue = mappingCatalogueOptional.orElseGet(
                    SampleDataUtil::getSampleMappingCatalogue);
        } catch (DataLoadingException e) {
            // Defensive programming
            logger.warning("Data file(s) could not be loaded."
                    + " Will be starting with empty catalogues.");
            initialLocalCourseCatalogue = getSampleLocalCourseCatalogue();
            initialPartnerCourseCatalogue = getSamplePartnerCourseCatalogue();
            initialUniversityCatalogue = getSampleUniversityCatalogue();
            initialMappingCatalogue = getSampleMappingCatalogue();
            initialNoteCatalogue = getSampleNoteCatalogue();
        }
        return new SeplendidModelManager(
                userPrefs,
                initialLocalCourseCatalogue,
                initialPartnerCourseCatalogue,
                initialUniversityCatalogue,
                initialMappingCatalogue,
                initialNoteCatalogue);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (configOptional.isEmpty()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (prefsOptional.isEmpty()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The overriding start method for MainApp class. the entry point.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(seplendidModel.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
