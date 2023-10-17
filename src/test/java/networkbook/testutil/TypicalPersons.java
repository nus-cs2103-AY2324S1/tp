package networkbook.testutil;

import static networkbook.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_GRADUATING_YEAR_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_GRADUATING_YEAR_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_LINK_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_LINK_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_SPECIALISATION_AMY;
import static networkbook.logic.commands.CommandTestUtil.VALID_SPECIALISATION_BOB;
import static networkbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static networkbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import networkbook.model.NetworkBook;
import networkbook.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmails(List.of("alice@example.com"))
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmails(List.of("johnd@example.com"))
            .withPhone("98765432")
            .withLinks(List.of("www.facebook.com/john-d"))
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmails(List.of("heinz@example.com")).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmails(List.of("cornelia@example.com"))
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmails(List.of("werner@example.com")).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmails(List.of("lydia@example.com")).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmails(List.of("anna@example.com")).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmails(List.of("stefan@example.com")).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmails(List.of("hans@example.com")).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmails(List.of(VALID_EMAIL_AMY)).withLinks(List.of(VALID_LINK_AMY))
            .withGraduatingYear(VALID_GRADUATING_YEAR_AMY)
            .withCourse(VALID_COURSE_AMY).withSpecialisation(VALID_SPECIALISATION_AMY).withTags(VALID_TAG_FRIEND)
            .withPriority("High").build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmails(List.of(VALID_EMAIL_BOB)).withLinks(List.of(VALID_LINK_BOB))
            .withGraduatingYear(VALID_GRADUATING_YEAR_BOB)
            .withCourse(VALID_COURSE_BOB).withSpecialisation(VALID_SPECIALISATION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code NetworkBook} with all the typical persons.
     */
    public static NetworkBook getTypicalNetworkBook() {
        NetworkBook ab = new NetworkBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
