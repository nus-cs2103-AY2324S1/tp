package seedu.address.ui;

import java.awt.Font;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DayCard extends UiPart<Region> {
    private static final String FXML = "DayCard.fxml";

    public final String dayName;

    @FXML
    private HBox dayCard;
    @FXML
    private Label day;
    @FXML
    private Label task;

    public DayCard(String dayName) {
        super(FXML);
        this.dayName = dayName;
        day.setText(dayName);
        task.setText("To be implemented");
    }

}
