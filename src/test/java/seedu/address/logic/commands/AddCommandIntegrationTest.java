package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.SpecialistBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPatient_success() {
        Person validPerson = new PatientBuilder().build();
        CommandHistory commandHistory = new CommandHistory();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);
        expectedModel.updateFilteredPersonList(PersonType.PATIENT.getSearchPredicate());
        expectedModel.commitAddressBook();

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel, commandHistory);
    }

    @Test
    public void execute_newSpecialist_success() {
        Person validPerson = new SpecialistBuilder().build();
        CommandHistory commandHistory = new CommandHistory();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);
        expectedModel.updateFilteredPersonList(PersonType.SPECIALIST.getSearchPredicate());
        expectedModel.commitAddressBook();

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel, commandHistory);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        CommandHistory commandHistory = new CommandHistory();
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON, commandHistory);
    }

}
