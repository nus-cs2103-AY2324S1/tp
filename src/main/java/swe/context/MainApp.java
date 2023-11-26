package swe.context;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import swe.context.annotation.Nullable;
import swe.context.commons.core.Config;
import swe.context.commons.core.LogsCenter;
import swe.context.commons.exceptions.DataLoadingException;
import swe.context.commons.util.ConfigUtil;
import swe.context.commons.util.StringUtil;
import swe.context.logic.Logic;
import swe.context.logic.LogicManager;
import swe.context.model.Contacts;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.ReadOnlyContacts;
import swe.context.model.ReadOnlySettings;
import swe.context.model.Settings;
import swe.context.model.util.SampleContactsUtil;
import swe.context.storage.ContactsStorage;
import swe.context.storage.JsonContactsStorage;
import swe.context.storage.JsonSettingsStorage;
import swe.context.storage.SettingsStorage;
import swe.context.storage.Storage;
import swe.context.storage.StorageManager;
import swe.context.ui.Ui;
import swe.context.ui.UiManager;



/**
 * Runs as a JavaFX application.
 */
public class MainApp extends Application {
    public static final String NAME = "ConText";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private Config config;

    private Ui ui;
    private Logic logic;
    private Model model;
    private Storage storage;

    @Override
    public void init() throws Exception {
        super.init();

        AppParameters appParameters = AppParameters.parse(this.getParameters());
        config = this.initConfig(appParameters.getConfigPath());

        this.initLogging(config);

        SettingsStorage settingsStorage = new JsonSettingsStorage(config.getSettingsPath());
        ReadOnlySettings settings = this.initSettings(settingsStorage);

        ContactsStorage contactsStorage = new JsonContactsStorage(settings.getContactsPath());
        storage = new StorageManager(contactsStorage, settingsStorage);

        model = this.initModel(storage, settings);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        try {
            storage.saveSettings(model.getSettings());
        } catch (IOException e) {
            logger.severe(String.format(
                "Failed to save settings: %s",
                StringUtil.getDetails(e)
            ));
        }
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    private Config initConfig(@Nullable Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_PATH;

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

        // Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns {@link Settings} from reading via the specified
     * {@link SettingsStorage}.
     *
     * If no file is found for the {@link SettingsStorage} or an error occurs
     * while trying to read the file, default settings will be populated.
     *
     * This always resaves the updated file.
     */
    private Settings initSettings(SettingsStorage settingsStorage) {
        Path settingsPath = settingsStorage.getSettingsPath();
        logger.info(String.format(
            "Settings storage path: %s",
            settingsPath
        ));

        @Nullable Settings settings = null;
        try {
            Optional<Settings> settingsOptional = settingsStorage.readSettings();
            if (settingsOptional.isPresent()) {
                settings = settingsOptional.get();
            } else {
                logger.info("No settings file found, populating with default settings.");
                settings = new Settings();
            }
        } catch (DataLoadingException e) {
            logger.warning("Failed to read settings file, populating with default settings.");
            settings = new Settings();
        }

        try {
            settingsStorage.saveSettings(settings);
        } catch (IOException e) {
            logger.severe(String.format(
                "Failed to save settings: %s",
                StringUtil.getDetails(e)
            ));
        }

        return settings;
    }

    /**
     * Returns a {@link Model} with the {@link ReadOnlyContacts} from reading via the
     * specified {@link Storage}, and with the specified
     * {@link ReadOnlySettings}.
     *
     * If no contacts file is found for the {@link Storage}, sample contacts
     * will be populated.
     *
     * If an error occurs while trying to read the file, no contacts will be
     * populated.
     */
    private Model initModel(Storage storage, ReadOnlySettings settings) {
        logger.info(String.format(
            "Storage contacts path: %s",
            storage.getContactsPath()
        ));

        @Nullable ReadOnlyContacts contacts = null;
        try {
            Optional<? extends ReadOnlyContacts> contactsOptional = storage.readContacts();
            if (contactsOptional.isPresent()) {
                contacts = contactsOptional.get();
            } else {
                logger.info("No contacts file found, populating with sample contacts.");
                contacts = SampleContactsUtil.getSampleContacts();
            }
        } catch (DataLoadingException e) {
            logger.warning("Failed to read contacts file.");
            contacts = new Contacts();
        }

        return new ModelManager(contacts, settings);
    }
}
