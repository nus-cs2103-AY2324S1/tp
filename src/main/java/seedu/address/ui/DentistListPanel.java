package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.dentist.Dentist;

/**
 * Panel containing the list of dentists.
 */
public class DentistListPanel extends UiPart<Region> {
    private static final String FXML = "DentistListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DentistListPanel.class);

    @FXML
    private ListView<Dentist> dentistListView;

    /**
     * Creates a {@code DentistListPanel} with the given {@code ObservableList}.
     */
    public DentistListPanel(ObservableList<Dentist> dentistList) {
        super(FXML);
        dentistListView.setItems(dentistList);
        dentistListView.setCellFactory(listView -> new DentistListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Dentist} using a {@code DentistCard}.
     */
    class DentistListViewCell extends ListCell<Dentist> {
        @Override
        protected void updateItem(Dentist dentist, boolean empty) {
            super.updateItem(dentist, empty);

            if (empty || dentist == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DentistCard(dentist, getIndex() + 1).getRoot());
            }
        }
    }

}
