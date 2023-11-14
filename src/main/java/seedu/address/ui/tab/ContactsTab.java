package seedu.address.ui.tab;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox;
import seedu.address.ui.HelpWindow;
import seedu.address.ui.ResultDisplay;
import seedu.address.ui.UiPart;
import seedu.address.ui.contacts.PersonListPanel;

/**
 * The Contacts Tab.
 */
public class ContactsTab extends UiPart<Region> {
    private static final String FXML = "ContactsTab.fxml";

    private static final String HELP_LINK = "https://ay2324s1-cs2103t-w09-2.github.io/tp/"
            + "UserGuide.html#contacts-management";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;

    private final HelpWindow helpWindow;
    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a {@code ContactsTab}.
     */
    public ContactsTab(TabPane tabPane) {
        super(FXML);
        helpWindow = new HelpWindow(HELP_LINK);
        this.tabPane = tabPane;
    }

    public void setup(Logic logic) {
        this.logic = logic;
        setInnerParts();
    }

    private void setInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
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

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isChangeTab()) {
                handleTabChange(commandResult.getTabToChange());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }
    private void handleExit() {
        System.exit(0);
    }

    private void handleTabChange(int tabIndex) {
        tabPane.getSelectionModel().select(tabIndex);
    }
}
