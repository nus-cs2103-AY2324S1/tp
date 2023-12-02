package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.address.model.company.Company;

/**
 * Utility class for creating FlowPanes for CompanyCards.
 */
public class CompanyCardUtils {

    /**
     * Generates a FlowPane representing the priority information of the given {@code Company}.
     * If the priority is not "NONE," a FlowPane containing a label and a value label with a background color
     * corresponding to the priority level is created and added to the {@code priority} FlowPane.
     * @param company The company for which the priority FlowPane is to be generated.
     * @param includeLabel Whether to include the label in the priority FlowPane.
     * @return A FlowPane representing the priority information of the given {@code Company}.
     */
    public static FlowPane createPriorityFlowPane(Company company, boolean includeLabel) {
        assert company != null;
        FlowPane priorityPane = new FlowPane();

        if (!company.getPriority().priority.equals("NONE")) {
            Label priorityLabel = new Label("Priority: ");
            Label priorityValue = new Label(company.getPriority().priority);

            String backgroundColor = "#444444"; // Dark gray (default)

            switch (company.getPriority().priority) {
            case "HIGH":
                backgroundColor = "#990000"; // Dark red
                break;
            case "MEDIUM":
                backgroundColor = "#FF8000"; // Dark orange
                break;
            case "LOW":
                backgroundColor = "#006400"; // Dark green
                break;
            default:
                break;
            }

            priorityValue.setStyle("-fx-background-color: " + backgroundColor);

            if (includeLabel) {
                priorityPane.getChildren().addAll(priorityLabel, priorityValue);
            } else {
                priorityPane.getChildren().addAll(priorityValue);
            }
        }

        return priorityPane;
    }

}
