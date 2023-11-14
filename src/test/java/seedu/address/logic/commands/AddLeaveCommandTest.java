package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddLeaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_invalidIndexFilteredListForAddingSingleLeave_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        LocalDate currentDate = LocalDate.now();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(outOfBoundIndex, currentDate);

        assertCommandFailure(addLeaveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredListForAddingMultipleLeave_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        LocalDate currentDate = LocalDate.now();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(outOfBoundIndex, currentDate,
                currentDate.plusDays(1));

        assertCommandFailure(addLeaveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSingleLeaveAddedToEmployee_addSuccessful() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        Person editedEmployee2 = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        editedEmployee2.getAnnualLeave().addLeave(currentDate);
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee);

        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate);
        String expectedMessage = String.format(AddLeaveCommand.MESSAGE_SUCCESS
                        + AddLeaveCommand.getLeaveStatusMessage(editedEmployee2),
                Messages.format(editedEmployee2));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedEmployee2);
        assertCommandSuccess(addLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleLeaveAddedToEmployee_addSuccessful() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        Person editedEmployee2 = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        editedEmployee2.getAnnualLeave().addLeave(currentDate, currentDate.plusDays(1));
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee);

        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate, currentDate.plusDays(1));
        String expectedMessage = String.format(AddLeaveCommand.MESSAGE_SUCCESS
                        + AddLeaveCommand.getLeaveStatusMessage(editedEmployee2),
                Messages.format(editedEmployee2));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedEmployee2);
        assertCommandSuccess(addLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStartAndEndDatesForAddingMultipleLeave_addLeaveFailure() throws Exception {
        LocalDate currentDate = LocalDate.now();
        AddLeaveCommand addLeaveCommand1 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate);
        AddLeaveCommand addLeaveCommand2 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(2), currentDate);

        assertCommandFailure(addLeaveCommand1, model, AnnualLeave.MESSAGE_INVALID_LEAVE);
        assertCommandFailure(addLeaveCommand2, model, AnnualLeave.MESSAGE_INVALID_LEAVE);
    }

    @Test
    public void execute_invalidStartDateForLeave_addLeaveFailure() throws Exception {
        Index targetIndex = INDEX_FIRST_PERSON;
        LocalDate currentDate = LocalDate.now();
        AddLeaveCommand addLeaveCommand1 =
                new AddLeaveCommand(targetIndex, currentDate.plusDays(-1), null);
        AddLeaveCommand addLeaveCommand2 =
                new AddLeaveCommand(targetIndex, currentDate.plusDays(-2), null);

        assertCommandFailure(addLeaveCommand1, model, AnnualLeave.MESSAGE_ADD_EXPIRED_LEAVE);
        assertCommandFailure(addLeaveCommand2, model, AnnualLeave.MESSAGE_ADD_EXPIRED_LEAVE);
    }

    @Test
    public void execute_invalidDatesForAddingMultipleLeave_addLeaveFailure() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        editedEmployee.getAnnualLeave().addLeave(currentDate, currentDate.plusDays(1));
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee);
        AddLeaveCommand addLeaveCommand1 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        AddLeaveCommand addLeaveCommand2 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(3));

        assertCommandFailure(addLeaveCommand1, model, AnnualLeave.MESSAGE_ADD_DUPLICATE_LEAVE);
        assertCommandFailure(addLeaveCommand2, model, AnnualLeave.MESSAGE_ADD_DUPLICATE_LEAVE);
    }

    @Test
    public void execute_addTooManyDatesForAddingMultipleLeave_addLeaveFailure() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee);
        AddLeaveCommand addLeaveCommand1 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(20));
        AddLeaveCommand addLeaveCommand2 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(30));

        assertCommandFailure(addLeaveCommand1, model, AnnualLeave.MESSAGE_LEAVE_CONSTRAINTS);
        assertCommandFailure(addLeaveCommand2, model, AnnualLeave.MESSAGE_LEAVE_CONSTRAINTS);
    }

    @Test
    public void execute_equals_failure() throws Exception {
        LocalDate currentDate = LocalDate.now();
        AddLeaveCommand addLeaveCommand1 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        AddLeaveCommand addLeaveCommand2 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        AddLeaveCommand addLeaveCommand3 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), null);
        AddLeaveCommand addLeaveCommand4 =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), null);
        assertTrue(addLeaveCommand1.equals(addLeaveCommand1));
        assertTrue(!addLeaveCommand2.equals(null));
        assertTrue(addLeaveCommand1.equals(addLeaveCommand2));
        assertTrue(addLeaveCommand3.equals(addLeaveCommand4));
    }

    @Test
    public void toStringMethod() {
        LocalDate currentDate = LocalDate.now();
        AddLeaveCommand addLeaveCommand =
                new AddLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        String expected = AddLeaveCommand.class.getCanonicalName() + "{toAddLeave="
                + INDEX_FIRST_PERSON + "}";
        assertEquals(expected, addLeaveCommand.toString());
    }

}
