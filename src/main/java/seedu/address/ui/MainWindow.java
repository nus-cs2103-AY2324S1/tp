package seedu.address.ui;


import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
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
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private final Stage primaryStage;
    private final Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private PersonProfile personProfile;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    private Index targetIndex;
    private boolean isSaved;

    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane personProfilePlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private CommandBox commandBox;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this, this::executeCommand);
        this.commandBox = commandBox;
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
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

    void show() {
        primaryStage.show();
    }

    void sendFeedback(String feedback) {
        resultDisplay.setFeedbackToUser(feedback);
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    private void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
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

    @FXML
    private void handleFocusField(PersonProfile.Field field) {
        if (personProfilePlaceholder.isVisible()) {
            personProfile.setFocus(field);
        }
    }

    private void resetValues() {
        if (personProfilePlaceholder.isVisible()) {
            personProfile.resetValues();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult;
            if (personListPanelPlaceholder.isVisible()) {
                commandResult = logic.execute(commandText);
            } else {
                commandResult = logic.executeInView(commandText, personProfile.getPerson(), targetIndex);
            }

            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.getCommandType() == null) {
                return commandResult;
            }

            switch (commandResult.getCommandType()) {

            case HELP:
                handleHelp();
                break;

            case EXIT:
                handleExit();
                break;

            case VIEW:
                targetIndex = commandResult.getTargetIndex();
                handleView(commandResult.getPersonToView());
                break;

            case VIEW_EXIT:
                isSaved = commandResult.getIsFostererEdited();
                handleViewExit();
                break;

            case EDIT_FIELD:
                handleEditField(commandText);
                break;

            case SAVE:
                handleSave();
                break;
            }

            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            resetValues();
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DataLoadingException e) {
            throw new RuntimeException(e);
        }
    }

    void handleViewExit() {
        // if the fosterer is already saved or the confirmation message is already displayed, then exit the profile page.
        if (isSaved || commandBox.getInConfirmationDialog()) {
            commandBox.setInConfirmationDialog(false);
            personProfile.setIsInConfirmationDialog(false);
            personListPanelPlaceholder.setVisible(true);
            personProfilePlaceholder.getChildren().remove(personProfile.getRoot());
            personProfilePlaceholder.setVisible(false);
            sendFeedback("Exiting view as requested.");
        } else {
            // if fosterer is not saved or message is not displayed before, display the confirmation message
            commandBox.setInConfirmationDialog(true);
            personProfile.setIsInConfirmationDialog(true);
        }
    }

    void handleCancelViewExit() {
        sendFeedback("Cancelled exit.");
        commandBox.setInConfirmationDialog(false);
        personProfile.setIsInConfirmationDialog(false);
    }


    /**
     * Opens a fosterer's profile.
     *
     * @param personToView is a person to view their profile.
     */
    private void handleView(Person personToView) {

        // open a profile of a fosterer in the list
        if (personListPanelPlaceholder.isVisible() && personToView != null) {
            personProfile = new PersonProfile(personToView, this);
        }

        // open an empty profile to add a new fosterer
        if (personListPanelPlaceholder.isVisible() && personToView == null) {
            personProfile = new PersonProfile(this);
        }

        personProfilePlaceholder.getChildren().add(personProfile.getRoot());
        personProfilePlaceholder.setVisible(true);
        personListPanelPlaceholder.setVisible(false);
    }

    private void handleEditField(String commandText) {
        String[] tagAndNote = new String[]{"tags", "notes"};
        Optional<PersonProfile.Field> field;
        Optional<String> tagOrNote = null;

        // check if the command text matches any one of the prefix of the fields
        field = findPrefixMatchField(commandText);

        // if no match, look for the prefix match from either tags or notes
        if (!field.isPresent()) {
            tagOrNote = findPrefixMatchString(commandText, tagAndNote);
        }

        // if still no match, look for a field name that contains the command text
        if (!field.isPresent() && !tagOrNote.isPresent()) {
            field = findContainsMatchField(commandText);
        }

        // if still no match, look for a contain match from either tags or notes
        if (!field.isPresent() && !tagOrNote.isPresent()) {
            tagOrNote = findContainsMatchString(commandText, tagAndNote);
        }

        // try setting focus
        setFocus(field, tagOrNote);

        // if no match is found, then display no such field is found
        if (!field.isPresent() && !tagOrNote.isPresent()) {
            sendFeedback("No such field found");
        }
    }

    private Optional<PersonProfile.Field> findPrefixMatchField(String commandText) {
        Optional<PersonProfile.Field> field = Arrays.stream(PersonProfile.Field.values())
                .filter(f -> f.getDisplayName().toLowerCase().startsWith(commandText.toLowerCase().trim()))
                .findFirst();

        return field;
    }

    private Optional<String> findPrefixMatchString(String commandText, String[] tagAndNote) {
        Optional<String> field = Arrays.stream(tagAndNote)
                .filter(f -> f.startsWith(commandText.toLowerCase().trim()))
                .findFirst();

        return field;
    }

    private Optional<PersonProfile.Field> findContainsMatchField(String commandText) {
        Optional<PersonProfile.Field> field = Arrays.stream(PersonProfile.Field.values())
                .filter(f -> f.getDisplayName().toLowerCase().contains(commandText.toLowerCase().trim()))
                .findFirst();

        return field;
    }

    private Optional<String> findContainsMatchString(String commandText, String[] tagAndNote) {
        Optional<String> field = Arrays.stream(tagAndNote)
                .filter(f -> f.contains(commandText.toLowerCase().trim()))
                .findFirst();

        return field;
    }

    private void setFocus(Optional<PersonProfile.Field> field, Optional<String> tagOrNote) {
        // if found a match from fields that are neither tags nor notes, then call setFocus
        if (field.isPresent()) {
            field.ifPresent(personProfile::setFocus);
        }

        // if the fields are either tag or note, then call setFocus accordingly
        if (!field.isPresent() && tagOrNote.isPresent()) {
            tagOrNote.ifPresent(f -> {
                if (f.equals("tags")) {
                    personProfile.setFocusTags();
                } else {
                    personProfile.setFocusNotes();
                }
            });
        }
    }

    private void handleSave() {
        resetValues();
    }
}