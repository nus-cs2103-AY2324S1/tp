package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BIG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CustomerBuilder;

public class CustomerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Customer customer = new CustomerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> customer.getTags().remove(0));
    }

    @Test
    public void isSameCustomer() {
        // same object -> returns true
        assertTrue(ALICE.isSameCustomer(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCustomer(null));
    }

    @Test
    public void isSameCustomer_sameName() {
        // same name, all other attributes different -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withBudget(VALID_BUDGET_BOB).withTags(VALID_TAG_BIG).build();
        assertFalse(ALICE.isSameCustomer(editedAlice));

        // same name, different email, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same name, different phone, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSameCustomer(editedAlice));

        // same name, different budget, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withBudget(VALID_BUDGET_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same name, different characteristic, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_BIG).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Customer editedBob = new CustomerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameCustomer(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameCustomer(editedBob));
    }

    @Test
    public void isSameCustomer_sameEmail() {
        // same email, all other attributes different -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withName(VALID_NAME_BOB)
                .withBudget(VALID_BUDGET_BOB).withTags(VALID_TAG_BIG).build();
        assertFalse(ALICE.isSameCustomer(editedAlice));

        // same email, different name, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same email, different phone, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSameCustomer(editedAlice));

        // same email, different budget, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withBudget(VALID_BUDGET_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same email, different characteristic, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_BIG).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));
    }

    @Test
    public void isSameCustomer_samePhone() {
        // same phone, all other attributes different -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withName(VALID_NAME_BOB)
                .withBudget(VALID_BUDGET_BOB).withTags(VALID_TAG_BIG).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same phone, different name, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same phone, different email, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same phone, different budget, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withBudget(VALID_BUDGET_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // same phone, different characteristic, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_BIG).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Customer aliceCopy = new CustomerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different customer -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different budget -> returns false
        editedAlice = new CustomerBuilder(ALICE).withBudget(VALID_BUDGET_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CustomerBuilder(ALICE).withTags(VALID_TAG_BIG).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Customer.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", budget=" + ALICE.getBudget() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
