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

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withRoles(VALID_ROLE_STUDENT).withContacts("alice@example.com")
            .withCourses(VALID_COURSE_1).withTutorials(VALID_TUTORIAL_1).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withRoles(VALID_ROLE_TA).withContacts("johnd@example.com")
            .withCourses(VALID_COURSE_1, VALID_COURSE_2)
            .withTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_2).build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withRoles(VALID_ROLE_PROFESSOR).withContacts("profcarl@example.com").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withRoles(VALID_ROLE_STUDENT).withContacts("cornelia@example.com").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withRoles(VALID_ROLE_TA, VALID_ROLE_STUDENT).withContacts("werner@example.com", "@werner1234")
            .withCourses(VALID_COURSE_4).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withRoles(VALID_ROLE_STUDENT).withContacts("lydia@example.com").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withRoles(VALID_ROLE_PROFESSOR).withContacts("anna@example.com")
            .withCourses(VALID_COURSE_1, VALID_COURSE_3).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withRoles("Student").withContacts("stefan@example.com")
            .withCourses(VALID_COURSE_1).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withRoles("TA").withContacts("hans@example.com")
            .withCourses(VALID_COURSE_2).withTutorials(VALID_TUTORIAL_2).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withRoles(VALID_ROLE_STUDENT).withContacts(VALID_CONTACT_AMY)
            .withCourses(VALID_COURSE_1).withTutorials(VALID_TUTORIAL_1).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withRoles(VALID_ROLE_TA).withContacts(VALID_CONTACT_BOB)
            .withCourses(VALID_COURSE_2).withTutorials(VALID_TUTORIAL_2).build();
    public static final Person CHARLIE = new PersonBuilder().withName(VALID_NAME_CHARLIE).build();

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
