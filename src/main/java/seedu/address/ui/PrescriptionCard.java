package seedu.address.ui;

import static seedu.address.model.prescription.Prescription.EXPIRE_PREDICATE;
import static seedu.address.model.prescription.Prescription.STOCK_PREDICATE;

// import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
// import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.prescription.Prescription;

/**
 * An UI component that displays information of a {@code Prescription}.
 */
public class PrescriptionCard extends UiPart<Region> {

    private static final String FXML = "PrescriptionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Prescription prescription;
    private final boolean showStatus;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label dosage;
    @FXML
    private Label frequency;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label expiryDateHeader;
    @FXML
    private Label expiryDate;
    @FXML
    private Label totalStockHeader;
    @FXML
    private Label totalStock;
    @FXML
    private Label consumptionCount;
    @FXML
    private Label note;

    /**
     * Creates a {@code PrescriptionCode} with the given {@code Prescription} and index to display.
     */
    public PrescriptionCard(Prescription prescription, int displayedIndex, boolean showStatus) {
        super(FXML);
        this.prescription = prescription;
        this.showStatus = showStatus;
        id.setText(displayedIndex + ". ");

        name.setText(prescription.getName().toString());

        if (prescription.getDosage().isPresent()) {
            dosage.setText(prescription.getDosage().get().toString());
        } else {
            dosage.setText("");
        }

        if (prescription.getFrequency().isPresent()) {
            frequency.setText(prescription.getFrequency().get().toString());
        } else {
            frequency.setText("");
        }

        startDate.setText(prescription.getStartDate().toString());

        if (prescription.getEndDate().isPresent()) {
            endDate.setText(prescription.getEndDate().get().toString());
        } else {
            endDate.setText("");
        }

        if (prescription.getExpiryDate().isPresent()) {
            expiryDate.setText(prescription.getExpiryDate().get().toString());
            setExpiryDateHeaderStyle(prescription);
        } else {
            expiryDate.setText("");
        }

        if (prescription.getTotalStock().isPresent()) {
            totalStock.setText(prescription.getTotalStock().get().toString());
            setStockHeaderStyle(prescription);
        } else {
            totalStock.setText("");
        }

        if (prescription.getNote().isPresent()) {
            note.setText(prescription.getNote().get().toString());
        } else {
            note.setText("");
        }

        setCompletionStatus(prescription);
    }

    private void setExpiryDateHeaderStyle(Prescription prescription) {
        expiryDateHeader.getStyleClass().clear();
        if (EXPIRE_PREDICATE.test(prescription)) {
            expiryDateHeader.getStyleClass().add("cell_small_header_red");
        } else {
            expiryDateHeader.getStyleClass().add("cell_small_header");
        }
    }

    private void setStockHeaderStyle(Prescription prescription) {
        totalStockHeader.getStyleClass().clear();
        if (STOCK_PREDICATE.test(prescription)) {
            totalStockHeader.getStyleClass().add("cell_small_header_red");
        } else {
            totalStockHeader.getStyleClass().add("cell_small_header");
        }
    }

    private void setCompletionStatus(Prescription prescription) {
        consumptionCount.getStyleClass().clear();

        if (!showStatus) {
            consumptionCount.setText("");
        } else if (!prescription.getDosage().isPresent()) {
            consumptionCount.setText(String.format("Consumed %s",
                prescription.getConsumptionCount().getConsumptionCount()));
            consumptionCount.getStyleClass().add("consumption-status-grey");
        } else if (prescription.getIsCompleted()) {
            consumptionCount.setText("Completed");
            consumptionCount.getStyleClass().add("consumption-status-green");
        } else {
            consumptionCount.setText(String.format("Incomplete %s/%s",
                prescription.getConsumptionCount().getConsumptionCount(),
                dosage.getText()));
            consumptionCount.getStyleClass().add("consumption-status-red");
        }
    }
}
