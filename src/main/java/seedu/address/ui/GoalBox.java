package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.goal.Goal;

/**
 * The UI component that is responsible for displaying the user's
 * progress and goal.
 */
public class GoalBox extends UiPart<Region> {
    private static final String FXML = "GoalBox.fxml";
    public final Goal goal;
    @javafx.fxml.FXML
    private HBox goalBox;
    @FXML
    private TextFlow goalString;

    private StringProperty goalTextProperty;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public GoalBox(Goal goal) {
        super(FXML);
        this.goal = goal;

        // Initialize the goalTextProperty and bind it to the goal's text property
        goalTextProperty = new SimpleStringProperty(goal.getGoalText());
        goalTextProperty.bind(goal.goalTextProperty());

        // Create a Text element to represent the goal text
        Text goalText = new Text();

        // Bind the Text element to the goalTextProperty
        goalText.textProperty().bind(goalTextProperty);

        // Create a TextFlow and add the Text element to it
        TextFlow textFlow = new TextFlow(goalText);

        // Add the TextFlow to the goalString
        goalString.getChildren().add(textFlow);
    }
}
