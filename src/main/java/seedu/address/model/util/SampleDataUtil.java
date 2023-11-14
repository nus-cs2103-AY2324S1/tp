package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.NullAppointment;
import seedu.address.model.appointment.ScheduleItem;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKinName;
import seedu.address.model.person.NextOfKinPhone;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final ScheduleItem EMPTY_APPOINTMENT = NullAppointment.getNullAppointment();
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new NextOfKinName("Alex Dad"),
                new NextOfKinPhone("999"), getFinancialPlanSet("voodoo financial plan A"),
                    getTagSet("friends"), EMPTY_APPOINTMENT),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new NextOfKinName("Bernice Mom"),
                new NextOfKinPhone("888"), getFinancialPlanSet("voodoo financial plan B"),
                    getTagSet("colleagues", "friends"), EMPTY_APPOINTMENT),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new NextOfKinName("Charlotte Bro"),
                new NextOfKinPhone("777"), getFinancialPlanSet("voodoo financial plan C"),
                    getTagSet("neighbours"), EMPTY_APPOINTMENT),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new NextOfKinName("David Sis"),
                new NextOfKinPhone("666"), getFinancialPlanSet("voodoo financial plan D"),
                    getTagSet("family"), EMPTY_APPOINTMENT),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new NextOfKinName("Ifran Grandpa"),
                new NextOfKinPhone("555"), getFinancialPlanSet("voodoo financial plan E"),
                    getTagSet("classmates"), EMPTY_APPOINTMENT),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new NextOfKinName("Roy Grandma"),
                new NextOfKinPhone("444"), getFinancialPlanSet("voodoo financial plan F"),
                    getTagSet("colleagues"), EMPTY_APPOINTMENT)
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
     * Returns a financial plan set containing the list of strings given.
     */
    public static Set<FinancialPlan> getFinancialPlanSet(String... strings) {
        return Arrays.stream(strings)
                .map(FinancialPlan::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
