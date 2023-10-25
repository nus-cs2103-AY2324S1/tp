package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PersonProfileField extends UiPart<SplitPane> {
    private static final String FXML = "PersonProfileField.fxml";

    @FXML private Label valueLabel;
    @FXML private TextField valueField;
    @FXML private Label keyLabel;
    private final PersonProfile personProfile;
    private PersonProfile.Field field;
    private String value;
    private State state;

    enum State {
        LABEL, TEXT_FIELD
    }

    public PersonProfileField(PersonProfile personProfile, PersonProfile.Field field) {
        super(FXML);
        this.personProfile = personProfile;
        this.field = field;
        value = personProfile.getValueOfField(field);
        initialize();
    }

    private void initialize() {
        keyLabel.setText(field.getDisplayName());
        valueLabel.setText(value);
        valueField.setText(value);
        valueField.setVisible(false);
        state = State.LABEL;
    }

    private void updateState(State state) {
        this.state = state;
        updateState();
    }

    private void updateState() {
        switch (state) {
        case LABEL:
            valueLabel.setVisible(true);
            valueField.setVisible(false);
            break;
        case TEXT_FIELD:
            valueLabel.setVisible(false);
            valueField.setVisible(true);
            valueField.requestFocus();
            break;
        default:
            initialize();
        }
    }
    @FXML
    public void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        case ENTER:
            handleConfirmation();
            break;
        case ESCAPE:
            handleCancellation();
            break;
        default:
            return;
        }
        updateState(State.LABEL);
    }

    private void handleConfirmation() {
        System.out.println("Confirmation detected");
        String newValue = valueField.getText().trim();
        if (!field.isValid(newValue) || !personProfile.replaceFieldIfValid(field, newValue)) {
            handleCancellation();
        }
        this.value = newValue;
        valueLabel.setText(newValue);
    }

    private void handleCancellation() {
        System.out.println("Cancellation detected");
        valueField.setText(value);
    }

    @FXML
    private void setFocus() {
        System.out.println("Focus detected");
        updateState(State.TEXT_FIELD);
    }


}
