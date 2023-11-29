package seedu.address.model.person.dentists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALIZATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDentists.BOB;
import static seedu.address.testutil.TypicalDentists.DENTIST_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

public class DentistTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Dentist dentist = new DentistBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> dentist.getTags().remove(0));
    }

    @Test
    public void isSameDentist() {
        // same object -> returns true
        assertTrue(DENTIST_ALICE.isSameDentist(DENTIST_ALICE));

        // null -> returns false
        assertFalse(DENTIST_ALICE.isSameDentist(null));

        // same name, all other attributes different -> returns true
        Dentist editedAlice = new DentistBuilder(DENTIST_ALICE)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withSpecialization(VALID_SPECIALIZATION_BOB)
            .withYoe(VALID_YOE_BOB)
            .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(DENTIST_ALICE.isSameDentist(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new DentistBuilder(DENTIST_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(DENTIST_ALICE.isSameDentist(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Dentist aliceCopy = new DentistBuilder(DENTIST_ALICE).build();
        // nassertTrue(DENTIST_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DENTIST_ALICE.equals(DENTIST_ALICE));

        // null -> returns false
        assertFalse(DENTIST_ALICE.equals(null));

        // different type -> returns false
        assertFalse(DENTIST_ALICE.equals(5));

        // different dentist -> returns false
        assertFalse(DENTIST_ALICE.equals(BOB));

        // different name -> returns false
        Dentist editedAlice = new DentistBuilder(DENTIST_ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(DENTIST_ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new DentistBuilder(DENTIST_ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DENTIST_ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new DentistBuilder(DENTIST_ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DENTIST_ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new DentistBuilder(DENTIST_ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DENTIST_ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DentistBuilder(DENTIST_ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DENTIST_ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        StringBuilder expected = new StringBuilder();
        expected.append(DENTIST_ALICE.getName())
                .append("; Phone: ")
                .append(DENTIST_ALICE.getPhone())
                .append("; Email: ")
                .append(DENTIST_ALICE.getEmail())
                .append("; Address: ")
                .append(DENTIST_ALICE.getAddress())
                .append("; Specialization: ")
                .append(DENTIST_ALICE.getSpecialization())
                .append("; Years of Experience: ")
                .append(DENTIST_ALICE.getYoe())
                .append("; Dentist ID: ")
                .append(DENTIST_ALICE.getId())
                .append("; Tags: ");
        DENTIST_ALICE.getTags().forEach(expected::append);

        assertEquals(expected.toString(), DENTIST_ALICE.toString());
    }
}
