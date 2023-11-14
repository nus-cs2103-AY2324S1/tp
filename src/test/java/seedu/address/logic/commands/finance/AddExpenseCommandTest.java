package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.TypicalFinances.EXPENSE_THIRTY_TO_K;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.finance.Expense;
import seedu.address.model.person.Person;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddExpenseCommandTest {

    @Test
    public void execute_addSuccessful() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Person validPerson = new PersonBuilder().withName(ExpenseBuilder.DEFAULT_NAME).build();
        new AddContactCommand(validPerson).execute(modelStub);
        Expense expense = new ExpenseBuilder().build();
        CommandResult commandResult = new AddExpenseCommand(expense).execute(modelStub);
        assertEquals(String.format(AddExpenseCommand.MESSAGE_SUCCESS, Messages.formatFinance(expense)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(expense), modelStub.expensesAdded);
    }

    @Test
    public void execute_invalidClient_throwsException() {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Expense expense = new ExpenseBuilder().build();
        assertThrows(CommandException.class, () -> new AddExpenseCommand(expense).execute(modelStub),
                Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        Expense alice = new ExpenseBuilder().withPerson("Alice").build();
        Expense bob = new ExpenseBuilder().withPerson("Bob").build();
        AddExpenseCommand addAliceCommand = new AddExpenseCommand(alice);
        AddExpenseCommand addBobCommand = new AddExpenseCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddExpenseCommand addAliceCommandCopy = new AddExpenseCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different commission -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddExpenseCommand addExpenseCommand = new AddExpenseCommand(EXPENSE_THIRTY_TO_K);
        String expected = AddExpenseCommand.class.getCanonicalName() + "{toAdd=" + EXPENSE_THIRTY_TO_K + "}";
        assertEquals(expected, addExpenseCommand.toString());
    }

    private class ModelStubAcceptingExpenseAdded extends ModelStub {
        final ArrayList<Expense> expensesAdded = new ArrayList<>();
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public void addExpense(Expense expense) {
            requireNonNull(expense);
            expensesAdded.add(expense);
        }

        @Override
        public boolean isValidClient(Person client) {
            requireAllNonNull(client);
            return personsAdded.stream().anyMatch(client::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public Person getMatchedClient(Person client) {
            for (Person p : personsAdded) {
                if (p.isSamePerson(client)) {
                    return p;
                }
            }
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
