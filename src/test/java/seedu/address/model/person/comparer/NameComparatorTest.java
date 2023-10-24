package seedu.address.model.person.comparer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class NameComparatorTest {

    private static Person p1 = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
    private static Person p2 = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
    private static Person p3 = new PersonBuilder(ALICE).withName(VALID_NAME_CANDY).build();
    private static Person p4 = new PersonBuilder(BOB).withName(VALID_NAME_DANIEL).build();

    @Test
    public void constructor_initialization_test1() {
        NameComparator nameComparator = new NameComparator(true, true, 0);
        assertTrue(nameComparator instanceof NameComparator);
        assertTrue(nameComparator.getIsActive());
        assertTrue(nameComparator.getIsReverse());
        assertEquals(nameComparator.getPriority(), 0);
    }

    @Test
    public void constructor_initialization_test2() {
        NameComparator nameComparator = new NameComparator(false, true, 9999);
        assertTrue(nameComparator instanceof NameComparator);
        assertFalse(nameComparator.getIsActive());
        assertTrue(nameComparator.getIsReverse());
        assertEquals(nameComparator.getPriority(), -1);
    }

    @Test
    public void execute_compareSuccess() {
        NameComparator nameComparator = new NameComparator(true, true, 1);
        int nameComparison1 = nameComparator.compare(p1, p3);
        int nameComparison2 = nameComparator.compare(p2, p4);
        int nameComparison3 = nameComparator.compare(p4, p1);

        assertEquals(nameComparator.compare(p1, p2), 0);
        assertEquals(nameComparator.compare(p1, p1), 0);
        assertTrue(nameComparison1 < 0);
        assertTrue(nameComparison2 < 0);
        assertTrue(nameComparison3 > 0);
    }
}


