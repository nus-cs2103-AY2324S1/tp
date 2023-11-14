package connectify.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connectify.logic.commands.CommandTestUtil;
import connectify.model.AddressBook;
import connectify.model.company.Company;
import connectify.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withNote("").withTags("friends").withPriority("1")
            .withParentCompany(TypicalCompanies.COMPANY_1).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withNote("")
            .withParentCompany(TypicalCompanies.COMPANY_1)
            .withTags("owesMoney", "friends").withPriority("2").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withPriority("3")
            .withParentCompany(TypicalCompanies.COMPANY_2).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withPriority("4").withTags("friends")
            .withParentCompany(TypicalCompanies.COMPANY_2).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withPriority("5")
            .withParentCompany(TypicalCompanies.COMPANY_3).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withPriority("6")
            .withParentCompany(TypicalCompanies.COMPANY_3).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("best@example.com").withAddress("4th street").withPriority("7")
            .withParentCompany(TypicalCompanies.COMPANY_3).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withPriority("7")
            .withParentCompany(TypicalCompanies.COMPANY_1).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withPriority("8")
            .withParentCompany(TypicalCompanies.COMPANY_1).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
            .withPhone(CommandTestUtil.VALID_PHONE_AMY).withEmail(CommandTestUtil.VALID_EMAIL_AMY)
            .withAddress(CommandTestUtil.VALID_ADDRESS_AMY).withTags(CommandTestUtil.VALID_TAG_FRIEND)
            .withPriority(CommandTestUtil.VALID_PRIORITY_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
            .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_BOB)
            .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND,
                    CommandTestUtil.VALID_TAG_FRIEND).withPriority(CommandTestUtil.VALID_PRIORITY_BOB).build();

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
        Company company1 = TypicalCompanies.COMPANY_1;
        company1 = company1.addPersonToCompany(ALICE);
        company1 = company1.addPersonToCompany(BENSON);
        ab.addCompany(company1);

        Company company2 = TypicalCompanies.COMPANY_2;
        company2 = company2.addPersonToCompany(CARL);
        company2 = company2.addPersonToCompany(DANIEL);
        ab.addCompany(company2);
        Company company3 = TypicalCompanies.COMPANY_3;
        company3 = company3.addPersonToCompany(ELLE);
        company3 = company3.addPersonToCompany(FIONA);
        company3 = company3.addPersonToCompany(GEORGE);
        ab.addCompany(company3);
        return ab;
    }

    public static AddressBook getRankedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getRankedPersons()) {
            ab.addPerson(person);
        }
        Company company1 = TypicalCompanies.COMPANY_1;
        company1 = company1.addPersonToCompany(ALICE);
        company1 = company1.addPersonToCompany(BENSON);
        ab.addCompany(company1);

        Company company2 = TypicalCompanies.COMPANY_2;
        company2 = company2.addPersonToCompany(CARL);
        company2 = company2.addPersonToCompany(DANIEL);
        ab.addCompany(company2);
        Company company3 = TypicalCompanies.COMPANY_3;
        company3 = company3.addPersonToCompany(ELLE);
        company3 = company3.addPersonToCompany(FIONA);
        company3 = company3.addPersonToCompany(GEORGE);
        ab.addCompany(company3);
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getRankedPersons() {
        return new ArrayList<>(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE));
    }
}
