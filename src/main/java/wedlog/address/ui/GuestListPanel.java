package wedlog.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import wedlog.address.commons.core.LogsCenter;
import wedlog.address.model.person.Guest;

/**
 * Panel containing the list of guests.
 */
public class GuestListPanel extends UiPart<Region> {
    private static final String FXML = "GuestListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GuestListPanel.class);

    @FXML
    private Label guestTitle;
    @FXML
    private ListView<Guest> guestListView;

    /**
     * Creates a {@code GuestListPanel} with the given {@code ObservableList}.
     */
    public GuestListPanel(ObservableList<Guest> guestList) {
        super(FXML);
        guestTitle.setText("Guests (" + guestList.size() + ")");
        guestListView.setItems(guestList);
        guestListView.setCellFactory(listView -> new GuestListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Guest} using a {@code GuestCard}.
     */
    class GuestListViewCell extends ListCell<Guest> {
        @Override
        protected void updateItem(Guest guest, boolean isEmpty) {
            super.updateItem(guest, isEmpty);

            if (isEmpty || guest == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GuestCard(guest, getIndex() + 1).getRoot());
            }
        }
    }
}
