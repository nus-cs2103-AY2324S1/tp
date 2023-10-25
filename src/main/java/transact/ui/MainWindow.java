package transact.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transact.MainApp;
import transact.commons.core.GuiSettings;
import transact.commons.core.LogsCenter;
import transact.logic.Logic;
import transact.logic.commands.CommandResult;
import transact.logic.commands.exceptions.CommandException;
import transact.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    // TODO Update to actual URL when done
    private static final String USER_GUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private OverviewPanel overviewPanel;
    private TransactionTablePanel transactionTablePanel;
    private CardListPanel cardListPanel;
    private ResultDisplay resultDisplay;

    /**
     * The available tab windows
     */
    public enum TabWindow {
        CURRENT, OVERVIEW, TRANSACTIONS, ADDRESSBOOK
    }

    @FXML
    private MenuItem userGuideMenuItem;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab overviewTab;

    @FXML
    private Tab cardListTab;

    @FXML
    private Tab transactionTab;

    @FXML
    private VBox bottomBar;

    // @FXML
    // private StackPane statusbarPlaceholder;

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
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(userGuideMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination
     *            the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);
    }

    /**
     * Switch tabs. If tabWindow is set to current, do nothing.
     *
     * @param tabWindow
     *            The tab to switch to
     */
    void switchTab(TabWindow tabWindow) {
        if (tabWindow != TabWindow.CURRENT) {
            tabPane.getSelectionModel().select(tabWindow.ordinal() - 1);
        }
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        overviewPanel = new OverviewPanel(logic.getFilteredTransactionList());
        overviewTab.setContent(overviewPanel.getRoot());

        transactionTablePanel = new TransactionTablePanel(logic.getFilteredTransactionList());
        transactionTab.setContent(transactionTablePanel.getRoot());

        cardListPanel = new CardListPanel(logic.getFilteredPersonList());
        cardListTab.setContent(cardListPanel.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        VBox.setVgrow(commandBox.getRoot(), Priority.NEVER);

        resultDisplay = new ResultDisplay();
        VBox.setVgrow(resultDisplay.getRoot(), Priority.ALWAYS);

        bottomBar.getChildren().addAll(commandBox.getRoot(), resultDisplay.getRoot());
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
        try {
            MainApp.getLocalHostServices().showDocument(USER_GUIDE_URL);
        } catch (Exception e) {
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
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        primaryStage.hide();
    }

    /**
     * Clear the Address Book.
     */
    @FXML
    private void handleClearStaffs() throws CommandException, ParseException {
        String commandText = "clearstaff";
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            switchTab(commandResult.getTabWindow());
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Clear the Transaction Book.
     */
    @FXML
    private void handleClearTransactions() throws CommandException, ParseException {
        String commandText = "cleartransaction";
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            switchTab(commandResult.getTabWindow());
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    public CardListPanel getCardListPanel() {
        return cardListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see transact.logic.Logic#execute(String)
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

            if (commandResult.isClearResultDisplay()) {
                resultDisplay.clear();
            }

            switchTab(commandResult.getTabWindow());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
