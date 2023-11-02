package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 * The top row of the PersonProfile UI, representing the header of the UI.
 */
public class PersonProfileHeader extends UiPart<SplitPane> {

    // region Constants
    private static final String FXML = "PersonProfileField.fxml";
    private static final String KEY_HEADER = "Field";
    private static final String VALUE_HEADER = "Value";
    private static final String CSS_FONT_SIZE = "-fx-font-size: 20px;";
    // endregion

    @FXML private Label valueLabel;
    @FXML private TextField valueField;
    @FXML private Label keyLabel;

    /**
     * Constructor for PersonProfileHeader.
     */
    public PersonProfileHeader() {
        super(FXML);
        initialize();
    }

    private void initialize() {
        keyLabel.setText(KEY_HEADER);
        valueLabel.setText(VALUE_HEADER);
        valueField.setVisible(false);

        keyLabel.setStyle(CSS_FONT_SIZE);
        valueLabel.setStyle(CSS_FONT_SIZE);
    }

    @FXML
    private void handleKey() {
        //do nothing
    }

    @FXML
    private void setFocus() {
        //do nothing
    }


}
