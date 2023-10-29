package connexion.testutil;

import static connexion.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static connexion.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static connexion.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static connexion.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static connexion.logic.commands.CommandTestUtil.VALID_JOB_AMY;
import static connexion.logic.commands.CommandTestUtil.VALID_JOB_BOB;
import static connexion.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static connexion.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static connexion.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static connexion.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static connexion.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static connexion.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connexion.model.AddressBook;
import connexion.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withCompany("Google").withJob("AI Analyst")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withCompany("Mandai Wildlife Group").withJob("Software Engineer")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .withMark(true)
            .withNote("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                    + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com")
            .withCompany("Grab").withJob("AI Engineer")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .withMark(true)
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withCompany("Uber").withJob("Data Analyst")
            .withTags("friends")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com")
            .withCompany("Central Provident Board").withJob("Machine Learning Analyst")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withCompany("Citadel")
            .withJob("AI Engineer")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withCompany("Morgan Stanley")
            .withJob("Risk Analyst")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .withMark(true)
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withCompany("Google")
            .withJob("Software Developer")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withCompany("Google")
            .withJob("Software Developer")
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withCompany(VALID_COMPANY_AMY)
            .withJob(VALID_JOB_AMY).withTags(VALID_TAG_FRIEND)
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withCompany(VALID_COMPANY_BOB)
            .withJob(VALID_JOB_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withLastModifiedDateTime(PersonBuilder.DEFAULT_LAST_MODIFIED)
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
}
