package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static transact.testutil.Assert.assertThrows;
import static transact.testutil.TransactionBuilder.DEFAULT_AMOUNT;
import static transact.testutil.TransactionBuilder.DEFAULT_DATE;
import static transact.testutil.TransactionBuilder.DEFAULT_DESC;
import static transact.testutil.TransactionBuilder.DEFAULT_PERSON;
import static transact.testutil.TransactionBuilder.DEFAULT_TYPE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import transact.logic.Messages;
import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionType;
import transact.testutil.ModelStub;

public class AddTransactionCommandTest {
    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTransactionCommand(null, null, null, null, null));
    }

    @Test
    public void execute_transactionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTransactionAdded modelStub = new ModelStubAcceptingTransactionAdded();
        TransactionType type = TransactionType.getType(DEFAULT_TYPE);
        Description desc = new Description(DEFAULT_DESC);
        Amount amt = new Amount(DEFAULT_AMOUNT);
        Date date = new Date(DEFAULT_DATE);
        Integer staffId = DEFAULT_PERSON.getPersonId().getValue();

        AddTransactionCommand command = new AddTransactionCommand(type, desc, amt, date, staffId);
        CommandResult result = command.execute(modelStub);

        Transaction addedTransaction = modelStub.getLastTransaction();

        assertEquals(String.format(AddTransactionCommand.MESSAGE_SUCCESS, Messages.format(addedTransaction)),
                result.getFeedbackToUser());
        assertEquals(Arrays.asList(addedTransaction), modelStub.transactionsAdded);
    }


    /**
     * A Model stub that always accept the transaction being added.
     */
    private class ModelStubAcceptingTransactionAdded extends ModelStub {

        final ArrayList<Transaction> transactionsAdded = new ArrayList<>();

        public Person getPerson(Integer staffId) {
            return DEFAULT_PERSON;
        }

        public void addTransaction(Transaction transaction) {
            requireNonNull(transaction);
            transactionsAdded.add(transaction);
        }

        public Transaction getLastTransaction() {
            return transactionsAdded.get(transactionsAdded.size() - 1);
        }
    }


}
