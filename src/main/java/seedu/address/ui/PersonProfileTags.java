package seedu.address.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import seedu.address.model.tag.Tag;

/**
 * A row of the PersonProfile UI, representing one field of the Person displayed.
 */
public class PersonProfileTags extends UiPart<SplitPane> {

    // region Super
    private static final String FXML = "PersonProfileTags.fxml";
    // endregion

    // region Constants
    private static final String KEY_NAME = "Tags";
    private static final String TAG_SEPARATOR_REGEX = "[ ,;\n]";
    private static final String DEFAULT_TAG_SEPARATOR = "\n";
    private static final String TAGS_HINT = Tag.MESSAGE_CONSTRAINTS + "\n"
            + "Tag names can be separated by spaces, commas, semicolons or new lines.";
    private static final String TAG_INVALID_MESSAGE = "is not a valid tag.";
    // endregion

    // region FXML
    @FXML private FlowPane valueFlowPane;
    @FXML private TextArea valueField;
    @FXML private Label keyLabel;
    // endregion

    // region Fields
    private final PersonProfile personProfile;
    private Set<Tag> tags;
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
    public PersonProfileTags(PersonProfile personProfile) {
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
            generateAndSetLabels();
            valueFlowPane.setVisible(true);
            valueField.setVisible(false);
            break;
        case EDITING:
            valueFlowPane.setVisible(false);
            valueField.setVisible(true);
            valueField.requestFocus();
            sendHint();
            break;
        default:
            initializeAndRefresh();
        }
    }

    private void generateAndSetLabels() {
        List<Label> labels = tags.stream()
                .map(Tag::getTagName).sorted()
                .map(Label::new)
                .collect(Collectors.toList());
        valueFlowPane.getChildren().setAll(labels);
    }

    private boolean confirmIfValid() {
        assert state == State.EDITING;
        if (!getTagsFromFieldIfValidElseError()) {
            return false;
        }
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

    private boolean getTagsFromFieldIfValidElseError() throws IllegalArgumentException {
        String text = valueField.getText();
        if (text == null || text.isBlank()) {
            tags.clear();
            return true;
        }
        Predicate<String> notBlank = ((Predicate<String>) String::isBlank).negate();
        Predicate<String> notValid = ((Predicate<String>) Tag::isValidTagName).negate();
        Optional<String> invalid = Arrays.stream(text.split(TAG_SEPARATOR_REGEX))
                .filter(Objects::nonNull).filter(notBlank)
                .filter(notValid)
                .findAny();
        if (invalid.isPresent()) {
            sendValueInvalid("'" + invalid.get() + "' " + TAG_INVALID_MESSAGE + "\n"
                    + Tag.MESSAGE_CONSTRAINTS);
            return false;
        }
        this.tags = Arrays.stream(text.split(TAG_SEPARATOR_REGEX))
                .filter(Objects::nonNull).filter(notBlank).map(Tag::new).collect(Collectors.toSet());
        return true;
    }

    private void getFieldFromTags() {
        String textRepresentation = tags.stream().map(Tag::getTagName)
                .collect(Collectors.joining(DEFAULT_TAG_SEPARATOR)).trim();
        valueField.setText(textRepresentation);
    }

    private void updateProfile() {
        personProfile.updateTags(tags);
    }

    private void refresh() {
        tags = personProfile.getTags();
        getFieldFromTags();
        updateState();
    }

    private void sendHint() {
        personProfile.sendHint(TAGS_HINT);
    }

    private void sendValueInvalid(String invalidTag) {
        personProfile.sendInvalidInput(KEY_NAME, invalidTag);
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
                String text = Objects.requireNonNullElse(valueField.getText(), "");
                String before = text.substring(0, cursor);
                String after = text.substring(cursor);
                valueField.setText(before + "\n" + after);
                valueField.positionCaret(cursor + 1);
                return;
            }
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

    public void setIsInConfirmationDialog(boolean isInConfirmationDialog) {
        this.isInConfirmationDialog = isInConfirmationDialog;
    }

    // endregion

}
