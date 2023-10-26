package seedu.address.ui;

import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthComponent extends Label {
    private LocalDate currentDate;

    public YearMonthComponent() {
        getStyleClass().add("year-month");
        currentDate = LocalDate.now();
        updateLabel(); // Initialize and update the label
    }

    private void updateLabel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = currentDate.format(formatter);
        setText(formattedDate);
    }
}
