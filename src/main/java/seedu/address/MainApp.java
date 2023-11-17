package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.AnimalLogic;
import seedu.address.logic.AnimalLogicManager;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalReadOnlyUserPrefs;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.ReadOnlyAnimalCatalog;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AnimalCatalogStorage;
import seedu.address.storage.AnimalStorage;
import seedu.address.storage.AnimalStorageManager;
import seedu.address.storage.AnimalUserPrefsStorage;
import seedu.address.storage.JsonAnimalCatalogStorage;
import seedu.address.storage.JsonAnimalUserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected AnimalLogic animalLogic;
    protected AnimalStorage storage;
    protected AnimalModel animalModel;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Pawfection ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        AnimalUserPrefsStorage userPrefsStorage = new JsonAnimalUserPrefsStorage(config.getUserPrefsFilePath());
        AnimalUserPrefs userPrefs = initPrefs(userPrefsStorage);
        AnimalCatalogStorage animalCatalogStorage = new JsonAnimalCatalogStorage(userPrefs.getAnimalCatalogFilePath());
        storage = new AnimalStorageManager(animalCatalogStorage, userPrefsStorage);

        animalModel = initModelManager(storage, userPrefs);

        animalLogic = new AnimalLogicManager(animalModel, storage);

        ui = new UiManager(animalLogic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s animal catalog and {@code userPrefs}. <br>
     * The data from the sample animal catalog will be used instead if {@code storage}'s animal catalog is not found,
     * or an empty animal catalog will be used instead if errors occur when reading {@code storage}'s animal catalog.
     */
    private AnimalModel initModelManager(AnimalStorage storage, AnimalReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getAnimalCatalogFilePath());

        Optional<ReadOnlyAnimalCatalog> animalCatalogOptional;
        ReadOnlyAnimalCatalog initialData;
        try {
            animalCatalogOptional = storage.readAnimalCatalog();
            if (animalCatalogOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getAnimalCatalogFilePath()
                        + " populated with a sample Animal Catalog.");
            }
            initialData = animalCatalogOptional.orElseGet(SampleDataUtil::getSampleAnimalCatalog);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getAnimalCatalogFilePath() + " could not be loaded."
                    + " Will be starting with an empty Animal Catalog.");
            initialData = new AnimalCatalog();
        }
        return new AnimalModelManager(initialData, userPrefs);
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

        // Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code AnimalUserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code AnimalUserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected AnimalUserPrefs initPrefs(AnimalUserPrefsStorage storage) {
        Path prefsFilePath = storage.getAnimalUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        AnimalUserPrefs initializedPrefs;
        try {
            Optional<AnimalUserPrefs> prefsOptional = storage.readAnimalUserPrefs();
            if (prefsOptional.isEmpty()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new AnimalUserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new AnimalUserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveAnimalUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Pawfection " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Pawfection ] =============================");
        try {
            storage.saveAnimalUserPrefs(animalModel.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
