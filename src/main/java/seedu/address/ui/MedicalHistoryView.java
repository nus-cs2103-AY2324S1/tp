package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.MedicalHistory;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class MedicalHistoryView extends UiPart<Region> {
    private static final String fxml = "MedicalHistoryView.fxml";

    @FXML
    private Label medicalHistory;
    @FXML
    private Label id;
    /**
     * Creates a {@code PersonCode} with the given {@code Person}.
     */
    public MedicalHistoryView(MedicalHistory medicalHistory, int displayedIndex) {
        super(fxml);
        this.medicalHistory.setText(medicalHistory.value);
        this.id.setText(displayedIndex + ". ");
    }
}
