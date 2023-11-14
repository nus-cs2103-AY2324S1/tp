package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANNY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_PROFESSOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    // ALICE --> all fields present, not favourited
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withRoles(VALID_ROLE_STUDENT).withContacts("alice@example.com")
            .withCoursesAndTutorials(VALID_TUTORIAL_1).withFavourite(false).build();

    // BENSON --> all fields present, multiple courses with tutorials, not favourited
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withRoles(VALID_ROLE_TA).withContacts("johnd@example.com")
            .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_2).withFavourite(false).build();

    // CARL --> courses and tutorials not present, not favourited
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withRoles(VALID_ROLE_PROFESSOR).withContacts("profcarl@example.com").withFavourite(false).build();

    // DANIEL --> courses and tutorials not present, favourited
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withRoles(VALID_ROLE_STUDENT).withContacts("cornelia@example.com").withFavourite(true).build();

    // ELLE --> multiple roles, multiple contacts, no courses or tutorials, not favourited
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withRoles(VALID_ROLE_TA, VALID_ROLE_STUDENT).withContacts("werner@example.com", "@werner1234")
            .withFavourite(false).build();

    // FIONA --> no tutorials present, not favourited
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withRoles(VALID_ROLE_STUDENT).withContacts("lydia@example.com").withCourses(VALID_COURSE_2)
            .withFavourite(false).build();

    // GEORGE --> multiple courses without tutorials, not favourited
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withRoles(VALID_ROLE_PROFESSOR).withContacts("anna@example.com")
            .withCourses(VALID_COURSE_1, VALID_COURSE_3, VALID_COURSE_4).withFavourite(false).build();

    // Manually added
    // HOON --> all fields present, favourited
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withRoles("Student").withContacts("stefan@example.com")
            .withCoursesAndTutorials(VALID_TUTORIAL_2).withFavourite(true).build();

    // IDA --> all fields present, not favourited
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withRoles("TA").withContacts("hans@example.com")
            .withCoursesAndTutorials(VALID_TUTORIAL_2).withFavourite(false).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    // AMY --> all fields present, not favourited
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withRoles(VALID_ROLE_STUDENT).withContacts(VALID_CONTACT_AMY)
            .withCoursesAndTutorials(VALID_TUTORIAL_1).withFavourite(false).build();

    // BOB --> all fields present, favourited
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withRoles(VALID_ROLE_TA).withContacts(VALID_CONTACT_BOB)
            .withCoursesAndTutorials(VALID_TUTORIAL_2).withFavourite(true).build();

    // CHARLIE --> name present only, not favourited
    public static final Person CHARLIE = new PersonBuilder().withName(VALID_NAME_CHARLIE)
            .withFavourite(false).build();
    public static final Person DANNY = new PersonBuilder().withName(VALID_NAME_DANNY)
            .withFavourite(false).build();

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
