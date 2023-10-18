package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class BandListPanel extends UiPart<Region> {
    private static final String FXML = "BandListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BandListPanel.class);

    @FXML
    private ListView<Band> bandListView;

    /**
     * Creates a {@code BandListPanel} with the given {@code ObservableList}.
     */
    public BandListPanel(ObservableList<Band> bandList) {
        super(FXML);
        bandListView.setItems(bandList);
        bandListView.setCellFactory(listView -> new BandListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Musician} using a {@code PersonCard}.
     */
    class BandListViewCell extends ListCell<Band> {
        @Override
        protected void updateItem(Band band, boolean empty) {
            super.updateItem(band, empty);

            if (empty || band == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BandCard(band, getIndex() + 1).getRoot());
            }
        }
    }

}
