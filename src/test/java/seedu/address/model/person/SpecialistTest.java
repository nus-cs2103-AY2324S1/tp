package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class SpecialistTest {
    @Test
    public void equals() {
        assertTrue(true);
        // TODO: Add tests for specialists equals method based on fields
    }
    @Test
    public void toStringMethod() {
        String expected = Specialist.class.getCanonicalName() + "{name=" + BOB.getName() + ", phone=" + BOB.getPhone()
                + ", email=" + BOB.getEmail() + ", location=" + BOB.getLocation() + ", tags=" + BOB.getTags()
                + ", specialty=" + BOB.getSpecialty() + "}";
        assertEquals(expected, BOB.toString());
    }
}
