package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemReviewPanel extends UiPart<Region> implements DetailsPanel {
    private static final String FXML = "ItemReviewPanel.fxml";
    private static Logger logger = Logger.getLogger("ItemReviewPanel");

    @FXML
    private HBox cardPane;

    @FXML
    private Text itemReview;

    @FXML
    private Text itemName;

    @FXML
    private Text itemPrice;

    @FXML
    private Label itemRating;


    /**
     * Creates an {@code ItemReviewPanel} with the given item.
     */
    public ItemReviewPanel(Item item) {
        super(FXML);
        itemPrice.setText(item.getPriceString());
        itemName.setText(item.getName().fullName);
        assert item.getItemRatingString() != null;
        itemRating.setText(item.getItemRatingString());
        assert item.getItemDescriptionString() != null;
        itemReview.setText(item.getItemDescriptionString());
        itemReview.wrappingWidthProperty().bind(cardPane.widthProperty());
        logger.info("ItemReviewPanel created");
    }
}
