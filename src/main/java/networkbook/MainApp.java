package networkbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import networkbook.commons.core.Config;
import networkbook.commons.core.LogsCenter;
import networkbook.commons.core.Version;
import networkbook.commons.exceptions.DataLoadingException;
import networkbook.commons.exceptions.NullValueException;
import networkbook.commons.util.ConfigUtil;
import networkbook.commons.util.StringUtil;
import networkbook.logic.Logic;
import networkbook.logic.LogicManager;
import networkbook.model.Model;
import networkbook.model.ModelManager;
import networkbook.model.NetworkBook;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.ReadOnlyUserPrefs;
import networkbook.model.UserPrefs;
import networkbook.model.util.SampleDataUtil;
import networkbook.storage.JsonNetworkBookStorage;
import networkbook.storage.JsonUserPrefsStorage;
import networkbook.storage.NetworkBookStorage;
import networkbook.storage.Storage;
import networkbook.storage.StorageManager;
import networkbook.storage.UserPrefsStorage;
import networkbook.ui.Ui;
import networkbook.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing NetworkBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        NetworkBookStorage networkBookStorage = new JsonNetworkBookStorage(userPrefs.getNetworkBookFilePath());
        storage = new StorageManager(networkBookStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s network book and {@code userPrefs}. <br>
     * The data from the sample network book will be used instead if {@code storage}'s network book is not found,
     * or an empty network book will be used instead if errors occur when reading {@code storage}'s network book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getNetworkBookFilePath());

        Optional<ReadOnlyNetworkBook> networkBookOptional;
        ReadOnlyNetworkBook initialData;
        try {
            networkBookOptional = storage.readNetworkBook();
            if (!networkBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getNetworkBookFilePath()
                        + " populated with a sample NetworkBook.");
            }
            initialData = networkBookOptional.orElseGet(SampleDataUtil::getSampleNetworkBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getNetworkBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty NetworkBook.");
            initialData = new NetworkBook();
        } catch (NullValueException e) {
            logger.warning(e.getMessage());
            logger.warning("Starting with an empty NetworkBook.");
            initialData = new NetworkBook();
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
        } catch (NullValueException e) {
            logger.warning(e.getMessage());
            logger.warning("Using default config properties.");
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
        } catch (NullValueException e) {
            logger.warning(e.getMessage());
            logger.warning("Using default preferences");
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
        logger.info("Starting NetworkBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Network Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
