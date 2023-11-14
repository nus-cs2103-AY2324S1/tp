package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.internship.role.InternshipRole;

/**
 * Card for an InternshipRole
 */
public class InternshipRoleCard extends UiPart<Region> {
    private static final String FXML = "InternshipRoleCard.fxml";

    private final InternshipRole internshipRole;

    @FXML
    private Label id;

    @FXML
    private Label roleDetails;

    @FXML
    private Label description;

    @FXML
    private Label pay;

    @FXML
    private Label outcome;

    @FXML
    private Label internLocation;

    @FXML
    private FlowPane tags;

    @FXML
    private HBox cardPane;


    /**
     * Represents an internship role gui card
     * @param internshipRole to display
     * @param displayedIndex to display
     */
    public InternshipRoleCard(InternshipRole internshipRole, int displayedIndex) {
        super(FXML);
        this.internshipRole = internshipRole;
        id.setText(displayedIndex + ". ");
        roleDetails.setText(this.internshipRole.getMainDetails());
        description.setText("Description: " + this.internshipRole.getDescription().toString());
        pay.setText("Pay: " + this.internshipRole.getPay().toString());
        outcome.setText("Outcome: " + this.internshipRole.getApplicationOutcome().toString());
        internLocation.setText("Location: " + this.internshipRole.getLocation().toString());
        internshipRole.getTags().stream()
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
