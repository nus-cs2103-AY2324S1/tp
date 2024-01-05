package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionList;
import seedu.address.model.session.SessionNumber;


/**
 * Represents a collection of sessions. This class is responsible for managing and manipulating
 * session data.
 */
public class SessionListBook implements ReadOnlySessionList {

    private final SessionList sessionList;

    /**
     * Initializes a new instance of SessionListBook.
     */
    public SessionListBook() {
        sessionList = new SessionList();
    }

    /**
     * Initializes a new instance of SessionListBook by copying data from an existing ReadOnlySessionList.
     *
     * @param toBeCopied The ReadOnlySessionList to copy data from.
     */
    public SessionListBook(ReadOnlySessionList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Sets the session list with the provided list of sessions.
     *
     * @param sessions The list of sessions to set.
     */
    public void setSessionList(List<Session> sessions) {
        this.sessionList.setSessionList(sessions);
    }

    /**
     * Resets the data in the SessionListBook by replacing it with the data from a ReadOnlySessionList.
     *
     * @param newData The ReadOnlySessionList to copy data from.
     */
    public void resetData(ReadOnlySessionList newData) {
        requireNonNull(newData);
        setSessionList(newData.getSessionList());
    }

    /**
     * Retrieves a session with the specified session number.
     *
     * @param sessionNumber The session number to search for.
     * @return The session with the specified session number, or null if not found.
     */
    public Session getSession(SessionNumber sessionNumber) {
        return sessionList.findSessionBySessionNumber(sessionNumber);
    }


    /**
     * Checks if the SessionListBook contains the provided session.
     *
     * @param session The session to check for existence.
     * @return True if the session exists in the list, false otherwise.
     */
    public boolean hasSession(Session session) {
        requireNonNull(session);
        return sessionList.contains(session);
    }

    /**
     * Adds a session to the SessionListBook.
     *
     * @param session The session to add.
     */
    public void addSession(Session session) {
        sessionList.addSession(session);
    }

    /**
     * Sets a session with the specified session number to a new session.
     *
     * @param targetSession The session to be modified.
     * @param editedSession The modified session information.
     */
    public void setSession(Session targetSession, Session editedSession) {
        requireNonNull(editedSession);
        sessionList.setSession(targetSession, editedSession);
    }

    /**
     * Removes a session from the SessionListBook.
     *
     * @param session The session to remove.
     */
    public void removeSession(Session session) {
        sessionList.remove(session);
    }

    /**
     * Removes {@code student} from all sessions in the {@code SessionListBook}.
     */
    public void removeStudent(Person student) {
        sessionList.removePerson(student);
    }

    /**
     * Replaces the {@code target} student with the new {@code editedStudent}.
     */
    public void setStudent(Person target, Person editedStudent) {
        sessionList.setStudent(target, editedStudent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sessions", sessionList)
                .toString();
    }

    /**
     * Returns an observable list of sessions from the SessionListBook.
     *
     * @return An unmodifiable observable list of sessions.
     */
    public ObservableList<Session> getSessionList() {
        return sessionList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SessionListBook)) {
            return false;
        }

        SessionListBook otherSessionList = (SessionListBook) other;
        return sessionList.equals(otherSessionList.sessionList);
    }

    @Override
    public int hashCode() {
        return sessionList.hashCode();
    }
}
