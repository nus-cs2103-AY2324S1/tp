package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.internship.task.InternshipTask;

/**
 * Represents an InternshipTask Card
 */
public class InternshipTaskCard extends UiPart<Region> {
    private static final String FXML = "InternshipTaskCard.fxml";

    private final InternshipTask task;

    @FXML
    private Label id;

    @FXML
    private Label roleDetails;

    @FXML
    private Label name;

    @FXML
    private Label deadline;

    @FXML
    private Label status;

    @FXML
    private Label outcome;

    @FXML
    private FlowPane tags;

    @FXML
    private HBox cardPane;

    /**
     * Crates an InternshipTask card with the given task
     * @param task to be displayed
     * @param displayedIndex of the task
     */
    public InternshipTaskCard(InternshipTask task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        roleDetails.setText("Role: " + task.getInternshipRole().getMainDetails());
        name.setText("Task: " + task.getTaskName().toString());
        deadline.setText("Deadline: " + task.getDeadline().toString());
        status.setText("Status: " + task.getStatus().toString());
        outcome.setText("Outcome: " + task.getOutcome().toString());
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    HBox container = new HBox();
                    Label l = new Label(tag.tagName);
                    container.getChildren().add(l);
                    l.setWrapText(true);
                    cardPane.widthProperty().addListener((obs, oldVal, newVal) -> {
                        l.setMaxWidth(newVal.doubleValue() - 25);
                    });
                    tags.getChildren().add(container);
                });
    }
}
