package transact.logic.commands;

import static transact.logic.commands.CommandTestUtil.assertCommandFailure;
import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.testutil.TypicalPersons.getTypicalAddressBook;
import static transact.testutil.TypicalTransactions.getTypicalTransactionBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import transact.logic.Messages;
import transact.model.Model;
import transact.model.ModelManager;
import transact.model.UserPrefs;
import transact.model.person.Person;
import transact.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code AddStaffCommand}.
 */
public class AddStaffCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTransactionBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTransactionBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddStaffCommand(validPerson), model,
                String.format(AddStaffCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddStaffCommand(personInList), model,
                AddStaffCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
