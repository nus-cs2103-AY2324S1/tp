package seedu.address.model.attendance;

import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionList;


/**
 * Utility class for managing attendance records.
 */
public abstract class Attendance {

    /**
     * Marks a student as present in a specific session and updates their attendance record.
     *
     * @param session The session in which the student is marked present.
     * @param student The student to be marked present.
     */
    public static void markPresent(Session session, Person student) {
        student.attendSession(session);
        session.addStudent(student);
    }

    /**
     * Marks a student as absent in a specific session and updates their attendance record.
     *
     * @param session The session in which the student is marked absent.
     * @param student The student to be marked absent.
     */
    public static void markAbsent(Session session, Person student) {
        student.missSession(session);
        session.removeStudent(student);
    }

    /**
     * Retrieves the list of students attending a specific session.
     *
     * @param session The session for which attendance is requested.
     * @return A set of students attending the session.
     */
    public static StudentSet getAttendanceBySession(Session session) {
        return session.getStudents();
    }

    /**
     * Retrieves the list of sessions attended by a specific student.
     *
     * @param student The student for which attendance records are requested.
     * @return A string representation of the sessions attended by the student.
     */
    public static Set<Session> getAttendanceByStudent(Person student, SessionList sessionList) {
        return sessionList.findSessionsByStudent(student);
    }
}
