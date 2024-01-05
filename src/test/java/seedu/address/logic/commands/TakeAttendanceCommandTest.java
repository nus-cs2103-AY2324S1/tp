package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.attendance.AttendancePresence;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionNumber;
import seedu.address.testutil.PersonBuilder;


public class TakeAttendanceCommandTest {

    @Test
    public void execute_takeAttendanceWithSingleStudent_success() throws CommandException {
        Model model = new ModelManager();
        Person student = new PersonBuilder().withName("Alice").build();
        Person newStudent = new PersonBuilder().withName("Bob").build();
        model.addPerson(student);
        model.addPerson(newStudent);
        Session currentSession = new Session(new SessionNumber("2"), student);
        model.addSession(currentSession);

        assertEquals(1, currentSession.getStudents().size());
        assertTrue(currentSession.getStudents().contains(student));

        TakeAttendanceCommand command =
                new TakeAttendanceCommand(new SessionNumber("2"), new Name("Bob"), AttendancePresence.PRESENT);
        command.execute(model);

        Session updatedSession = model.findSessionBySessionNumber(currentSession.getSessionNumber());

        assertEquals(2, updatedSession.getStudents().size());
        assertTrue(updatedSession.getStudents().contains(newStudent));

        TakeAttendanceCommand markAbsentCommand =
                new TakeAttendanceCommand(new SessionNumber("2"), new Name("Bob"), AttendancePresence.ABSENT);
        markAbsentCommand.execute(model);

        updatedSession = model.findSessionBySessionNumber(updatedSession.getSessionNumber());
        assertEquals(1, updatedSession.getStudents().size());
        assertFalse(updatedSession.getStudents().contains(newStudent));
    }


    @Test
    public void execute_takeAttendanceWithMultipleStudents_success() throws CommandException {
        Model model = new ModelManager();
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        Session currentSession = new Session(new SessionNumber("3"));
        model.addPerson(alice);
        model.addPerson(bob);
        model.addSession(currentSession);

        Set<Name> studentNames = new HashSet<>();
        studentNames.add(new Name("Alice"));
        studentNames.add(new Name("Bob"));

        assertEquals(0, currentSession.getStudents().size());
        TakeAttendanceCommand command = new TakeAttendanceCommand(
                currentSession.getSessionNumber(), studentNames, AttendancePresence.PRESENT);
        command.execute(model);

        Session updatedSession = model.findSessionBySessionNumber(currentSession.getSessionNumber());

        assertEquals(2, updatedSession.getStudents().size());
        assertTrue(updatedSession.getStudents().contains(alice));
        assertTrue(updatedSession.getStudents().contains(bob));

        TakeAttendanceCommand markAbsentCommand = new TakeAttendanceCommand(
                currentSession.getSessionNumber(), studentNames, AttendancePresence.ABSENT);
        markAbsentCommand.execute(model);

        updatedSession = model.findSessionBySessionNumber(updatedSession.getSessionNumber());

        assertEquals(0, updatedSession.getStudents().size());
        assertFalse(updatedSession.getStudents().contains(alice));
        assertFalse(updatedSession.getStudents().contains(bob));
    }

    @Test
    public void execute_invalidArguments_throwsPersonNotFoundException() {
        Model model = new ModelManager();
        Person alice = new PersonBuilder().withName("Alice").build();
        Session currentSession = new Session(new SessionNumber("3"));
        model.addPerson(alice);
        model.addSession(currentSession);

        Set<Name> studentNames = new HashSet<>();
        studentNames.add(new Name("Alice")); // Alice in model
        studentNames.add(new Name("Bob")); // Bob not in model


        TakeAttendanceCommand markPresentCommandInvalidName = new TakeAttendanceCommand(
                currentSession.getSessionNumber(), studentNames, AttendancePresence.PRESENT);
        assertCommandFailure(markPresentCommandInvalidName, model, TakeAttendanceCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidArguments_throwsSessionNotFoundException() {
        Model model = new ModelManager();
        Person alice = new PersonBuilder().withName("Alice").build();
        Session currentSession = new Session(new SessionNumber("3"));
        model.addPerson(alice);
        model.addSession(currentSession); // Session with SessionNumber 3 in model

        Set<Name> studentNames = new HashSet<>();
        studentNames.add(new Name("Alice"));

        TakeAttendanceCommand markPresentCommandInvalidSessionNumber = new TakeAttendanceCommand(
                new SessionNumber("5"), studentNames, AttendancePresence.PRESENT);
        assertCommandFailure(markPresentCommandInvalidSessionNumber, model, Messages.MESSAGE_SESSION_NOT_FOUND);

    }


    @Test
    public void getCommandType() {
        TakeAttendanceCommand command =
                new TakeAttendanceCommand(new SessionNumber("2"), new Name("Bob"), AttendancePresence.PRESENT);

        assertEquals(command.getCommandType(), CommandType.TAKE_ATTENDANCE);
    }

    @Test
    public void equals_sameCommandArguments_returnsTrue() {
        TakeAttendanceCommand command1 =
                new TakeAttendanceCommand(new SessionNumber("1"), new Name("Alice"), AttendancePresence.ABSENT);
        TakeAttendanceCommand command2 =
                new TakeAttendanceCommand(new SessionNumber("1"), new Name("Alice"), AttendancePresence.ABSENT);

        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        TakeAttendanceCommand command =
                new TakeAttendanceCommand(new SessionNumber("2"), new Name("Bob"), AttendancePresence.PRESENT);
        assertTrue(command.equals(command));
    }

    @Test
    public void equals_otherInstance_returnsFalse() {
        TakeAttendanceCommand command =
                new TakeAttendanceCommand(new SessionNumber("2"), new Name("Bob"), AttendancePresence.PRESENT);
        assertFalse(command.equals(1));
    }

    @Test
    public void equals_nullNames_returnsFalse() {
        TakeAttendanceCommand command1 =
                new TakeAttendanceCommand(new SessionNumber("2"), new Name("Bob"), AttendancePresence.PRESENT);
        Set<Name> names = new HashSet<>();
        TakeAttendanceCommand command2 =
                new TakeAttendanceCommand(new SessionNumber("2"), names, AttendancePresence.PRESENT);
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        TakeAttendanceCommand command1 = new TakeAttendanceCommand(
                new SessionNumber("1"), new Name("Alice"), AttendancePresence.PRESENT);
        TakeAttendanceCommand command2 =
                new TakeAttendanceCommand(new SessionNumber("1"), new Name("Bob"), AttendancePresence.PRESENT);
        TakeAttendanceCommand command3 =
                new TakeAttendanceCommand(new SessionNumber("1"), new Name("Alice"), AttendancePresence.ABSENT);
        assertFalse(command1.equals(command2));
        assertFalse(command1.equals(command3));
    }

    @Test
    public void toStringMethod() throws CommandException {
        Model model = new ModelManager();
        String name = "Bob";
        Person bob = new PersonBuilder().withName(name).build();
        SessionNumber sessionNumber = new SessionNumber("1");
        model.addPerson(bob);
        Session session = new Session(sessionNumber, bob);
        model.addSession(session);

        TakeAttendanceCommand command =
                new TakeAttendanceCommand(sessionNumber, new Name(name), AttendancePresence.PRESENT);
        command.execute(model);


        String expected = TakeAttendanceCommand.class.getCanonicalName()
                + "{takeAttendance=" + sessionNumber + ", students=[" + bob + "]}";
        assertEquals(expected, command.toString());
    }
}
