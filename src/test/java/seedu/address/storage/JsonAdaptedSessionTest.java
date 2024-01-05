package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionNumber;
import seedu.address.model.session.SessionRemark;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.TypicalPersons;


public class JsonAdaptedSessionTest {
    @Test
    public void toModelType_validSessionDetails_returnsSession() throws Exception {
        JsonAdaptedSession session = new JsonAdaptedSession(new SessionBuilder().withSessionNumber("0").build());
        assertEquals(new SessionBuilder().withSessionNumber("0").build(), session.toModelType());
    }

    @Test
    public void toModelType_validSessionDetailsWithMultipleStudents_returnsSession() throws Exception {
        JsonAdaptedSession session = new JsonAdaptedSession(new Session(new SessionNumber("1"), TypicalPersons.BOB));
        assertEquals(new Session(new SessionNumber("1"), TypicalPersons.BOB), session.toModelType());
    }

    @Test
    public void toModelType_invalidSessionNumber_throwsIllegalValueException() {
        List<JsonAdaptedPerson> students = new ArrayList<>();
        JsonAdaptedSession session = new JsonAdaptedSession(null, students, "NA");
        String expectedMessage = SessionNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_invalidSessionRemark_throwsIllegalValueException() {
        List<JsonAdaptedPerson> students = new ArrayList<>();
        JsonAdaptedSession session = new JsonAdaptedSession("2", students, null);
        String expectedMessage = SessionRemark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }
}
