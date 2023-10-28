package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PersonProfileField extends UiPart<SplitPane> {

    // region Super
    private static final String FXML = "PersonProfileField.fxml";
    // endregion

    // region FXML
    @FXML private Label valueLabel;
    @FXML private TextField valueField;
    @FXML private Label keyLabel;
    // endregion

    // region Fields
    private final PersonProfile personProfile;
    private final PersonProfile.Field field;

    private String value;
    private State state;
    // endregion

    enum State {
        LABEL, TEXT_FIELD
    }

    // region Constructor
    public PersonProfileField(PersonProfile personProfile, PersonProfile.Field field) {
        super(FXML);
        this.personProfile = personProfile;
        this.field = field;
        initializeAndRefresh();
    }

    private void initializeAndRefresh() {
        keyLabel.setText(field.getDisplayName());
        valueField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue) {
                handleLoseFocus();
            }
        }));
        state = State.LABEL;
        personProfile.setEventHandler(PersonProfile.Event.CONFIRM_SUCCESS, this::refresh);
        refresh();
    }
    // endregion

    // region Internal Actions
    private void updateState(State state) {
        this.state = state;
        updateState();
    }

    private void updateState() {
        switch (state) {
        case LABEL:
            valueLabel.setText(value);
            valueField.setText(value);
            valueLabel.setVisible(true);
            valueField.setVisible(false);
            break;
        case TEXT_FIELD:
            valueLabel.setVisible(false);
            valueField.setVisible(true);
            valueField.requestFocus();
            personProfile.sendHint(field);
            break;
        default:
            initializeAndRefresh();
        }
    }

    private boolean confirmIfValid() {
        assert state == State.TEXT_FIELD;
        if (isValueValid()) {
            this.value = getTextOrNil();
            updateState(State.LABEL);
            personProfile.triggerEvent(PersonProfile.Event.CONFIRM_SUCCESS);
            return true;
        } else {
            personProfile.triggerEvent(PersonProfile.Event.CONFIRM_FAIL);
            sendValueInvalid();
            return false;
        }
    }

    private boolean isValueValid() {
        return field.isValid(getTextOrNil());
    }

    private void cancel() {
        updateState(State.LABEL);
        updateProfile();
        personProfile.triggerEvent(PersonProfile.Event.CANCEL);
    }

    private String getTextOrNil() {
        String newValue = valueField.getText();
        if (newValue == null || newValue.isBlank()) {
            newValue = "nil";
        } else {
            newValue = newValue.trim();
        }
        return newValue;
    }

    private void updateProfile() {
        personProfile.updateField(field, value);
    }

    private void refresh() {
        this.value = personProfile.getValueOfField(field);
        updateState();
    }

    private void sendValueInvalid() {
        personProfile.sendInvalidInput(field);
    }

    // endregion

    // region Event Handlers

    @FXML
    private void handleKey(KeyEvent keyEvent) {
        if (!isEditing()) {
            return;
        }
        switch (keyEvent.getCode()) {
        case ENTER:
            if (confirmIfValid()) {
                return;
            }
        case ESCAPE:
            cancel();
        }
    }

    private void handleLoseFocus() {
        if (!isEditing()) {
            return;
        }
        if (confirmIfValid()) {
            return;
        }
        cancel();
    }

    // endregion

    // region External

    @FXML
    void setFocus() {
        personProfile.triggerEvent(PersonProfile.Event.BEFORE_START_EDIT);
        updateState(State.TEXT_FIELD);
    }

    boolean isEditing() {
        return state == State.TEXT_FIELD;
    }

    // endregion

}
