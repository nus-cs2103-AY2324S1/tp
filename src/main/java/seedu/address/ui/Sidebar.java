package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A UI component representing a sidebar in the application interface. The sidebar typically
 * contains user-related information, such as the username.
 */
public class Sidebar extends UiPart<Region> {
    private static final String FXML = "Sidebar.fxml";

    @FXML
    private Label username;

    /**
     * Creates a new {@code Sidebar} instance with the specified FXML resource.
     */
    public Sidebar() {
        super(FXML);
    }

    /**
     * Sets the username to be displayed in the sidebar.
     *
     * @param name The username to be displayed.
     */
    public void setUsername(String name) {
        username.setText(name);
    }
}
