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

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.TypicalPersons;

public class SessionTest {
    @Test
    public void isSameSession() {
        assertTrue(SESSION1A.isSameSession(SESSION1B));

        assertFalse(SESSION1A.isSameSession(null));

        assertFalse(SESSION2.isSameSession(SESSION3A));

        Person bob = new PersonBuilder(TypicalPersons.BOB).build();
        SESSION3B.addStudent(bob);
        assertTrue(SESSION3B.isSameSession(SESSION3A));
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
        Session tempSession = new Session(SESSION1B.getSessionNumber(), SESSION1B.getStudents());
        Person carl = new PersonBuilder(TypicalPersons.CARL).build();
        tempSession.addStudent(carl);
        assertFalse(SESSION1B.equals(tempSession));
    }

    @Test
    public void createSessionWithoutStudents() {
        Session sessionWithoutStudents = new SessionBuilder().withSessionNumber("99").build();
        assertTrue("99 -  - NA".equals(sessionWithoutStudents.toString()));
    }


    @Test
    public void toStringMethod() {
        String expected1 = "15 - Bob Choo - Some snark remarks";
        Session session2 = new SessionBuilder()
                .withSessionNumber("15").withStudents(TypicalPersons.BOB)
                .withSessionRemark("Some snark remarks").build();
        assertEquals(expected1, session2.toString());

        Session tempSession3A = new SessionBuilder()
                .withSessionNumber("73").withStudents(TypicalPersons.ALICE, TypicalPersons.BOB)
                .withSessionRemark("They are smart students").build();
        String expected2 = "73 - Alice Pauline, Bob Choo - They are smart students";
        assertEquals(expected2, tempSession3A.toString());
    }
}
