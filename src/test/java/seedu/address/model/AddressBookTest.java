package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BRITISH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.AUNTIES_COOKING;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.exceptions.DuplicateStallException;
import seedu.address.testutil.StallBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStallList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStalls_throwsDuplicateStallException() {
        // Two stalls with the same identity fields
        Stall editedAuntiesCooking1 = new StallBuilder(AUNTIES_COOKING)
                .withLocation(VALID_LOCATION_BRITISH).build();
        Stall editedAuntiesCooking2 = new StallBuilder(AUNTIES_COOKING)
                .withLocation(VALID_LOCATION_BRITISH).build();
        List<Stall> newStalls = Arrays.asList(editedAuntiesCooking1, editedAuntiesCooking2);
        AddressBookStub newData = new AddressBookStub(newStalls);

        assertThrows(DuplicateStallException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStall_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStall(null));
    }

    @Test
    public void hasStall_stallNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStall(AUNTIES_COOKING));
    }

    @Test
    public void hasStall_stallInAddressBook_returnsTrue() {
        addressBook.addStall(AUNTIES_COOKING);
        assertTrue(addressBook.hasStall(AUNTIES_COOKING));
    }

    @Test
    public void hasStall_stallWithSameIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addStall(AUNTIES_COOKING);
        Stall editedAlice = new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BRITISH)
                .build();
        assertFalse(addressBook.hasStall(editedAlice));
    }

    @Test
    public void getStallList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStallList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{stalls=" + addressBook.getStallList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose stalls list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Stall> stalls = FXCollections.observableArrayList();

        AddressBookStub(Collection<Stall> stalls) {
            this.stalls.setAll(stalls);
        }

        @Override
        public ObservableList<Stall> getStallList() {
            return stalls;
        }
    }

}
