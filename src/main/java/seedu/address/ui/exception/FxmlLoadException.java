package seedu.address.ui.exception;

/**
 * Represents the error that is thrown when there is a problem loading up
 * the relevant FXML files for the UI.
 */
public class FxmlLoadException extends Error {
    public FxmlLoadException(Exception cause) {
        super(cause);
    }
}
