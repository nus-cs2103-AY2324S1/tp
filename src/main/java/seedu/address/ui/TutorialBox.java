package seedu.address.ui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.model.tutorial.Tutorial;

/**
 * A UI component that displays information of a {@code Tutorial}.
 */
public class TutorialBox extends UiPart<Region> {

    private static final String FXML = "TutorialBox.fxml";

    private List<Tutorial> tutorialList;

    @FXML
    private HBox tutorialPane;

    @FXML
    private Text tutorialsTaken;

    /**
     * Creates a {@code TutorialBox} with the given {@code Tutorial} to display.
     */
    public TutorialBox(ObservableList<Tutorial> tutorials) {
        super(FXML);
        this.tutorialList = tutorials;
        // tutorialsTaken.setText("Tutorials taken: ");
        setDisplayText();
        tutorialsTaken.setStroke(Color.WHITE);
    }

    public void setDisplayText() {
        String toBeDisplayed = "Tutorials: ";
        for (Tutorial tutorial : tutorialList) {
            toBeDisplayed += (tutorial + " ");
        }
        tutorialsTaken.setText(toBeDisplayed);
    }
}
