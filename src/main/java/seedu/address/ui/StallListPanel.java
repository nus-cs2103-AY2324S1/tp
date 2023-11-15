package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.stall.Stall;

/**
 * Panel containing the list of stalls.
 */
public class StallListPanel extends UiPart<Region> implements ListPanel {
    private static final String FXML = "LeftListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StallListPanel.class);

    @FXML
    private ListView<Stall> leftListView;

    @FXML
    private Text name;

    @FXML
    private Label stallIndex;

    @FXML
    private Label details;

    /**
     * Creates a {@code StallListPanel} with the given {@code ObservableList}.
     */
    public StallListPanel(ObservableList<Stall> stallList) {
        super(FXML);
        name.setText("List of Stalls:");
        leftListView.setItems(stallList);
        leftListView.setCellFactory(listView -> new StallListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stall} using a {@code StallCard}.
     */
    class StallListViewCell extends ListCell<Stall> {
        @Override
        protected void updateItem(Stall stall, boolean empty) {
            super.updateItem(stall, empty);

            if (empty || stall == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StallCard(stall, getIndex() + 1).getRoot());
            }
        }
    }

}
