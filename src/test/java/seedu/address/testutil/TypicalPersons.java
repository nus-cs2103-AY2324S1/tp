package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_1;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_2;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_3;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_4;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_5;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_6;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FEVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FLU;

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
            .withGender("FEMALE")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withBirthdate("1999/01/12")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withGender("MALE")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withBirthdate("2001/10/20")
            .withTags("owesMoney", "friends").build();
    public static final Person LOWERCASE_BENJAMIN = new PersonBuilder().withName("benjamin Monk")
            .withGender("MALE")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("benj@example.com").withPhone("98765432")
            .withBirthdate("2001/10/16")
            .withTags("cancer", "diabetes").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withGender("MALE")
            .withPhone("95352563").withEmail("heinz@example.com").withBirthdate("1987/01/02")
            .withAddress("wall street").withTags("malaria").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withGender("MALE")
            .withPhone("87652533").withEmail("cornelia@example.com").withBirthdate("2003/03/28")
            .withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withGender("FEMALE")
            .withPhone("9482224").withEmail("werner@example.com").withBirthdate("1989/05/19")
            .withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withGender("FEMALE")
            .withPhone("9482427").withEmail("lydia@example.com").withBirthdate("2002/06/12")
            .withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withGender("MALE")
            .withPhone("9482442").withEmail("anna@example.com").withBirthdate("1994/08/23")
            .withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withGender("MALE")
            .withPhone("8482424").withEmail("stefan@example.com").withBirthdate("1978/10/20")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withGender("FEMALE")
            .withPhone("8482131").withEmail("hans@example.com").withBirthdate("2009/09/09")
            .withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withGender(VALID_GENDER_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withBirthdate(VALID_BIRTHDATE_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FLU).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withGender(VALID_GENDER_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withBirthdate(VALID_BIRTHDATE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FEVER, VALID_TAG_FLU)
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
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static AddressBook getTypicalAddressBookWithIllness() {
        AddressBook ab = new AddressBook();
        String[] nameArray = {"ALICE", "BENSON", "CARL", "DANIEL", "ELLE", "FIONA", "GEORGE"};
        String[] illnessArray = {TYPICAL_ILLNESS_1, TYPICAL_ILLNESS_2, TYPICAL_ILLNESS_3, TYPICAL_ILLNESS_4,
            TYPICAL_ILLNESS_5, TYPICAL_ILLNESS_6};
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            PersonBuilder pb = new PersonBuilder();
            Person tempPerson = pb.withName(nameArray[counter]).withTags(illnessArray[counter]).build();
            ab.addPerson(tempPerson);
            counter++;
        }
        return ab;
    }
}
