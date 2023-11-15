package seedu.address.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import seedu.address.model.person.Person;

/**
 * A row of the PersonProfile UI, representing one field of the Person displayed.
 */
public class PersonProfileField extends UiPart<SplitPane> {

    // region Super
    private static final String FXML = "PersonProfileField.fxml";
    // endregion

    // region Constants
    private static final String errorTextCss = "-fx-text-fill: red";
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

    private Boolean isInConfirmationDialog = false;

    // endregion

    // region Enums

    /**
     * Represents that the Field is either under edit, or not.
     */
    enum State {
        READ_ONLY, EDITING, LOCKED
    }

    // endregion

    // region Constructor

    /**
     * Creates a PersonProfileField from the provided UI parent, as well as a Field value.
     *
     * @param personProfile UI parent, serving as a container for this object.
     * @param field field of a Person to display and allow editing of.
     */
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
        state = State.READ_ONLY;
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
        case READ_ONLY:
            valueLabel.setText(value);
            valueField.setText(value);
            valueLabel.setVisible(true);
            valueField.setVisible(false);
            break;
        case EDITING:
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
        assert state == State.EDITING;
        if (isValueValid()) {
            this.value = getTextOrNil();
            updateProfile();
            updateState(State.READ_ONLY);
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
        updateState(State.READ_ONLY);
        updateProfile();
        personProfile.triggerEvent(PersonProfile.Event.CANCEL);
    }

    private String getTextOrNil() {
        String newValue = valueField.getText();
        if (newValue == null || newValue.isBlank()) {
            newValue = Person.NIL_WORD;
        } else {
            newValue = newValue.trim();
        }
        return newValue;
    }

    private void updateProfile() {
        personProfile.updateField(field, value);
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
        default:
            //noinspection UnnecessarySemicolon
            ;
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
        if (!isInConfirmationDialog) {
            personProfile.triggerEvent(PersonProfile.Event.BEFORE_START_EDIT);
            updateState(State.EDITING);
        }
    }

    boolean isEditing() {
        return state == State.EDITING;
    }

    void indicateIsError() {
        setErrorTextPaint();
    }

    void refresh() {
        value = personProfile.getValueOfField(field);
        resetTextPaint();
        updateState();
    }

    public void setIsInConfirmationDialog(boolean isInConfirmationDialog) {
        this.isInConfirmationDialog = isInConfirmationDialog;
    }

    // endregion

}
