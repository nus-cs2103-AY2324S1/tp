package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSessions.SESSION1A;
import static seedu.address.testutil.TypicalSessions.SESSION1B;
import static seedu.address.testutil.TypicalSessions.SESSION2;
import static seedu.address.testutil.TypicalSessions.SESSION3A;
import static seedu.address.testutil.TypicalSessions.SESSION3B;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.exceptions.DuplicateSessionException;
import seedu.address.model.session.exceptions.SessionNotFoundException;



public class SessionListTest {
    private final SessionList sessionList = new SessionList();
    @Test
    public void contains_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sessionList.contains(null));
    }

    @Test
    public void contains_sessionNotInList_returnsFalse() {
        assertFalse(sessionList.contains(SESSION2));
    }

    @Test
    public void contains_sessionInList_returnsTrue() {
        sessionList.addSession(SESSION3A);
        assertTrue(sessionList.contains(SESSION3A));

        sessionList.addSession(SESSION1B);
        assertTrue(sessionList.contains(SESSION1B));
    }

    @Test
    public void add_duplicateSession_throwsDuplicateSessionException() {
        sessionList.addSession(SESSION3B);
        assertThrows(DuplicateSessionException.class, () -> sessionList.addSession(SESSION3B));
    }

    @Test
    public void set_setSession_throwsSessionNotFoundException() {
        assertThrows(SessionNotFoundException.class, () -> sessionList.setSession(SESSION1A, SESSION2));
    }

    @Test
    public void set_setSession_throwsDuplicateSessionException() {
        sessionList.addSession(SESSION1A);
        sessionList.addSession(SESSION2);
        assertThrows(DuplicateSessionException.class, () -> sessionList.setSession(SESSION1A, SESSION2));
    }

    @Test
    public void set_setSession_successful() {
        sessionList.addSession(SESSION3A);
        sessionList.setSession(SESSION3A, SESSION2);

        assertTrue(sessionList.contains(SESSION2));
        assertFalse(sessionList.contains(SESSION3A));
    }

    @Test
    public void remove_addedSession_successful() {
        sessionList.addSession(SESSION1A);
        assertTrue(sessionList.contains(SESSION1A));
        sessionList.remove(SESSION1A);
        assertFalse(sessionList.contains(SESSION1A));
    }

    @Test
    public void remove_throwsSessionNotFoundException() {
        sessionList.addSession(SESSION1A);
        assertThrows(SessionNotFoundException.class, () -> sessionList.remove(SESSION2));
    }

    @Test
    public void find_sessionNotInSessionList_throwsSessionNotFoundException() {
        sessionList.addSession(SESSION1B);
        assertThrows(SessionNotFoundException.class, () -> sessionList.findSessionBySessionNumber(
                new SessionNumber("5")));
    }

    @Test
    public void find_validSession_returnsTrue() {
        sessionList.addSession(SESSION3A);
        assertEquals(SESSION3A, sessionList.findSessionBySessionNumber(new SessionNumber("3")));
    }

    @Test
    public void setSessionList_setNewSessionList() {
        List<Session> newSessionList = new ArrayList<>();
        newSessionList.add(SESSION1A);
        sessionList.setSessionList(newSessionList);
        assertTrue(sessionList.contains(SESSION1A));
    }

    @Test
    public void toStringMethod() {
        assertEquals(sessionList.asUnmodifiableObservableList().toString(), sessionList.toString());
    }
}
