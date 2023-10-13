package transact.storage;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Optional;

import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyTransactionBook;
import transact.model.TransactionBook;
import transact.model.transaction.Expense;
import transact.model.transaction.Revenue;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

public class CsvAdaptedTransactionStorageManualTest {
    public static void main(String[] args) {
        Path filePath = Path.of("transactions.csv");

        ReadOnlyTransactionBook transactionBook = createTestTransactionBook();

        CsvAdaptedTransactionStorage storage = new CsvAdaptedTransactionStorage(filePath);

        try {
            storage.saveTransactionBook(transactionBook);
            System.out.println("Transaction log saved successfully.");

            Optional<ReadOnlyTransactionBook> readTransactionBook = storage.readTransactionBook();
            if (readTransactionBook.isPresent()) {
                System.out.println("Transaction log loaded successfully.");
                System.out.println("Loaded Transaction Book:");
                System.out.println(readTransactionBook.get());
            } else {
                System.out.println("No transaction log found.");
            }
            storage.saveTransactionBook(transactionBook);
            System.out.println("Transaction log saved successfully.");
        } catch (IOException | DataLoadingException e) {
            e.printStackTrace();
        }
    }

    private static ReadOnlyTransactionBook createTestTransactionBook() {
        Transaction transaction1 = new Expense(new TransactionId("11111111"), new Description("Expense 1"),
                new Amount(new BigDecimal("10.50")));
        Transaction transaction2 = new Revenue(new TransactionId("22222222"), new Description("Revenue 1"),
                new Amount(new BigDecimal("20.75")));
        Transaction transaction3 = new Revenue(new TransactionId("2222222A"), new Description("Revenue 1"),
                new Amount(new BigDecimal("20.75")));
        Transaction transaction4 = new Expense(new TransactionId("22222E22"), new Description("E"),
                new Amount(new BigDecimal("20.75")));
        Transaction transaction5 = new Revenue(new TransactionId("22223E22"), new Description("Revenue 1"),
                new Amount(new BigDecimal("20.75")));
        Transaction transaction6 = new Expense(new TransactionId("23232R22"), new Description("E"),
                new Amount(new BigDecimal("20.75")));

        TransactionBook transactionBook = new TransactionBook();
        transactionBook.addTransaction(transaction1);
        transactionBook.addTransaction(transaction2);
        transactionBook.addTransaction(transaction3);
        transactionBook.addTransaction(transaction4);
        transactionBook.addTransaction(transaction5);
        transactionBook.addTransaction(transaction6);

        return transactionBook;
    }
}
