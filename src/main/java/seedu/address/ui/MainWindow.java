package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final String SHOWING_HELP_MESSAGE = HelpCommand.SHOWING_HELP_MESSAGE;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;

    private TeamListPanel teamListPanel;
    @FXML
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane teamListPanelPlaceholder;
    @FXML
    private StackPane D_personListPanelPlaceholder;
    @FXML
    private StackPane D_teamListPanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private VBox singleListContainer;
    @FXML
    private VBox personList;
    @FXML
    private VBox teamList;
    @FXML
    private HBox dualListContainer;
    @FXML
    private VBox D_personList;
    @FXML
    private VBox D_teamList;

    @FXML
    private StackPane tree;

    @FXML
    private StackPane personStats;

    @FXML
    private StackPane teamStats;

    private boolean isListingPerson;

    private boolean isListingTeam;

    private boolean isShowingTree;

    private double originalResultDisplayHeight;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LinkTree App");
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
     * Resize the ResultDisplay to a larger size.
     */
    private void expandResultDisplay() {
        resultDisplayPlaceholder.setPrefHeight(400);
    }

    /**
     * Resize the ResultDisplay back to default size.
     */
    private void revertResultDisplay() {
        resultDisplayPlaceholder.setPrefHeight(originalResultDisplayHeight);
    }


    /**
     * Fills up all the placeholders of this window.
     *
     * @param whatToFill String that indicates what to fill in the inner parts
     */
    void fillInnerParts(String whatToFill) {
        if (whatToFill.equals("persons")) {
            fillPersonList();
        } else if (whatToFill.equals("teams")) {
            fillTeamList();
        } else if (whatToFill.equals("both")) {
            fillBothList();
        }
        switchToListPanel(whatToFill);
    }


    void fillInnerParts() {
        fillBothList();
        switchToListPanel("both");

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        setStatistics();

        originalResultDisplayHeight = resultDisplayPlaceholder.getPrefHeight();
    }

    /**
     * set the label and values for the statistic area.
     */
    private void setStatistics() {
        Label personLabel = new Label("   Total number of developers: "
                + logic.getFilteredPersonList().size());
        personLabel.setStyle("-fx-text-fill: #ecbdbd;");
        personStats.getChildren().add(personLabel);
        StackPane.setAlignment(personLabel, Pos.CENTER_LEFT);

        Label teamLabel = new Label("   Total number of teams: "
                + logic.getFilteredTeamList().size());
        teamLabel.setStyle("-fx-text-fill: #ecbdbd;");
        teamStats.getChildren().add(teamLabel);
        StackPane.setAlignment(teamLabel, Pos.CENTER_LEFT);
    }

    /**
     * Fills the Person list.
     */
    private void fillPersonList() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    /**
     * Fills the teams list.
     */
    private void fillTeamList() {
        teamListPanel = new TeamListPanel(logic.getFilteredTeamList(), logic.getFilteredPersonList());
        teamListPanelPlaceholder.getChildren().add(teamListPanel.getRoot());
    }

    /**
     * Fills both the person and the teams list.
     */
    private void fillBothList() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        D_personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        teamListPanel = new TeamListPanel(logic.getFilteredTeamList(), logic.getFilteredPersonList());
        D_teamListPanelPlaceholder.getChildren().add(teamListPanel.getRoot());
    }

    /**
     * Method to switch between panels.
     *
     * @param panelType String indicating which panel to display
     */
    public void switchToListPanel(String panelType) {
        if ("persons".equals(panelType)) {
            showPersonList();
        } else if ("teams".equals(panelType)) {
            showTeamList();
        } else if ("both".equals(panelType)) {
            showBothList();
        }
    }

    /**
     * Set visibility of Vbox: personList -> visible, teamList -> not visible
     */
    public void showPersonList() {
        singleListContainer.getChildren().setAll(personList);
        singleListContainer.setVisible(true);
        tree.setVisible(false);
        dualListContainer.setVisible(false);
        personList.setVisible(true);
        teamList.setVisible(false);
        personListPanelPlaceholder.setVisible(true);
        teamListPanelPlaceholder.setVisible(false);

        isListingPerson = true;
        isListingTeam = false;
        isShowingTree = false;
    }

    /**
     * Set visibility of Vbox: personList -> not visible, teamList -> visible
     */
    public void showTeamList() {
        singleListContainer.getChildren().setAll(teamList);
        singleListContainer.setVisible(true);
        tree.setVisible(false);
        dualListContainer.setVisible(false);
        personList.setVisible(false);
        teamList.setVisible(true);
        personListPanelPlaceholder.setVisible(false);
        teamListPanelPlaceholder.setVisible(true);

        isListingTeam = true;
        isListingPerson = false;
        isShowingTree = false;
    }

    /**
     * Set visibility of Vbox: personList -> not visible, teamList -> visible
     */
    public void showBothList() {
        dualListContainer.getChildren().setAll(D_personList, D_teamList);
        tree.setVisible(false);
        singleListContainer.setVisible(false);
        dualListContainer.setVisible(true);

        this.isListingPerson = false;
        this.isListingTeam = false;
        this.isShowingTree = false;
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

        resultDisplay.setFeedbackToUser(SHOWING_HELP_MESSAGE);

        expandResultDisplay();
    }

    /**
     * Fill the panel with list of person.
     */
    @FXML
    public void handleListPerson() {
        if (isListingPerson) {
            fillInnerParts("both");
        } else {
            fillInnerParts("persons");
        }
    }

    /**
     * Fill the panel with list of teams instead of person.
     */
    @FXML
    public void handleListTeam() {
        if (isListingTeam) {
            fillInnerParts("both");
        } else {
            fillInnerParts("teams");
        }
    }

    /**
     * Fill the panel with list of person.
     */
    @FXML
    public void handleFindPerson() {
        fillInnerParts("persons");
    }

    /**
     * Fill the panel with list of teams instead of person.
     */
    @FXML
    public void handleFindTeam() {
        fillInnerParts("teams");
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

    @FXML
    private void handleTree() {
        if (isShowingTree) {
            hideTree();
        } else {
            showTree();
        }
    }

    /**
     * Refresh the card container to show any changes.
     */
    public void refreshCardContainer() {
        if (!isListingPerson && !isListingTeam && !isShowingTree) {
            fillInnerParts("both");
        } else if (isListingTeam) {
            fillInnerParts("teams");
        }
    }

    /**
     * Refresh the statistics to show any changes.
     */
    public void refreshStatistics() {
        teamStats.getChildren().clear();
        personStats.getChildren().clear();
        setStatistics();
    }

    /**
     * @return the current personList Panel
     */
    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * @return the current teamList Panel
     */
    public TeamListPanel getTeamListPanel() {
        return teamListPanel;
    }

    /**
     * To toggle the tree to be shown.
     * Called when a single 'tree' command is received.
     */
    public void showTree() {
        singleListContainer.setVisible(false);
        dualListContainer.setVisible(false);

        //todo: maybe add functions to define project name
        String projectName = "LinkTree";

        LinkTreeDisplay linkTreeDisplay = new LinkTreeDisplay(logic, projectName);
        tree.getChildren().add(linkTreeDisplay.getRoot());
        tree.setVisible(true);

        this.isShowingTree = true;
        this.isListingPerson = false;
        this.isListingTeam = false;
    }

    /**
     * To toggle the tree to be hidden.
     * By right only the second 'tree' command will call this function.
     */
    public void hideTree() {
        tree.getChildren().clear();
        tree.setVisible(false);
        fillInnerParts("both");
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
            } else {
                revertResultDisplay();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isListTeam()) {
                handleListTeam();
            }

            if (commandResult.isListPerson()) {
                handleListPerson();
            }

            if (commandResult.isShowTree()) {
                handleTree();
            }

            if (commandResult.isFindPerson()) {
                handleFindPerson();
            }

            if (commandResult.isFindTeam()) {
                handleFindTeam();
            }

            if (commandResult.isAddingOrDeleting()) {
                refreshStatistics();
            }

            refreshCardContainer();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
