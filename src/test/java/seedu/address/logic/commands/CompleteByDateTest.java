package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_COMPLETE_SUCCESS;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_DATE_NO_APPOINTMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CompleteByDateTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void constructor_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompleteByDate(null));
    }
    @Test
    public void execute_dateSpecified_success() {
        Person editedPerson1 = new PersonBuilder(BENSON).withNullAppointment().build();
        Person editedPerson2 = new PersonBuilder(CARL).withNullAppointment().build();
        String expectedMessage = MESSAGE_COMPLETE_SUCCESS;


        CompleteByDate completeCommand = new CompleteByDate(LocalDate.of(2023, 05, 01));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(BENSON, editedPerson1);
        expectedModel.setPerson(CARL, editedPerson2);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatchingDate_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        //no matching appointment date
        CompleteByDate completeCommand = new CompleteByDate(LocalDate.of(2023, 02, 10));

        assertCommandFailure(completeCommand, expectedModel, MESSAGE_DATE_NO_APPOINTMENT);
    }

    /**
     * All entries having matching dates in addressBook should be cleared regardless of filtered list.
     */
    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON); //model filtered to show only first person
        Person editedPerson1 = new PersonBuilder(BENSON).withNullAppointment().build();
        Person editedPerson2 = new PersonBuilder(CARL).withNullAppointment().build();

        CompleteByDate completeCommand = new CompleteByDate(LocalDate.of(2023, 05, 01));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(BENSON, editedPerson1);
        expectedModel.setPerson(CARL, editedPerson2);

        assertCommandSuccess(completeCommand, model, MESSAGE_COMPLETE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        LocalDate date1 = LocalDate.of(2023, 02, 02);
        LocalDate date2 = LocalDate.of(2023, 01, 01);

        CompleteByDate completeCommand1 = new CompleteByDate(date1);
        CompleteByDate completeCommand2 = new CompleteByDate(date2);

        //same object
        assertTrue(completeCommand1.equals(completeCommand1));

        //same index
        assertTrue(completeCommand1.equals(new CompleteByDate(date1)));

        // null -> returns false
        assertFalse(completeCommand1.equals(null));

        // different types -> returns false
        assertFalse(completeCommand1.equals(new ClearCommand()));

        // diff index -> returns false
        assertFalse(completeCommand1.equals(completeCommand2));
    }
}
