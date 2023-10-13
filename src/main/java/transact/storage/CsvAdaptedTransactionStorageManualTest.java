package transact.storage;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Optional;

import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyTransactionLog;
import transact.model.TransactionLog;
import transact.model.transaction.Expense;
import transact.model.transaction.Revenue;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

public class CsvAdaptedTransactionStorageManualTest {
    public static void main(String[] args) {
        Path filePath = Path.of("transactions.csv");

        ReadOnlyTransactionLog transactionLog = createTestTransactionLog();

        CsvAdaptedTransactionStorage storage = new CsvAdaptedTransactionStorage(filePath);

        try {
            storage.saveTransactionLog(transactionLog);
            System.out.println("Transaction log saved successfully.");

            Optional<ReadOnlyTransactionLog> readTransactionLog = storage.readTransactionLog();
            if (readTransactionLog.isPresent()) {
                System.out.println("Transaction log loaded successfully.");
                System.out.println("Loaded Transaction Log:");
                System.out.println(readTransactionLog.get());
            } else {
                System.out.println("No transaction log found.");
            }
            storage.saveTransactionLog(transactionLog);
            System.out.println("Transaction log saved successfully.");
        } catch (IOException | DataLoadingException e) {
            e.printStackTrace();
        }
    }

    private static ReadOnlyTransactionLog createTestTransactionLog() {
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

        TransactionLog transactionLog = new TransactionLog();
        transactionLog.addTransaction(transaction1);
        transactionLog.addTransaction(transaction2);
        transactionLog.addTransaction(transaction3);
        transactionLog.addTransaction(transaction4);
        transactionLog.addTransaction(transaction5);
        transactionLog.addTransaction(transaction6);

        return transactionLog;
    }
}
