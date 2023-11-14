package transact.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import transact.commons.core.GuiSettings;
import transact.logic.commands.CommandResult;
import transact.logic.commands.exceptions.CommandException;
import transact.logic.parser.exceptions.ParseException;
import transact.model.ReadOnlyAddressBook;
import transact.model.person.Person;
import transact.model.transaction.Transaction;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText
     *            The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException
     *             If an error occurs during command execution.
     * @throws ParseException
     *             If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Handles commandResult with exportTransactions as true.
     *
     * @throws CommandException
     */
    void handleTransactionsExport() throws CommandException;

    /**
     * Handles commandResult with exportStaff as true.
     *
     * @throws CommandException
     */
    void handleStaffExport() throws CommandException;

    /**
     * Returns the AddressBook.
     *
     * @see transact.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of transactions */
    ObservableList<Transaction> getFilteredTransactionList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
