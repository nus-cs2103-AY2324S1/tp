package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private boolean shouldDisplayTags;
    private boolean shouldDisplayContacts;
    private boolean shouldDisplayEvents;
    private boolean shouldReturnToHome;

    // Independent Ui parts residing in this Ui container
    private ContactListPanel contactListPanel;
    private TagListPanel tagListPanel;
    private EventListPanel eventListPanel;
    private EventContactDisplay eventContactDisplay;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.shouldDisplayTags = false;
        this.shouldDisplayEvents = false;
        this.shouldDisplayContacts = false;
        this.shouldReturnToHome = false;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setTagDisplayStatus(boolean status) {
        shouldDisplayTags = status;
    }

    private void setContactDisplayStatus(boolean status) {
        shouldDisplayContacts = status;
    }

    private void setEventDisplayStatus(boolean status) {
        shouldDisplayEvents = status;
    }

    private void setReturnToHomeStatus(boolean status) {
        shouldReturnToHome = status;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        if (shouldDisplayTags) {
            fillTagListPanel();
        } else if (shouldDisplayContacts) {
            fillContactListPanel();
        } else if (shouldDisplayEvents) {
            fillEventListPanel();
        } else if (shouldReturnToHome) {
            fillEventContactDisplay(true);
        } else {
            fillEventContactDisplay(false);
        }

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getJobFestGoFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Fills up the contact list panel with the contacts in the filtered contact list.
     */
    void fillContactListPanel() {

        contactListPanel = new ContactListPanel(logic.getFilteredContactList());

        if (!listPanelPlaceholder.getChildren().isEmpty()) {
            listPanelPlaceholder.getChildren().clear();
        }

        listPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }

    /**
     * Fills up the tag list panel with the tags in the filtered tag list.
     */
    void fillTagListPanel() {
        tagListPanel = new TagListPanel(logic.getFilteredTagList());

        if (!listPanelPlaceholder.getChildren().isEmpty()) {
            listPanelPlaceholder.getChildren().clear();
        }

        listPanelPlaceholder.getChildren().add(tagListPanel.getRoot());
    }

    /**
     * Fills up the event list panel with the events in the filtered event list.
     */
    void fillEventListPanel() {
        eventListPanel = new EventListPanel(logic.getFilteredEventList());

        if (!listPanelPlaceholder.getChildren().isEmpty()) {
            listPanelPlaceholder.getChildren().clear();
        }

        listPanelPlaceholder.getChildren().add(eventListPanel.getRoot());
    }

    /**
     * Fills up the event and contact display with the events and contacts
     * in the filtered event and contact list.
     */
    void fillEventContactDisplay(boolean shouldReset) {
        eventContactDisplay = new EventContactDisplay(logic, shouldReset);

        if (!listPanelPlaceholder.getChildren().isEmpty()) {
            listPanelPlaceholder.getChildren().clear();
        }

        listPanelPlaceholder.getChildren().add(eventContactDisplay.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public ContactListPanel getContactListPanel() {
        return contactListPanel;
    }

    public TagListPanel getTagListPanel() {
        return tagListPanel;
    }

    public EventListPanel getEventListPanel() {
        return eventListPanel;
    }

    public void updateResultDisplay(CommandResult commandResult) {
        resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
    }

    /**
     * Shows the contacts panel to the user if the user wants to view contacts.
     * @param shouldDisplayContacts true if the user wants to view contacts
     */
    public void handleViewContacts(boolean shouldDisplayContacts) {
        setContactDisplayStatus(shouldDisplayContacts);
        setTagDisplayStatus(false);
        setReturnToHomeStatus(false);
        fillInnerParts();
    }

    /**
     * Shows the tags panel to the user.
     */
    public void handleViewTags(boolean shouldDisplayTags) {
        setTagDisplayStatus(shouldDisplayTags);
        setContactDisplayStatus(false);
        setReturnToHomeStatus(false);
        fillInnerParts();
    }

    /**
     * Shows the events panel to the user if the user wants to view events.
     * @param shouldDisplayEvents true if the user wants to view events
     */
    public void handleViewEvents(boolean shouldDisplayEvents) {
        setEventDisplayStatus(shouldDisplayEvents);
        setContactDisplayStatus(false);
        setTagDisplayStatus(false);
        setReturnToHomeStatus(false);
        fillInnerParts();
    }

    /**
     * Shows the event and contact panel to the user.
     */
    public void handleElse() {
        setTagDisplayStatus(false);
        setContactDisplayStatus(false);
        setEventDisplayStatus(false);
        fillInnerParts();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.shouldDisplayContactsPanel()) {
                handleViewContacts(true);
            }

            if (commandResult.shouldDisplayTagsPanel()) {
                handleViewTags(true);
            }

            if (commandResult.shouldDisplayEventsPanel()) {
                handleViewEvents(true);
            }

            boolean shouldReturnHome = commandResult.shouldReturnToHome();
            setReturnToHomeStatus(shouldReturnHome);

            if (commandResult.shouldHideAllPanels()) {
                if (!commandResult.shouldStayOnScreen()) {
                    handleElse();
                }
                // to display that an event is selected
                eventContactDisplay.setFeedbackToUser(commandResult);
            }

            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
