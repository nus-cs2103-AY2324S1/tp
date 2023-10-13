package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_validData_returnsArrayOfPersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertNotNull(samplePersons);
        assertEquals(6, samplePersons.length);

        for (Person person : samplePersons) {
            assertNotNull(person);
            assertEquals(Name.class, person.getName().getClass());
            assertEquals(Phone.class, person.getPhone().getClass());
            assertEquals(Email.class, person.getEmail().getClass());
        }
    }

    @Test
    public void getSampleAddressBook_validData_returnsAddressBookWithSamplePersons() {
        ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();

        assertNotNull(addressBook);
        assertEquals(6, addressBook.getPersonList().size());

        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        for (int i = 0; i < samplePersons.length; i++) {
            assertEquals(samplePersons[i], addressBook.getPersonList().get(i));
        }
    }
}
