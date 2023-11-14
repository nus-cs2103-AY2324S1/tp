package transact.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.ObservableMap;
import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.person.Person;
import transact.model.person.PersonId;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.TransactionId;
import transact.ui.MainWindow.TabWindow;

/**
 * Deletes a person identified using it's id.
 */
public class DeleteStaffCommand extends Command {

    public static final String COMMAND_WORD = "delstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their unique id displayed in the person list.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_FAILURE = "Cannot find person with id: %d";
    private final Integer personId;

    public DeleteStaffCommand(Integer personId) {
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (PersonId id : model.getAddressBook().getPersonMap().keySet()) {
            if (id.getValue().equals(personId)) {
                Person deletedPerson = model.deletePerson(id);

                // This will set transactions associated with the deleted person to not be associated with anyone
                ArrayList<Transaction> transactionsToEdit = new ArrayList<>();
                ObservableMap<TransactionId, Transaction> transactionMap = model.getTransactionMap();
                transactionMap.values().stream().filter(t -> t.getPersonId().equals(id.getValue()))
                        .forEach(transactionsToEdit::add);
                transactionsToEdit.forEach(t -> {
                    Transaction editedTransaction = new Transaction(t.getTransactionId(), t.getTransactionType(), t.getDescription(),
                            t.getAmount(), t.getDate(), -1);
                    model.setTransaction(t.getTransactionId(), editedTransaction);
                });

                return new CommandResult(
                        String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(deletedPerson)),
                        TabWindow.ADDRESSBOOK);
            }
        }
        throw new CommandException(String.format(MESSAGE_DELETE_PERSON_FAILURE, personId));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStaffCommand)) {
            return false;
        }

        DeleteStaffCommand otherDeleteStaffCommand = (DeleteStaffCommand) other;
        return personId.equals(otherDeleteStaffCommand.personId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personId", personId)
                .toString();
    }
}
