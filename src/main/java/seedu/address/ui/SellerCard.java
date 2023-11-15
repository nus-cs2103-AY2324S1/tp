package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.displayable.seller.Seller;


/**
 * A UI component that displays information of a {@code Seller}.
 */
public class SellerCard extends PersonCard {
    private static final String FXML = "SellerListCard.fxml";

    @FXML
    private Label sellingAddress;
    @FXML
    private Label houseInfo;

    /**
     * Creates a {@code SellerCard} with the given {@code Seller} and index to display.
     */
    public SellerCard(Seller seller, int displayedIndex) {
        super(seller, displayedIndex, FXML);
        houseInfo.setText(seller.getHouseInfo().toString());
        sellingAddress.setText(seller.getSellingAddress().value);
    }
}
