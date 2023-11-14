package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.university.University;

/**
 * An UI component that displays information of a {@code University}.
 */
public class UniversityItem extends UiPart<Region> {

    private static final String FXML = "UniversityItemCard.fxml";

    public final University university;

    @FXML
    private HBox universityItemPane;
    @FXML
    private Label id;
    @FXML
    private Label universityName;

    /**
     * Creates a {@code UniversityItem} with the given {@code University}
     */
    public UniversityItem(University university, int displayedIndex) {
        super(FXML);
        this.university = university;
        id.setText(displayedIndex + ".");
        universityName.setText(university.getUniversityName().toString());
    }
}
