package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Buyer[] getSampleBuyers() {
        return new Buyer[]{
            new Buyer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new HouseInfo("Wants 4-room HDB"), getTagSet("speaks chinese only")),
            new Buyer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new HouseInfo("Below $500,000"), getTagSet("low budget", "urgent")),
            new Buyer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new HouseInfo("Wants a pool"), getTagSet("late to meetups")),
            new Buyer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new HouseInfo("Wants a semi-detached"), getTagSet("family")),
            new Buyer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new HouseInfo("Child-friendly"), getTagSet("2 children")),
            new Buyer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new HouseInfo("Sea view"), getTagSet("first-time homeowner"))
        };
    }
    public static Seller[] getSampleSellers() {
        return new Seller[]{
            new Seller(new Name("Jayden Goh"), new Phone("93801238"), new Email("jaygoh@example.com"),
                    new Address("Blk 302 Choa Chu Kang Street 51, #03-01"),
                    new Address("Yew Mei Green, Choa Chu Kang North 6, #10-10"),
                    new HouseInfo("Condominium"), getTagSet("urgent")),
            new Seller(new Name("Allan Blackrock"), new Phone("98001223"), new Email("allblkrk@example.com"),
                    new Address("Ridout Road No. 31"),
                    new Address("Ridout Road No. 26"),
                    new HouseInfo("Bungalow"), getTagSet("Non-Singaporean")),
            new Seller(new Name("Shannon Chew"), new Phone("88811247"), new Email("shanshan@example.com"),
                    new Address("Blk 807A Chai Chee road, #12-09"),
                    new Address("Everton Rd No. 36"),
                    new HouseInfo("Shophouse"), getTagSet("Historic house")),
            new Seller(new Name("Adarsh Shankar"), new Phone("82838391"), new Email("adarshankar@example.com"),
                    new Address("Blk 621 Hougang Ave 8, #18-03"),
                    new Address("Blk 677 Hougang Ave 8, #12-11"),
                    new HouseInfo("1 room in 5-rm flat"), getTagSet("rental")),
            new Seller(new Name("Aishah Binte Haziq"), new Phone("7232832"), new Email("aish@example.com"),
                    new Address("The Trilinq, Clementi Ave 6, #07-16"),
                    new Address("The Parc Condominium, West Coast Rd, #20-31"),
                    new HouseInfo("Sea view"), getTagSet("patient seller")),
            new Seller(new Name("Ramanathan s/o Srinivasan"), new Phone("68933011"), new Email("rmnth@example.com"),
                    new Address("The Anchorage, Alexandria Rd, #18-17"),
                    new Address("Blk 420 Ang Mo Kio St 41, #10-02"),
                    new HouseInfo("Near playground & primary school"), getTagSet("Meet through zoom"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Buyer sampleBuyer : getSampleBuyers()) {
            sampleAb.addBuyer(sampleBuyer);
        }
        for (Seller sampleSeller : getSampleSellers()) {
            sampleAb.addSeller(sampleSeller);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String...strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
