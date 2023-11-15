package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
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

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private CourseListPanel courseListPanel;
    private TagListPanel tagListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private FeedbackWindow feedbackWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private MenuItem feedbackMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane courseListPanelPlaceholder;
    @FXML
    private StackPane tagListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private MenuItem lightModeMenuItem;

    @FXML
    private MenuItem darkModeMenuItem;
    @FXML
    private Label numberStudents;
    @FXML
    private Label numberCourses;
    @FXML
    private Label numberTags;

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
        feedbackWindow = new FeedbackWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(lightModeMenuItem, KeyCombination.valueOf("F2"));
        setAccelerator(darkModeMenuItem, KeyCombination.valueOf("F3"));
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
        updateTotalStudents();

        courseListPanel = new CourseListPanel(logic.getFilteredCourseList());
        courseListPanelPlaceholder.getChildren().add(courseListPanel.getRoot());
        updateTotalCourses();

        tagListPanel = new TagListPanel(logic.getFilteredTagList());
        tagListPanelPlaceholder.getChildren().add(tagListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Updates the total number of tags.
     */
    private void updateTags() {
        tagListPanel = new TagListPanel(logic.getFilteredTagList());
        tagListPanelPlaceholder.getChildren().add(tagListPanel.getRoot());
    }

    /**
     * Updates the individual tag numbers.
     */
    private void updateCourses() {
        courseListPanel = new CourseListPanel(logic.getFilteredCourseList());
        courseListPanelPlaceholder.getChildren().add(courseListPanel.getRoot());
    }

    /**
     * Updates the total number of students.
     */
    private void updateTotalStudents() {
        ObservableList<Person> personList = logic.getFilteredPersonList();
        numberStudents.setText(Integer.toString(personList.size()));
    }

    /**
     * Updates the total number of courses.
     */
    private void updateTotalCourses() {
        ObservableList<Pair<String, Integer>> courseList = logic.getFilteredCourseList();
        numberCourses.setText(Integer.toString(courseList.size()));
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
     * Opens the feedback window or focuses on it if it's already opened.
     */
    @FXML
    public void handleFeedback() {
        if (!feedbackWindow.isShowing()) {
            feedbackWindow.show();
        } else {
            feedbackWindow.focus();
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
        feedbackWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public CourseListPanel getCourseListPanel() {
        return courseListPanel;
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

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowFeedback()) {
                handleFeedback();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isLight()) {
                handleTheme("LIGHT");
            }

            if (commandResult.isDark()) {
                handleTheme("DARK");
            }

            updateTotalStudents();
            updateTotalCourses();
            updateCourses();
            updateTags();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Handles the application's theme based on the specified theme string.
     * This method allows switching between "DARK" and "LIGHT" themes by setting the appropriate mode.
     *
     * @param theme The theme to apply. It must be either "DARK" or "LIGHT".
     * @throws AssertionError if the provided theme is neither "DARK" nor "LIGHT".
     */
    public void handleTheme(String theme) {
        assert theme == "DARK" || theme == "LIGHT" : "Invalid theme input";
        switch (theme) {
        case "DARK":
            setDarkMode();
            break;
        case "LIGHT":
            setLightMode();
            break;
        default:
        }
    }

    /**
     * Sets the light mode theme for the application's main window and associated windows.
     * This method updates the visual style of the application to a light theme.
     * It removes the dark theme stylesheet and adds the light theme stylesheet to the primary stage's scene.
     * Additionally, it sets the light theme for the FeedbackWindow and HelpWindow.
     */
    @FXML
    private void setLightMode() {
        String lightThemePath = getClass().getResource("/view/LightTheme.css").toExternalForm();
        String darkThemePath = getClass().getResource("/view/DarkTheme.css").toExternalForm();
        primaryStage.getScene().getStylesheets().remove(darkThemePath);
        primaryStage.getScene().getStylesheets().add(lightThemePath);

        feedbackWindow.setTheme(true);
        helpWindow.setTheme(true);
    }

    /**
     * Sets the dark mode theme for the application's main window and associated windows.
     * This method updates the visual style of the application to a dark theme.
     * It removes the light theme stylesheet and adds the dark theme stylesheet to the primary stage's scene.
     * Additionally, it sets the dark theme for the FeedbackWindow and HelpWindow.
     */
    @FXML
    private void setDarkMode() {
        String lightThemePath = getClass().getResource("/view/LightTheme.css").toExternalForm();
        String darkThemePath = getClass().getResource("/view/DarkTheme.css").toExternalForm();
        primaryStage.getScene().getStylesheets().remove(lightThemePath);
        primaryStage.getScene().getStylesheets().add(darkThemePath);

        feedbackWindow.setTheme(false);
        helpWindow.setTheme(false);
    }
}
