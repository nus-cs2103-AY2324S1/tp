package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_SESSIONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SessionBuilder;

public class ViewAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), new ConsultationListBook(), new GradedTestListBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), new ConsultationListBook(), new GradedTestListBook());

    @Test
    public void equals() {
        Person alice = new PersonBuilder(ALICE).build();
        Person bob = new PersonBuilder(BOB).build();

        Set<Name> namesA = new HashSet<>();
        Set<Name> namesB = new HashSet<>();
        namesA.add(alice.getName());
        namesB.add(bob.getName());

        ViewAttendanceCommand viewAttendanceFirstCommand = new ViewAttendanceCommand(namesA);
        ViewAttendanceCommand viewAttendanceSecondCommand = new ViewAttendanceCommand(namesB);

        // same object -> returns true
        assertTrue(viewAttendanceFirstCommand.equals(viewAttendanceFirstCommand));

        // same values -> returns true
        ViewAttendanceCommand viewAttendanceFirstCommandCopy = new ViewAttendanceCommand(namesA);
        assertTrue(viewAttendanceFirstCommand.equals(viewAttendanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewAttendanceFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewAttendanceFirstCommand.equals(null));

        // different names -> returns false
        assertFalse(viewAttendanceFirstCommand.equals(viewAttendanceSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noSessionFound() {
        String expectedMessage = String.format(MESSAGE_SESSIONS_LISTED_OVERVIEW, 0);
        ViewAttendanceCommand command = new ViewAttendanceCommand(new HashSet<>());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSessionList());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Set<Name> names = new HashSet<>();
        names.add(new Name("John Doe"));

        ViewAttendanceCommand viewAttendanceCommand = new ViewAttendanceCommand(names);

        String expectedMessage = Messages.MESSAGE_PERSON_NOT_FOUND;
        assertCommandFailure(viewAttendanceCommand, model, expectedMessage);
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        Session s1 = new SessionBuilder().withSessionNumber("1").withStudent(CARL).build();
        Session s2 = new SessionBuilder().withSessionNumber("2").withStudent(DANIEL).build();
        model.addSession(s1);
        model.addSession(s2);
        expectedModel.addSession(s1);
        expectedModel.addSession(s2);
        String expectedMessage = String.format(MESSAGE_SESSIONS_LISTED_OVERVIEW, 2);
        Set<Name> names = new HashSet<>();
        names.add(CARL.getName());
        names.add(DANIEL.getName());
        ViewAttendanceCommand command = new ViewAttendanceCommand(names);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(s1, s2), model.getFilteredSessionList());
    }

    @Test
    public void getCommandType() {
        Set<Name> names = new HashSet<>();
        names.add(new Name("first"));
        ViewAttendanceCommand command = new ViewAttendanceCommand(names);

        assertEquals(command.getCommandType(), CommandType.VIEW_ATTENDANCE);
    }

    @Test
    public void toStringMethod() {
        Set<Name> names = new HashSet<>();
        names.add(new Name("keyword"));
        ViewAttendanceCommand viewAttendanceCommand = new ViewAttendanceCommand(names);
        String expected = ViewAttendanceCommand.class.getCanonicalName()
                + "{names=" + names + "}";
        assertEquals(expected, viewAttendanceCommand.toString());
    }
}
