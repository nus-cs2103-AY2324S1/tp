package transact.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionType;

/**
 * Panel containing the table of transactions.
 */
public class TransactionTablePanel extends UiPart<Region> {

    private static final String FXML = "TransactionTablePanel.fxml";

    @FXML
    private TableView<Transaction> transactionTable;

    /**
     * Creates a {@code TransactionTablePanel} with the given
     * {@code ObservableList}.
     */
    public TransactionTablePanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        TableColumn<Transaction, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionId"));

        TableColumn<Transaction, TransactionType> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Transaction, TransactionType>("transactionType"));

        TableColumn<Transaction, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));

        TableColumn<Transaction, Description> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<Transaction, Description>("description"));

        TableColumn<Transaction, Amount> amtCol = new TableColumn<>("Amount");
        amtCol.setCellValueFactory(new PropertyValueFactory<Transaction, Amount>("amount"));

        // TableColumn<Transaction, Integer> staffCol = new TableColumn<>("Staff");

        transactionTable.getColumns().setAll(idCol, typeCol, dateCol, descCol, amtCol);

        transactionTable.setItems(transactionList);
    }
}
