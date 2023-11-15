package seedu.address.model.displayable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BOB;
import static seedu.address.testutil.TypicalSellers.SALICE;
import static seedu.address.testutil.TypicalSellers.SBOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.exceptions.DisplayableNotFoundException;
import seedu.address.model.displayable.exceptions.DuplicateException;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.SellerBuilder;

public class UniqueDisplayableListTest {

    private final UniqueDisplayableList<Buyer> uniqueBuyerList = new UniqueDisplayableList<>();
    private final UniqueDisplayableList<Seller> uniqueSellerList = new UniqueDisplayableList<>();

    ///// All tests for uniqueBuyerList
    @Test
    public void contains_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.contains(null));
    }
    @Test
    public void contains_buyerNotInBuyerList_returnsFalse() {
        assertFalse(uniqueBuyerList.contains(ALICE));
    }
    @Test
    public void contains_buyerInList_returnsTrue() {
        uniqueBuyerList.add(ALICE);
        assertTrue(uniqueBuyerList.contains(ALICE));
    }
    @Test
    public void contains_buyerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBuyerList.add(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueBuyerList.contains(editedAlice));
    }
    @Test
    public void add_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.add(null));
    }
    @Test
    public void add_duplicateBuyer_throwsDuplicatePersonException() {
        uniqueBuyerList.add(ALICE);
        assertThrows(DuplicateException.class, () -> uniqueBuyerList.add(ALICE));
    }
    @Test
    public void setBuyer_nullTargetBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setDisplayable(null, ALICE));
    }
    @Test
    public void setBuyer_nullEditedBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setDisplayable(ALICE, null));
    }
    @Test
    public void setBuyer_targetBuyerNotInList_throwsPersonNotFoundException() {
        assertThrows(DisplayableNotFoundException.class, () -> uniqueBuyerList.setDisplayable(ALICE, ALICE));
    }
    @Test
    public void setBuyer_editedBuyerIsSameBuyer_success() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.setDisplayable(ALICE, ALICE);
        UniqueDisplayableList<Buyer> expectedUniqueBuyerList = new UniqueDisplayableList<>();
        expectedUniqueBuyerList.add(ALICE);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }
    @Test
    public void setBuyer_editedBuyerHasSameIdentity_success() {
        uniqueBuyerList.add(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueBuyerList.setDisplayable(ALICE, editedAlice);
        UniqueDisplayableList<Buyer> expectedUniqueBuyerList = new UniqueDisplayableList<>();
        expectedUniqueBuyerList.add(editedAlice);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }
    @Test
    public void setBuyer_editedBuyerHasDifferentIdentity_success() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.setDisplayable(ALICE, BOB);
        UniqueDisplayableList<Buyer> expectedUniqueBuyerList = new UniqueDisplayableList<>();
        expectedUniqueBuyerList.add(BOB);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }
    @Test
    public void setBuyer_editedBuyerHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.add(BOB);
        assertThrows(DuplicateException.class, () -> uniqueBuyerList.setDisplayable(ALICE, BOB));
    }
    @Test
    public void remove_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.remove(null));
    }
    @Test
    public void remove_buyerDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(DisplayableNotFoundException.class, () -> uniqueBuyerList.remove(ALICE));
    }
    @Test
    public void remove_existingBuyer_removesBuyer() {
        uniqueBuyerList.add(ALICE);
        uniqueBuyerList.remove(ALICE);
        UniqueDisplayableList<Buyer> expectedUniqueBuyerList = new UniqueDisplayableList<>();
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }
    @Test
    public void setBuyers_nullUniqueBuyerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList
                .setDisplayables((UniqueDisplayableList<Buyer>) null));
    }
    @Test
    public void setBuyers_uniqueBuyerList_replacesOwnListWithProvidedUniqueBuyerList() {
        uniqueBuyerList.add(ALICE);
        UniqueDisplayableList<Buyer> expectedUniqueBuyerList = new UniqueDisplayableList<>();
        expectedUniqueBuyerList.add(BOB);
        uniqueBuyerList.setDisplayables(expectedUniqueBuyerList);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }
    @Test
    public void setBuyers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBuyerList.setDisplayables((List<Buyer>) null));
    }
    @Test
    public void setBuyers_list_replacesOwnListWithProvidedList() {
        uniqueBuyerList.add(ALICE);
        List<Buyer> personList = Collections.singletonList(BOB);
        uniqueBuyerList.setDisplayables(personList);
        UniqueDisplayableList<Buyer> expectedUniqueBuyerList = new UniqueDisplayableList<>();
        expectedUniqueBuyerList.add(BOB);
        assertEquals(expectedUniqueBuyerList, uniqueBuyerList);
    }
    @Test
    public void setBuyers_listWithDuplicateBuyers_throwsDuplicatePersonException() {
        List<Buyer> listWithDuplicateBuyers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateException.class, () -> uniqueBuyerList.setDisplayables(listWithDuplicateBuyers));
    }
    @Test
    public void asUnmodifiableObservableList_modifyBuyerList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueBuyerList.asUnmodifiableObservableList().remove(0));
    }
    @Test
    public void toStringMethodBuyerList() {
        assertEquals(uniqueBuyerList.asUnmodifiableObservableList().toString(), uniqueBuyerList.toString());
    }

    ///// All tests for uniqueSellerList
    @Test
    public void contains_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.contains(null));
    }
    @Test
    public void contains_sellerNotInSellerList_returnsFalse() {
        assertFalse(uniqueSellerList.contains(SALICE));
    }
    @Test
    public void contains_sellerInList_returnsTrue() {
        uniqueSellerList.add(SALICE);
        assertTrue(uniqueSellerList.contains(SALICE));
    }
    @Test
    public void contains_sellerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueSellerList.add(SALICE);
        Seller editedSalice = new SellerBuilder(SALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueSellerList.contains(editedSalice));
    }
    @Test
    public void add_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.add(null));
    }
    @Test
    public void add_duplicateSeller_throwsDuplicatePersonException() {
        uniqueSellerList.add(SALICE);
        assertThrows(DuplicateException.class, () -> uniqueSellerList.add(SALICE));
    }
    @Test
    public void setSeller_nullEditedSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setDisplayable(SALICE, null));
    }
    @Test
    public void setSeller_nullTargetSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setDisplayable(null, SALICE));
    }
    @Test
    public void setSeller_targetSellerNotInList_throwsPersonNotFoundException() {
        assertThrows(DisplayableNotFoundException.class, () -> uniqueSellerList.setDisplayable(SALICE, SALICE));
    }
    @Test
    public void setSeller_editedSellerIsSameSeller_success() {
        uniqueSellerList.add(SALICE);
        uniqueSellerList.setDisplayable(SALICE, SALICE);
        UniqueDisplayableList<Seller> expectedUniqueSellerList = new UniqueDisplayableList<>();
        expectedUniqueSellerList.add(SALICE);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }
    @Test
    public void setSeller_editedSellerHasSameIdentity_success() {
        uniqueSellerList.add(SALICE);
        Seller editedSalice = new SellerBuilder(SALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueSellerList.setDisplayable(SALICE, editedSalice);
        UniqueDisplayableList<Seller> expectedUniqueSellerList = new UniqueDisplayableList<>();
        expectedUniqueSellerList.add(editedSalice);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }
    @Test
    public void setSeller_editedSellerHasDifferentIdentity_success() {
        uniqueSellerList.add(SALICE);
        uniqueSellerList.setDisplayable(SALICE, SBOB);
        UniqueDisplayableList<Seller> expectedUniqueSellerList = new UniqueDisplayableList<>();
        expectedUniqueSellerList.add(SBOB);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }
    @Test
    public void setSeller_editedSellerHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueSellerList.add(SALICE);
        uniqueSellerList.add(SBOB);
        assertThrows(DuplicateException.class, () -> uniqueSellerList.setDisplayable(SALICE, SBOB));
    }
    @Test
    public void remove_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.remove(null));
    }
    @Test
    public void remove_sellerDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(DisplayableNotFoundException.class, () -> uniqueSellerList.remove(SALICE));
    }
    @Test
    public void remove_existingSeller_removesSeller() {
        uniqueSellerList.add(SALICE);
        uniqueSellerList.remove(SALICE);
        UniqueDisplayableList<Seller> expectedUniqueSellerList = new UniqueDisplayableList<>();
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }
    @Test
    public void setSellers_nullUniqueSellerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList
                .setDisplayables((UniqueDisplayableList<Seller>) null));
    }
    @Test
    public void setSellers_uniqueSellerList_replacesOwnListWithProvidedUniqueSellerList() {
        uniqueSellerList.add(SALICE);
        UniqueDisplayableList<Seller> expectedUniqueSellerList = new UniqueDisplayableList<>();
        expectedUniqueSellerList.add(SBOB);
        uniqueSellerList.setDisplayables(expectedUniqueSellerList);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }
    @Test
    public void setSellers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSellerList.setDisplayables((List<Seller>) null));
    }
    @Test
    public void setSellers_list_replacesOwnListWithProvidedList() {
        uniqueSellerList.add(SALICE);
        List<Seller> personList = Collections.singletonList(SBOB);
        uniqueSellerList.setDisplayables(personList);
        UniqueDisplayableList<Seller> expectedUniqueSellerList = new UniqueDisplayableList<>();
        expectedUniqueSellerList.add(SBOB);
        assertEquals(expectedUniqueSellerList, uniqueSellerList);
    }
    @Test
    public void setSellers_listWithDuplicateSellers_throwsDuplicatePersonException() {
        List<Seller> listWithDuplicateSellers = Arrays.asList(SALICE, SALICE);
        assertThrows(DuplicateException.class, () -> uniqueSellerList.setDisplayables(listWithDuplicateSellers));
    }
    @Test
    public void asUnmodifiableObservableList_modifySellerList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueSellerList.asUnmodifiableObservableList().remove(0));
    }
    @Test
    public void toStringMethodSellerList() {
        assertEquals(uniqueSellerList.asUnmodifiableObservableList().toString(), uniqueSellerList.toString());
    }

}
