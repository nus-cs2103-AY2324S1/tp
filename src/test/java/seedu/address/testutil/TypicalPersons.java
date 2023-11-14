package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CS1231S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_FIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_SIXTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTelegram("@aliceheyhey").withEmail("alice@example.com")
            .withPhone("94351253").withHour(8).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTelegram("@bensonnn123")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withFreeTime("21:15", "22:30").withCourses("CS2103T")
            .withHour(14).build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withTelegram("@carlkurz").withHour(24).withCourses("CS1231S").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTelegram("@danielmeier")
            .withTags("friends").withFreeTime("10:45", "11:30").withHour(38).withCourses("CS1231S").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withTelegram("@hiitselle").withHour(63).withCourses("CS2100").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withTelegram("@imfionaa").withFreeTime("21:10", "23:15")
            .withHour(39).withCourses("CS2100").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withTelegram("@georgiey").withFreeTime("07:00", "09:00")
            .withHour(46).withCourses("CS2100").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withTelegram("@hooniehoonhoon").withHour(14).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withTelegram("@idamueller").withHour(27).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY).withTags(VALID_TAG_FRIEND)
            .withCourses(VALID_COURSE_CS1231S, VALID_COURSE_CS2103T).withHour(VALID_HOUR_FIVE).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withHour(VALID_HOUR_SIXTY).build();

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
