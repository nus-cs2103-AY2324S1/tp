package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION1;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class ConsultationListBookTest {
    private final ConsultationListBook consultationListBook = new ConsultationListBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), consultationListBook.getConsultationList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> consultationListBook.resetData(null));
    }

    @Test
    public void hasConsultation_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> consultationListBook.hasConsultation(null));
    }

    @Test
    public void hasConsultation_consultationNotInConsultationListBook_returnsFalse() {
        assertFalse(consultationListBook.hasConsultation(CONSULTATION1));
    }

    @Test
    public void hasConsultation_consultationInConsultationListBook_returnsTrue() {
        consultationListBook.addConsultation(CONSULTATION1);
        assertTrue(consultationListBook.hasConsultation(CONSULTATION1));
    }

    @Test
    public void getConsultationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                consultationListBook.getConsultationList().remove(0));
    }

    @Test
    public void equals() {
        ConsultationListBook consultationListBook = new ConsultationListBook();

        // New SessionListBook should be equal to itself
        assertEquals(consultationListBook, consultationListBook);

        // Other class objects not equals to ConsultationListBook
        assertFalse(consultationListBook.equals(1));

        // ConsultationListBook with different data should not be equal
        ConsultationListBook other = new ConsultationListBook();
        other.addConsultation(CONSULTATION1);
        assertNotEquals(consultationListBook, other);

        // ReadOnlyConsultationList should be equal to ConsultationListBook with the same data
        ReadOnlyConsultationList readOnlyConsultationList = new ConsultationListBook();
        assertEquals(consultationListBook, readOnlyConsultationList);
    }
}
