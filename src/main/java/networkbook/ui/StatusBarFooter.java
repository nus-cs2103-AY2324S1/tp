package networkbook.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import networkbook.model.person.PersonSortComparator.SortField;
import networkbook.model.person.PersonSortComparator.SortOrder;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";
    private static final String FILTER_INDICATOR_FORMAT = "[ Filter by: %s ]";
    private static final String SORT_INDICATOR_FORMAT = "[ Sort by: %s%s ]";

    @FXML
    private Label saveLocationStatus;
    @FXML
    private Label filterStatus;
    @FXML
    private Label sortStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
        updateFilterStatus("none");
        updateSortStatus(SortField.NAME, SortOrder.ASCENDING);
    }

    /**
     * Updates displayed filter status.
     * @param field Field that is currently being filtered by.
     */
    public void updateFilterStatus(String field) {
        filterStatus.setText(String.format(FILTER_INDICATOR_FORMAT, field));
    }

    /**
     * Updates displayed sorting status.
     * @param field Field that is currently being sorted by
     * @param order Order that is currently being sorted by.
     */
    public void updateSortStatus(SortField field, SortOrder order) {
        String displayedOrder = ", with order: " + order.toString().toLowerCase();
        sortStatus.setText(String.format(SORT_INDICATOR_FORMAT, field.toString().toLowerCase(), displayedOrder));
    }
}
