package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;

/**
 * Represents a class for managing a session, which can hold a list of students and session-specific details.
 */
public class Session {
    public static final SessionRemark DEFAULT_SESSION_REMARK = new SessionRemark("NA");

    private final SessionNumber sessionNumber;
    private StudentSet students;
    private SessionRemark sessionRemark;

    /**
     * Creates a session with a session number and an initial set of students.
     *
     * @param sessionNumber The unique identifier for this session.
     * @param presentStudents The set of students present in this session.
     * @param sessionRemark The remarks for this session.
     */
    public Session(SessionNumber sessionNumber, StudentSet presentStudents, SessionRemark sessionRemark) {
        requireAllNonNull(sessionNumber, presentStudents, sessionRemark);
        this.sessionNumber = sessionNumber;
        this.students = presentStudents;
        this.sessionRemark = sessionRemark;
    }

    /**
     * Creates a session with a session number and an initial set of students.
     *
     * @param sessionNumber The unique identifier for this session.
     * @param presentStudents The set of students present in this session.
     */
    public Session(SessionNumber sessionNumber, StudentSet presentStudents) {
        requireAllNonNull(sessionNumber, presentStudents);
        this.sessionNumber = sessionNumber;
        this.students = presentStudents;
        this.sessionRemark = DEFAULT_SESSION_REMARK;
    }

    /**
     * Creates a session with a session number and a single student. Useful for adding students individually.
     *
     * @param sessionNumber The unique identifier for this session.
     * @param student The student to add to this session.
     */
    public Session(SessionNumber sessionNumber, Person student) {
        requireAllNonNull(sessionNumber, student);
        this.sessionNumber = sessionNumber;
        this.students = new StudentSet(student);
        this.sessionRemark = DEFAULT_SESSION_REMARK;
    }

    /**
     * Creates a session with just a session number. Useful for creating empty sessions.
     *
     * @param sessionNumber The unique identifier for this session.
     */
    public Session(SessionNumber sessionNumber) {
        requireNonNull(sessionNumber);
        this.sessionNumber = sessionNumber;
        this.sessionRemark = DEFAULT_SESSION_REMARK;
    }


    /**
     * Updates a remark for the session.
     *
     * @param sessionRemark The remark to update.
     */
    public void updateRemark(SessionRemark sessionRemark) {
        requireNonNull(sessionRemark);
        this.sessionRemark = sessionRemark;
    }


    /**
     * Adds a person to the StudentSet in a Session.
     *
     * @param student The student to be added into the StudentSet.
     */
    public void addStudent(Person student) {
        this.students.add(student);
    }

    /**
     * Removes a person from the StudentSet in the session.
     *
     * @param student The student to be removed from the StudentSet.
     * @return Returns itself after removing student.
     */
    public Session removeStudent(Person student) {
        this.students.remove(student);
        return this;
    }

    /**
     * Replaces a student in the session with another new student.
     *
     * @param target The target student to remove.
     * @param newStudent The new student to add.
     */
    public void replaceStudent(Person target, Person newStudent) {
        this.students.remove(target);
        this.students.add(newStudent);
    }

    /**
     * Check if StudentSet contains the {@code student}.
     */
    public boolean contains(Person student) {
        return this.students.contains(student);
    }

    /**
     * Checks if StudentSet has no students.
     *
     * @return Returns true if there is no students in the StudentSet and false if there are at least 1 student.
     */
    public boolean isEmpty() {
        return this.students.size() < 1;
    }

    /**
     * Checks if this session is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;

        return sessionNumber.equals(otherSession.sessionNumber)
                && students.equals(otherSession.students) && sessionRemark.equals(otherSession.sessionRemark);
    }


    /**
     * Checks if this session is the same as another session.
     *
     * @param other The session to compare with.
     * @return True if the sessions are the same, false otherwise.
     */
    public boolean isSameSession(Session other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getSessionNumber().equals(sessionNumber);
    }

    /**
     * Retrieves the set of students in this session.
     *
     * @return The set of students.
     */
    public StudentSet getStudents() {
        if (students == null) {
            students = new StudentSet();
        }
        return students.getStudents();
    }

    /**
     * Retrieves the session number, a unique identifier for this session.
     *
     * @return The session number.
     */
    public SessionNumber getSessionNumber() {
        return sessionNumber;
    }

    /**
     * Retrieves the session remark, a remark for this session.
     *
     * @return The session remark.
     */
    public SessionRemark getSessionRemark() {
        return sessionRemark;
    }

    /**
     * Generates a human-readable representation of the session, including the session number and the set of students.
     *
     * @return A string representation of the session.
     */
    public String getSessionInfo() {
        String studentNames = "";
        if (students != null) {
            studentNames = students.toStudentNames();
        }
        return String.format("%s - %s - %s", sessionNumber, studentNames, sessionRemark);
    }

    @Override
    public String toString() {
        return this.getSessionInfo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionNumber, students, sessionRemark);
    }
}
