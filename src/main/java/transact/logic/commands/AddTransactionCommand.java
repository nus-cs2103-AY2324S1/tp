package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.CollectionUtil.requireAllNonNull;
import static transact.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static transact.logic.parser.CliSyntax.PREFIX_STAFF;
import static transact.logic.parser.CliSyntax.PREFIX_TYPE;

import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionType;
import transact.ui.MainWindow.TabWindow;

/**
 * Adds a person to the address book.
 */
public class AddTransactionCommand extends Command {

    public static final String COMMAND_WORD = "addtransaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to the address book. "
            + "Parameters: "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATE + "DATE "
            + PREFIX_STAFF + "STAFF \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "E "
            + PREFIX_DESCRIPTION + "Monthly Salary "
            + PREFIX_AMOUNT + "1000 "
            + PREFIX_DATE + "10/11/23 "
            + PREFIX_STAFF + "John";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "This transaction already exists in the transaction book.";

    private final TransactionType transactionType;
    private final Description description;
    private final Amount amount;
    private final Date date;
    private final Integer staffId;

    /**
     * Creates an AddTransactionCommand to add the specified {@code Transaction} and
     * staffId {@code Integer}
     */
    public AddTransactionCommand(
            TransactionType transactionType,
            Description description,
            Amount amount,
            Date date,
            Integer staffId) {
        requireAllNonNull(transactionType, description, amount, date, staffId);
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.staffId = staffId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (staffId >= 0 && model.getPerson(staffId).equals(Person.NULL_PERSON)) {
            throw new CommandException("Staff not found");
        }

        Transaction newTransaction = new Transaction(transactionType, description, amount, date, staffId);
        model.addTransaction(newTransaction);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(newTransaction)),
                TabWindow.TRANSACTIONS);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", transactionType)
                .add("description", description)
                .add("amount", amount)
                .add("staffId", staffId)
                .toString();
    }
}
