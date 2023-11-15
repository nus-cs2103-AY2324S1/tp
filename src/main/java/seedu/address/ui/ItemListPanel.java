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
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;

/**
 * Panel containing the list of menu items.
 */
public class ItemListPanel extends UiPart<Region> implements ListPanel {
    private static final String FXML = "LeftListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);

    @FXML
    private ListView<Item> leftListView;

    @FXML
    private Text name;

    @FXML
    private Label details;

    @FXML
    private Label stallIndex;

    /**
     * Creates a {@code itemListPanel} with the given {@code ObservableList}.
     */
    public ItemListPanel(ObservableList<Item> itemList, Stall stall, int stallIndex) {
        super(FXML);
        this.stallIndex.setText("Stall Index: " + stallIndex);
        name.setText(stall.getStallString());
        details.setText(stall.getLocationString());
        leftListView.setItems(itemList);
        leftListView.setCellFactory(listView -> new ItemListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stall} using a {@code StallCard}.
     */
    class ItemListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
