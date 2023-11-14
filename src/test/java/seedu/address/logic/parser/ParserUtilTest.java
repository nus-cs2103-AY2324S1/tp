package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Name;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ROLE = "Teacher";
    private static final String INVALID_CONTACT = " ";
    private static final String INVALID_COURSE = " ";
    private static final String INVALID_TUTORIAL_1 = "/tutorial";
    private static final String INVALID_TUTORIAL_2 = "CS2103T/ F08";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_ROLE_1 = "TA";
    private static final String VALID_ROLE_2 = "Student";
    private static final String VALID_CONTACT_1 = "johndoe@example.com";
    private static final String VALID_CONTACT_2 = "@johndoee";
    private static final String VALID_COURSE_1 = "CS2103T";
    private static final String VALID_COURSE_2 = "CS2100";
    private static final String VALID_TUTORIAL_1 = "CS2103T/F08";
    private static final String VALID_TUTORIAL_2 = "CS2100/T30";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    // parseRole
    @Test
    public void parseRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRole((String) null));
    }

    @Test
    public void parseRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(INVALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithoutWhitespace_returnsRole() throws Exception {
        Role expectedRole = new Role(VALID_ROLE_1);
        assertEquals(expectedRole, ParserUtil.parseRole(VALID_ROLE_1));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE_1 + WHITESPACE;
        Role expectedPhone = new Role(VALID_ROLE_1);
        assertEquals(expectedPhone, ParserUtil.parseRole(roleWithWhitespace));
    }

    // parseRoles
    @Test
    public void parseRoles_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoles(null));
    }

    @Test
    public void parseRoles_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoles(Arrays.asList(VALID_ROLE_1, INVALID_ROLE)));
    }

    @Test
    public void parseRoles_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseRoles(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseRoles_collectionWithValidTags_returnsRoleSet() throws Exception {
        Set<Role> actualTagSet = ParserUtil.parseRoles(Arrays.asList(VALID_ROLE_1, VALID_ROLE_2));
        Set<Role> expectedTagSet = new HashSet<Role>(Arrays.asList(new Role(VALID_ROLE_1), new Role(VALID_ROLE_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    // parse contact
    @Test
    public void parseContact_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContact((String) null));
    }

    @Test
    public void parseContact_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContact(INVALID_CONTACT));
    }

    @Test
    public void parseContact_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Contact expectedAddress = new Contact(VALID_CONTACT_1);
        assertEquals(expectedAddress, ParserUtil.parseContact(VALID_CONTACT_1));
    }

    @Test
    public void parseContact_validValueWithWhitespace_returnsTrimmedContact() throws Exception {
        String contactWithWhitespace = WHITESPACE + VALID_CONTACT_1 + WHITESPACE;
        Contact expectedAddress = new Contact(VALID_CONTACT_1);
        assertEquals(expectedAddress, ParserUtil.parseContact(contactWithWhitespace));
    }

    // parse contacts
    @Test
    public void parseContacts_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContacts(null));
    }

    @Test
    public void parseContacts_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContacts(Arrays.asList(VALID_CONTACT_1,
                INVALID_CONTACT)));
    }

    @Test
    public void parseContacts_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseContacts(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseContacts_collectionWithValidTags_returnsContactSet() throws Exception {
        Set<Contact> actualTagSet = ParserUtil.parseContacts(Arrays.asList(VALID_CONTACT_1, VALID_CONTACT_2));
        Set<Contact> expectedTagSet = new HashSet<Contact>(Arrays.asList(new Contact(VALID_CONTACT_1),
                new Contact(VALID_CONTACT_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    // parse course
    @Test
    public void parseCourse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCourse((String) null));
    }

    @Test
    public void parseCourse_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCourse(INVALID_COURSE));
    }

    @Test
    public void parseCourse_validValueWithoutWhitespace_returnsCourse() throws Exception {
        Course expectedCourse = new Course(VALID_COURSE_1);
        assertEquals(expectedCourse, ParserUtil.parseCourse(VALID_COURSE_1));
    }

    @Test
    public void parseCourse_validValueWithWhitespace_returnsTrimmedCourse() throws Exception {
        String courseWithWhitespace = WHITESPACE + VALID_COURSE_1 + WHITESPACE;
        Course expectedEmail = new Course(VALID_COURSE_1);
        assertEquals(expectedEmail, ParserUtil.parseCourse(courseWithWhitespace));
    }

    // parse courses
    @Test
    public void parseCourses_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCourses(null));
    }

    @Test
    public void parseCourses_collectionWithInvalidCourses_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCourses(Arrays.asList(VALID_COURSE_1,
                INVALID_COURSE)));
    }

    @Test
    public void parseCourses_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseCourses(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseCourses_collectionWithValidTags_returnsCourseSet() throws Exception {
        Set<Course> actualTagSet = ParserUtil.parseCourses(Arrays.asList(VALID_COURSE_1, VALID_COURSE_2));
        Set<Course> expectedTagSet = new HashSet<Course>(Arrays.asList(new Course(VALID_COURSE_1),
                new Course(VALID_COURSE_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    // parse tutorials

    @Test
    public void parseTutorialsSetCourse_nullCourseSet_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTutorials((Set<Course>) null));
    }

    @Test
    public void parseTutorialsSetCourse_emptyCourseSet_success() throws ParseException {
        Set<Course> courseList = new HashSet<>();
        Set<Tutorial> expectedTutorials = new HashSet<>();
        assertEquals(expectedTutorials, ParserUtil.parseTutorials(courseList));
    }

    @Test
    public void parseTutorialsSetCourse_setWithInvalidTutorial_throwParseException() throws ParseException {

        Set<Course> invalidTutorials = new HashSet<>();
        invalidTutorials.add(new Course(INVALID_TUTORIAL_2));

        assertThrows(ParseException.class, () -> ParserUtil.parseTutorials(invalidTutorials));
    }

    @Test
    public void parseTutorialsSetCourse_setWithValidTutorial_success() throws ParseException {
        Set<Course> courseList = new HashSet<>();
        courseList.add(new Course(VALID_TUTORIAL_1));
        courseList.add(new Course(VALID_TUTORIAL_2));

        Set<Tutorial> expectedTutorials = new HashSet<>();
        expectedTutorials.add(new Tutorial(new Course(VALID_COURSE_1), VALID_TUTORIAL_1));
        expectedTutorials.add(new Tutorial(new Course(VALID_COURSE_2), VALID_TUTORIAL_2));

        assertEquals(expectedTutorials, ParserUtil.parseTutorials(courseList));
    }

    @Test
    public void parseTutorialSetCourse_validTutorialStringWithNoMatchingCourse_throwsParseException() {
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(CommandTestUtil.VALID_COURSE_1);

        String validTutorialString = VALID_TUTORIAL_2.toString();
        assertThrows(ParseException.class, () -> ParserUtil.parseTutorial(courseSet, validTutorialString));
    }

    @Test
    public void parseTutorialsCollection_nullCollection_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTutorials((Collection<String>) null));
    }

    @Test
    public void parseTutorialsCollection_emptyCollection_success() throws ParseException {
        assertTrue(ParserUtil.parseTutorials(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTutorialsCollection_collectionWithInvalidTutorial_throwParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTutorials(Arrays.asList(VALID_TUTORIAL_1,
                INVALID_TUTORIAL_2)));
    }

    @Test
    public void parseTutorialsCollection_collectionWithValidTutorial_success() throws ParseException {
        Collection<String> courseCollection = new HashSet<>();
        courseCollection.add(VALID_TUTORIAL_1);
        courseCollection.add(VALID_TUTORIAL_2);

        Set<Tutorial> expectedTutorials = new HashSet<>();
        expectedTutorials.add(new Tutorial(new Course(VALID_COURSE_1), VALID_TUTORIAL_1));
        expectedTutorials.add(new Tutorial(new Course(VALID_COURSE_2), VALID_TUTORIAL_2));

        assertEquals(expectedTutorials, ParserUtil.parseTutorials(courseCollection));
    }

    // Added some tests for parse tutorial, can add more if there are any
    /*
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
    */
}
