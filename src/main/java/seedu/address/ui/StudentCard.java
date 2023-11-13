package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;
    private CommandExecutor showNote;
    private int index;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex, CommandExecutor showNote) {
        super(FXML);
        this.student = student;
        this.showNote = showNote;
        index = displayedIndex;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().value);
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);

        student.getRiskLevel().forEach(tag -> {
            Label tagLabel = new Label(tag.riskLevel);
            tagLabel.getStyleClass().add(getTagStyleClass(tag.riskLevel));
            tags.getChildren().add(tagLabel);
        });
    }

    @FXML
    private void displayNote(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            showNote.execute(index);
        }
    }


    private String getTagStyleClass(String riskLevel) {
        switch (riskLevel) {
        case "low":
            return "low-risk-tag";
        case "medium":
            return "medium-risk-tag";
        case "high":
            return "high-risk-tag";
        default:
            return "";
        }
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        void execute(int studentIndex);
    }
}
