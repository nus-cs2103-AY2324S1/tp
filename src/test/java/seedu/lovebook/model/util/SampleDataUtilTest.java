package seedu.lovebook.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersonsTest() {
        assertTrue(SampleDataUtil.getSamplePersons().length == 0);
    }

    @Test
    public void getSampleLoveBookTest() {
        assertEquals("seedu.lovebook.model.LoveBook{dates=[]}", SampleDataUtil.getSampleLoveBook().toString());
    }
}
