package transact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static transact.testutil.Assert.assertThrows;
import static transact.testutil.TypicalTransactions.APPLES;
import static transact.testutil.TypicalTransactions.BANANAS;
import static transact.testutil.TypicalTransactions.getTypicalTransactionBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transact.model.entry.exceptions.DuplicateEntryException;
import transact.model.transaction.Transaction;
import transact.testutil.TransactionBuilder;

public class TransactionBookTest {

    private final TransactionBook transactionBook = new TransactionBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), transactionBook.getTransactionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTransactionBook_replacesData() {
        TransactionBook newData = getTypicalTransactionBook();
        transactionBook.resetData(newData);
        assertEquals(newData, transactionBook);
    }

    @Test
    public void resetData_withDuplicateTransactions_throwsDuplicateEntryException() {
        // Two transactions with the same identity fields
        Transaction editedTrans = new TransactionBuilder(APPLES).withDescription("Apple 123").withAmount(10.00f)
                .build();
        List<Transaction> newTransactions = Arrays.asList(APPLES, editedTrans);
        TransactionBookTest.TransactionBookStub newData = new TransactionBookTest.TransactionBookStub(newTransactions);

        assertThrows(DuplicateEntryException.class, () -> transactionBook.resetData(newData));
    }

    @Test
    public void hasTransaction_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionBook.hasTransaction(null));
    }

    @Test
    public void hasTransaction_transactionNotInAddressBook_returnsFalse() {
        assertFalse(transactionBook.hasTransaction(APPLES));
    }

    @Test
    public void hasTransaction_transactionInAddressBook_returnsTrue() {
        transactionBook.addTransaction(APPLES);
        assertTrue(transactionBook.hasTransaction(APPLES));
    }

    @Test
    public void hasTransaction_transactionWithSameIdentityFieldsInAddressBook_returnsTrue() {
        transactionBook.addTransaction(BANANAS);
        Transaction editedTrans = new TransactionBuilder(BANANAS).withDescription("Banana 123").withAmount(123)
                .build();
        assertTrue(transactionBook.hasTransaction(editedTrans));
    }

    @Test
    public void getTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> transactionBook.getTransactionList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = TransactionBook.class.getCanonicalName() + "{transactions=" + transactionBook.getTransactionList() + "}";
        assertEquals(expected, transactionBook.toString());
    }

    /**
     * A stub ReadOnlyTransactionBook whose transactions list can violate interface
     * constraints.
     */
    private static class TransactionBookStub implements ReadOnlyTransactionBook {
        private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        TransactionBookStub(Collection<Transaction> transactions) {
            this.transactions.setAll(transactions);
        }

        @Override
        public ObservableList<Transaction> getTransactionList() {
            return transactions;
        }
    }
}
