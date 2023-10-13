package transact.storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import transact.commons.exceptions.DataLoadingException;
import transact.model.ReadOnlyTransactionLog;
import transact.model.TransactionLog;
import transact.model.transaction.Expense;
import transact.model.transaction.Revenue;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

/**
 * CsvAdaptedTransactionStorage class
 */
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
    public Optional<ReadOnlyTransactionLog> readTransactionLog() throws DataLoadingException {
        return readTransactionLog(filePath);
    }

    @Override
    public Optional<ReadOnlyTransactionLog> readTransactionLog(Path path) throws DataLoadingException {
        try {
            if (!Files.exists(path)) {
                return Optional.empty();
            }

            TransactionLog transactions = new TransactionLog();

            try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
                String[] header = reader.readNext();
                String[] row;
                while ((row = reader.readNext()) != null) {
                    String transactionId = row[0];
                    String category = row[1];
                    String person = row[2];
                    String description = row[3];
                    BigDecimal amount = new BigDecimal(row[4]).setScale(2, RoundingMode.HALF_UP);

                    // TODO Read in optional staff when ready
                    Transaction transaction;
                    switch (category) {
                    case "Expense":
                        transaction = new Expense(new TransactionId(transactionId), new Description(description),
                                new Amount(amount));
                        break;
                    case "Revenue":
                        transaction = new Revenue(new TransactionId(transactionId), new Description(description),
                                new Amount(amount));
                        break;
                    default:
                        // TODO Throw/Print an error
                        continue;
                    }

                    transactions.addTransaction(transaction);
                }
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }

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
            String[] header = { "TransactionId", "Category", "Person", "Description", "Amount" };
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                String transactionId = transaction.getTransactionId().toString();
                String category = (transaction instanceof Expense) ? "Expense" : "Revenue";
                String person = (transaction.hasPersonInfo()) ? transaction.getPerson().toString() : "";
                String description = transaction.getDescription().toString();
                String amount = transaction.getAmount().toString();

                String[] row = { transactionId, category, person, description, amount };

                writer.writeNext(row);
            }
        }
    }

    @Override
    public void saveTransactionLog(ReadOnlyTransactionLog transactionLog, Path filePath) throws IOException {
        List<Transaction> transactions = transactionLog.getTransactionList();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath.toFile()))) {
            String[] header = { "TransactionId", "Category", "Person", "Description", "Amount" };
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                String transactionId = transaction.getTransactionId().toString();
                String category = (transaction instanceof Expense) ? "Expense" : "Revenue";
                String person = (transaction.hasPersonInfo()) ? transaction.getPerson().toString() : "";
                String description = transaction.getDescription().toString();
                String amount = transaction.getAmount().toString();

                String[] row = { transactionId, category, person, description, amount };

                writer.writeNext(row);
            }
        }
    }

}
