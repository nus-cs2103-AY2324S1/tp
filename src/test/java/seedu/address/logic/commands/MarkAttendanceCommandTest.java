package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Attendance;
import seedu.address.testutil.PersonBuilder;

/**
 * Test class for MarkAttendanceCommand.
 *
 * Tests include checking for valid name and ID inputs, as well as the equality of command objects.
 */
public class MarkAttendanceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Test for marking attendance using a valid person's name.
     */
    @Test
    public void execute_marksAttendanceWithValidPersonName_success() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand("Amy Bee", true, LocalDate.now());

        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s", amy.getName());

        Person expectedAmy = new PersonBuilder(amy).withAttendance(new Attendance(LocalDate.now(), true)).build();
        expectedModel.addPerson(expectedAmy);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test for marking attendance using a valid person's ID.
     */
    @Test
    public void execute_marksAttendanceWithValidPersonID_success() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand("A1234567E", true, LocalDate.now());

        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s", amy.getName());

        Person expectedAmy = new PersonBuilder(amy).withAttendance(new Attendance(LocalDate.now(), true)).build();
        expectedModel.addPerson(expectedAmy);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test for marking attendance using an invalid person's name.
     */
    @Test
    public void execute_marksAttendanceWithInvalidPersonName_failure() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand("Bobby", true, LocalDate.now());

        assertCommandFailure(markAttendanceCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    /**
     * Test for marking attendance using an invalid person's ID.
     */
    @Test
    public void execute_marksAttendanceWithInvalidPersonID_failure() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand("A1234555E", true, LocalDate.now());

        assertCommandFailure(markAttendanceCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    /**
     * Test for the equality of MarkAttendanceCommand objects.
     */
    @Test
    public void equals() {

        MarkAttendanceCommand markAmyAttendanceFirstCommand = new MarkAttendanceCommand("Amy Bee", true,
                LocalDate.now());
        MarkAttendanceCommand markBobAttendanceCommand = new MarkAttendanceCommand("Bob", false, LocalDate.now());

        // same object -> returns true
        assertEquals(markAmyAttendanceFirstCommand, markAmyAttendanceFirstCommand);

        // same values -> returns true
        MarkAttendanceCommand markAmyAttendanceFirstCommandCopy = new MarkAttendanceCommand("Amy Bee", true,
                LocalDate.now());
        assertEquals(markAmyAttendanceFirstCommand, markAmyAttendanceFirstCommandCopy);

        // null -> returns not equals
        assertNotEquals(markAmyAttendanceFirstCommand, null);

        // different values -> returns not equals
        assertNotEquals(markAmyAttendanceFirstCommand, markBobAttendanceCommand);
    }
}
