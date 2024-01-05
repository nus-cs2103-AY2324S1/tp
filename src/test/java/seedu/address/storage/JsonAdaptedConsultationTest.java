package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalConsultations;

public class JsonAdaptedConsultationTest {
    @Test
    public void toModelType_validConsultationDetails_returnsConsultation() throws Exception {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(TypicalConsultations.CONSULTATION1);
        assertEquals(TypicalConsultations.CONSULTATION1, consultation.toModelType());
    }

    @Test
    public void toModelType_validConsultationDetailsWithMultipleStudents_returnConsultation() throws Exception {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(TypicalConsultations.CONSULTATION2);
        assertEquals(TypicalConsultations.CONSULTATION2, consultation.toModelType());
    }

    @Test
    public void toModelType_invalidConsultationDateTime_throwsIllegalValueException() {
        List<JsonAdaptedPerson> students = new ArrayList<>();
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation("2023-11-11", "01:00", students);
        assertThrows(DateTimeParseException.class, consultation::toModelType);
    }
}
