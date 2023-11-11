package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing student notes of a specific student.
 */
public class StudentNotePanel extends UiPart<Region> {

    private static final String FXML = "StudentNotePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private VBox columnContainer;
    @FXML
    private Label columnTitle;
    @FXML
    private Label studentName;
    @FXML
    private Label notesPara;

    /**
     * Creates a {@code StudentNotePanel} with the given {@code ObservableList}.
     */
    public StudentNotePanel(String name, String notes) {
        super(FXML);
        columnContainer.setAlignment(Pos.CENTER);
        studentName.setText(name);
        notesPara.setText(notes);
    }

    /**
     * Update the notes in the UI
     * @param name Name of the chosen student
     * @param notes Student Notes of the student
     */
    public void updateNotes(String name, String notes) {
        if (notes.trim().length() == 0) {
            notesPara.setText("You have no notes on this student");
        } else {
            notesPara.setText(notes);
        }
        studentName.setText("Student Name: " + name);
    }

    /**
     * Reset the note panel in the UI
     */
    public void resetNotes() {
        studentName.setText("No student information chosen currently");
        notesPara.setText("");
    }
}
