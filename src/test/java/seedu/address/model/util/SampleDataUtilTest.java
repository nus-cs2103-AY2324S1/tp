package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_validData_returnsMapOfPersons() {
        HashMap<String, Person> samplePersons = SampleDataUtil.getSamplePersons();

        assertNotNull(samplePersons);
        assertEquals(6, samplePersons.size());

        for (Person person : samplePersons.values()) {
            assertNotNull(person);
            assertEquals(Name.class, person.getName().getClass());
            assertEquals(Phone.class, person.getPhone().getClass());
            assertEquals(Email.class, person.getEmail().getClass());
        }
    }

    @Test
    public void generateSchedules_validData_returnsArrayOfSchedules() {
        HashMap<String, Person> samplePersons = SampleDataUtil.getSamplePersons();
        Schedule[] sampleSchedules = SampleDataUtil.generateSchedules(samplePersons);

        assertNotNull(sampleSchedules);
        assertEquals(6, sampleSchedules.length);

        for (Schedule schedule : sampleSchedules) {
            assertNotNull(schedule);
            assertNotNull(schedule.getTutor());
            assertNotNull(schedule.getStartTime());
            assertNotNull(schedule.getEndTime());
        }
    }

    @Test
    public void getSampleAddressBook_validData_returnsAddressBookWithSamplePersonsAndSchedules() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();

        assertNotNull(addressBook);
        assertEquals(6, addressBook.getPersonList().size());
        assertEquals(6, addressBook.getScheduleList().size());

        HashMap<String, Person> samplePersons = SampleDataUtil.getSamplePersons();
        for (int i = 0; i < addressBook.getPersonList().size(); i++) {
            assertEquals(samplePersons.get(addressBook.getPersonList().get(i).getName().toString()),
                addressBook.getPersonList().get(i));
        }
    }
}
