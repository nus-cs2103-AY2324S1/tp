package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_THIRTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_OSTEOPOROSIS;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.SpecialistBuilder;

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

    @Test
    public void isSamePerson() {
        // same name different personType -> false
        assertFalse(new PatientBuilder().build().isSamePerson(new SpecialistBuilder().build()));

        Person amyBee = new PatientBuilder().build();
        Person evilAmyBee = new PatientBuilder().withEmail("evilAmy@example.com").build();
        Person aliceBee = new PatientBuilder().withName("Alice Bee").build();

        // same name same personType -> true
        assertTrue(new PatientBuilder().build().isSamePerson(new PatientBuilder().build()));

        // same object -> true
        assertTrue(aliceBee.isSamePerson(aliceBee));

        // same fields different name -> false
        assertFalse(amyBee.isSamePerson(aliceBee));

        // same name different fields -> true
        assertTrue(amyBee.isSamePerson(evilAmyBee));
    }

}
