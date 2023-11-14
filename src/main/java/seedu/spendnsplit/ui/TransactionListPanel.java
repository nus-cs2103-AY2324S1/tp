package seedu.spendnsplit.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.spendnsplit.commons.core.LogsCenter;
import seedu.spendnsplit.model.transaction.Transaction;

/**
 * Panel containing the list of transactions.
 */
public class TransactionListPanel extends UiPartFocusable<Region> {
    private static final String FXML = "TransactionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransactionListPanel.class);

    @FXML
    private ListView<Transaction> transactionListView;

    /**
     * Creates a {@code TransactionListPanel} with the given {@code ObservableList}.
     */
    public TransactionListPanel(ObservableList<Transaction> transactionList) {
        super(FXML);
        transactionListView.setItems(transactionList);
        transactionListView.setCellFactory(listView -> new TransactionListViewCell());
    }

    /**
     * Focuses on the transaction list.
     */
    public void focus() {
        if (!transactionListView.getItems().isEmpty()) {
            transactionListView.requestFocus();
            transactionListView.getSelectionModel().select(0);
        }
    }

    /**
     * Un-focuses on the transaction list.
     * This is done by clearing the selection in the list view.
     */
    public void unFocus() {
        transactionListView.getSelectionModel().clearSelection();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Transaction} using a {@code TransactionCard}.
     */
    class TransactionListViewCell extends ListCell<Transaction> {
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
