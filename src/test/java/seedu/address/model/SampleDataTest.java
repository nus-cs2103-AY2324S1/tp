package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.company.Company;
import seedu.address.model.person.Person;
import seedu.address.model.util.CompanySampleDataUtil;
import seedu.address.model.util.PersonSampleDataUtil;
import seedu.address.model.util.SampleDataUtil;

public class SampleDataTest {

    @Test
    public void getSampleCompanies_validData_returnsArrayWithCorrectSize() {
        Company[] sampleCompanies = CompanySampleDataUtil.getSampleCompanies();
        assertEquals(7, sampleCompanies.length); // Check if the array has the expected size
    }

    @Test
    public void getSampleCompanies_checkFirstCompanyAttributes() {
        Company[] sampleCompanies = CompanySampleDataUtil.getSampleCompanies();
        Company firstCompany = sampleCompanies[0];
        assertEquals("Apple Inc.", firstCompany.getCompanyName().toString());
        assertEquals("12345678", firstCompany.getCompanyPhone().toString());
        assertEquals("apple@example.com", firstCompany.getCompanyEmail().toString());
        assertEquals("A technology company.", firstCompany.getCompanyDescription().toString());
        assertEquals(3, firstCompany.getTags().size());
    }

    @Test
    public void getSampleCompanies_checkLastCompanyAttributes() {
        Company[] sampleCompanies = CompanySampleDataUtil.getSampleCompanies();
        Company lastCompany = sampleCompanies[sampleCompanies.length - 1];
        assertEquals("Tesla, Inc.", lastCompany.getCompanyName().toString());
        assertEquals("78901234", lastCompany.getCompanyPhone().toString());
        assertEquals("tesla@example.com", lastCompany.getCompanyEmail().toString());
        assertEquals("An electric vehicle and clean energy company.", lastCompany.getCompanyDescription().toString());
        assertEquals(2, lastCompany.getTags().size());
    }

    @Test
    public void getSamplePersons_validData_returnsArrayWithCorrectSize() {
        Person[] samplePersons = PersonSampleDataUtil.getSamplePersons();
        assertEquals(6, samplePersons.length); // Check if the array has the expected size
    }

    @Test
    public void getSamplePersons_checkFirstPersonAttributes() {
        Person[] samplePersons = PersonSampleDataUtil.getSamplePersons();
        Person firstPerson = samplePersons[0];
        assertEquals("Alex Yeoh", firstPerson.getName().toString());
        assertEquals("87438807", firstPerson.getPhone().toString());
        assertEquals("alexyeoh@example.com", firstPerson.getEmail().toString());
        assertEquals("Blk 30 Geylang Street 29, #06-40", firstPerson.getAddress().toString());
        assertEquals(1, firstPerson.getTags().size()); // Check if the number of tags is as expected
    }

    // Add more tests to verify the attributes of other persons and their sizes

    @Test
    public void getSamplePersons_checkLastPersonAttributes() {
        Person[] samplePersons = PersonSampleDataUtil.getSamplePersons();
        Person lastPerson = samplePersons[samplePersons.length - 1];
        assertEquals("Roy Balakrishnan", lastPerson.getName().toString());
        assertEquals("92624417", lastPerson.getPhone().toString());
        assertEquals("royb@example.com", lastPerson.getEmail().toString());
        assertEquals("Blk 45 Aljunied Street 85, #11-31", lastPerson.getAddress().toString());
        assertEquals(1, lastPerson.getTags().size()); // Check if the number of tags is as expected
    }

    @Test
    public void getSampleAddressBook_validData_returnsAddressBookWithCorrectSize() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
        AddressBook addressBook = new AddressBook(sampleAddressBook);

        // Verify that the size of persons and companies in the address book matches the expected size
        assertEquals(6, addressBook.getPersonList().size());
        assertEquals(7, addressBook.getCompanyList().size());
    }

    @Test
    public void getSampleAddressBook_checkSamplePersonAttributes() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
        AddressBook addressBook = new AddressBook(sampleAddressBook);

        // Check attributes of a sample person (e.g., the first person)
        Person samplePerson = addressBook.getPersonList().get(0);
        assertEquals("Alex Yeoh", samplePerson.getName().toString());
        assertEquals("87438807", samplePerson.getPhone().toString());
        assertEquals("alexyeoh@example.com", samplePerson.getEmail().toString());
        assertEquals("Blk 30 Geylang Street 29, #06-40", samplePerson.getAddress().toString());
        assertEquals(1, samplePerson.getTags().size()); // Check if the number of tags is as expected
    }

    @Test
    public void getSampleAddressBook_checkSampleCompanyAttributes() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();
        AddressBook addressBook = new AddressBook(sampleAddressBook);

        // Check attributes of a sample company (e.g., the first company)
        Company sampleCompany = addressBook.getCompanyList().get(0);
        assertEquals("Apple Inc.", sampleCompany.getCompanyName().toString());
        assertEquals("12345678", sampleCompany.getCompanyPhone().toString());
        assertEquals("apple@example.com", sampleCompany.getCompanyEmail().toString());
        assertEquals("A technology company.", sampleCompany.getCompanyDescription().toString());
        assertEquals(3, sampleCompany.getTags().size()); // Check if the number of tags is as expected
    }
}
