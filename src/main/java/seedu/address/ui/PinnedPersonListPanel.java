package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of pinned persons.
 */
public class PinnedPersonListPanel extends UiPart<Region> {
    private static final String FXML = "PinnedPersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PinnedPersonListPanel.class);

    @FXML
    private ListView<Person> pinnedPersonListView;

    /**
     * Creates a {@code PinnedPersonListPanel} with the given {@code ObservableList}.
     */
    public PinnedPersonListPanel(ObservableList<Person> pinnedPersonList) {
        super(FXML);
        pinnedPersonListView.setItems(pinnedPersonList);
        pinnedPersonListView.setCellFactory(listView -> new PinnedPersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PinnedPersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
