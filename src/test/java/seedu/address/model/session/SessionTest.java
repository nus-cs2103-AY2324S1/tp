package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSessions.SESSION1A;
import static seedu.address.testutil.TypicalSessions.SESSION1B;
import static seedu.address.testutil.TypicalSessions.SESSION2;
import static seedu.address.testutil.TypicalSessions.SESSION3A;
import static seedu.address.testutil.TypicalSessions.SESSION3B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPersons;

public class SessionTest {
    @Test
    public void isSameSession() {
        assertTrue(SESSION1A.isSameSession(SESSION1B));

        assertFalse(SESSION1A.isSameSession(null));

        assertFalse(SESSION2.isSameSession(SESSION3A));

        assertTrue(SESSION3B.addStudent(TypicalPersons.BOB).isSameSession(SESSION3A));
    }

    @Test
    public void equalsMethod() {
        // same values -> returns true
        Session otherSession = new Session(SESSION1A.getSessionNumber(), SESSION1A.getStudents());
        assertTrue(SESSION1A.equals(otherSession));

        // same object -> returns true
        assertTrue(SESSION2.equals(SESSION2));

        // null -> returns false
        assertFalse(SESSION3A.equals(null));

        // different type -> returns false
        assertFalse(SESSION3B.equals("test"));

        // different task -> returns false
        assertFalse(SESSION2.equals(SESSION3A));

        // different students -> returns false
        Session editedSession = SESSION1B.addStudent(TypicalPersons.CARL);
        assertFalse(SESSION1B.equals(editedSession));
    }

    @Test
    public void toStringMethod() {
        String expected1 = String.format("%s - %s",
                SESSION2.getSessionNumber(), SESSION2.getStudents());
        assertEquals(expected1, SESSION2.toString());

        String expected2 = String.format("%s - %s", SESSION3A.getSessionNumber(), SESSION3A.getStudents());
        assertEquals(expected2, SESSION3A.toString());
    }
}
