package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

/**
 * A row of the PersonProfile UI, representing one field of the Person displayed.
 */
public class PersonProfileNote extends UiPart<SplitPane> {

    // region Super
    private static final String FXML = "PersonProfileNote.fxml";
    // endregion

    // region Constants
    private static final String KEY_NAME = "Notes";
    private static final String NOTES_HINT = "Notes can be any text.";
    // endregion

    // region FXML
    @FXML private Label valueLabel;
    @FXML private TextArea valueField;
    @FXML private Label keyLabel;
    // endregion

    // region Fields
    private final PersonProfile personProfile;
    private String value;
    private State state;
    private boolean isInConfirmationDialog;

    // endregion

    // region Enums

    /**
     * Represents that the Field is either under edit, or not.
     */
    enum State {
        READ_ONLY, EDITING
    }

    // endregion

    // region Constructor

    /**
     * Creates a PersonProfileField from the provided UI parent, as well as a Field value.
     *
     * @param personProfile UI parent, serving as a container for this object.
     */
    public PersonProfileNote(PersonProfile personProfile) {
        super(FXML);
        this.personProfile = personProfile;
        initializeAndRefresh();
    }

    private void initializeAndRefresh() {
        keyLabel.setText(KEY_NAME);
        valueField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue) {
                handleLoseFocus();
            }
        }));
        valueField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
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
            sendHint();
            break;
        default:
            initializeAndRefresh();
        }
    }

    private boolean confirm() {
        assert state == State.EDITING;
        retrieveValue();
        updateProfile();
        updateState(State.READ_ONLY);
        personProfile.triggerEvent(PersonProfile.Event.AFTER_CONFIRM);
        return true;
    }

    private void cancel() {
        updateState(State.READ_ONLY);
        updateProfile();
        personProfile.triggerEvent(PersonProfile.Event.CANCEL);
    }

    private String getNonNull() {
        String text = valueField.getText();
        if (text == null) {
            text = "";
        }
        return text;
    }

    private void updateProfile() {
        personProfile.updateNote(value);
    }

    private void refresh() {
        value = personProfile.getNote();
        updateState();
    }

    private void sendHint() {
        personProfile.sendHint(NOTES_HINT);
    }

    private void retrieveValue() {
        this.value = getNonNull().trim();
    }

    // endregion

    // region Event Handlers

    private void handleKey(KeyEvent keyEvent) {
        if (!isEditing()) {
            return;
        }
        switch (keyEvent.getCode()) {
        case ENTER:
            keyEvent.consume();
            if (keyEvent.isShiftDown()) {
                int cursor = valueField.getCaretPosition();
                String text = getNonNull();
                String before = text.substring(0, cursor);
                String after = text.substring(cursor);
                valueField.setText(before + "\n" + after);
                valueField.positionCaret(cursor + 1);
                return;
            }
            if (confirm()) {
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
        if (confirm()) {
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

    public void setIsInConfirmationDialog(boolean isInConfirmationDialog) {
        this.isInConfirmationDialog = isInConfirmationDialog;
    }

    // endregion

}
