package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarComponent extends VBox {

    private LocalDate currentDate;
    private ObservableList<Person> personList;
    private GridPane calendarGrid;

    public CalendarComponent(ObservableList<Person> personList) {
        currentDate = LocalDate.now();
        this.personList = personList;
        calendarGrid = new GridPane();
        createCalendarUI();
    }

    private void createCalendarUI() {

        calendarGrid.getStyleClass().add("calendar-grid");

        // Define the labels for the days of the week
        String[] daysOfWeek = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };

        // Add day labels (Sun, Mon, Tue, etc.) to the calendar grid
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.getStyleClass().add("day-name-label");
            calendarGrid.add(dayLabel, i, 0);
        }

        Map<LocalDate, List<String>> leaveRecords = new HashMap<>();

        for (Person person : personList) {
            for (LocalDate leaveDate : person.getLeaveList()) {
                LocalDate currentDateToCheck = leaveDate;
                leaveRecords.computeIfAbsent(currentDateToCheck, k -> new ArrayList<>()).add(person.getName().fullName);
            }
        }

        // Determine the first day of the current month
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1 for Monday, 7 for Sunday

        int currentRow = 1;

        // Fill in the calendar grid with day labels for the current month
        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            Label dayLabel = new Label(Integer.toString(day));
            VBox dayContainer = new VBox(dayLabel);
            dayContainer.getStyleClass().add("day-container");
            dayLabel.getStyleClass().add("day-label");
            calendarGrid.add(dayContainer, (firstDayOfWeek + day - 2) % 7, currentRow);

            // Check if there is a leave record for this date
            LocalDate currentDateToCheck = currentDate.withDayOfMonth(day);

            if (leaveRecords.containsKey(currentDateToCheck)) {
                List<String> personNames = leaveRecords.get(currentDateToCheck);
                Label nameLabel = new Label(String.format("On leave : %d", personNames.size()));
                dayContainer.getChildren().add(nameLabel);
                nameLabel.getStyleClass().add("name-label");
            } else {
                Label emptyLabel = new Label("On leave : 0");
                dayContainer.getChildren().add(emptyLabel);
                emptyLabel.getStyleClass().add("empty-label");
            }

            if (day == currentDate.getDayOfMonth()) {
                dayContainer.getStyleClass().add("today");
            }

            if (day != currentDate.getDayOfMonth()) {
                dayContainer.getStyleClass().add("not-today");
            }

            if ((firstDayOfWeek + day - 2) % 7 == 6) {
                currentRow++;
            }
        }

        this.getChildren().add(calendarGrid);
    }

}
