package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LICENCE_PLATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LICENCE_PLATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.policy.PolicyExpirationDateComparator;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withTags("friends").withNric("000A").withLicencePlate("SBA1234A").withRemark("")
            .withPolicy("NTUC", "AIA1", "01-01-2020", "01-02-2020").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withNric("001B").withLicencePlate("SBA5555B").withRemark("")
            .withPolicy("InsureMe", "AIA2", "12-12-2010", "12-12-2020").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withNric("123C").withLicencePlate("SLR8E")
            .withPolicy("InsureMe", "5734534X", "31-12-2019", "01-02-2020").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withNric("464J").withLicencePlate("STG46P")
            .withPolicy("NTUC", "4532", "29-02-2004", "29-02-2008").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822244")
            .withEmail("werner@example.com").withAddress("michegan ave").withNric("573H").withLicencePlate("SHL463C")
            .withPolicy("!@#NO_COMPANY!@#", "NOPOLICY", "01-01-1000", "01-01-1000").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824277")
            .withEmail("lydia@example.com").withAddress("little tokyo").withNric("743G").withLicencePlate("SBA1Z")
            .withPolicy("!@#NO_COMPANY!@#", "NOPOLICY", "01-01-1000", "01-01-1000").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824422")
            .withEmail("anna@example.com").withAddress("4th street").withNric("674G").withLicencePlate("SNM9735R")
            .withPolicy("!@#NO_COMPANY!@#", "NOPOLICY", "01-01-1000", "01-01-1000").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824244")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821311")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withNric(VALID_NRIC_AMY).withLicencePlate(VALID_LICENCE_PLATE_AMY)
            .withPolicy(
                VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY,
                VALID_POLICY_EXPIRY_DATE_AMY
            )
        .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withNric(VALID_NRIC_BOB).withLicencePlate(VALID_LICENCE_PLATE_BOB)
            .withPolicy(
                VALID_COMPANY_BOB,
                VALID_POLICY_NO_BOB,
                VALID_POLICY_ISSUE_DATE_BOB,
                VALID_POLICY_EXPIRY_DATE_BOB
            )
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

    /**
     * Returns an {@code AddressBook} with all the typical persons in sorted order.
     */
    public static AddressBook getTypicalSortedAddressBook() {
        AddressBook ab = new AddressBook();
        List<Person> listOfPeople = getTypicalPersons();
        listOfPeople.sort(new PolicyExpirationDateComparator());
        for (Person person : listOfPeople) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
