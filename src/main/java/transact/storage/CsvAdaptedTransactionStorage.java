package transact.storage;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyTransactionLog;
import transact.model.TransactionLog;
import transact.model.person.Name;
import transact.model.person.Person;
import transact.model.transaction.*;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.storage.TransactionLogStorage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvAdaptedTransactionStorage implements TransactionLogStorage {

    private Path filePath;

    public CsvAdaptedTransactionStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getTransactionLogFilePath() {
        return filePath;
    }
    @Override
    public Optional<ReadOnlyTransactionLog> readTransactionLog(Path path) throws DataLoadingException {
        try {
            if (!Files.exists(path)) {
                // 如果文件不存在，返回一个空的Optional
                return Optional.empty();
            }

            // 创建一个用于存储读取的事务的列表
            TransactionLog transactions = new TransactionLog();

            // 使用CSVReader读取CSV文件
            try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
                String[] header = reader.readNext(); // 读取CSV文件的标题行
                String[] row;
                while ((row = reader.readNext()) != null) {
                    String transactionId = row[0];
                    String category = row[1];
                    String person = row[2];
                    String description = row[3];
                    BigDecimal amount = new BigDecimal(row[4]).setScale(2, RoundingMode.HALF_UP);

                    // 根据category创建Expense或Revenue对象
                    Transaction transaction;
                    if (category.equals("Expense")) {
                        if (person.isEmpty()) {
                            transaction = new Expense(new TransactionId(transactionId), new Description(description), new Amount(amount));
                        } else {
                            transaction = new Expense(new TransactionId(transactionId), new Description(description), new Amount(amount));
                        }
                    } else {
                        if (person.isEmpty()) {
                            transaction = new Revenue(new TransactionId(transactionId), new Description(description), new Amount(amount));
                        } else {
                            transaction = new Expense(new TransactionId(transactionId), new Description(description), new Amount(amount));
                        }
                    }

                    transactions.addTransaction(transaction);
                }
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }

            // 使用读取的数据创建一个新的TransactionLog对象
            ReadOnlyTransactionLog transactionLog = new TransactionLog(transactions);

            return Optional.of(transactionLog);
        } catch (IOException e) {
            throw new DataLoadingException(e);
        }
    }

    @Override
    public void saveTransactionLog(ReadOnlyTransactionLog transactionLog) throws IOException {
        List<Transaction> transactions = transactionLog.getTransactionList();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath.toFile()))) {
            // 创建CSV文件的标题行
            String[] header = {"TransactionId", "Category", "Person", "Description", "Amount"};
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                // 获取事务的各个字段
                String transactionId = transaction.getTransactionId().toString();
                String category = (transaction instanceof Expense) ? "Expense" : "Revenue";
                String person = (transaction.hasPersonInfo()) ? transaction.getPerson().toString() : "";
                String description = transaction.getDescription().toString();
                String amount = transaction.getAmount().toString();

                // 创建CSV行
                String[] row = {transactionId, category, person, description, amount};

                // 将CSV行写入文件
                writer.writeNext(row);
            }
        }
    }

    @Override
    public Optional<ReadOnlyTransactionLog> readTransactionLog() throws DataLoadingException {
        return readTransactionLog(filePath);
    }

    @Override
    public void saveTransactionLog(ReadOnlyTransactionLog transactionLog, Path filePath) throws IOException {
        List<Transaction> transactions = transactionLog.getTransactionList();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath.toFile()))) {
            // 创建CSV文件的标题行
            String[] header = {"TransactionId", "Category", "Person", "Description", "Amount"};
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                // 获取事务的各个字段
                String transactionId = transaction.getTransactionId().toString();
                String category = (transaction instanceof Expense) ? "Expense" : "Revenue";
                String person = (transaction.hasPersonInfo()) ? transaction.getPerson().toString() : "";
                String description = transaction.getDescription().toString();
                String amount = transaction.getAmount().toString();

                // 创建CSV行
                String[] row = {transactionId, category, person, description, amount};

                // 将CSV行写入文件
                writer.writeNext(row);
            }
        }
    }

}
