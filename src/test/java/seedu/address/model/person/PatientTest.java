package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientTest {
    @Test
    public void equals() {
        assertTrue(true);
        // TODO: Add tests for patient equals method based on fields
        Person aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));
    }
    @Test
    public void toStringMethod() {
        String expected = Patient.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", tags=" + ALICE.getTags()
                + ", medical history=" + ALICE.getMedicalHistory() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
