package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.plan.Plan;

/**
 * Panel containing the list of plans.
 */
public class PlanListPanel extends UiPart<Region> {
    private static final String FXML = "PlanListPanel.fxml";
    @FXML
    private ListView<Plan> planListView;

    /**
     * Creates a {@code PlanListPanel} with the given {@code ObservableList}.
     */
    public PlanListPanel(ObservableList<Plan> planList) {
        super(FXML);
        planListView.setItems(planList);
        planListView.setCellFactory(listView -> new PlanListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Plan} using a {@code PlanCard}.
     */
    class PlanListViewCell extends ListCell<Plan> {
        @Override
        protected void updateItem(Plan plan, boolean empty) {
            super.updateItem(plan, empty);

            if (empty || plan == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PlanCard(plan, getIndex() + 1).getRoot());
            }
        }
    }

}
