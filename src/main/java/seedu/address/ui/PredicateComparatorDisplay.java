package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.SortCommand;

/**
 * A ui to show active predicates and comparators that is displayed at the header of the application.
 */
public class PredicateComparatorDisplay extends UiPart<Region> {

    private static final String FXML = "PredicateComparatorDisplay.fxml";

    @FXML
    private HBox placeHolderBox;

    @FXML
    private TextArea comparatorDisplay;

    @FXML
    private TextArea filterDisplay;

    /**
     * Initialises the Predicate Comparator Display.
     */
    public PredicateComparatorDisplay() {
        super(FXML);
    }

    /**
     * Sets the comparator to be displayed.
     * @param prefix The current prefix being compared.
     * @param order The order used in comparison.
     */
    public void setComparator(String prefix, SortCommand.Order order) {
        comparatorDisplay.setText(String.format("Sorting by %s in %s order", prefix, order));
    }

    /**
     * Sets the filter that is displayed.
     * @param prefix The current prefix used for filtering
     * @param value The value used for filtering
     */
    public void setFilter(String prefix, String value) {
        filterDisplay.setText(prefix.equals("default")
                ? "No filters are applied."
                : String.format("Filtering by %s with values %s", prefix, value));
    }
}
