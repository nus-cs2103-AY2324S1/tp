package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in
 * tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withEmail("alice@example.com")
            .withLastContactedTime("20.10.2023 1100").withPhone("94351253").withRemark("").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withEmail("johnd@example.com")
            .withLastContactedTime("20.10.2023 1200").withPhone("98765432").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withLastContactedTime("20.10.2023 1300").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withLastContactedTime("20.10.2023 1400").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withLastContactedTime("20.10.2023 1500").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withLastContactedTime("20.10.2023 1600").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withLastContactedTime("20.10.2023 1700").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withLastContactedTime("20.10.2023 1800").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withLastContactedTime("20.10.2023 1900").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withLastContactedTime(VALID_LASTTIME_AMY).withRemark(VALID_REMARK_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withLastContactedTime(VALID_LASTTIME_BOB).withStatus(VALID_STATUS_BOB)
            .withRemark(VALID_REMARK_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, BOB));
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons only.
     */
    public static AddressBook getTypicalPersonsAddressBook() {
        AddressBook ab = new AddressBook();

        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }

        return ab;
    }

    public static String[] getTypicalAttendeesAll() {
        List<Person> typicalPersons = TypicalPersons.getTypicalPersons();
        String[] typicalAttendees = typicalPersons.stream().map(Person::getName).map(Name::toString)
                .collect(Collectors.toList()).toArray(String[]::new);
        return typicalAttendees;
    }

    public static String[] getTypicalAttendeesSubset1() {
        return Arrays.copyOfRange(getTypicalAttendeesAll(), 3, 7);
    }

    public static String[] getTypicalAttendeesSubset2() {
        return Arrays.copyOfRange(getTypicalAttendeesAll(), 1, 4);
    }
}
