package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalSessions.EMPTY_SESSION;
import static seedu.address.testutil.TypicalSessions.SESSION1A;
import static seedu.address.testutil.TypicalSessions.SESSION3A;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionList;
import seedu.address.testutil.PersonBuilder;

public class AttendanceTest {
    @Test
    public void markPresentTest() {
        Person alice = new PersonBuilder(ALICE).build();
        Session emptySession = EMPTY_SESSION;
        Attendance.markPresent(emptySession, alice);
        assertTrue(emptySession.getStudents().contains(alice));
    }

    @Test
    public void markAbsentTest() {
        Person alice = new PersonBuilder(ALICE).build();
        Session sessionWithAlice = new Session(SESSION1A.getSessionNumber(), SESSION1A.getStudents());
        Attendance.markAbsent(sessionWithAlice, alice);

        assertFalse(sessionWithAlice.getStudents().contains(alice));
    }

    @Test
    public void getAttendanceBySessionTest() {
        assertTrue(Attendance.getAttendanceBySession(SESSION3A).equals(SESSION3A.getStudents()));
    }

    @Test
    public void getAttendanceByStudentTest() {

        SessionList sessionList = new SessionList();
        Person carl = new PersonBuilder(CARL).build();
        Session tempSession3a = new Session(SESSION3A.getSessionNumber(), SESSION3A.getStudents());
        sessionList.addSession(tempSession3a);
        Set<Session> attendanceBefore = Attendance.getAttendanceByStudent(carl, sessionList);
        // bob has not attended tempSession3a
        assertFalse(attendanceBefore.contains(tempSession3a));

        carl.attendSession(tempSession3a);

        Set<Session> attendanceAfter = Attendance.getAttendanceByStudent(carl, sessionList);
        // Check if the list contains the expected session
        assertTrue(attendanceAfter.contains(tempSession3a));
    }
}
