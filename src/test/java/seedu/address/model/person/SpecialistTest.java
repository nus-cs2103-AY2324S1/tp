package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_ORTHOPAEDIC;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

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
}
