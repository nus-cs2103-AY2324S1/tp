package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ScoreList;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("intern")
            .withStatus("interviewed")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("role developer")
            .withRemark("Benson the builder")
            .withScoreList(new ScoreList())
            .withStatus("rejected")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("dept software").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}

    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    // Manually added - Person with "Interview" tag with a score attached to them
    public static final Person ALPHA = new PersonBuilder().withName("Alpha").withPhone("8482424")
            .withEmail("example1@example.com").withAddress("a")
            .withTags("assessment Interview").withInterviewScore(50).build();
    public static final Person BETA = new PersonBuilder().withName("Beta").withPhone("8482424")
            .withEmail("example2@example.com").withAddress("b")
            .withTags("assessment Interview").withInterviewScore(60).build();
    public static final Person CHARLIE = new PersonBuilder().withName("Charlie").withPhone("8482424")
            .withEmail("example3@example.com").withAddress("c")
            .withTags("assessment Interview").withInterviewScore(65).build();
    public static final Person DELTA = new PersonBuilder().withName("Delta").withPhone("8482424")
            .withEmail("example4@example.com").withAddress("d")
            .withTags("assessment Interview").withInterviewScore(70).build();
    public static final Person ECHO = new PersonBuilder().withName("Echo").withPhone("8482424")
            .withEmail("example5@example.com").withAddress("e")
            .withTags("assessment Interview").withInterviewScore(75).build();
    public static final Person FOXTROT = new PersonBuilder().withName("Foxtrot").withPhone("8482424")
            .withEmail("example6@example.com").withAddress("f")
            .withTags("assessment Interview").withInterviewScore(80).build();
    public static final Person GOLF = new PersonBuilder().withName("Golf").withPhone("8482424")
            .withEmail("example7@example.com").withAddress("g")
            .withTags("assessment Interview").withInterviewScore(85).build();
    public static final Person HOTEL = new PersonBuilder().withName("Hotel").withPhone("8482424")
            .withEmail("example8@example.com").withAddress("h")
            .withTags("assessment Interview").withInterviewScore(90).build();



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
     * Returns an {@code AddressBook} with all the typical persons with interview scores.
     * @return
     */
    public static AddressBook getTypicalAddressBookWithTagScores() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersonsWithTagScores()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersonsWithTagScores() {
        return new ArrayList<>(Arrays.asList(ALPHA, BETA, CHARLIE, DELTA, ECHO, FOXTROT, GOLF, HOTEL));
    }


    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static Person getTypicalTagPerson() {
        return BENSON;
    }

    public static Person getTypicalStatusPerson() {
        return BENSON;
    }

    public static Person getTypicalTagAndStatusPerson() {
        return BENSON;
    }
}
