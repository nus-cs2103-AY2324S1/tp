package seedu.address.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";

    public static final KeyCode AUTOCOMPLETE_KEY = KeyCode.TAB;

    private static final String FXML = "CommandBox.fxml";

    /**
     * For autocompletion function;
     * an array to keep track of completion suggestions,
     * an index of what completion suggestion we are currently on,
     * and a boolean to keep track of whether the user is currently in ""autocomplete mode",
     * and cycling through suggestions.
     */
    private ArrayList<String> autocompleteSuggestions;
    private int autocompleteCurrentSuggestionIndex = -1;
    private boolean isAutocompleteMode = false;

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the On Key Typed event.
     * This will trigger autocomplete.
     */
    @FXML
    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == AUTOCOMPLETE_KEY) {
            showAutocompleteSuggestions();

            commandTextField.requestFocus();
            commandTextField.end();

        } else {
            // Exit autocomplete mode if any other key is pressed.
            exitAutocompleteMode();
        }
    }

    private void showAutocompleteSuggestions() {
        if (!isAutocompleteMode) {
            // Get user's current text. Right now, autocomplete works on the entire user text,
            // rather than the current word.
            String userText = commandTextField.getText();

            // Find all commands that start with user text.
            Set<String> commandWordSet = (new AddressBookParser()).getCommandWords();

            autocompleteSuggestions = new ArrayList<>(commandWordSet.stream()
                    .filter((commandWord) -> commandWord.startsWith(userText))
                    .collect(Collectors.toList()));

            // Alphabetical order
            Collections.sort(autocompleteSuggestions);

            // "Exit suggestions" by offering the user's own text as a suggestion
            autocompleteSuggestions.add(userText);

            // Guard clause; no suggestions
            if (autocompleteSuggestions.size() <= 0) {
                return;
            }

            // Initialize autocomplete mode.
            autocompleteCurrentSuggestionIndex = 0;

            isAutocompleteMode = true;

            commandTextField
                .setText(autocompleteSuggestions.get(autocompleteCurrentSuggestionIndex));
            return;
        }

        // Already in autocomplete mode; cycle through the completions.
        autocompleteCurrentSuggestionIndex++;
        autocompleteCurrentSuggestionIndex %= autocompleteSuggestions.size();

        commandTextField.setText(autocompleteSuggestions.get(autocompleteCurrentSuggestionIndex));

    }

    private void exitAutocompleteMode() {
        autocompleteSuggestions = null;
        autocompleteCurrentSuggestionIndex = -1;
        isAutocompleteMode = false;
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
