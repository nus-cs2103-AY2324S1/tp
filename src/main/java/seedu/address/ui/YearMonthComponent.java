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
        createLabel(); // Initialize and update the label
    }

    /**
     * Creates a {@code YearMonthComponent} with a given date.
     */
    public YearMonthComponent(LocalDate newDate) {
        getStyleClass().add("year-month");
        currentDate = newDate;
        createLabel(); // Initialize and update the label
    }

    private void createLabel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = currentDate.format(formatter);
        setText(formattedDate);
    }

    public void updateLabel() {
        createLabel();
    }
    public void setCurrentDate(LocalDate newDate) {
        this.currentDate = newDate;
    }
}
