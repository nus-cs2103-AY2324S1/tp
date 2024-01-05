package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui to display graphical results of a command.
 */
public class GraphicalResultDisplay extends UiPart<Region> {
    private static final String FXML = "ResultGraphicalDisplay.fxml";

    @FXML
    private VBox graphicalResultDisplay;

    public GraphicalResultDisplay() {
        super(FXML);
    }

    public void setGraphicsToUser() {
        System.out.println("Hello");
    }

}
