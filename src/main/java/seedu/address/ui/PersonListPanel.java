package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.musician.Musician;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Musician> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Musician> musicianList) {
        super(FXML);
        personListView.setItems(musicianList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Musician} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Musician> {
        @Override
        protected void updateItem(Musician musician, boolean empty) {
            super.updateItem(musician, empty);

            if (empty || musician == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(musician, getIndex() + 1).getRoot());
            }
        }
    }

}
