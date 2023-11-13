package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Specialisation;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Doctor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    getAffiliationSet("Bernice Yu", "Charlotte Oliveiro"))
                    .setSpecialisations(getSpecialisationSet("ENT", "Radiologist"))
                    .setShiftDays(getShiftDays(1, 3, 6)),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    getAffiliationSet("Alex Yeoh"))
                    .setNextOfKin(getNextOfKin("Holland Tan", "91334428", "Father")),
            new Nurse(new Name("May Ho"), new Phone("94437233"), new Email("homimay@example.com"),
                        getAffiliationSet("Irfan Ibrahim"))
                    .setShiftDays(getShiftDays(3, 4, 5)),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    getAffiliationSet("Alex Yeoh", "Evelyn Ng"))
                    .setNextOfKin(getNextOfKin("Ancob Maximus", "87769988", "Husband")),
            new Doctor(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    getAffiliationSet("Irfan Ibrahim"))
                    .setSpecialisations(getSpecialisationSet("Cardiologist"))
                    .setShiftDays(getShiftDays(2, 5, 7)),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    getAffiliationSet("David Li", "May Ho")),
            new Nurse(new Name("Evelyn Ng"), new Phone("92624417"), new Email("eveng@example.com"),
                    getAffiliationSet("Charlotte Oliveiro"))
                    .setShiftDays(getShiftDays(1, 2, 4, 6))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns an affiliation set containing the list of strings given.
     */
    public static Set<Affiliation> getAffiliationSet(String... strings) {
        return Arrays.stream(strings)
                .map(Affiliation::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a @code{ShiftDays} containing the list of days given as integers from 1 to 7.
     */
    public static ShiftDays getShiftDays(Integer... days) {
        return new ShiftDays(new HashSet<>(Arrays.asList(days)));
    }

    /**
     * Returns a @code{Specialisation} containing the list of specialisations given as strings in a set.
     */
    public static HashSet<Specialisation> getSpecialisationSet(Set<String> strings) {
        return strings.stream()
                .map(Specialisation::new)
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Returns a @code{Specialisation} containing the list of specialisations given as strings.
     */
    public static Set<Specialisation> getSpecialisationSet(String... strings) {
        return Arrays.stream(strings)
                .map(Specialisation::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a @code{NextOfKin} containing the list of attributes given as strings.
     */
    public static NextOfKin getNextOfKin(String name, String phone, String relationship) {
        return new NextOfKin(new Name(name), new Phone(phone), new Relationship(relationship));
    }

}
