package transact.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import transact.commons.core.GuiSettings;
import transact.commons.core.LogsCenter;
import transact.commons.exceptions.DataLoadingException;
import transact.logic.commands.Command;
import transact.logic.commands.CommandResult;
import transact.logic.commands.exceptions.CommandException;
import transact.logic.parser.AddressBookParser;
import transact.logic.parser.exceptions.ParseException;
import transact.model.Model;
import transact.model.ReadOnlyAddressBook;
import transact.model.ReadOnlyTransactionBook;
import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;
import transact.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT = "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    public static final String IMPORT_FILE_MISSING = "Could not find import file at specified location in user preferences";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        if (commandResult.isExportTransactions()) {
            handleTransactionsExport();
        }

        if (commandResult.isImportTransactions()) {
            handleTransactionsImport();
        }

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveTransactionBook(model.getTransactionBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    /**
     * Handles commandResult of ExportTransactionsCommand by exporting transaction book to specified exportFilePath
     *
     * @throws CommandException
     */
    @Override
    public void handleTransactionsExport() throws CommandException {

        try {
            storage.saveTransactionBook(model.getTransactionBook(), storage.getExportTransactionsFilePath());
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }
    }

    /**
     * Handles commandResult of ImportTransactionsCommand by reading transactions at importFilePath and adding them to transaction book
     *
     * @throws CommandException if import file does not exist
     */
    @Override
    public void handleTransactionsImport() throws CommandException {
        Optional<ReadOnlyTransactionBook> transactionBookOptional;
        ReadOnlyTransactionBook importedTransactionData;

        try {
            transactionBookOptional = storage.readTransactionBook(storage.getImportTransactionsFilePath());
            if (!transactionBookOptional.isPresent()) {
                logger.info("No Import file present");
                throw new CommandException(IMPORT_FILE_MISSING);
            } else {
                importedTransactionData = transactionBookOptional.get();
                Map<TransactionId, Transaction> transactions = importedTransactionData.getTransactionMap();
                for (Map.Entry<TransactionId, Transaction> entry : transactions.entrySet()) {
                    Transaction transaction = entry.getValue();
                    model.addTransaction(transaction);
                }
            }
        } catch (DataLoadingException e) {
            logger.warning("Import file at " + storage.getImportTransactionsFilePath() + " could not be loaded.");
        }
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return model.getFilteredTransactionList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
