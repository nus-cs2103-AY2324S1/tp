package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Name;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRoleType(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses {@code Collection<String> roles} into a {@code Set<Role>}.
     */
    public static Set<Role> parseRoles(Collection<String> roles) throws ParseException {
        requireNonNull(roles);
        final Set<Role> roleSet = new HashSet<>();
        for (String roleNames : roles) {
            String[] roleNameSplit = roleNames.split(Role.PARSE_ROLE_DELIMITER);

            for (String roleName : roleNameSplit) {
                if (!roleName.trim().isEmpty()) {
                    roleSet.add(parseRole(roleName));
                } else {
                    throw new ParseException(Role.MESSAGE_CONSTRAINTS);
                }
            }
        }
        return roleSet;
    }

    /**
     * Parses a {@code String contact} into a {@code Contact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code contact} is invalid.
     */
    public static Contact parseContact(String contact) throws ParseException {
        requireNonNull(contact);
        String trimmedContact = contact.trim();
        if (!Contact.isValidContactName(trimmedContact)) {
            throw new ParseException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(trimmedContact);
    }

    /**
     * Parses {@code Collection<String> contacts} into a {@code Set<Contact>}.
     */
    public static Set<Contact> parseContacts(Collection<String> contacts) throws ParseException {
        requireNonNull(contacts);
        final Set<Contact> contactSet = new HashSet<>();
        for (String contactName : contacts) {
            String[] splitContacts = contactName.split(Contact.PARSE_CONTACT_DELIMITER);
            for (String splitContact : splitContacts) {
                if (!splitContact.trim().isEmpty()) {
                    contactSet.add(parseContact(splitContact));
                } else {
                    throw new ParseException(Contact.MESSAGE_CONSTRAINTS);
                }
            }
        }
        return contactSet;
    }

    /**
     * Parses a {@code String course} into a {@code Course}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code course} is invalid.
     */
    public static Course parseCourse(String course) throws ParseException {
        requireNonNull(course);
        String trimmedCourse = course.trim();
        String[] splitTrimmedCourse = Course.splitCourseName(trimmedCourse);
        String courseCode = splitTrimmedCourse[0];
        if (!Course.isValidCourseName(courseCode)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(courseCode);
    }

    /**
     * Parses {@code Collection<String> courses} into a {@code Set<Course>}.
     */
    public static Set<Course> parseCourses(Collection<String> courses) throws ParseException {
        requireNonNull(courses);
        final Set<Course> courseSet = new HashSet<>();
        for (String courseNames : courses) {
            String[] splitCourse = courseNames.split(Course.PARSE_COURSE_DELIMITER);
            for (String courseName : splitCourse) {
                if (!courseName.trim().isEmpty()) {
                    courseSet.add(parseCourse(courseName));
                } else {
                    throw new ParseException(Course.MESSAGE_CONSTRAINTS);
                }
            }
        }
        return courseSet;
    }

    /**
     * Parses {@code Collection<String> coursesCollection} into a {@code Set<Tutorial>}.
     * The courseCollection will contain the full course input that has
     * yet be split into course code and tutorial class
     */
    public static Set<Course> parseCourseInput(Collection<String> courseCollection) throws ParseException {
        requireNonNull(courseCollection);
        final Set<Course> courseSet = new HashSet<>();
        for (String course : courseCollection) {
            String[] splitCourseInputs = course.split(Course.PARSE_COURSE_DELIMITER);
            for (String splitCourseInput : splitCourseInputs) {
                String[] splitCourseTutorial = Course.splitCourseName(splitCourseInput);
                int courseTutorialLength = splitCourseTutorial.length;
                if (courseTutorialLength == 2) {
                    Course stringToCourse = new Course(splitCourseInput);
                    courseSet.add(stringToCourse);
                } else if (courseTutorialLength > 2) {
                    throw new ParseException(AddCommand.MESSAGE_USAGE);
                }
            }
        }
        return courseSet;
    }

    /**
     * Parses a {@code String tutorial} into a {@code Tutorial}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorial} is invalid.
     */
    public static Tutorial parseTutorial(Set<Course> courseSet, String tutorialString) throws ParseException {
        requireNonNull(tutorialString);
        String trimmedTutorialString = tutorialString.trim();
        if (!Tutorial.isValidTutorialString(trimmedTutorialString)) {
            throw new ParseException(Tutorial.MESSAGE_CONSTRAINTS);
        }

        // Check if in the format of COURSECODE/TUTORIALCODE
        String[] courseTutorialName = Tutorial.splitCourseTutorialName(trimmedTutorialString);
        if (courseTutorialName == null || courseTutorialName.length <= 0) {
            throw new ParseException(Tutorial.MESSAGE_CONSTRAINTS);
        }

        // Get the relevant course if it exists.
        Optional<Course> relevantCourse = Tutorial.findMatchingCourse(courseSet, trimmedTutorialString);
        Tutorial parsedTutorial = relevantCourse.map((course) -> {
            return new Tutorial(course, trimmedTutorialString);
        }).orElseThrow(() -> {
            // Prepare the error message; we should format the invalid course message with
            // the concatenation of all course strings.
            String allCoursesString = courseSet.stream()
                .map((course) -> course.getCourseName())
                .reduce("", (current, next) -> current + next.toString() + "  ");
            return new ParseException(String.format(Tutorial.INVALID_COURSE_MESSAGE,
                    courseTutorialName[0], allCoursesString));
        });
        return parsedTutorial;
    }

    /**
     * Parses {@code Collection<String> tutorials} into a {@code Set<Tutorial>}.
     */
    public static Set<Tutorial> parseTutorials(Set<Course> courseList) throws ParseException {
        requireNonNull(courseList);
        final Set<Tutorial> tutorialSet = new HashSet<>();
        for (Course courseName : courseList) {
            String[] splitCourseAndTutorial = Course.splitCourseName(courseName.toString());
            if (splitCourseAndTutorial.length == 2) {
                tutorialSet.add(parseTutorial(courseList, courseName.toString()));
            }
        }
        return tutorialSet;
    }

    /**
     * Parses {@code Collection<String> courseCollection} into a {@code Set<Tutorial>}.
     */
    public static Set<Tutorial> parseTutorials(Collection<String> courseCollection) throws ParseException {
        requireNonNull(courseCollection);
        Set<Course> courseSet = parseCourseInput(courseCollection);
        Set<Tutorial> tutSet = parseTutorials(courseSet);
        return tutSet;
    }
}
