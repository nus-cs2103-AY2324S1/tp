package seedu.codesphere.ui;

import static seedu.codesphere.ui.ExternalLinks.DEVELOPERGUIDE_URL;
import static seedu.codesphere.ui.ExternalLinks.GITHUB_URL;
import static seedu.codesphere.ui.ExternalLinks.USERGUIDE_URL;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.logic.Logic;
import seedu.codesphere.logic.commands.CommandResult;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.logic.stagemanager.Stages;
import seedu.codesphere.storage.Storage;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiComponent<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private Storage storage;

    // Independent Ui parts residing in this Ui container
    private DisplayPanel displayPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem ugMenuItem;
    @FXML

    private MenuItem dgMenuItem;
    @FXML
    private MenuItem ghMenuItem;

    @FXML
    private StackPane panelPlaceholder;

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
        this.storage = logic.getStorage();

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();
        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(ugMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(dgMenuItem, KeyCombination.valueOf("F2"));
        setAccelerator(ghMenuItem, KeyCombination.valueOf("F3"));
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
    void fillInnerParts() throws InterruptedException {

        displayPanel = new DisplayPanel(logic);
        panelPlaceholder.getChildren().add(displayPanel.getRoot());
        displayPanel.loadStartSequence();

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getCourseListFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, storage);
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
     * Opens the User Guide in the default browser
     */
    @FXML
    public void openUG() {
        try {
            Desktop.getDesktop().browse(URI.create(USERGUIDE_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the User Guide in the default browser
     */
    @FXML
    public void openDG() {
        try {
            Desktop.getDesktop().browse(URI.create(DEVELOPERGUIDE_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the Team's GitHub Repository in the default browser
     */
    @FXML
    public void openGH() {
        try {
            Desktop.getDesktop().browse(URI.create(GITHUB_URL));
        } catch (IOException e) {
            e.printStackTrace();
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
        logger.info("Exiting application");
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
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

            StageManager stageManager = StageManager.getInstance();

            Stages current = stageManager.getStage();
            if (current == Stages.SELECTED_COURSE) {
                displayPanel.loadCombinedPanel();
            } else if (current == Stages.HOME) {
                displayPanel.loadCourseListPanel();
            }

            storage.addValidInput(commandText);
            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            storage.addInvalidInput(commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
