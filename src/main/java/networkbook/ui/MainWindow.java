package networkbook.ui;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import networkbook.commons.core.GuiSettings;
import networkbook.commons.core.LogsCenter;
import networkbook.logic.Logic;
import networkbook.logic.commands.CommandResult;
import networkbook.logic.commands.FilterCommandResult;
import networkbook.logic.commands.RedoCommand;
import networkbook.logic.commands.SaveCommand;
import networkbook.logic.commands.SortCommandResult;
import networkbook.logic.commands.UndoCommand;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.logic.parser.exceptions.ParseException;
import networkbook.model.person.PersonSortComparator.SortField;
import networkbook.model.person.PersonSortComparator.SortOrder;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final KeyCombination SHORTCUT_HELP = KeyCombination.valueOf("F1");
    private static final KeyCharacterCombination SHORTCUT_EXIT =
            new KeyCharacterCombination("W", KeyCombination.SHORTCUT_DOWN);
    private static final KeyCharacterCombination SHORTCUT_UNDO_UNFOCUSED =
            new KeyCharacterCombination("Z", KeyCombination.SHORTCUT_DOWN);
    private static final KeyCharacterCombination SHORTCUT_REDO_UNFOCUSED =
            new KeyCharacterCombination("Y", KeyCombination.SHORTCUT_DOWN);
    private static final KeyCharacterCombination SHORTCUT_SAVE =
            new KeyCharacterCombination("S", KeyCombination.SHORTCUT_DOWN);
    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private CommandBox commandBox;
    private StatusBarFooter statusBarFooter;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

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

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, SHORTCUT_HELP);
        exitMenuItem.setAccelerator(SHORTCUT_EXIT);
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (KeyboardShortcutUtil.shortcutMatchEvent(SHORTCUT_EXIT, event)) {
                exitMenuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), cmd -> submitCommand(cmd));
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        statusBarFooter = new StatusBarFooter(logic.getNetworkBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        setCommandBoxShortcutsWhenUnfocused(commandBox);
    }

    private void setCommandBoxShortcutsWhenUnfocused(CommandBox commandBox) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (!commandBox.isTextFieldFocused()
                    && KeyboardShortcutUtil.shortcutMatchEvent(SHORTCUT_UNDO_UNFOCUSED, event)) {
                injectCommand(UndoCommand.COMMAND_WORD);
                event.consume();
            } else if (!commandBox.isTextFieldFocused()
                    && KeyboardShortcutUtil.shortcutMatchEvent(SHORTCUT_REDO_UNFOCUSED, event)) {
                injectCommand(RedoCommand.COMMAND_WORD);
                event.consume();
            } else if (KeyboardShortcutUtil.shortcutMatchEvent(SHORTCUT_SAVE, event)) {
                injectCommand(SaveCommand.COMMAND_WORD);
                event.consume();
            }
        });
    }

    /**
     * Executes a command that does not come from user input in the command box.
     * @param commandText is the command text to be injected and executed.
     */
    private void injectCommand(String commandText) {
        try {
            executeCommand(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("This command is injected using a keyboard shortcut");
        }
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

    /**
     * Updates sort info display.
     * @param field Sort field to display.
     * @param order Sort order to display.
     */
    public void handleSort(SortField field, SortOrder order) {
        statusBarFooter.updateSortStatus(field, order);
    }

    /**
     * Updates filter info display.
     * @param field Filter field to display.
     */
    public void handleFilter(String field) {
        statusBarFooter.updateFilterStatus(field);
    }

    /**
     * Inputs the given command string into the command box and executes it.
     * @param commandText String command to execute.
     */
    private void submitCommand(String commandText) {
        requireAllNonNull(commandText);
        commandBox.submitCommand(commandText);
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

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult instanceof SortCommandResult) {
                SortCommandResult sortCommandResult = (SortCommandResult) commandResult;
                handleSort(sortCommandResult.getSortField(), sortCommandResult.getSortOrder());
            }

            if (commandResult instanceof FilterCommandResult) {
                FilterCommandResult filterCommandResult = (FilterCommandResult) commandResult;
                handleFilter(filterCommandResult.getFilterField());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
