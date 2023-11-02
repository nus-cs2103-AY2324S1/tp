package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withBirthday("1998-01-01").withRemark("She likes korean food")
            .withGroups("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withBirthday("1998-01-02").withRemark("He owes me money")
            .withGroups("owesMoney", "friends", "teammates").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withBirthday("1998-01-03").withRemark("He likes to swim").withGroups("family").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withBirthday("1998-01-04").withGroups("friends")
            .withRemark("He owes me a lot of money").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withBirthday("1998-01-05").withRemark("She likes to eat cheese")
            .withGroups("teammates").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withBirthday("1998-01-06").withRemark("She likes steak")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withBirthday("1998-01-07").withRemark("He likes to play football").build();


    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withRemark("Likes hamsters.")
            .withBirthday("1998-01-08").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRemark("Likes iguanas.")
            .withBirthday("1998-01-09").build();

    // Manually added for remind command
    public static final Person JOHN = new PersonBuilder().withName("John")
            .withBirthday(LocalDate.now().plusDays(3).toString()).build();
    public static final Person JANE = new PersonBuilder().withName("Jane")
            .withBirthday(LocalDate.now().plusDays(5).toString()).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY).withRemark(VALID_REMARK_AMY)
            .withGroups(VALID_GROUP_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthday(VALID_BIRTHDAY_BOB)
            .withRemark(VALID_REMARK_BOB).withGroups(VALID_GROUP_HUSBAND, VALID_GROUP_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Event event : new ArrayList<>(
                Arrays.asList(TypicalEvents.TP_MEETING, TypicalEvents.MEETING_WITHOUT_PERSONS,
                        TypicalEvents.TP_MEETING_WITH_PERSONS, TypicalEvents.TP_MEETING_WITH_GROUPS))) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static AddressBook getBirthdayAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getBirthdayPersons()) {
            ab.addPerson(person);
        }
        for (Event event : new ArrayList<>(
                Arrays.asList(TypicalEvents.MEETING_3_DAYS_AFTER_TODAY,
                        TypicalEvents.MEETING_6_DAYS_AFTER_TODAY))) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Person> getBirthdayPersons() {
        return new ArrayList<>(Arrays.asList(JOHN, JANE));
    }
}
