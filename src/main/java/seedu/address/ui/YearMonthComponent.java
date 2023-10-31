package seedu.address.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Label;

/**
 * The UI component that is responsible for showing the year and month.
 */
public class YearMonthComponent extends Label {
    private LocalDate currentDate;

    /**
     * Creates a {@code YearMonthComponent} with the current date.
     */
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
