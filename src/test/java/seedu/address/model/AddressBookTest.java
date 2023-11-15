package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalSellers.SALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.exceptions.DuplicateException;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.SellerBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getSellerList());
        assertEquals(Collections.emptyList(), addressBook.getBuyerList());
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
    public void resetData_withDuplicateSellers_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Seller editedAlice = new SellerBuilder(SALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Seller> newSellers = Arrays.asList(SALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newSellers);

        assertThrows(DuplicateException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasSeller(null));
    }
    @Test
    public void hasBuyer_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasBuyer(null));
    }
    @Test
    public void hasSellerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasSeller(SALICE));
    }
    @Test
    public void hasBuyerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasBuyer(ALICE));
    }

    @Test
    public void hasBuyer_buyerInAddressBook_returnsTrue() {
        addressBook.addBuyer(ALICE);
        assertTrue(addressBook.hasBuyer(ALICE));
    }
    @Test
    public void hasSeller_sellerInAddressBook_returnsTrue() {
        addressBook.addSeller(SALICE);
        assertTrue(addressBook.hasSeller(SALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addBuyer(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasBuyer(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getBuyerList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{buyers=" + addressBook.getBuyerList()
                + ", sellers=" + addressBook.getSellerList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Seller> sellers = FXCollections.observableArrayList();
        private final ObservableList<Buyer> buyers = FXCollections.observableArrayList();

        AddressBookStub(Collection<Seller> sellers) {
            this.sellers.setAll(sellers);
        }

        @Override
        public ObservableList<Seller> getSellerList() {
            return sellers;
        }
        @Override
        public ObservableList<Buyer> getBuyerList() {
            return buyers;
        }
    }

}
