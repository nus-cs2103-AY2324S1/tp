package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of persons.
 */
public class IncomeListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(IncomeListPanel.class);

    @FXML
    private ListView<Transaction> transactionListView;

    /**
     * Creates a {@code IncomeListPanel} with the given {@code ObservableList}.
     */
    public IncomeListPanel(ObservableList<Transaction> transactionList) {
        super(FXML);
        transactionListView.setItems(transactionList);
        transactionListView.setCellFactory(listView -> new IncomeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Income} using a {@code IncomeCard}.
     */
    class IncomeListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
            super.updateItem(transaction, empty);

            if (empty || transaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionCard(transaction, getIndex() + 1).getRoot());
            }
        }
    }

}
