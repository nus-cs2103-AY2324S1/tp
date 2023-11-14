package seedu.lovebook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.lovebook.commons.core.LogsCenter;
import seedu.lovebook.model.date.Date;

/**
 * Panel containing the list of dates.
 */
public class DateListPanel extends UiPart<Region> {
    private static final String FXML = "DateListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DateListPanel.class);

    @FXML
    private ListView<Date> personListView;

    /**
     * Creates a {@code DateListPanel} with the given {@code ObservableList}.
     */
    public DateListPanel(ObservableList<Date> dateList) {
        super(FXML);
        personListView.setItems(dateList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setSelectionModel(new NoSelectionModel<Date>());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Date} using a {@code DateCard}.
     */
    class PersonListViewCell extends ListCell<Date> {
        @Override
        protected void updateItem(Date date, boolean empty) {
            super.updateItem(date, empty);
            if (empty || date == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DateCard(date, getIndex() + 1).getRoot());
            }
        }
    }

}
