package seedu.cc;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.cc.commons.core.Config;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.commons.core.Version;
import seedu.cc.commons.exceptions.DataLoadingException;
import seedu.cc.commons.util.ConfigUtil;
import seedu.cc.commons.util.StringUtil;
import seedu.cc.logic.Logic;
import seedu.cc.logic.LogicManager;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.ReadOnlyUserPrefs;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.util.SampleDataUtil;
import seedu.cc.storage.ClinicBookStorage;
import seedu.cc.storage.JsonClinicBookStorage;
import seedu.cc.storage.JsonUserPrefsStorage;
import seedu.cc.storage.Storage;
import seedu.cc.storage.StorageManager;
import seedu.cc.storage.UserPrefsStorage;
import seedu.cc.ui.Ui;
import seedu.cc.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ClinicBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ClinicBookStorage clinicBookStorage = new JsonClinicBookStorage(userPrefs.getClinicBookFilePath());
        storage = new StorageManager(clinicBookStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s clinic book and {@code userPrefs}. <br>
     * The data from the sample clinic book will be used instead if {@code storage}'s clinic book is not found,
     * or an empty clinic book will be used instead if errors occur when reading {@code storage}'s clinic book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getClinicBookFilePath());

        Optional<ReadOnlyClinicBook> clinicBookOptional;
        ReadOnlyClinicBook initialData;
        try {
            clinicBookOptional = storage.readClinicBook();
            if (!clinicBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getClinicBookFilePath()
                    + " populated with a sample ClinicBook.");
            }
            initialData = clinicBookOptional.orElseGet(SampleDataUtil::getSampleClinicBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getClinicBookFilePath() + " could not be loaded."
                + " Will be starting with an empty ClinicBook.");
            initialData = new ClinicBook();
        }

        return new ModelManager(initialData, userPrefs);
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
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
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

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting ClinicBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Clinic Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
