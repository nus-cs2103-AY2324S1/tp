//@@author ruiyangzh-unused
//This was meant to test the house class.
package seedu.address.model.displayable.house;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Price;


public class HouseTest {
    private final House dummyHouse =
            new House(new Name("Residential College 4"),
                    new Price("1 million dollars"),
                    new Address("NUS"),
                    new HouseInfo("Suite room"),
                    new HashSet<>());
    private final House dummyHouseDifferentObject =
            new House(new Name("Residential College 4"),
                    new Price("1 million dollars"),
                    new Address("NUS"),
                    new HouseInfo("Suite room"),
                    new HashSet<>());
    private final House differentHouse =
            new House(new Name("Residential College 4"),
            new Price("1 million dollars"),
            new Address("Kent Ridge"),
            new HouseInfo("Suite room"),
            new HashSet<>());
    private final House dummyHouseDifferentName =
            new House(new Name("RC4"),
                    new Price("1 million dollars"),
                    new Address("NUS"),
                    new HouseInfo("Suite room"),
                    new HashSet<>());

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> dummyHouse.getTags().remove(0));
    }

    @Test
    public void isSameHouse_and_equals() {
        assertTrue(dummyHouse.isSameHouse(dummyHouse));
        assertFalse(dummyHouse.isSameHouse(null));

        assertTrue(dummyHouse.isSameHouse(dummyHouseDifferentObject));
        assertTrue(dummyHouse.equals(dummyHouseDifferentObject));

        assertFalse(dummyHouse.isSameHouse(differentHouse));
        assertFalse(dummyHouse.equals(differentHouse));

        assertTrue(dummyHouse.isSameHouse(dummyHouseDifferentName));
        assertFalse(dummyHouse.equals(dummyHouseDifferentName));
    }

    @Test
    public void toStringMethod() {
        String expected = House.class.getCanonicalName()
                + "{name=" + dummyHouse.getName()
                + ", price=" + dummyHouse.getPrice()
                + ", address=" + dummyHouse.getAddress()
                + ", info=" + dummyHouse.getHouseInfo()
                + ", tags=" + dummyHouse.getTags()
                + "}";
        assertEquals(expected, dummyHouse.toString());
    }
}
