package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PersonProfileHeader extends UiPart<AnchorPane> {
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
    private void handleOnAction() {}


}
