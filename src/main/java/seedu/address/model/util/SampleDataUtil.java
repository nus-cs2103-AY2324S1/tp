package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.BookingsBook;
import seedu.address.model.ReadOnlyBookingsBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Email;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.Room;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Booking[] getSamplePersons() {
        return new Booking[] {
            new Booking(new Room("69"), new BookingPeriod("2023-01-01 to 2023-01-02"),
                    new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Booking(new Room("420"), new BookingPeriod("2023-01-01 to 2023-01-02"),
                    new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Booking(new Room("300"),
                    new BookingPeriod("2023-01-01 to 2023-01-02"),
                    new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Booking(new Room("111"),
                    new BookingPeriod("2023-01-01 to 2023-01-02"),
                    new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Booking(new Room("500"),
                    new BookingPeriod("2023-01-01 to 2023-01-02"),
                    new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Booking(new Room("1"),
                    new BookingPeriod("2023-01-01 to 2023-01-02"),
                    new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyBookingsBook getSampleAddressBook() {
        BookingsBook sampleAb = new BookingsBook();
        for (Booking sampleBooking : getSamplePersons()) {
            sampleAb.addBooking(sampleBooking);
        }
        return sampleAb;
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
