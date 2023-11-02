package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_COMPLETE_SUCCESS;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_DATE_NO_APPOINTMENT;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_PERSON_NO_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CompleteCommand.CompleteDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CompleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_indexSpecified_success() {
        Person editedPerson = new PersonBuilder(BENSON).withNullAppointment().build();
        String expectedMessage = MESSAGE_COMPLETE_SUCCESS;
        CompleteDescriptor completeDescriptor = new CompleteDescriptor();
        completeDescriptor.setIndex(INDEX_SECOND_PERSON);
        CompleteCommand completeCommand = new CompleteCommand(completeDescriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPerson);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateSpecified_success() {
        Person editedPerson1 = new PersonBuilder(BENSON).withNullAppointment().build();
        Person editedPerson2 = new PersonBuilder(CARL).withNullAppointment().build();
        String expectedMessage = MESSAGE_COMPLETE_SUCCESS;
        CompleteDescriptor completeDescriptor = new CompleteDescriptor();
        completeDescriptor.setDate(LocalDate.of(2023, 05, 01));
        CompleteCommand completeCommand = new CompleteCommand(completeDescriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPerson1);
        expectedModel.setPerson(model.getFilteredPersonList().get(2), editedPerson2);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatchingDate_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        CompleteDescriptor completeDescriptor = new CompleteDescriptor();
        completeDescriptor.setDate(LocalDate.of(2023, 02, 10)); //no matching appointment date
        CompleteCommand completeCommand = new CompleteCommand(completeDescriptor);

        assertCommandFailure(completeCommand, expectedModel, MESSAGE_DATE_NO_APPOINTMENT);
    }

    @Test
    public void execute_personNoAppointment_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        CompleteDescriptor completeDescriptor = new CompleteDescriptor();
        completeDescriptor.setIndex(INDEX_FIRST_PERSON); //person has no appointment
        CompleteCommand completeCommand = new CompleteCommand(completeDescriptor);

        assertCommandFailure(completeCommand, expectedModel, MESSAGE_PERSON_NO_APPOINTMENT);
    }

    @Test
    public void equals() {
        CompleteDescriptor completeDescriptor1 = new CompleteDescriptor();
        CompleteDescriptor completeDescriptor2 = new CompleteDescriptor();

        completeDescriptor1.setIndex(INDEX_FIRST_PERSON);
        completeDescriptor2.setIndex(INDEX_SECOND_PERSON);

        CompleteCommand completeCommand1 = new CompleteCommand(completeDescriptor1);
        CompleteCommand completeCommand2 = new CompleteCommand(completeDescriptor2);

        //same object
        assertTrue(completeCommand1.equals(completeCommand1));

        // null -> returns false
        assertFalse(completeCommand1.equals(null));

        // different types -> returns false
        assertFalse(completeCommand1.equals(new ClearCommand()));

        // diff descriptor -> returns false
        assertFalse(completeCommand1.equals(completeCommand2));
    }
}
