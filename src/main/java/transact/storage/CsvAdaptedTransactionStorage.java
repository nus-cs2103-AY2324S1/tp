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
import transact.model.ReadOnlyTransactionBook;
import transact.model.TransactionBook;
import transact.model.transaction.Transaction;



/**
 * A class to access TransactionBook data stored as a csv file on the hard disk.
 */
public class CsvAdaptedTransactionStorage implements TransactionBookStorage {

    private Path filePath;

    public CsvAdaptedTransactionStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getTransactionBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTransactionBook> readTransactionBook() throws DataLoadingException {
        return readTransactionBook(filePath);
    }

    @Override
    public Optional<ReadOnlyTransactionBook> readTransactionBook(Path path) throws DataLoadingException {
        try {
            if (!Files.exists(path)) {
                return Optional.empty();
            }

            TransactionBook transactions = new TransactionBook();

            try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
                String[] header = reader.readNext();
                String[] row;
                while ((row = reader.readNext()) != null) {
                    String transactionId = row[0];
                    String category = row[1];
                    String description = row[2];
                    BigDecimal amount = new BigDecimal(row[3]).setScale(2, RoundingMode.HALF_UP);
                    String date = row[4];
                    String person = row[5];

                    /* TODO Read in optional staff when ready
                    Transaction transaction = new Transaction(transactionId, TransactionType.R, )
                    }

                    transactions.addTransaction(transaction);
                     */
                }
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }

            ReadOnlyTransactionBook transactionBook = new TransactionBook(transactions);

            return Optional.of(transactionBook);
        } catch (IOException e) {
            throw new DataLoadingException(e);
        }
    }

    @Override
    public void saveTransactionBook(ReadOnlyTransactionBook transactionBook) throws IOException {
        List<Transaction> transactions = transactionBook.getTransactionList();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath.toFile()))) {
            String[] header = { "TransactionId", "Category", "Description", "Amount", "Date", "Person"};
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                String transactionId = transaction.getTransactionId().toString();
                String category = (transaction instanceof Transaction) ? "Expense" : "Revenue";
                String person = (transaction.hasPersonInfo()) ? transaction.getPerson().toString() : "";
                String description = transaction.getDescription().toString();
                String amount = transaction.getAmount().toString();
                String date = transaction.getDate().toString();

                String[] row = { transactionId, category, description, amount, date, person };

                writer.writeNext(row);
            }
        }
    }

    /**
     * Similar to {@link #saveTransactionBook(ReadOnlyTransactionBook)} (ReadOnlyTransactionBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTransactionBook(ReadOnlyTransactionBook transactionBook, Path filePath) throws IOException {
        List<Transaction> transactions = transactionBook.getTransactionList();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath.toFile()))) {
            String[] header = { "TransactionId", "Category", "Description", "Amount", "Date", "Person" };
            writer.writeNext(header);

            for (Transaction transaction : transactions) {
                String transactionId = transaction.getTransactionId().toString();
                String category = (transaction instanceof Transaction) ? "Expense" : "Revenue";
                String person = (transaction.hasPersonInfo()) ? transaction.getPerson().toString() : "";
                String description = transaction.getDescription().toString();
                String amount = transaction.getAmount().toString();
                String date = transaction.getDate().toString();
                String[] row = { transactionId, category, description, amount, date, person };

                writer.writeNext(row);
            }
        }
    }

}
