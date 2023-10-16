package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.IdentityCode;

public class IdentityCodeManagerTest {

    @BeforeEach
    public void setUp() {
        // Reset the ID before each test to maintain a consistent state.
        IdentityCodeManager.updateMaxID(Collections.emptyList());
    }

    @Test
    public void getNextIdentityCode_emptyAddressBook_returnsOne() {
        assertEquals(1, IdentityCodeManager.getNextIdentityCode());
    }

    @Test
    public void getNextIdentityCode_afterSeveralGets_returnsIncrementedValue() {
        IdentityCodeManager.getNextIdentityCode(); // ID 1
        IdentityCodeManager.getNextIdentityCode(); // ID 2
        assertEquals(3, IdentityCodeManager.getNextIdentityCode()); // ID 3
    }

    @Test
    public void updateMaxID_emptyList_resetsToZero() {
        // Given a non-empty ID state
        IdentityCodeManager.getNextIdentityCode(); // ID 1
        IdentityCodeManager.getNextIdentityCode(); // ID 2

        // Reset ID state to zero
        IdentityCodeManager.updateMaxID(Collections.emptyList());

        assertEquals(1, IdentityCodeManager.getNextIdentityCode()); // ID should reset to 1
    }

    @Test
    public void updateMaxID_listWithPersons_updatesToMaxIdentityCode() {
        Person alice = new Person(new IdentityCode("3"),);
        Person bob = new Person(new IdentityCode("5"),);
        Person charlie = new Person(new IdentityCode("1"),);

        IdentityCodeManager.updateMaxID(Arrays.asList(alice, bob, charlie));

        assertEquals(6, IdentityCodeManager.getNextIdentityCode());
    }
}