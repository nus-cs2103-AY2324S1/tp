package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    private static final int SAMPLE_SIZE = 21;

    @Test
    public void getSamplePersons_returnsNonNullArray() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertNotNull(samplePersons);
    }

    @Test
    public void getSamplePersons_returnsCorrectNumberOfPersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertEquals(SAMPLE_SIZE, samplePersons.length);
    }

    //Check properties of the first entry
    @Test
    public void getSamplePersons_checkFirstEntry() {
        Person firstPerson = SampleDataUtil.getSamplePersons()[0];
        assertEquals("Alex Yeoh", firstPerson.getName().fullName);
        assertEquals("87438807", firstPerson.getPhone().value);
        assertEquals("alexyeoh@example.com", firstPerson.getEmail().value);
        assertEquals("Blk 30 Geylang Street 29, #06-40", firstPerson.getAddress().value);
        assertTrue(firstPerson.getTags().contains(new Tag("friends")));
        assertEquals("@Allyeo", firstPerson.getTelegram().value);
        assertEquals("Software Engineer", firstPerson.getProfession().value);
        assertEquals(Integer.parseInt("80000"), firstPerson.getIncome().value);
        assertEquals("Looking for automated solutions for project management", firstPerson.getDetails().value);
        assertEquals(Lead.of("COLD"), firstPerson.getLead());
        assertEquals(1, firstPerson.getInteractions().size());
        assertEquals("setting next meeting", firstPerson.getInteractions().get(0).getInteractionNote());
        assertEquals("INTERESTED", firstPerson.getInteractions().get(0).getOutcome().name());
        assertEquals(LocalDate.of(2023, 11, 12), firstPerson.getInteractions().get(0).getDate());
    }
}
