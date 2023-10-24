package seedu.address.model.person.comparer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PhoneComparatorTest {

    private static Person p1 = new PersonBuilder(ALICE).build();
    private static Person p2 = new PersonBuilder(BOB).build();
    private static Person p3 = new PersonBuilder(ALICE).withPhone(VALID_PHONE_AMY).build();
    private static Person p4 = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY).build();

    @Test
    public void constructor_initialization_test1() {
        PhoneComparator phoneComparator = new PhoneComparator(true, true, 0);
        assertTrue(phoneComparator instanceof PhoneComparator);
        assertTrue(phoneComparator.getIsActive());
        assertTrue(phoneComparator.getIsReverse());
        assertEquals(phoneComparator.getPriority(), 0);
    }

    @Test
    public void constructor_initialization_test2() {
        PhoneComparator phoneComparator = new PhoneComparator(false, true, 9999);
        assertTrue(phoneComparator instanceof PhoneComparator);
        assertFalse(phoneComparator.getIsActive());
        assertTrue(phoneComparator.getIsReverse());
        assertEquals(phoneComparator.getPriority(), -1);
    }

    @Test
    public void execute_compareSuccess() {
        PhoneComparator phoneComparator = new PhoneComparator(true, true, 1);
        int phoneComparison1 = phoneComparator.compare(p1, p2);
        int phoneComparison2 = phoneComparator.compare(p1, p3);
        int phoneComparison3 = phoneComparator.compare(p2, p3);

        assertEquals(phoneComparator.compare(p3, p4), 0);
        assertTrue(phoneComparison1 > 0);
        assertTrue(phoneComparison2 > 0);
        assertTrue(phoneComparison3 > 0);
    }
}
