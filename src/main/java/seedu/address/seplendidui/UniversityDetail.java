package seedu.address.seplendidui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.university.University;

/**
 * An UI component that displays information of a {@code University}.
 */
public class UniversityDetail extends UiPart<Region> {

    private static final String FXML = "UniversityDetailCard.fxml";

    public final University university;

    @FXML
    private HBox universityDetailPane;
    @FXML
    private TextFlow universityName;

    /**
     * Creates a {@code UniversityDetail} with the given {@code University}
     */
    public UniversityDetail(University university) {
        super(FXML);
        this.university = university;
        TextFlowHeightUpdater.setAutoFitText(universityName);

        universityName.getChildren().add(new Text(university.getUniversityName().toString()));
    }
}
