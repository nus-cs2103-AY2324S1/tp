package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Panel to display tasks and roles
 */
public class InternPanel extends UiPart<Region> {

    private static final String FXML = "InternPanel.fxml";

    @FXML
    private VBox internshipRolePlaceHolder;

    @FXML
    private VBox internshipTaskPlaceHolder;

    /**
     * Adds role panel task panel to this panel
     * @param rolePanel to be added
     * @param taskPanel to be added
     */
    public InternPanel(InternshipRolePanel rolePanel, InternshipTaskPanel taskPanel) {
        super(FXML);
        internshipRolePlaceHolder.getChildren().add(rolePanel.getRoot());
        internshipTaskPlaceHolder.getChildren().add(taskPanel.getRoot());
    }

}
