package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

public class PersonProfileHeader extends UiPart<SplitPane> {
    private static final String FXML = "PersonProfileField.fxml";
    private static final String KEY_HEADER = "Field";
    private static final String VALUE_HEADER = "Value";

    @FXML private Label valueLabel;
    @FXML private TextField valueField;
    @FXML private Label keyLabel;

    public PersonProfileHeader() {
        super(FXML);
        initialize();
    }

    private void initialize() {
        keyLabel.setText(KEY_HEADER);
        valueLabel.setText(VALUE_HEADER);
        valueField.setVisible(false);

        keyLabel.setStyle("-fx-font-size: 20px;");
        valueLabel.setStyle("-fx-font-size: 20px;");
    }

    @FXML
    private void handleKey() {}

    @FXML
    private void setFocus() {}


}
