package seedu.address.model.event.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.DUPLICATED_EVENTS;

import org.junit.jupiter.api.Test;

public class DuplicateEventExceptionTest {
    @Test
    public void test_createException_pass() {
        assertEquals(new DuplicateEventException().getMessage(), DUPLICATED_EVENTS);
    }
}

