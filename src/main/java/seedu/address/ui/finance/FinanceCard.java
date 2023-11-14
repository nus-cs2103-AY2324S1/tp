package seedu.address.ui.finance;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Finance;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Finance}.
 */
public class FinanceCard extends UiPart<Region> {
    private static final String FXML = "FinanceListCard.fxml";
    public final Finance finance;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label amount;
    @FXML
    private Label client;
    @FXML
    private Label description;
    @FXML
    private Label timeDue;
    /**
     * Creates a {@code FinanceCode} with the given {@code Finance} and index to display.
     */
    public FinanceCard(Finance finance, int displayedIndex) {
        super(FXML);
        this.finance = finance;
        id.setText(displayedIndex + ". ");
        timeDue.setText(finance.getTimeDue().toString());
        client.setText(finance.getClientName().fullName);
        description.setText(finance.getDescription().value);
        boolean isCommission = finance instanceof Commission;
        if (isCommission) {
            amount.setText("+ " + finance.getAmount().value);
            amount.setStyle("-fx-text-fill: #18A827;");
        } else {
            amount.setText("- " + finance.getAmount().value);
            amount.setStyle("-fx-text-fill: #D04127;");
        }
    }
}
