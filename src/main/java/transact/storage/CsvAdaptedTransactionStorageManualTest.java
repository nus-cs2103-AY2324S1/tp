package transact.storage;
import transact.model.ReadOnlyTransactionLog;
import transact.model.TransactionLog;
import transact.storage.CsvAdaptedTransactionStorage;
import transact.commons.exceptions.DataLoadingException;
import transact.model.transaction.*;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.math.RoundingMode;
import java.util.Optional;

public class CsvAdaptedTransactionStorageManualTest {
    public static void main(String[] args) {
        // 指定要保存和读取的文件路径
        Path filePath = Path.of("transactions.csv");

        // 创建一个测试事务日志
        ReadOnlyTransactionLog transactionLog = createTestTransactionLog();

        // 创建 CsvAdaptedTransactionStorage 对象
        CsvAdaptedTransactionStorage storage = new CsvAdaptedTransactionStorage(filePath);

        try {
            // 保存事务日志到文件
            storage.saveTransactionLog(transactionLog);
            System.out.println("Transaction log saved successfully.");

            // 读取事务日志
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
        // 创建一个测试事务日志
        // 这里需要根据你的业务逻辑创建事务对象
        // 以下仅为示例代码
        Transaction transaction1 = new Expense(new TransactionId("11111111"), new Description("Expense 1"), new Amount(new BigDecimal("10.50")));
        Transaction transaction2 = new Revenue(new TransactionId("22222222"), new Description("Revenue 1"), new Amount(new BigDecimal("20.75")));
        Transaction transaction3 = new Revenue(new TransactionId("2222222A"), new Description("Revenue 1"), new Amount(new BigDecimal("20.75")));
        Transaction transaction4 = new Expense(new TransactionId("22222E22"), new Description("E"), new Amount(new BigDecimal("20.75")));
        Transaction transaction5 = new Revenue(new TransactionId("22223E22"), new Description("Revenue 1"), new Amount(new BigDecimal("20.75")));
        Transaction transaction6 = new Expense(new TransactionId("23232R22"), new Description("E"), new Amount(new BigDecimal("20.75")));

        // 创建一个 ReadOnlyTransactionLog
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
