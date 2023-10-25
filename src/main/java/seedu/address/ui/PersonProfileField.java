package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PersonProfileField extends UiPart<SplitPane> {
    private static final String FXML = "PersonProfileField.fxml";
    private static final String INVALID_FIELD_FEEDBACK = "Invalid value for: ";

    @FXML private Label valueLabel;
    @FXML private TextField valueField;
    @FXML private Label keyLabel;
    private final PersonProfile personProfile;
    private PersonProfile.Field field;
    private String value;
    private State state;
    private boolean changesSubmitted = false;

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
    private void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        case ENTER:
            handleConfirmation();
            break;
        case ESCAPE:
            handleCancellation();
            break;
        }
    }

    private void handleConfirmation() {
        System.out.println("Confirmation detected");
        String newValue = getTextOrNull();
        if (newValue != null && !field.isValid(newValue)) {
            personProfile.sendFeedback(INVALID_FIELD_FEEDBACK + field.getDisplayName());
            changesSubmitted = false;
            return;
        }
        if (!personProfile.replaceFieldIfValid(field, newValue)){
            changesSubmitted = true;
            return;
        }
        confirm();
    }

    private void handleCancellation() {
        System.out.println("Cancellation detected");
        valueField.setText(value);
        valueLabel.setText(value);
        personProfile.replaceFieldIfValid(field, value);
        updateState(State.LABEL);
    }

    private void confirm() {
        if (state == State.TEXT_FIELD) {
            String newValue = getTextOrNull();
            this.value = newValue;
            valueLabel.setText(newValue);
            updateState(State.LABEL);
        }
    }

    void confirmIfSubmitted() {
        if (changesSubmitted) {
            confirm();
        }
    }

    private String getTextOrNull() {
        String newValue = valueField.getText();
        if (newValue != null) {
            if (newValue.isEmpty() || newValue.isBlank()) {
                newValue = null;
            } else {
                newValue = newValue.trim();
            }
        }
        return newValue;
    }

    @FXML
    void setFocus() {
        System.out.println("Focus detected");
        updateState(State.TEXT_FIELD);
    }


}
