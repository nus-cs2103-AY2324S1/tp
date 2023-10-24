package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class ProjectCard extends UiPart<Region> {

    private static final String FXML = "ProjectListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Project project;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label projects;
    @FXML
    private Label deadline;


    /**
     * Creates a {@code ProjectCode} with the given {@code Project} and index to display.
     */
    public ProjectCard(Project project, int displayedIndex) {
        super(FXML);
        this.project = project;

        // Set the index
        id.setText(displayedIndex + ". ");

        // Set the name
        name.setText(project.getNameString());

        // Set the list of employees
        String listOfEmployeesString =
                project.getEmployees().asUnmodifiableObservableList().size() == 0
                ? "No members yet."
                : "Members: " + project.getListOfEmployeeNames();
        projects.setText(listOfEmployeesString);

        // Set the deadline
        if (project.getDeadline().value == "") {
            deadline.setText("No deadline set");
        } else {
            LocalDate currentDateTime = LocalDate.now(); // Get the current date
            LocalDate deadlineDate = project.getDeadline().getLocalDate();

            String formattedDeadline = deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Format deadline for display
            deadline.setText("Deadline: " + formattedDeadline);

            if (deadlineDate.isBefore(currentDateTime)) {
                // Set the style to red if the deadline is in the past
                deadline.setStyle("-fx-text-fill: red;");
            } else {
                // Set the style to green if the deadline is in the future
                deadline.setStyle("-fx-text-fill: green;");
            }
        }
    }

}
