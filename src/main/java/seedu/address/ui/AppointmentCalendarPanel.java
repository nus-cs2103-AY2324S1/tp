package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;

/**
 * Panel containing the list of appointments.
 */
public class AppointmentCalendarPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentCalenderPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentCalendarPanel.class);

    @FXML
    private ObservableList<Appointment> appointmentList;

    @FXML
    private GridPane calendarGrid;
    @FXML
    private Label currentMonthLabel;

    private LocalDate currentDate;

    /**
     * Creates a {@code AppointmentCalender
     * Panel} with the given {@code ObservableList}.
     */
    public AppointmentCalendarPanel(ObservableList<Appointment> appointmentList) {
        super(FXML);
        this.appointmentList = appointmentList;
        currentDate = LocalDate.now();
        populateMonth(currentDate);
        this.appointmentList.addListener((javafx.collections.ListChangeListener.Change<? extends Appointment> c) -> {
            populateMonth(currentDate);
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment}
     * using
     * a {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
            }
        }
    }

    @FXML
    private void previousMonth() {
        currentDate = currentDate.minusMonths(1);
        populateMonth(currentDate);
    }

    @FXML
    private void nextMonth() {
        currentDate = currentDate.plusMonths(1);
        populateMonth(currentDate);
    }

    private void populateMonth(LocalDate date) {
        calendarGrid.getChildren().clear(); // Clear the previous month's data
        currentMonthLabel.setText(date.getMonth() + " " + date.getYear());

        // Print headers (days of the week)
        DayOfWeek[] daysOfWeek = { DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY };
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label label = new Label(daysOfWeek[i].toString().substring(0, 3));
            label.setAlignment(Pos.CENTER);
            label.getStyleClass().add("label-bright");
            calendarGrid.add(label, i, 0);
        }

        // Determine the first day of the month
        LocalDate firstOfMonth = date.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;

        int rowOffset = dayOfWeek;

        // Populate days
        for (int day = 1; day <= date.lengthOfMonth(); day++) {
            VBox dateBox = new VBox();
            dateBox.setMinHeight(50);
            Label dateLabel = new Label(Integer.toString(day));
            dateLabel.getStyleClass().add("label-bright");
            dateBox.getChildren().add(dateLabel);
            int currentRow = 1 + (day + rowOffset - 1) / 7;
            calendarGrid.add(dateBox, dayOfWeek, currentRow);
            dayOfWeek = (dayOfWeek + 1) % 7;
        }
        for (Appointment appointment : appointmentList) {
            LocalDate appointmentDate = appointment.getDateTime().dateTime.toLocalDate();
            if (appointmentDate.getMonth() == date.getMonth() && appointmentDate.getYear() == date.getYear()) {
                // This appointment belongs to the currently displayed month

                int day = appointmentDate.getDayOfMonth();
                int cellDayOfWeek = (firstOfMonth.getDayOfWeek().getValue() + day - 1) % 7;
                // Find the label that corresponds to this day
                for (Node node : calendarGrid.getChildren()) {
                    if (GridPane.getRowIndex(node) == 1 + (day + rowOffset - 1) / 7
                            && GridPane.getColumnIndex(node) == cellDayOfWeek) {
                        VBox dateBox = (VBox) node;
                        if (dateBox.getChildren().size() < 3) { // Assuming 'n' is 3 for illustration
                            Label appointmentLabel = new Label(appointment.getName().fullName);
                            appointmentLabel.getStyleClass().add("label-bright");
                            dateBox.getChildren().add(appointmentLabel);
                            break;
                        }
                    }
                }
            }
        }
    }
}
