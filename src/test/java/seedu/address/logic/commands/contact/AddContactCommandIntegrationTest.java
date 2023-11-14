package seedu.address.logic.commands.contact;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsBook;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddContactCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), getTypicalEventsBook(), getTypicalFinancesBook(),
                new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getEventsBook(), model.getFinancesBook(),
                new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddContactCommand(validPerson), model,
                String.format(AddContactCommand.MESSAGE_SUCCESS, Messages.formatPerson(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddContactCommand(personInList), model,
                AddContactCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
