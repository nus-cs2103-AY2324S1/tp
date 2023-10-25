package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<Event> eventListView;

    private ObservableList<Person> personList;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<Event> eventList, ObservableList<Person> personList) {
        super(FXML);
        this.personList = personList;
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (event.isOverDue()) {
                    setGraphic(new ExpiredEventCard(personList, event,getIndex() + 1).getRoot());
                } else {
                    setGraphic(new EventCard(personList, event, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
