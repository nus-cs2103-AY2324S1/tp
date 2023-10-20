package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class Sidebar extends UiPart<Region> {
    private static final String FXML = "Sidebar.fxml";

    @FXML
    private Label username;

    public Sidebar() {
        super(FXML);
    }

    public void setUsername(String name) {
        username.setText(name);
    }
}
