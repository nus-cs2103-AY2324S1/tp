package seedu.lovebook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.model.person.Date;

/**
 * Panel containing the list of dates.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Date> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Date> dateList) {
        super(FXML);
        personListView.setItems(dateList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Date} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Date> {
        @Override
        protected void updateItem(Date date, boolean empty) {
            super.updateItem(date, empty);

            if (empty || date == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(date, getIndex() + 1).getRoot());
            }
        }
    }

}
