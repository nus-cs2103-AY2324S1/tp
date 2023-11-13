package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W1_ABSENT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDANCE_W1_PRESENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_ABSENT;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_PERSON_NOT_FOUND_MULTIPLE;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_PRESENT;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.MarkAttendanceCommand.MESSAGE_UPDATED_SUCCESS;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.week.Week;
import seedu.address.testutil.PersonBuilder;

/**
 * Test class for MarkAttendanceCommand.
 *
 * Tests include checking for valid name and ID inputs, as well as the equality of command objects.
 */
public class MarkAttendanceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());

    /**
     * Test for marking attendance as present using a valid person's name.
     */
    @Test
    public void execute_marksAttendanceAsPresentWithValidPersonName_success() {
        Person amy = new PersonBuilder().build();

        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("Amy Bee"), true,
                new Week(1), null);

        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName());

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        expectedModel.addPerson(expectedAmy);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test for marking attendance as absent using a valid person's name.
     */
    @Test
    public void execute_marksAttendanceAsAbsentWithValidPersonName_success() {
        Person amy = new PersonBuilder(AMY).build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("Amy Bee"), false,
                new Week(1), "Late");

        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "1\nReason: %s\n",
                amy.getName(), amy.getName(), "Late");

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_ABSENT);
        expectedModel.addPerson(expectedAmy);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test for marking attendance as present using a valid person's ID.
     */
    @Test
    public void execute_marksAttendanceAsPresentWithValidPersonID_success() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A"), true,
                new Week(1), null);
        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName());

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        expectedModel.addPerson(expectedAmy);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test for marking attendance as absent using a valid person's ID.
     */
    @Test
    public void execute_marksAttendanceAsAbsentWithValidPersonID_success() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A"), false,
                new Week(1), "Late");
        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "1\nReason: %s\n",
                amy.getName(), amy.getName(), "Late");

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_ABSENT);
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
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("Bobby"), true,
                new Week(1), null);
        assertCommandFailure(markAttendanceCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    /**
     * Test for marking attendance using an invalid person's ID.
     */
    @Test
    public void execute_marksAttendanceWithInvalidPersonID_failure() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234555E"), true,
                new Week(1), null);
        assertCommandFailure(markAttendanceCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    /**
     * Test for marking attendance as present using multiple valid person's name.
     */
    @Test
    public void execute_marksAttendanceAsPresentWithMultipleValidPersonName_success() {

        StringBuilder expectedMessage = new StringBuilder();

        Person amy = new PersonBuilder().build();
        Person bob = new PersonBuilder(BOB).build();
        model.addPerson(amy);
        model.addPerson(bob);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("Amy Bee", "Bob Choo"),
                true, new Week(1), null);

        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName()));
        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                bob.getName(), bob.getName()));

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        Person expectedBob = new PersonBuilder(bob).build();
        expectedBob.addAttendance(ATTENDANCE_W1_PRESENT);

        expectedModel.addPerson(expectedAmy);
        expectedModel.addPerson(expectedBob);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage.toString(), expectedModel);
    }

    /**
     * Test for marking attendance as absent using multiple valid person's name.
     */
    @Test
    public void execute_marksAttendanceAsAbsentWithMultipleValidPersonName_success() {

        StringBuilder expectedMessage = new StringBuilder();

        Person amy = new PersonBuilder(AMY).build();
        Person bob = new PersonBuilder(BOB).build();
        model.addPerson(amy);
        model.addPerson(bob);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("Amy Bee", "Bob Choo"),
                false, new Week(1), "Late");

        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "1\nReason: %s\n",
                amy.getName(), amy.getName(), "Late"));
        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "1\nReason: %s\n",
                bob.getName(), bob.getName(), "Late"));

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_ABSENT);
        Person expectedBob = new PersonBuilder(bob).build();
        expectedBob.addAttendance(ATTENDANCE_W1_ABSENT);

        expectedModel.addPerson(expectedAmy);
        expectedModel.addPerson(expectedBob);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage.toString(), expectedModel);
    }

    /**
     * Test for marking attendance as present using multiple valid person's ID.
     */
    @Test
    public void execute_marksAttendanceAsPresentWithMultipleValidPersonID_success() {

        StringBuilder expectedMessage = new StringBuilder();

        Person amy = new PersonBuilder().build();
        Person bob = new PersonBuilder(BOB).build();
        model.addPerson(amy);
        model.addPerson(bob);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A", "A2345678B"),
                true, new Week(1), null);

        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName()));
        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                bob.getName(), bob.getName()));

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        Person expectedBob = new PersonBuilder(bob).build();
        expectedBob.addAttendance(ATTENDANCE_W1_PRESENT);

        expectedModel.addPerson(expectedAmy);
        expectedModel.addPerson(expectedBob);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage.toString(), expectedModel);
    }

    /**
     * Test for marking attendance as absent using mulitple valid person's ID.
     */
    @Test
    public void execute_marksAttendanceAsAbsentWithMultipleValidPersonID_success() {

        StringBuilder expectedMessage = new StringBuilder();

        Person amy = new PersonBuilder().build();
        Person bob = new PersonBuilder(BOB).build();
        model.addPerson(amy);
        model.addPerson(bob);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A", "A2345678B"),
                false, new Week(1), "Late");

        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "1\nReason: %s\n",
                amy.getName(), amy.getName(), "Late"));
        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT + "1\nReason: %s\n",
                bob.getName(), bob.getName(), "Late"));

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_ABSENT);
        Person expectedBob = new PersonBuilder(bob).build();
        expectedBob.addAttendance(ATTENDANCE_W1_ABSENT);

        expectedModel.addPerson(expectedAmy);
        expectedModel.addPerson(expectedBob);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage.toString(), expectedModel);
    }

    /**
     * Test for marking attendance using multiple names present but containing an invalid person's name.
     */
    @Test
    public void execute_marksAttendanceWithMultipleContainingInvalidPersonName_success() {

        StringBuilder expectedMessage = new StringBuilder();

        Person amy = new PersonBuilder().build();
        Person bob = new PersonBuilder(BOB).build();
        model.addPerson(amy);
        model.addPerson(bob);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("Amy Bee", "Zac"), true,
                new Week(1), null);

        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName()));
        expectedMessage.append(String.format(MESSAGE_PERSON_NOT_FOUND_MULTIPLE + "%s\n", "Zac"));

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        Person expectedBob = new PersonBuilder(bob).build();

        expectedModel.addPerson(expectedAmy);
        expectedModel.addPerson(expectedBob);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage.toString(), expectedModel);
    }

    /**
     * Test for marking attendance using multiple IDs present but containing an invalid person's ID.
     */
    @Test
    public void execute_marksAttendanceWithMultipleContainingInvalidPersonID_success() {

        StringBuilder expectedMessage = new StringBuilder();

        Person amy = new PersonBuilder().build();
        Person bob = new PersonBuilder(BOB).build();
        model.addPerson(amy);
        model.addPerson(bob);

        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A", "A0000000Z"), true,
                new Week(1), null);

        expectedMessage.append(String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName()));
        expectedMessage.append(String.format(MESSAGE_PERSON_NOT_FOUND_MULTIPLE + "%s\n", "A0000000Z"));

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        Person expectedBob = new PersonBuilder(bob).build();

        expectedModel.addPerson(expectedAmy);
        expectedModel.addPerson(expectedBob);

        assertCommandSuccess(markAttendanceCommand, model, expectedMessage.toString(), expectedModel);
    }

    /**
     * Tests if the attendance of a person is correctly updated when an attendance
     * record for the current week already exists, updating attendance to present.
     */
    @Test
    public void execute_personWithExistingAttendanceMarkedAsAbsent_updatesAttendanceAsPresent() {
        Person amy = new PersonBuilder().build();
        Week testWeek = new Week(1);
        model.addPerson(amy);
        amy.addAttendance(new Attendance(testWeek, false, "Late"));
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A"), true,
                testWeek, null);

        String expectedMessage = String.format(MESSAGE_UPDATED_SUCCESS + "%s\n%s" + MESSAGE_PRESENT + "%d\n",
                amy.getName(), amy.getName(), 1);

        Person expectedAmy = new PersonBuilder(amy).withAttendance(ATTENDANCE_W1_PRESENT).build();
        expectedModel.addPerson(expectedAmy);
        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests if the attendance of a person is correctly updated when an attendance
     * record for the current week already exists, updating attendance to absent.
     */
    @Test
    public void execute_personWithExistingAttendanceMarkedAsPresent_updatesAttendanceAsAbsent() {
        Person amy = new PersonBuilder().build();
        Week testWeek = new Week(1);
        model.addPerson(amy);
        amy.addAttendance(new Attendance(testWeek, true, null));
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A"), false,
                testWeek, "Late");

        String expectedMessage = String.format(MESSAGE_UPDATED_SUCCESS + "%s\n" + "%s" + MESSAGE_ABSENT
                        + "1\nReason: %s\n",
                amy.getName(), amy.getName(), "Late");

        Person expectedAmy = new PersonBuilder(amy).withAttendance(ATTENDANCE_W1_PRESENT).build();
        expectedModel.addPerson(expectedAmy);
        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests if the attendance of a person is correctly added when no attendance record for the current week exists.
     */
    @Test
    public void execute_personWithoutExistingAttendance_addsAttendance() {
        Person amy = new PersonBuilder().build();
        Week testWeek = new Week(1);
        model.addPerson(amy);
        MarkAttendanceCommand markAttendanceCommand = new MarkAttendanceCommand(List.of("A1234567A"), true,
            testWeek, null);

        String expectedMessage = String.format(MESSAGE_SUCCESS + "%s\n" + "%s" + MESSAGE_PRESENT + "1\n",
                amy.getName(), amy.getName());

        Person expectedAmy = new PersonBuilder(amy).build();
        expectedAmy.addAttendance(ATTENDANCE_W1_PRESENT);
        expectedModel.addPerson(expectedAmy);
        assertCommandSuccess(markAttendanceCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Test for the equality of MarkAttendanceCommand objects.
     */
    @Test
    public void equals() {
        MarkAttendanceCommand markAmyAttendanceFirstCommand = new MarkAttendanceCommand(List.of("Amy Bee"), true,
                new Week(1), null);
        MarkAttendanceCommand markBobAttendanceCommand = new MarkAttendanceCommand(List.of("Bob"), false,
                new Week(1), "Late");

        // same object -> returns true
        assertEquals(markAmyAttendanceFirstCommand, markAmyAttendanceFirstCommand);

        // same values -> returns true
        MarkAttendanceCommand markAmyAttendanceFirstCommandCopy = new MarkAttendanceCommand(List.of("Amy Bee"), true,
                new Week(1), null);

        assertEquals(markAmyAttendanceFirstCommand, markAmyAttendanceFirstCommandCopy);

        // null -> returns not equals
        assertNotEquals(markAmyAttendanceFirstCommand, null);

        // different values -> returns not equals
        assertNotEquals(markAmyAttendanceFirstCommand, markBobAttendanceCommand);
    }
}
