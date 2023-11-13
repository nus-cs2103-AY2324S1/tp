package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TutorialTest {

    @Test
    void reformat_validInputs_successfullyReformat() {
        String expectedAmOutput = "Mon 1AM";
        String expectedPmOutput = "Mon 1PM";

        // test AM/am PM/pm
        assertEquals(expectedAmOutput, Tutorial.reformat("Mon 1AM"));
        assertEquals(expectedAmOutput, Tutorial.reformat("Mon 1am"));
        assertEquals(expectedPmOutput, Tutorial.reformat("Mon 1PM"));
        assertEquals(expectedPmOutput, Tutorial.reformat("Mon 1pm"));

        // test MON/mon
        assertEquals(expectedAmOutput, Tutorial.reformat("MON 1AM"));
        assertEquals(expectedAmOutput, Tutorial.reformat("mon 1AM"));
        assertEquals(expectedPmOutput, Tutorial.reformat("MON 1PM"));
        assertEquals(expectedPmOutput, Tutorial.reformat("mon 1PM"));

        // test fullName
        assertEquals(expectedAmOutput, Tutorial.reformat("Monday 1AM"));
        assertEquals(expectedAmOutput, Tutorial.reformat("MonDay 1AM"));
        assertEquals(expectedPmOutput, Tutorial.reformat("Monday 1PM"));
        assertEquals(expectedPmOutput, Tutorial.reformat("MonDay 1PM"));

        // test all the other days-of-the-week with upper-case AM
        assertEquals("Tue 1AM", Tutorial.reformat("Tue 1AM"));
        assertEquals("Wed 1AM", Tutorial.reformat("Wed 1AM"));
        assertEquals("Thu 1AM", Tutorial.reformat("Thu 1AM"));
        assertEquals("Fri 1AM", Tutorial.reformat("Fri 1AM"));
        assertEquals("Sat 1AM", Tutorial.reformat("Sat 1AM"));
        assertEquals("Sun 1AM", Tutorial.reformat("Sun 1AM"));
        assertEquals("Tue 1AM", Tutorial.reformat("Tuesday 1AM"));
        assertEquals("Wed 1AM", Tutorial.reformat("Wednesday 1AM"));
        assertEquals("Thu 1AM", Tutorial.reformat("Thursday 1AM"));
        assertEquals("Fri 1AM", Tutorial.reformat("Friday 1AM"));
        assertEquals("Sat 1AM", Tutorial.reformat("Saturday 1AM"));
        assertEquals("Sun 1AM", Tutorial.reformat("Sunday 1AM"));
    }

    @Test
    void reformat_invalidInputs_returnNull() {
        assertThrows(IllegalArgumentException.class, () -> Tutorial.reformat("Mon 0AM"));
        assertThrows(IllegalArgumentException.class, () -> Tutorial.reformat("Mon 13AM"));
        assertThrows(IllegalArgumentException.class, () -> Tutorial.reformat("test time"));
    }
}
