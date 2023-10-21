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
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.gradedcomponent.model.ReadOnlyGradedComponentBook;
import seedu.address.model.student.model.ReadOnlyStudentBook;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.model.ReadOnlyStudentScoreBook;
import seedu.address.model.studentscore.model.StudentScoreBook;
import seedu.address.model.util.SampleGcDataUtil;
import seedu.address.model.util.SampleStudentDataUtil;
import seedu.address.model.util.SampleStudentScoreDataUtil;
import seedu.address.storage.GradedComponentBookStorage;
import seedu.address.storage.JsonGradedComponentBookStorage;
import seedu.address.storage.JsonStudentBookStorage;
import seedu.address.storage.JsonStudentScoreBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.StudentBookStorage;
import seedu.address.storage.StudentScoreBookStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;


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
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        StudentBookStorage studentBookStorage = new JsonStudentBookStorage(userPrefs.getStudentBookFilePath());
        StudentScoreBookStorage studentScoreBookStorage =
                new JsonStudentScoreBookStorage(userPrefs.getStudentScoreBookFilePath());
        GradedComponentBookStorage gradedComponentBookStorage =
            new JsonGradedComponentBookStorage(userPrefs.getGcBookFilePath());
        storage = new StorageManager(studentBookStorage, studentScoreBookStorage, gradedComponentBookStorage,
            userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getStudentBookFilePath());

        Optional<ReadOnlyStudentBook> studentBookOptional;
        Optional<ReadOnlyStudentScoreBook> studentScoreBookOptional;
        Optional<ReadOnlyGradedComponentBook> gradedComponentBookOptional;
        ReadOnlyStudentBook initialStudentData = new StudentBook();
        ReadOnlyStudentScoreBook initialStudentScoreData;
        ReadOnlyGradedComponentBook initialGradedComponentData;
        try {
            studentBookOptional = storage.readStudentBook();
            studentScoreBookOptional = storage.readStudentScoreBook();
            gradedComponentBookOptional = storage.readGradedComponentBook();
            if (!studentBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getStudentBookFilePath()
                        + " populated with a sample AddressBook.");
            }
            if (!studentScoreBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getStudentBookFilePath()
                        + " populated with a sample AddressBook.");
            }
            if (!gradedComponentBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getGcBookFilePath()
                    + " populated with a sample " + "GradedComponentBook");
            }

            initialStudentData = studentBookOptional.orElseGet(SampleStudentDataUtil::getSampleStudentBook);
            initialStudentScoreData = studentScoreBookOptional
                .orElseGet(SampleStudentScoreDataUtil::getSampleStudentScoreBook);
            initialGradedComponentData = gradedComponentBookOptional
                .orElseGet(SampleGcDataUtil::getSampleGcBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getStudentBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty AddressBook.");
            initialStudentData = new StudentBook();
            initialStudentScoreData = new StudentScoreBook();
            initialGradedComponentData = new GradedComponentBook();
        }

        return new ModelManager(initialStudentData, initialStudentScoreData,
                initialGradedComponentData, userPrefs);
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
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
