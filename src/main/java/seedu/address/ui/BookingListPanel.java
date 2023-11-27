package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;

/**
 * Panel containing the list of persons.
 */
public class BookingListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BookingListPanel.class);

    @FXML
    private ListView<Booking> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public BookingListPanel(ObservableList<Booking> bookingList) {
        super(FXML);
        personListView.setItems(bookingList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Booking> {
        @Override
        protected void updateItem(Booking booking, boolean empty) {
            super.updateItem(booking, empty);

            if (empty || booking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BookingCard(booking, getIndex() + 1).getRoot());
            }
        }
    }

}
