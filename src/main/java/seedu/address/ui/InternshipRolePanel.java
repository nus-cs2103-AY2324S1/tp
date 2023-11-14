package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.internship.role.InternshipRole;

/**
 * Panel to display internshipRoles
 */
public class InternshipRolePanel extends UiPart<Region> {
    private static final String FXML = "InternshipRolePanel.fxml";

    @FXML
    private ListView<InternshipRole> internshipRoleView;

    /**
     * Creates an internship role panel with the given roles
     * @param roles to be added to panel
     */
    public InternshipRolePanel(ObservableList<InternshipRole> roles) {
        super(FXML);
        internshipRoleView.setItems(roles);
        internshipRoleView.setCellFactory(listView -> new InternshipRoleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of
     * a {@code InternshipRole} using a {@code InternshipRoleCard}.
     */
    class InternshipRoleListViewCell extends ListCell<InternshipRole> {
        @Override
        protected void updateItem(InternshipRole role, boolean empty) {
            super.updateItem(role, empty);

            if (empty || role == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InternshipRoleCard(role, getIndex() + 1).getRoot());
            }
        }
    }
}
