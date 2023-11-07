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

public class DeleteLeaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_invalidIndexFilteredListForDeletingSingleLeave_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        LocalDate currentDate = LocalDate.now();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(outOfBoundIndex, currentDate);

        assertCommandFailure(deleteLeaveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredListForDeletingMultipleLeave_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        LocalDate currentDate = LocalDate.now();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(outOfBoundIndex, currentDate,
                currentDate.plusDays(1));

        assertCommandFailure(deleteLeaveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSingleLeaveDeletedFromEmployee_deleteSuccessful() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        Person editedEmployee2 = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        editedEmployee2.getAnnualLeave().addLeave(currentDate);
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee2);

        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate);
        String expectedMessage = String.format(DeleteLeaveCommand.MESSAGE_SUCCESS
                        + DeleteLeaveCommand.getLeaveStatusMessage(editedEmployee),
                Messages.format(editedEmployee));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedEmployee);
        assertCommandSuccess(deleteLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleLeaveDeletedFromEmployee_deleteSuccessful() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        Person editedEmployee2 = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        editedEmployee2.getAnnualLeave().addLeave(currentDate, currentDate.plusDays(1));
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee2);

        DeleteLeaveCommand deleteLeaveCommand =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate, currentDate.plusDays(1));
        String expectedMessage = String.format(DeleteLeaveCommand.MESSAGE_SUCCESS
                        + DeleteLeaveCommand.getLeaveStatusMessage(editedEmployee),
                Messages.format(editedEmployee));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedEmployee);
        assertCommandSuccess(deleteLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStartDateForLeave_deleteLeaveFailure() throws Exception {
        Index targetIndex = INDEX_FIRST_PERSON;
        LocalDate currentDate = LocalDate.now();
        DeleteLeaveCommand deleteLeaveCommand1 =
                new DeleteLeaveCommand(targetIndex, currentDate.plusDays(-1), null);
        DeleteLeaveCommand deleteLeaveCommand2 =
                new DeleteLeaveCommand(targetIndex, currentDate.plusDays(-2), currentDate.plusDays(1));

        assertCommandFailure(deleteLeaveCommand1, model, AnnualLeave.MESSAGE_DELETE_EXPIRED_LEAVE);
        assertCommandFailure(deleteLeaveCommand2, model, AnnualLeave.MESSAGE_DELETE_EXPIRED_LEAVE);
    }

    @Test
    public void execute_invalidEndDateForLeave_deleteLeaveFailure() throws Exception {
        Index targetIndex = INDEX_FIRST_PERSON;
        LocalDate currentDate = LocalDate.now();
        DeleteLeaveCommand deleteLeaveCommand1 =
                new DeleteLeaveCommand(targetIndex, currentDate.plusDays(1), currentDate);
        DeleteLeaveCommand deleteLeaveCommand2 =
                new DeleteLeaveCommand(targetIndex, currentDate.plusDays(1), currentDate.plusDays(-1));

        assertCommandFailure(deleteLeaveCommand1, model, AnnualLeave.MESSAGE_INVALID_LEAVE);
        assertCommandFailure(deleteLeaveCommand2, model, AnnualLeave.MESSAGE_INVALID_LEAVE);
    }

    @Test
    public void execute_invalidDatesForDeletingMultipleLeave_deleteLeaveFailure() throws Exception {
        Person editedEmployee = new PersonBuilder().build();
        LocalDate currentDate = LocalDate.now();
        editedEmployee.getAnnualLeave().addLeave(currentDate, currentDate.plusDays(1));
        model.setPerson(model.getFilteredPersonList().get(0), editedEmployee);
        DeleteLeaveCommand deleteLeaveCommand1 =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        DeleteLeaveCommand deleteLeaveCommand2 =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(3));

        assertCommandFailure(deleteLeaveCommand1, model, AnnualLeave.MESSAGE_DELETE_INVALID_LEAVE);
        assertCommandFailure(deleteLeaveCommand2, model, AnnualLeave.MESSAGE_DELETE_INVALID_LEAVE);
    }

    @Test
    public void execute_equals_failure() throws Exception {
        LocalDate currentDate = LocalDate.now();
        DeleteLeaveCommand deleteLeaveCommand1 =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        DeleteLeaveCommand deleteLeaveCommand2 =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        DeleteLeaveCommand deleteLeaveCommand3 =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), null);
        DeleteLeaveCommand deleteLeaveCommand4 =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), null);
        assertTrue(deleteLeaveCommand1.equals(deleteLeaveCommand1));
        assertTrue(!deleteLeaveCommand1.equals(null));
        assertTrue(deleteLeaveCommand1.equals(deleteLeaveCommand2));
        assertTrue(deleteLeaveCommand3.equals(deleteLeaveCommand4));
    }

    @Test
    public void toStringMethod() {
        LocalDate currentDate = LocalDate.now();
        DeleteLeaveCommand deleteLeaveCommand =
                new DeleteLeaveCommand(INDEX_FIRST_PERSON, currentDate.plusDays(1), currentDate.plusDays(2));
        String expected = DeleteLeaveCommand.class.getCanonicalName() + "{toDeleteLeave="
                + INDEX_FIRST_PERSON + "}";
        assertEquals(expected, deleteLeaveCommand.toString());
    }


}
