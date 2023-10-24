package seedu.address.model.person.comparer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CANDY;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddressComparatorTest {

    private static Person p1 = new PersonBuilder(ALICE).build();
    private static Person p2 = new PersonBuilder(BOB).build();
    private static Person p3 = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_CANDY).build();
    private static Person p4 = new PersonBuilder(BOB).withAddress(VALID_ADDRESS_CANDY).build();

    @Test
    public void constructor_initialization_test1() {
        AddressComparator addressComparator = new AddressComparator(true, true, 0);
        assertTrue(addressComparator instanceof AddressComparator);
        assertTrue(addressComparator.getIsActive());
        assertTrue(addressComparator.getIsReverse());
        assertEquals(addressComparator.getPriority(), 0);
    }

    @Test
    public void constructor_initialization_test2() {
        AddressComparator addressComparator = new AddressComparator(false, true, 9999);
        assertTrue(addressComparator instanceof AddressComparator);
        assertFalse(addressComparator.getIsActive());
        assertTrue(addressComparator.getIsReverse());
        assertEquals(addressComparator.getPriority(), -1);
    }

    @Test
    public void execute_compareSuccess() {
        AddressComparator addressComparator = new AddressComparator(true, true, 1);
        int addressComparison1 = addressComparator.compare(p1, p3);
        int addressComparison2 = addressComparator.compare(p2, p4);

        assertEquals(addressComparator.compare(p3, p4), 0);
        assertEquals(addressComparator.compare(p1, p1), 0);
        assertTrue(addressComparison1 < 0);
        assertTrue(addressComparison2 < 0);
    }
}
