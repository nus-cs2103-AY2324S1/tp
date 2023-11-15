package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.displayable.buyer.Buyer;


/**
 * A UI component that displays information of a {@code Buyer}.
 */
public class BuyerCard extends PersonCard {
    private static final String FXML = "BuyerListCard.fxml";
    @FXML
    private Label houseInfo;

    /**
     * Creates a {@code BuyerCard} with the given {@code Buyer} and index to display.
     */
    public BuyerCard(Buyer buyer, int displayedIndex) {
        super(buyer, displayedIndex, FXML);
        houseInfo.setText(buyer.getHouseInfo().toString());
    }
}
