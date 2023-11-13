package seedu.address.model.person.comparer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class EmailComparatorTest {

    private static Person p1 = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_AMY).build();
    private static Person p2 = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_AMY).build();
    private static Person p3 = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_AMY).build();
    private static Person p4 = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
    private static Person p5 = new PersonBuilder(ALICE).withName(VALID_NAME_CANDY).build();
    private static Person p6 = new PersonBuilder(BOB).withName(VALID_NAME_DANIEL).build();

    @Test
    public void constructor_initialization_test1() {
        EmailComparator emailComparator = new EmailComparator(true, true, 0);
        assertTrue(emailComparator instanceof EmailComparator);
        assertTrue(emailComparator.getIsActive());
        assertTrue(emailComparator.getIsReverse());
        assertEquals(emailComparator.getPriority(), 0);
    }

    @Test
    public void constructor_initialization_test2() {
        EmailComparator emailComparator = new EmailComparator(false, true, 1234);
        assertTrue(emailComparator instanceof EmailComparator);
        assertFalse(emailComparator.getIsActive());
        assertTrue(emailComparator.getIsReverse());
        assertEquals(emailComparator.getPriority(), -1);
    }

    @Test
    public void execute_compareSuccess() {
        EmailComparator emailComparator = new EmailComparator(true, true, 1);
        int emailComparison1 = emailComparator.compare(p1, p2);
        int emailComparison2 = emailComparator.compare(p1, p3);
        int emailComparison3 = emailComparator.compare(p4, p6);
        int emailComparison4 = emailComparator.compare(p3, p5);
        int emailComparison5 = emailComparator.compare(p1, p4);
        int emailComparison6 = emailComparator.compare(p2, p6);
        assertEquals(emailComparison1, 0);
        assertEquals(emailComparison2, 0);
        assertEquals(emailComparison3, 0);
        assertTrue(emailComparison4 > 0);
        assertTrue(emailComparison5 < 0);
        assertTrue(emailComparison6 < 0);
    }

}
