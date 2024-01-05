package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSessions.SESSION1A;
import static seedu.address.testutil.TypicalSessions.SESSION3A;
import static seedu.address.testutil.TypicalSessions.SESSION3B;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class SessionListBookTest {
    private final SessionListBook sessionListBook = new SessionListBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), sessionListBook.getSessionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sessionListBook.resetData(null));
    }


    @Test
    public void resetData_withValidReadOnlySessionListBook_replacesData() {
        SessionListBook newData = getTypicalSessionList();
        sessionListBook.resetData(newData);
        assertEquals(newData, sessionListBook);
    }
    @Test
    public void hasSession_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sessionListBook.hasSession(null));
    }

    @Test
    public void hasSession_sessionNotInSessionListBook_returnsFalse() {
        assertFalse(sessionListBook.hasSession(SESSION3B));
    }

    @Test
    public void hasSession_sessionInSessionListBook_returnsTrue() {
        sessionListBook.addSession(SESSION3A);
        assertTrue(sessionListBook.hasSession(SESSION3A));
    }

    @Test
    public void getSessionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sessionListBook.getSessionList().remove(0));
    }

    @Test
    public void equalsMethod() {
        SessionListBook sessionListBook = new SessionListBook();

        // New SessionListBook should be equal to itself
        assertTrue(sessionListBook.equals(sessionListBook));

        // SessionListBook with different data should not be equal
        SessionListBook differentSessionListBook = new SessionListBook();
        differentSessionListBook.addSession(SESSION1A);
        assertFalse(sessionListBook.equals(differentSessionListBook));

        // ReadOnlySessionList should be equal to SessionListBook with the same data
        ReadOnlySessionList readOnlySessionList = new SessionListBook();
        assertTrue(sessionListBook.equals(readOnlySessionList));
    }

    @Test
    public void toStringMethod() {
        String expected = SessionListBook.class.getCanonicalName() + "{sessions="
                + sessionListBook.getSessionList() + "}";
        assertEquals(expected, sessionListBook.toString());
    }
}
