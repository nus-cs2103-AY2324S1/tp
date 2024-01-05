package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.session.exceptions.DuplicateSessionException;
import seedu.address.model.session.exceptions.SessionNotFoundException;


/**
 * A list of sessions that can be used to manage and manipulate session data.
 */
public class SessionList implements Iterable<Session> {
    private ObservableList<Session> internalList = FXCollections.observableArrayList();
    private final ObservableList<Session> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setSessionList(List<Session> sessionList) {
        requireAllNonNull(sessionList);
        internalList.setAll(sessionList);
    }

    /**
     * Checks if the list contains a specific session.
     *
     * @param toCheck The session to check for existence.
     * @return True if the session exists in the list, false otherwise.
     */
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Adds a session to the list.
     *
     * @param toAdd The session to add.
     */
    public void addSession(Session toAdd) {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateSessionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Sets the information of a session in the list.
     *
     * @param target The session to be modified.
     * @param editedSession The modified session information.
     * @throws SessionNotFoundException If the target session is not found in the list.
     * @throws DuplicateSessionException If the edited session information duplicates an existing session.
     */
    public void setSession(Session target, Session editedSession) {
        requireAllNonNull(target, editedSession);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new SessionNotFoundException();
        }

        if (!target.isSameSession(editedSession) && contains(editedSession)) {
            throw new DuplicateSessionException();
        }

        internalList.set(index, editedSession);
    }

    /**
     * Finds a session in the list based on its session number.
     *
     * @param sessionNumber The session number to search for.
     * @return The session with the specified session number, or null if not found.
     */
    public Session findSessionBySessionNumber(SessionNumber sessionNumber) {
        requireNonNull(sessionNumber);

        for (Session session : internalList) {
            if (session.getSessionNumber().equals(sessionNumber)) {
                return session;
            }
        }
        throw new SessionNotFoundException();
    }

    /**
     * Finds and returns a set of sessions attended by a specific student.
     *
     * @param student The student for whom to find attended sessions.
     * @return A set of sessions that the specified student has attended.
     */
    public Set<Session> findSessionsByStudent(Person student) {
        Set<Session> sessionsAttendedByStudent = new HashSet<>();
        for (Session session : internalList) {
            if (session.getStudents().contains(student)) {
                sessionsAttendedByStudent.add(session);
            }
        }
        return sessionsAttendedByStudent;
    }


    /**
     * Removes a session from the list.
     *
     * @param toRemove The session to remove.
     * @throws SessionNotFoundException If the session to remove is not found in the list.
     */
    public void remove(Session toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SessionNotFoundException();
        }
    }

    /**
     * Removes the {@code student} from all sessions in the sessions list.
     */
    public void removePerson(Person student) {
        requireNonNull(student);
        List<Session> newSessions = new ArrayList<>();

        for (Session session : internalList) {
            Session updatedSession = session.removeStudent(student);
            if (!updatedSession.isEmpty()) {
                newSessions.add(updatedSession);
            }
        }
        internalList.setAll(newSessions);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     */
    public void setStudent(Person target, Person editedStudent) {
        requireAllNonNull(target, editedStudent);
        List<Session> newSessions = new ArrayList<>();

        for (Session session : internalList) {
            if (session.contains(target)) {
                session.replaceStudent(target, editedStudent);
            }
            newSessions.add(session);
        }
        internalList.setAll(newSessions);
    }

    /**
     * Returns an unmodifiable view of the list of sessions.
     *
     * @return An unmodifiable list of sessions.
     */
    public ObservableList<Session> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Provides an iterator to iterate through the list of sessions.
     *
     * @return An iterator for the list of sessions.
     */
    @Override
    public Iterator<Session> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns a string representation of the list of sessions.
     *
     * @return A string representation of the sessions in the list.
     */
    @Override
    public String toString() {
        return internalList.toString();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Same object, equal
        }

        if (!(other instanceof SessionList)) {
            return false; // Not the same class, not equal
        }

        SessionList otherList = (SessionList) other;

        // Check if the lists have the same sessions in the same order
        return internalList.equals(otherList.internalList);
    }
}
