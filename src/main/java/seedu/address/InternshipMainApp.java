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
import seedu.address.logic.InternshipLogic;
import seedu.address.logic.InternshipLogicManager;
import seedu.address.model.InternshipBook;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyInternshipUserPrefs;
import seedu.address.model.util.InternshipSampleDataUtil;
import seedu.address.storage.InternshipBookStorage;
import seedu.address.storage.InternshipStorage;
import seedu.address.storage.InternshipStorageManager;
import seedu.address.storage.InternshipUserPrefsStorage;
import seedu.address.storage.JsonInternshipBookStorage;
import seedu.address.storage.JsonInternshipUserPrefsStorage;
import seedu.address.ui.InternshipUiManager;

/**
 * Runs the application.
 */
public class InternshipMainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(InternshipMainApp.class);

    protected InternshipUiManager ui;
    protected InternshipLogic logic;
    protected InternshipStorage storage;
    protected InternshipModel model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing InternshipBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        InternshipUserPrefsStorage userPrefsStorage = new JsonInternshipUserPrefsStorage(config.getUserPrefsFilePath());
        InternshipUserPrefs userPrefs = initPrefs(userPrefsStorage);
        InternshipBookStorage internshipBookStorage = new JsonInternshipBookStorage(userPrefs.getInternshipFilePath());
        storage = new InternshipStorageManager(internshipBookStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new InternshipLogicManager(model, storage);

        ui = new InternshipUiManager(logic);
    }

    /**
     * Returns a {@code InternshipModelManager} with the data from {@code InternshipStorage}'s internship book
     * and {@code InternshipUserPrefs}. <br> The data from the sample internship book will
     * be used instead if {@code InternshipStorage}'s internship book is not found,
     * or an empty internship book will be used instead if errors occur
     * when reading {@code InternshipStorage}'s internship book.
     */
    private InternshipModel initModelManager(InternshipStorage storage, ReadOnlyInternshipUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getInternshipBookFilePath());
        Optional<ReadOnlyInternshipBook> internshipBookOptional;
        ReadOnlyInternshipBook initialData;
        try {
            internshipBookOptional = storage.readInternshipBook();
            if (!internshipBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getInternshipBookFilePath()
                        + " populated with a sample InternshipBook.");
            }
            initialData = internshipBookOptional.orElseGet(InternshipSampleDataUtil::getSampleInternshipBook);

        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getInternshipBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty InternshipBook.");
            initialData = new InternshipBook();
        }

        return new InternshipModelManager(initialData, userPrefs);
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
            if (!configOptional.isPresent()) {
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
    protected InternshipUserPrefs initPrefs(InternshipUserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        InternshipUserPrefs initializedPrefs;
        try {
            Optional<InternshipUserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new InternshipUserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new InternshipUserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting InternshipBook " + InternshipMainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Internship Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
