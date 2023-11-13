package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_ORTHOPAEDIC;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.SpecialistBuilder;

public class SpecialistTest {
    @Test
    public void equals() {
        assertTrue(true);

        // different speciality -> returns false
        Person editedBob = new SpecialistBuilder(BOB).withSpecialty(VALID_SPECIALTY_ORTHOPAEDIC).build();
        assertFalse(BOB.equals(editedBob));
    }
    @Test
    public void toStringMethod() {
        String expected = Specialist.class.getCanonicalName() + "{name=" + BOB.getName() + ", phone=" + BOB.getPhone()
                + ", email=" + BOB.getEmail() + ", tags=" + BOB.getTags() + ", location=" + BOB.getLocation()
                + ", specialty=" + BOB.getSpecialty() + "}";
        assertEquals(expected, BOB.toString());
    }

    @Test
    public void isSamePerson() {
        // same name different personType -> false
        assertFalse(new SpecialistBuilder().build().isSamePerson(new PatientBuilder().build()));

        Person amyBee = new SpecialistBuilder().build();
        Person evilAmyBee = new SpecialistBuilder().withEmail("evilAmy@example.com").build();
        Person aliceBee = new SpecialistBuilder().withName("Alice Bee").build();

        // same name same personType -> true
        assertTrue(new SpecialistBuilder().build().isSamePerson(new SpecialistBuilder().build()));

        // same object -> true
        assertTrue(aliceBee.isSamePerson(aliceBee));

        // same fields different name -> false
        assertFalse(amyBee.isSamePerson(aliceBee));

        // same name different fields -> true
        assertTrue(amyBee.isSamePerson(evilAmyBee));
    }
}
