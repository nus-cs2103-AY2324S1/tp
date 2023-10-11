package seedu.address.model.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SampleDataUtilTest {
    @Test
    public void getSamplePersons() {
        try {
            assertTrue(SampleDataUtil.getSamplePersons().length > 0);
        } catch (Exception e) {
            assert false;
        }
    }
}