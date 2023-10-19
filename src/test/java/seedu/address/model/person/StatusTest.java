package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusTest {


    @Test
    public void toStringMethod() {
        assertEquals(new Status().toString(), "Preliminary");
    }
}
