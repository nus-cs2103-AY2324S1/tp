package seedu.letsgethired.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.letsgethired.model.application.InternApplication;

/**
 * A ui for the select view that displays the notes of a selected application.
 */
public class SelectView extends UiPart<Region> {
    private static final String FXML = "SelectView.fxml";
    @FXML
    private TextField company;
    @FXML
    private TextField role;
    @FXML
    private TextField cycle;
    @FXML
    private TextField status;
    @FXML
    private TextField deadline;
    @FXML
    private TextArea note;

    /**
     * Creates a {@code SelectView}.
     */
    public SelectView() {
        super(FXML);
    }

    /**
     * Sets detail text onto the select view text area
     * @param internApplication the application to be viewed
     */
    public void displayDetails(InternApplication internApplication) {
        requireNonNull(internApplication);
        company.setText(internApplication.getCompany().value);
        role.setText(internApplication.getRole().value);
        cycle.setText(internApplication.getCycle().value);
        status.setText(internApplication.getStatus().value);
        deadline.setText(internApplication.getDeadline().value);
        note.setText(internApplication.getNumberedListOfNotes());
    }

    /**
     * Clears detail text on the select view text area
     */
    public void clearDetails() {
        company.clear();
        role.clear();
        cycle.clear();
        status.clear();
        deadline.clear();
        note.clear();
    }
}
