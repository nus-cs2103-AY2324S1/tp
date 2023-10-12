package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_validData_returnsArrayOfPersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        // Check if the array is not null
        assertNotNull(samplePersons);

        // Check if the length of the array matches the number of sample persons
        assertEquals(6, samplePersons.length);

        // Check if each element in the array is an instance of Person
        for (Person person : samplePersons) {
            assertNotNull(person);
            assertEquals(Name.class, person.getName().getClass());
            assertEquals(Phone.class, person.getPhone().getClass());
            assertEquals(Email.class, person.getEmail().getClass());
        }
    }
}
