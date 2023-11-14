package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_TO_STRING_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_TO_STRING_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_TO_STRING_VALID_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_TO_STRING_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_TO_STRING_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_TO_STRING_VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_TO_STRING_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_TO_STRING_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_TO_STRING_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_TO_STRING_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_TO_STRING_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_TO_STRING_VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CONTACT_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CONTACT_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_PROFESSOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_CONTACT_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_3;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CHARLIE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;
import seedu.address.testutil.PersonBuilder;

public class ListCommandTest {
    private Model model;
    private Model expectedModel;
    private Model dummyModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        dummyModel = new ModelManager();
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), dummyModel, ListCommand.MESSAGE_SUCCESS);
    }

    @Test
    public void roleToString_oneRole_success() {
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(new Role(VALID_ROLE_STUDENT));
        String expected = ROLE_TO_STRING_STUDENT;
        String result = ListCommand.roleToString(roles);
        assertEquals(expected, result);
    }

    @Test
    public void roleToString_multipleRoles_success() {
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(new Role(VALID_ROLE_STUDENT));
        roles.add(new Role(VALID_ROLE_TA));
        roles.add(new Role(VALID_ROLE_PROFESSOR));
        String expected = ROLE_TO_STRING_MULTIPLE;
        String result = ListCommand.roleToString(roles);
        assertEquals(expected, result);
    }

    @Test
    public void roleToString_emptyRoleList_success() {
        Set<Role> roles = new LinkedHashSet<>();
        String expected = ROLE_TO_STRING_EMPTY;
        String result = ListCommand.roleToString(roles);
        assertEquals(expected, result);
    }


    @Test
    public void courseToString_oneCourse_success() {
        Set<Course> courses = new LinkedHashSet<>();
        courses.add(VALID_COURSE_1);
        String expected = COURSE_TO_STRING_VALID_COURSE_1;
        String result = ListCommand.courseToString(courses);
        assertEquals(expected, result);
    }

    @Test
    public void courseToString_multipleCourses_success() {
        Set<Course> courses = new LinkedHashSet<>();
        courses.add(VALID_COURSE_1);
        courses.add(VALID_COURSE_2);
        courses.add(VALID_COURSE_3);
        String expected = COURSE_TO_STRING_MULTIPLE;
        String result = ListCommand.courseToString(courses);
        assertEquals(expected, result);
    }

    @Test
    public void courseToString_emptyCourseList_success() {
        Set<Course> courses = new LinkedHashSet<>();
        String expected = COURSE_TO_STRING_EMPTY;
        String result = ListCommand.courseToString(courses);
        assertEquals(expected, result);
    }

    @Test
    public void contactToString_oneContact_success() {
        Set<Contact> contacts = new LinkedHashSet<>();
        contacts.add(new Contact(VALID_CONTACT_AMY));
        String expected = CONTACT_TO_STRING_VALID_CONTACT_AMY;
        String result = ListCommand.contactToString(contacts);
        assertEquals(expected, result);
    }

    @Test
    public void contactToString_multipleContacts_success() {
        Set<Contact> contacts = new LinkedHashSet<>();
        contacts.add(new Contact(VALID_EMAIL_CONTACT_CHARLIE));
        contacts.add(new Contact(VALID_TELE_CONTACT_CHARLIE));
        contacts.add(new Contact(VALID_PHONE_CONTACT_CHARLIE));
        String expected = CONTACT_TO_STRING_MULTIPLE;
        String result = ListCommand.contactToString(contacts);
        assertEquals(expected, result);
    }

    @Test
    public void contactToString_emptyContactList_success() {
        Set<Contact> contacts = new LinkedHashSet<>();
        String expected = CONTACT_TO_STRING_EMPTY;
        String result = ListCommand.contactToString(contacts);
        assertEquals(expected, result);
    }

    @Test
    public void tutorialToString_singleTutorial_success() {
        Set<Tutorial> tutorials = new LinkedHashSet<>();
        tutorials.add(VALID_TUTORIAL_1);
        String expected = TUTORIAL_TO_STRING_VALID_TUTORIAL_1;
        String result = ListCommand.tutorialToString(tutorials);
        assertEquals(expected, result);
    }

    @Test
    public void tutorialToString_multipleTutorials_success() {
        Set<Tutorial> tutorials = new LinkedHashSet<>();
        tutorials.add(VALID_TUTORIAL_1);
        tutorials.add(VALID_TUTORIAL_2);
        tutorials.add(VALID_TUTORIAL_3);
        String expected = TUTORIAL_TO_STRING_MULTIPLE;
        String result = ListCommand.tutorialToString(tutorials);
        assertEquals(expected, result);
    }

    @Test
    public void tutorialToString_emptyTutorialList_success() {
        Set<Tutorial> tutorials = new LinkedHashSet<>();
        String expected = TUTORIAL_TO_STRING_EMPTY;
        String result = ListCommand.tutorialToString(tutorials);
        assertEquals(expected, result);
    }

    @Test
    public void execute_emptyPersonList_success() {
        Model model = new ModelManager();
        CommandResult result = new ListCommand().execute(model);
        assertEquals(ListCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
    }

    @Test
    public void execute_onePersonInList_success() {
        Model model = new ModelManager();
        model.addPerson(new PersonBuilder(CHARLIE).build());
        CommandResult result = new ListCommand().execute(model);
        String expectedOutput = "You have 1 person in your list\n"
                + "1. Name: " + VALID_NAME_CHARLIE + "\n"
                + "   Role: \n" + "   Contact: \n" + "   Courses: \n" + "   Tutorials: \n";
        assertEquals(expectedOutput, result.getFeedbackToUser());
    }

    @Test
    public void execute_multiplePeopleInList_success() {
        Model model = new ModelManager();
        model.addPerson(AMY);
        model.addPerson(BOB);
        CommandResult result = new ListCommand().execute(model);
        String expectedOutput = "You have 2 persons in your list\n"
                + "1. Name: " + VALID_NAME_AMY + "\n"
                + "   Role: " + VALID_ROLE_STUDENT + "\n"
                + "   Contact: " + "[" + VALID_CONTACT_AMY + "]" + "\n"
                + "   Courses: " + VALID_COURSE_1.toString() + "\n"
                + "   Tutorials: " + VALID_TUTORIAL_1.toString() + "\n"
                + "2. Name: " + VALID_NAME_BOB + "\n"
                + "   Role: " + VALID_ROLE_TA + "\n"
                + "   Contact: " + "[" + VALID_CONTACT_BOB + "]" + "\n"
                + "   Courses: " + VALID_COURSE_2.toString() + "\n"
                + "   Tutorials: " + VALID_TUTORIAL_2.toString() + "\n";;
        assertEquals(expectedOutput, result.getFeedbackToUser());
    }
}
