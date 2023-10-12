package transact.ui;

import java.util.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import transact.model.transaction.Transaction;

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
        idCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("id"));
        // TODO Switch to enum when transaction class is implemented
        TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));

        TableColumn<Transaction, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("id"));

        TableColumn<Transaction, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("id"));

        TableColumn<Transaction, Integer> amtCol = new TableColumn<>("Amount");
        amtCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("id"));

        // TableColumn<Transaction, Integer> staffCol = new TableColumn<>("Staff");

        transactionTable.getColumns().setAll(idCol, typeCol, dateCol, descCol, amtCol);

        transactionTable.setItems(transactionList);
    }
}
