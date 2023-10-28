package seedu.address.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PersonProfileField extends UiPart<SplitPane> {

    // region Super
    private static final String FXML = "PersonProfileField.fxml";
    // endregion

    private static final String errorTextCss = "-fx-text-fill: red";

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
        personProfile.setEventHandler(PersonProfile.Event.AFTER_CONFIRM, this::refresh);
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
            updateProfile();
            updateState(State.LABEL);
            personProfile.triggerEvent(PersonProfile.Event.AFTER_CONFIRM);
            return true;
        } else {
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
        value = personProfile.getValueOfField(field);
        resetTextPaint();
        updateState();
    }

    private void resetTextPaint() {
        keyLabel.setStyle("");
        valueLabel.setStyle("");
    }

    private void setErrorTextPaint() {
        Platform.runLater(() -> {
            keyLabel.setStyle(errorTextCss);
            valueLabel.setStyle(errorTextCss);
        });
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
            break;
        case ESCAPE:
            cancel();
            break;
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

    void indicateIsError() {
        setErrorTextPaint();
    }

    // endregion

}
