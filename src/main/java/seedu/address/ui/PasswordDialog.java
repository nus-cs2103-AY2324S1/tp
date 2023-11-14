package seedu.address.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import seedu.address.storage.PasswordManager;


/**
 * This class represents a dialog that appears when handling setting or entering of password.
 */
public class PasswordDialog extends Dialog<String> {
    private boolean isPasswordIncorrect = true;
    private Label incorrectPasswordLabel;
    private Node okButton;
    private VBox vbox;
    /**
     * Constructs a PasswordDialog object and either displays the sets or enters a password dialog
     * based on the provided PasswordManager whether there exist a current password.
     *
     * @param passwordManager The PasswordManager used for setting or verifying the password.
     */
    public PasswordDialog(PasswordManager passwordManager) {
        String password = passwordManager.getPassword();
        if (password.isEmpty()) {
            setPassword(passwordManager);
        } else {
            enterPassword(passwordManager);
        }
    }

    /**
     * Initializes the "OK" button for the dialog and sets up the event handling for password validation.
     *
     * @param passwordField The PasswordField for entering the password.
     * @param okButtonType  The ButtonType for the "OK" button.
     */
    public void okButtonNode(PasswordField passwordField, ButtonType okButtonType) {
        this.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        okButton = this.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });
        vbox = new VBox(passwordField);
        this.getDialogPane().setContent(vbox);

        Platform.runLater(passwordField::requestFocus);
    }

    /**
     * Sets the password for the PasswordManager and handles the "OK" button action.
     *
     * @param passwordManager The PasswordManager used for setting the password.
     */
    private void setPassword(PasswordManager passwordManager) {
        this.setTitle("InsuraHub");
        this.setHeaderText("Please set your password");
        PasswordField passwordField = new PasswordField();
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        okButtonNode(passwordField, okButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            String enteredPassword = passwordField.getText();
            passwordManager.setPassword(enteredPassword);
        });
    }

    /**
     * Enters the password and handles the "OK" button action, including validation.
     *
     * @param passwordManager The PasswordManager used for validating the entered password.
     */
    private void enterPassword(PasswordManager passwordManager) {
        this.setTitle("InsuraHub");
        this.setHeaderText("Please enter the password");
        PasswordField passwordField = new PasswordField();
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        okButtonNode(passwordField, okButtonType);
        incorrectPasswordLabel = new Label("Wrong password, please try again.");
        incorrectPasswordLabel.setStyle("-fx-text-fill: red;");
        incorrectPasswordLabel.setVisible(false);
        vbox.getChildren().add(incorrectPasswordLabel);

        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            String enteredPassword = passwordField.getText();
            if (passwordManager.check(enteredPassword)) {
                // Password is correct, close the dialog
                isPasswordIncorrect = false;
            } else {
                // Password is incorrect, show the message, and prevent the dialog from closing
                isPasswordIncorrect = true;
                showIncorrectPasswordMessage();
                event.consume(); // This prevents the dialog from closing
            }
        });
    }

    /**
     * Checks PasswordDialog's isPasswordIncorrect attribute
     *
     * @return isPasswordIncorrect attribute
     */
    public boolean isPasswordIncorrect() {
        return isPasswordIncorrect;
    }

    /**
     * resets the PasswordDialog's isPasswordIncorrect attribute back to true
     */
    public void resetPasswordIncorrectState() {
        isPasswordIncorrect = true;
    }

    /**
     * shows the incorrectPasswordLabel that tells the user a wrong password have been typed
     */
    public void showIncorrectPasswordMessage() {
        incorrectPasswordLabel.setVisible(true);
    }
}
