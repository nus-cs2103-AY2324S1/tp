package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.stall.Stall;

/**
 * Panel containing the list of menu items in a stall.
 */
public class StallDetailsPanel extends UiPart<Region> implements DetailsPanel {
    private static final String FXML = "StallDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StallListPanel.class);
    @FXML
    private HBox cardPane;
    @FXML
    private Label stallRating;

    @FXML
    private Text stallDescription;

    /**
     * Creates a {@code StallListPanel} with the given {@code ObservableList}.
     */
    public StallDetailsPanel(Stall stall) {
        super(FXML);
        if (stall.hasStallReview()) {
            stallRating.setText("Rating: " + stall.getStallStarRating());
            stallDescription.setText(stall.getStallDescription());
            stallDescription.wrappingWidthProperty().bind(cardPane.widthProperty());
        } else {
            stallRating.setText("Rating: No rating yet!");
            stallDescription.setText("Add a review to see it here!");
        }
    }
}
