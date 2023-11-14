package seedu.address.ui.finance;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.finance.Finance;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of finances.
 */
public class FinanceListPanel extends UiPart<Region> {
    private static final String FXML = "FinanceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FinanceListPanel.class);
    @FXML
    private ListView<Finance> financeListView;

    /**
     * Creates a {@code FinanceListPanel} with the given {@code ObservableList}.
     */
    public FinanceListPanel(ObservableList<Finance> financeList) {
        super(FXML);
        financeListView.setItems(financeList);
        financeListView.setCellFactory(listView -> new FinanceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Finance} using a {@code FinanceCard}.
     */
    class FinanceListViewCell extends ListCell<Finance> {
        @Override
        protected void updateItem(Finance finance, boolean empty) {
            super.updateItem(finance, empty);

            if (empty || finance == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FinanceCard(finance, getIndex() + 1).getRoot());
            }
        }
    }
}
