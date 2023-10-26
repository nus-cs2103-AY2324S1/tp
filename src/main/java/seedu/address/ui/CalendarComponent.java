package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

import java.time.LocalDate;

public class CalendarComponent extends VBox {

    private LocalDate currentDate;
    private ObservableList<Person> personList;

    public CalendarComponent(ObservableList<Person> personList) {
        currentDate = LocalDate.now();
        this.personList = personList;
        getStyleClass().add("calendar-label");
        createCalendarUI();
    }

    private void createCalendarUI() {
        // Create your calendar UI elements, such as labels, to display the current month's calendar.
        GridPane calendarGrid = new GridPane();

        calendarGrid.getStyleClass().add("calendar-grid");

        // Define the labels for the days of the week
        String[] daysOfWeek = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };

        // Add day labels (Sun, Mon, Tue, etc.) to the calendar grid
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.getStyleClass().add("day");
            calendarGrid.add(dayLabel, i, 0);
        }
        
        // Determine the first day of the current month
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1 for Monday, 7 for Sunday

        int currentRow = 1;

        // Fill in the calendar grid with day labels for the current month
        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            Label dayLabel = new Label(Integer.toString(day));

            calendarGrid.add(dayLabel, (firstDayOfWeek + day - 2) % 7, currentRow);

            if (day == currentDate.getDayOfMonth()) {
                dayLabel.getStyleClass().add("today");
            }

            if (day != currentDate.getDayOfMonth()) {
                dayLabel.getStyleClass().add("not-today");
            }

            if ((firstDayOfWeek + day - 2) % 7 == 6) {
                currentRow++;
            }
        }

        this.getChildren().add(calendarGrid);
    }
}