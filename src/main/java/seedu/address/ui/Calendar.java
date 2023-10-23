package seedu.address.ui;

import java.net.URL;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class Calendar extends UiPart<Region> {

    private static final String FXML = "CalendarList.fxml";

    public static class Day {
        static ObservableList<String> days = FXCollections.observableArrayList(
            "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");
    }

    @FXML
    private ListView<String> dayListView;
    public Calendar() {
        super(FXML);
        dayListView.setItems(Day.days);
        dayListView.setCellFactory(listView -> new DayListViewCell());
    }

    class DayListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String day, boolean empty) {
            super.updateItem(day, empty);

            if (empty || day == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(day).getRoot());
            }
        }
    }
}
