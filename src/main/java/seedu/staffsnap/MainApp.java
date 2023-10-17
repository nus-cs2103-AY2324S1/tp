package seedu.staffsnap;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import seedu.staffsnap.commons.core.Config;
import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.commons.core.Version;
import seedu.staffsnap.commons.exceptions.DataLoadingException;
import seedu.staffsnap.commons.util.ConfigUtil;
import seedu.staffsnap.commons.util.StringUtil;
import seedu.staffsnap.logic.Logic;
import seedu.staffsnap.logic.LogicManager;
import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.ReadOnlyApplicantBook;
import seedu.staffsnap.model.ReadOnlyUserPrefs;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.util.SampleDataUtil;
import seedu.staffsnap.storage.ApplicantBookStorage;
import seedu.staffsnap.storage.JsonApplicantBookStorage;
import seedu.staffsnap.storage.JsonUserPrefsStorage;
import seedu.staffsnap.storage.Storage;
import seedu.staffsnap.storage.StorageManager;
import seedu.staffsnap.storage.UserPrefsStorage;
import seedu.staffsnap.ui.Ui;
import seedu.staffsnap.ui.UiManager;

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
    protected HostServices hostServices = getHostServices();

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Staff-Snap ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ApplicantBookStorage applicantBookStorage = new JsonApplicantBookStorage(userPrefs.getApplicantBookFilePath());
        storage = new StorageManager(applicantBookStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic, hostServices);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s applicant book and {@code userPrefs}. <br>
     * The data from the sample applicant book will be used instead if {@code storage}'s applicant book is not found,
     * or an empty applicant book will be used instead if errors occur when reading {@code storage}'s applicant book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getApplicantBookFilePath());

        Optional<ReadOnlyApplicantBook> applicantBookOptional;
        ReadOnlyApplicantBook initialData;
        try {
            applicantBookOptional = storage.readApplicantBook();
            if (!applicantBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getApplicantBookFilePath()
                        + " populated with a sample ApplicantBook.");
            }
            initialData = applicantBookOptional.orElseGet(SampleDataUtil::getSampleApplicantBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getApplicantBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty ApplicantBook.");
            initialData = new ApplicantBook();
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
        logger.info("Starting ApplicantBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Staff-Snap ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
