package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_THIRTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_OSTEOPOROSIS;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientTest {
    @Test
    public void equals() {
        assertTrue(true);

        // different age -> returns false
        Person editedAlice = new PatientBuilder(ALICE).withAge(VALID_AGE_THIRTY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different medical history -> returns false
        editedAlice = new PatientBuilder(ALICE).withMedicalHistory(VALID_MEDICAL_HISTORY_OSTEOPOROSIS).build();
        assertFalse(ALICE.equals(editedAlice));
    }
    @Test
    public void toStringMethod() {
        String expected = Patient.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", tags=" + ALICE.getTags()
                + ", age=" + ALICE.getAge()
                + ", medical history=" + ALICE.getMedicalHistory() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
