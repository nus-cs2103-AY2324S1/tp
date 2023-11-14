package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_CHARLIE_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_CHARLIE_TELE;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_TUTORIAL_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_TUTORIAL_DESC_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADD_COMMAND_MISSING_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC_PREAMBLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC_SYMBOL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DANNY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_MULTIPLE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_TA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CONTACT_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CONTACT_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_CONTACT_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_4;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.CHARLIE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // All fields present
        Person personWithAllFields = new PersonBuilder(CHARLIE)
                .withRoles(VALID_ROLE_STUDENT)
                .withContacts(VALID_TELE_CONTACT_CHARLIE)
                .withCourses(VALID_COURSE_1)
                .build();
        String userInput4 = NAME_DESC_CHARLIE + ROLE_DESC_STUDENT + CONTACT_DESC_CHARLIE_TELE + COURSE_DESC_1;
        assertParseSuccess(parser, userInput4, new AddCommand(personWithAllFields));
    }


    @Test
    public void parse_optionalFieldsMissing_success() {

        // Only name is provided
        Person personWithName = new PersonBuilder().withName(VALID_NAME_CHARLIE).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE, new AddCommand(personWithName));

        // Name and one role
        Person personWithOneRole = new PersonBuilder().withName(VALID_NAME_CHARLIE).withRoles(VALID_ROLE_TA).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + ROLE_DESC_TA,
                new AddCommand(personWithOneRole));

        // Name and one contact
        Person personWithOneContact = new PersonBuilder(CHARLIE).withContacts(VALID_PHONE_CONTACT_CHARLIE).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + CONTACT_DESC_CHARLIE_PHONE,
                new AddCommand(personWithOneContact));

        // Name and a course without tutorial
        Person personWithOneCourse = new PersonBuilder(CHARLIE).withCourses(VALID_COURSE_1).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + COURSE_DESC_1,
                new AddCommand(personWithOneCourse));

        // Name and a course with tutorial
        Person personWithCourseAndTutorial = new PersonBuilder(CHARLIE)
                .withCoursesAndTutorials(VALID_TUTORIAL_1)
                .build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + COURSE_TUTORIAL_DESC_1,
                new AddCommand(personWithCourseAndTutorial));
    }

    @Test
    public void parse_optionalFieldsWithMultiplesInput_success() {

        // Name and multiple roles
        Person personWithManyRoles = new PersonBuilder().withName(VALID_NAME_CHARLIE)
                .withRoles(VALID_ROLE_STUDENT, VALID_ROLE_TA).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + ROLE_DESC_MULTIPLE,
                new AddCommand(personWithManyRoles));

        // Name and many contacts
        Person personWithManyContacts = new PersonBuilder(CHARLIE).withContacts(VALID_PHONE_CONTACT_CHARLIE,
                VALID_EMAIL_CONTACT_CHARLIE, VALID_TELE_CONTACT_CHARLIE).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + CONTACT_DESC_MULTIPLE,
                new AddCommand(personWithManyContacts));

        // Name and many roles and many contacts
        Person manyRolesAndManyContacts = new PersonBuilder(CHARLIE)
                .withRoles(VALID_ROLE_TA, VALID_ROLE_STUDENT)
                .withContacts(VALID_EMAIL_CONTACT_CHARLIE, VALID_PHONE_CONTACT_CHARLIE, VALID_TELE_CONTACT_CHARLIE)
                .build();
        String userInput2 = NAME_DESC_CHARLIE + ROLE_DESC_MULTIPLE + CONTACT_DESC_MULTIPLE;
        assertParseSuccess(parser, userInput2, new AddCommand(manyRolesAndManyContacts));

        // Name and many courses without tutorial class
        Person personWithManyCourses = new PersonBuilder(CHARLIE)
                .withCourses(VALID_COURSE_1, VALID_COURSE_2, VALID_COURSE_3, VALID_COURSE_4).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + COURSE_DESC_MULTIPLE,
                new AddCommand(personWithManyCourses));

        // Name, many roles, many contacts and many courses without tutorial class
        Person manyRolesContactsCourses = new PersonBuilder(CHARLIE)
                .withRoles(VALID_ROLE_TA, VALID_ROLE_STUDENT)
                .withContacts(VALID_PHONE_CONTACT_CHARLIE, VALID_EMAIL_CONTACT_CHARLIE, VALID_TELE_CONTACT_CHARLIE)
                .withCourses(VALID_COURSE_1, VALID_COURSE_2, VALID_COURSE_3, VALID_COURSE_4)
                .build();
        String userInput3 = NAME_DESC_CHARLIE + ROLE_DESC_MULTIPLE + CONTACT_DESC_MULTIPLE + COURSE_DESC_MULTIPLE;
        assertParseSuccess(parser, userInput3, new AddCommand(manyRolesContactsCourses));

        // Name and many courses with tutorial class
        Person personWithManyCoursesAndTut = new PersonBuilder(CHARLIE)
                .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_2, VALID_TUTORIAL_3, VALID_TUTORIAL_4)
                .build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + COURSE_TUTORIAL_DESC_MULTIPLE,
                new AddCommand(personWithManyCoursesAndTut));

        // Name, many roles, many contacts and many courses without tutorial class
        Person manyRolesContactsCoursesWithTut = new PersonBuilder(CHARLIE)
                .withRoles(VALID_ROLE_TA, VALID_ROLE_STUDENT)
                .withContacts(VALID_PHONE_CONTACT_CHARLIE, VALID_EMAIL_CONTACT_CHARLIE, VALID_TELE_CONTACT_CHARLIE)
                .withCoursesAndTutorials(VALID_TUTORIAL_1, VALID_TUTORIAL_2, VALID_TUTORIAL_3, VALID_TUTORIAL_4)
                .build();
        String userInput4 = NAME_DESC_CHARLIE + ROLE_DESC_MULTIPLE + CONTACT_DESC_MULTIPLE
                + COURSE_TUTORIAL_DESC_MULTIPLE;
        assertParseSuccess(parser, userInput4, new AddCommand(manyRolesContactsCoursesWithTut));
    }

    @Test
    public void parse_addPersonWithRepeatedPrefix_failure() {

        String validPersonStringWithCourse = NAME_DESC_CHARLIE + ROLE_DESC_TA
                + CONTACT_DESC_CHARLIE_TELE + COURSE_DESC_1;

        //Adding a person that has multiple names
        assertParseFailure(parser, NAME_DESC_DANNY + validPersonStringWithCourse,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // Adding a person using more than one role prefix
        assertParseFailure(parser, ROLE_DESC_STUDENT + validPersonStringWithCourse,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // Adding a person using more than one contact prefix
        assertParseFailure(parser, CONTACT_DESC_CHARLIE_PHONE + validPersonStringWithCourse,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT));

        // Adding a person using more than one course prefix
        assertParseFailure(parser, COURSE_DESC_2 + validPersonStringWithCourse,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE));
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC_SYMBOL, Name.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, NAME_DESC_CHARLIE + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS);

        // invalid contact
        assertParseFailure(parser, NAME_DESC_CHARLIE + INVALID_CONTACT_DESC, Contact.MESSAGE_CONSTRAINTS);

        // invalid course
        assertParseFailure(parser, NAME_DESC_CHARLIE + INVALID_COURSE_DESC, Course.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        // Non-empty preamble
        String nonEmptyPreamble = INVALID_NAME_DESC_PREAMBLE;
        assertThrows(ParseException.class, () -> parser.parse(nonEmptyPreamble));
    }

    @Test
    public void parse_missingPrefixes_throwsParseException() {
        // Missing all required prefixes
        String missingPrefixes = INVALID_NAME_DESC_PREFIX;
        assertThrows(ParseException.class, () -> parser.parse(missingPrefixes));

        // Missing some required prefixes
        String missingSomePrefixes = INVALID_ADD_COMMAND_MISSING_PREFIX;
        assertThrows(ParseException.class, () -> parser.parse(missingSomePrefixes));
    }
}
