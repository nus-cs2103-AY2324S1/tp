package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.CollectionUtil.requireAllNonNull;
import static transact.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static transact.logic.parser.CliSyntax.PREFIX_STAFF;
import static transact.logic.parser.CliSyntax.PREFIX_TYPE;
import static transact.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.Objects;
import java.util.Optional;

import transact.commons.util.CollectionUtil;
import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.TransactionType;
import transact.ui.MainWindow.TabWindow;

/**
 * Edits the details of an existing transaction.
 */
public class EditTransactionCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the transaction identified "
            + "by its unique id displayed transactions list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ID (must be a positive integer) "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_STAFF + "STAFF_ID]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TYPE + "E "
            + PREFIX_AMOUNT + "10000";
    public static final String MESSAGE_EDIT_TRANSACTION_SUCCESS = "Edited Transaction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "This transaction already exists in the Transaction book.";
    public static final String MESSAGE_TRANSACTION_ID_NOT_FOUND = "Cannot find transaction with id: %d";

    private final Integer transactionId;
    private final EditTransactionDescriptor editTransactionDescriptor;

    /**
     * @param transactionId
     *            of the transaction to edit
     * @param editTransactionDescriptor
     *            details to edit the transaction with
     */
    public EditTransactionCommand(Integer transactionId, EditTransactionDescriptor editTransactionDescriptor) {
        requireAllNonNull(transactionId, editTransactionDescriptor);

        this.transactionId = transactionId;
        this.editTransactionDescriptor = new EditTransactionDescriptor(editTransactionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (editTransactionDescriptor.staffId != null && editTransactionDescriptor.staffId >= 0
                && model.getPerson(editTransactionDescriptor.staffId).equals(Person.NULL_PERSON)) {
            throw new CommandException("Staff not found");
        }

        for (TransactionId id : model.getTransactionBook().getTransactionMap().keySet()) {
            if (id.getValue().equals(transactionId)) {
                Transaction transactionToEdit = model.getTransaction(id);
                Transaction editedTransaction = createEditedTransaction(transactionToEdit, editTransactionDescriptor,
                        model);

                model.setTransaction(id, editedTransaction);

                model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
                return new CommandResult(
                        String.format(MESSAGE_EDIT_TRANSACTION_SUCCESS, Messages.format(editedTransaction)),
                        TabWindow.TRANSACTIONS);
            }
        }
        return new CommandResult(
                String.format(MESSAGE_TRANSACTION_ID_NOT_FOUND, transactionId),
                TabWindow.TRANSACTIONS);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Transaction createEditedTransaction(Transaction transactionToEdit,
            EditTransactionDescriptor editTransactionDescriptor, Model model) {
        assert transactionToEdit != null;

        TransactionType updatedTransactionType = editTransactionDescriptor.getType()
                .orElse(transactionToEdit.getTransactionType());
        Description updatedDescription = editTransactionDescriptor.getDescription()
                .orElse(transactionToEdit.getDescription());
        Amount updatedAmount = editTransactionDescriptor.getAmount()
                .orElse(transactionToEdit.getAmount());
        Date updatedDate = editTransactionDescriptor.getDate()
                .orElse(transactionToEdit.getDate());

        Integer updatedStaffId = transactionToEdit.getPersonId();

        if (editTransactionDescriptor.getStaffId().isPresent()) {
            int tem = editTransactionDescriptor.getStaffId().get();
            if (tem < 0) {
                updatedStaffId = -1;
            } else {
                updatedStaffId = tem;
            }
        }

        return new Transaction(transactionToEdit.getTransactionId(), updatedTransactionType, updatedDescription,
                updatedAmount, updatedDate, updatedStaffId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTransactionCommand)) {
            return false;
        }

        EditTransactionCommand otherEditTransactionCommand = (EditTransactionCommand) other;
        return transactionId.equals(otherEditTransactionCommand.transactionId)
                && editTransactionDescriptor.equals(otherEditTransactionCommand.editTransactionDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactionId", transactionId)
                .add("editTransactionDescriptor", editTransactionDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the transaction with. Each non-empty field value
     * will replace the corresponding field value of the transaction.
     */
    public static class EditTransactionDescriptor {

        private TransactionType transactionType;
        private Description description;
        private Amount amount;
        private Date date;
        private Integer staffId;

        public EditTransactionDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTransactionDescriptor(EditTransactionDescriptor toCopy) {
            setType(toCopy.transactionType);
            setDescription(toCopy.description);
            setAmount(toCopy.amount);
            setDate(toCopy.date);
            setStaffId(toCopy.staffId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(transactionType, description, amount, date, staffId);
        }

        // Getter and Setter for 'transactionType'
        public Optional<TransactionType> getType() {
            return Optional.ofNullable(transactionType);
        }

        public void setType(TransactionType transactionType) {
            this.transactionType = transactionType;
        }

        // Getter and Setter for 'description'
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        // Getter and Setter for 'amount'
        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        // Getter and Setter for 'staff'
        public Optional<Integer> getStaffId() {
            return Optional.ofNullable(staffId);
        }

        public void setStaffId(Integer staffId) {
            this.staffId = staffId;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTransactionDescriptor)) {
                return false;
            }

            EditTransactionDescriptor otherEditPersonDescriptor = (EditTransactionDescriptor) other;
            return Objects.equals(transactionType, otherEditPersonDescriptor.transactionType)
                    && Objects.equals(description, otherEditPersonDescriptor.description)
                    && Objects.equals(amount, otherEditPersonDescriptor.amount)
                    && Objects.equals(date, otherEditPersonDescriptor.date)
                    && Objects.equals(staffId, otherEditPersonDescriptor.staffId);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("type", transactionType)
                    .add("description", description)
                    .add("amount", amount)
                    .add("date", date)
                    .add("staff", staffId)
                    .toString();
        }
    }
}
