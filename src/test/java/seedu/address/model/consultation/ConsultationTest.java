package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION1;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION2;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION3;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION4;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ConsultationBuilder;

public class ConsultationTest {
    @Test
    public void isSameConsultation() {
        // same object -> returns true
        assertTrue(CONSULTATION1.isSameConsultation(CONSULTATION1));

        // null -> returns false
        assertFalse(CONSULTATION1.isSameConsultation(null));

        // same date, same time, same set of students -> returns true
        Consultation editedConsultation = new ConsultationBuilder(CONSULTATION1).build();
        assertTrue(CONSULTATION1.isSameConsultation(editedConsultation));

        // different date, same time, same set of students -> returns false
        editedConsultation = new ConsultationBuilder(CONSULTATION1).withDate("01/01/2002").build();
        assertFalse(CONSULTATION1.isSameConsultation(editedConsultation));

        // different time, same date, same set of students -> returns false
        editedConsultation = new ConsultationBuilder(CONSULTATION2).withTime("23:59").build();
        assertFalse(CONSULTATION2.isSameConsultation(editedConsultation));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Consultation consultationCopy = new ConsultationBuilder(CONSULTATION2).build();
        assertTrue(CONSULTATION2.equals(consultationCopy));

        // same object -> returns true
        assertTrue(CONSULTATION1.equals(CONSULTATION1));

        // null -> returns false
        assertFalse(CONSULTATION4.equals(null));

        // different consultation -> returns false
        assertFalse(CONSULTATION1.equals(CONSULTATION3));
    }
}
