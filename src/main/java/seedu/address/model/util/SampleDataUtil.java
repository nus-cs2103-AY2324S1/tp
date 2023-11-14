package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.PropertyBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Budget("100000"),
                getTagSet("bright")),
            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Budget("999000"),
                getTagSet("white", "square")),
            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Budget("40000000"),
                getTagSet("neighbours")),
            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Budget("7600000"),
                getTagSet("family")),
            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Budget("50000"),
                getTagSet("small")),
            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Budget("200000"),
                getTagSet("city", "center"))
        };
    }

    public static Property[] getSampleProperties() {
        return new Property[] {
            new Property(new PropName("Aquavista"), new PropAddress("123 Orchid Lane, Singapore 456789"),
                    new PropPhone("94351253"), new Price("123456"),
                        getTagSet("pink")),
            new Property(new PropName("Skyvista"), new PropAddress("456 Sapphire Avenue, Singapore 987654"),
                    new PropPhone("98765432"), new Price("8880000"),
                        getTagSet("square", "garden")),
            new Property(new PropName("Horizonview"), new PropAddress("789 Palm Grove Road, Singapore 321012"),
                    new PropPhone("95352563"), new Price("400000"),
                        getTagSet()),
            new Property(new PropName("Luxeloft"), new PropAddress("234 Amber Crescent, Singapore 567890"),
                    new PropPhone("87652533"), new Price("100000"),
                        getTagSet("garage")),
            new Property(new PropName("Riveria"), new PropAddress("567 Maple Lane, Singapore 109876"),
                    new PropPhone("94821224"), new Price("4000000"),
                        getTagSet()),
            new Property(new PropName("Azure"), new PropAddress("202 Shoreline Street, Singapore 654321"),
                    new PropPhone("94821442"), new Price("321950"),
                        getTagSet())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        return sampleAb;
    }

    public static ReadOnlyPropertyBook getSamplePropertyBook() {
        PropertyBook samplePb = new PropertyBook();
        for (Property sampleProperty : getSampleProperties()) {
            samplePb.addProperty(sampleProperty);
        }
        return samplePb;
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
