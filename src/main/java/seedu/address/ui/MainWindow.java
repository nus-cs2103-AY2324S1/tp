package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
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

    private static final int TAB_PERSONS_INDEX = 0;
    private static final int TAB_TASKS_INDEX = 1;
    private static final int TAB_ASSIGNMENTS_INDEX = 2;
    private static final int TAB_SESSIONS_INDEX = 3;
    private static final int TAB_CONSULTATIONS_INDEX = 4;


    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private TaskListPanel taskListPanel;
    private ConsultationListPanel consultationListPanel;
    private SessionListPanel sessionListPanel;
    private ResultDisplay resultDisplay;
    private GraphicalResultDisplay graphicalResultDisplay;
    private HelpWindow helpWindow;
    private AssignmentNameListPanel assignmentNameListPanel;
    private AssignmentListPanel assignmentListPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane taskListPanelPlaceholder;

    @FXML
    private StackPane consultationListPanelPlaceholder;

    @FXML
    private StackPane sessionListPanelPlaceholder;

    @FXML
    private StackPane assignmentListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane resultGraphicalDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private Label assignmentListLabel;

    @FXML
    private TabPane listTabs;

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

        taskListPanel = new TaskListPanel(logic.getFilteredTaskList());
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        consultationListPanel = new ConsultationListPanel(logic.getFilteredConsultationList());
        consultationListPanelPlaceholder.getChildren().add(consultationListPanel.getRoot());

        sessionListPanel = new SessionListPanel(logic.getFilteredSessionList());
        sessionListPanelPlaceholder.getChildren().add(sessionListPanel.getRoot());

        assignmentNameListPanel = new AssignmentNameListPanel(logic.getAssignmentNameList());
        assignmentListPanelPlaceholder.getChildren().add(assignmentNameListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        graphicalResultDisplay = new GraphicalResultDisplay();
        resultGraphicalDisplayPlaceholder.getChildren().add(graphicalResultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
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
     * Displays graphically the list of assignments for a student.
     */
    public void handleViewAssignments() {
        assignmentListPanel = new AssignmentListPanel(logic.getAssignments());
        if (resultGraphicalDisplayPlaceholder.getChildren().size() != 0) {
            resultGraphicalDisplayPlaceholder.getChildren().set(0, assignmentListPanel.getRoot());
        } else {
            resultGraphicalDisplayPlaceholder.getChildren().add(assignmentListPanel.getRoot());
        }
    }

    /**
     * Clears GraphicalDisplayPlaceholder.
     */
    public void clearGraphicalResultDisplay() {
        resultGraphicalDisplayPlaceholder.getChildren().clear();
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

    /**
     * Selects the tab of the TabPane listTabs by an integer index tabIndex.
     *
     * @param tabIndex The index of tab to be switched to.
     */
    public void selectTab(int tabIndex) {
        listTabs.getSelectionModel().select(tabIndex);
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
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            switch(commandResult.getCommandType()) {
            // General
            case HELP:
                handleHelp();
                break;
            case EXIT:
                handleExit();
                break;
            case TAB:
                selectTab(commandResult.getTabIndex());
                break;
            // Assignments
            case DELETE_COMMENT:
            case DELETE_GRADE:
            case EDIT_COMMENT:
            case EDIT_GRADE:
            case VIEW_ASSIGNMENTS:
                selectTab(TAB_PERSONS_INDEX);
                handleViewAssignments();
                break;
            case EDIT_GRADED_TEST:
                selectTab(TAB_PERSONS_INDEX);
                clearGraphicalResultDisplay();
                break;
            // Students Address Book
            case ADD:
            case CLEAR:
            case DELETE:
            case EDIT:
            case FIND:
            case LIST:
                selectTab(TAB_PERSONS_INDEX);
                break;
            // Tasks
            case ADD_TASK:
            case DELETE_TASK:
            case UPDATE_TASK_PROGRESS:
            case VIEW_TASKS:
                selectTab(TAB_TASKS_INDEX);
                clearGraphicalResultDisplay();
                break;
            // Consultations
            case CREATE_CONSULT:
            case ADD_TO_CONSULT:
            case DELETE_CONSULT:
            case REMOVE_FROM_CONSULT:
                selectTab(TAB_CONSULTATIONS_INDEX);
                clearGraphicalResultDisplay();
                break;
            // Sessions
            case CREATE_SESSION:
            case DELETE_SESSION:
            case UPDATE_SESSION_REMARK:
            case TAKE_ATTENDANCE:
            case VIEW_ATTENDANCE:
                selectTab(TAB_SESSIONS_INDEX);
                clearGraphicalResultDisplay();
                break;
            default:
                clearGraphicalResultDisplay();
                break;
            }

            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
