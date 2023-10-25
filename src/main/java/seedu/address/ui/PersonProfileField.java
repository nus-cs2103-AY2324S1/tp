package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PersonProfileField extends UiPart<AnchorPane> {
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

    private void toggleState() {
        switch (state) {
        case LABEL:
            state = State.TEXT_FIELD;
            updateState();
            break;
        case TEXT_FIELD:
            state = State.LABEL;
            updateState();
            break;
        default:
            initialize();
        }
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
            break;
        default:
            initialize();
        }
    }

    @FXML
    private void handleOnAction() {
        System.out.println("Action detected");
        toggleState();
    }


}
