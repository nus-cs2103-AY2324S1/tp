package seedu.address.model.displayable.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalSellers.SALICE;
import static seedu.address.testutil.TypicalSellers.SBOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SellerBuilder;

public class SellerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Seller person = new SellerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameSeller() {
        // same object -> returns true
        assertTrue(SALICE.isSamePerson(SALICE));

        // null -> returns false
        assertFalse(SALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Seller editedAlice = new SellerBuilder(SALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(SALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new SellerBuilder(SALICE).withName(VALID_NAME_BOB).build();
        assertFalse(SALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Seller editedBob = new SellerBuilder(SBOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(SBOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new SellerBuilder(SBOB).withName(nameWithTrailingSpaces).build();
        assertFalse(SBOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Seller aliceCopy = new SellerBuilder(SALICE).build();
        assertTrue(SALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(SALICE.equals(SALICE));

        // null -> returns false
        assertFalse(SALICE.equals(null));

        // different type -> returns false
        assertFalse(SALICE.equals(5));

        // different displayable -> returns false
        assertFalse(SALICE.equals(SBOB));

        // different name -> returns false
        Seller editedAlice = new SellerBuilder(SALICE).withName(VALID_NAME_BOB).build();
        assertFalse(SALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new SellerBuilder(SALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(SALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new SellerBuilder(SALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(SALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new SellerBuilder(SALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(SALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new SellerBuilder(SALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(SALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Seller.class.getCanonicalName() + "{name=" + SALICE.getName() + ", phone=" + SALICE.getPhone()
                + ", email=" + SALICE.getEmail() + ", address=" + SALICE.getAddress() + ", tags=" + SALICE.getTags()
                + ", priority=" + ALICE.getPriority()
                + ", selling address=" + SALICE.getSellingAddress()
                + ", house info=" + SALICE.getHouseInfo() + "}";
        assertEquals(expected, SALICE.toString());
    }
}

