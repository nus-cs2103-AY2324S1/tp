package seedu.cc.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.model.appointment.AppointmentEvent;

/**
 * Panel containing the appointments.
 */
public class AppointmentPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentPanel.class);

    @javafx.fxml.FXML
    private ListView<AppointmentEvent> appointmentEventListView;

    /**
     * Creates a {@code AppointmentPanel} with the given {@code ObservableList}.
     */
    public AppointmentPanel(ObservableList<AppointmentEvent> appointments) {
        super(FXML);
        appointmentEventListView.setItems(appointments);
        appointmentEventListView.setCellFactory(listView -> new AppointmentPanel
                .AppointmentEventListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code AppointmentEventCard}.
     */
    class AppointmentEventListViewCell extends ListCell<AppointmentEvent> {
        @Override
        protected void updateItem(AppointmentEvent appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentEventCard(appointment, getIndex() + 1).getRoot());
            }
        }
    }
}
