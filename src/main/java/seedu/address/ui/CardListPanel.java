package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of persons.
 */
public class CardListPanel extends UiPart<Region> {
    private static final String FXML = "CardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CardListPanel.class);

    @FXML
    private ListView<Person> personListView;

    @FXML
    private ListView<Transaction> transactionListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CardListPanel(ObservableList<Person> personList, ObservableList<Transaction> transactionList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new ItemListViewCell());
        personList.addListener((ListChangeListener<Person>) c -> {
            personListView.setVisible(!personList.isEmpty());
            personListView.setManaged(!personList.isEmpty());
        });

        transactionListView.setItems(transactionList);
        transactionListView.setCellFactory(listView -> new TransactionListViewCell());
        // Hide transaction list on startup
        transactionListView.setVisible(false);
        transactionListView.setManaged(false);
        transactionList.addListener((ListChangeListener<Transaction>) c -> {
            transactionListView.setVisible(!transactionList.isEmpty());
            transactionListView.setManaged(!transactionList.isEmpty());
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ItemListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
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
