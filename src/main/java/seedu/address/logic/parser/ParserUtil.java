package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_GROUP_NUMBER = "Group number is not a non-zero unsigned integer.";

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
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String major} into a {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(trimmedMajor);
    }

    /**
     * Parses a {@code String year} into a {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Year.isValidYear(trimmedYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(trimmedYear);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String tutorial} into a {@code Tutorial}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorial} is invalid.
     */
    public static Tutorial parseTutorial(String tutorial) throws ParseException {
        requireNonNull(tutorial);
        String trimmedTutorial = tutorial.trim();
        if (!Tutorial.isValidTutorial(trimmedTutorial)) {
            throw new ParseException(Tutorial.MESSAGE_CONSTRAINTS);
        }
        return new Tutorial(trimmedTutorial);
    }

    /**
     * Parses {@code Collection<String> tutorials} into a {@code Set<Tutorial>}.
     */
    public static Set<Tutorial> parseTutorials(Collection<String> tutorials) throws ParseException {
        requireNonNull(tutorials);
        final Set<Tutorial> tutorialSet = new HashSet<>();
        for (String tut : tutorials) {
            tutorialSet.add(parseTutorial(tut));
        }
        return tutorialSet;
    }

    /**
     * Parses a {@code String socialMediaLink} into a {@code SocialMediaLink}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code socialMediaLink} is invalid.
     */
    public static SocialMediaLink parseSocialMediaLink(String socialMediaLink) throws ParseException {
        requireNonNull(socialMediaLink);
        String trimmedSocialMediaLink = socialMediaLink.trim();
        if (!SocialMediaLink.isValidSocialMediaLink(trimmedSocialMediaLink)) {
            throw new ParseException(SocialMediaLink.MESSAGE_CONSTRAINTS);
        }
        return new SocialMediaLink(trimmedSocialMediaLink);
    }

    /**
     * Parses a collection of social media links into a set of SocialMediaLink objects.
     *
     * @param socialMediaLinks A collection of social media links as a Collection of strings.
     * @return A Set of SocialMediaLink objects representing the parsed social media links.
     * @throws ParseException If there is an issue parsing the social media links.
     */
    public static Set<SocialMediaLink> parseSocialMediaLinks(Collection<String> socialMediaLinks)
            throws ParseException {
        requireNonNull(socialMediaLinks);
        final Set<SocialMediaLink> socialMediaLinkSet = new HashSet<>();
        for (String socialMedia : socialMediaLinks) {
            socialMediaLinkSet.add(parseSocialMediaLink(socialMedia));
        }
        return socialMediaLinkSet;
    }

    /**
     * Parses a {@code String nationality} into a {@code Nationality}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param nationality A string representing the nationality.
     * @return A {@code Nationality} object.
     * @throws ParseException If the given {@code nationality} is invalid.
     */
    public static Nationality parseNationality(String nationality) throws ParseException {
        requireNonNull(nationality);
        String trimmedNationality = nationality.trim();
        try {
            return new Nationality(trimmedNationality);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Nationality.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim().toLowerCase(); // Convert to lowercase for case-insensitive check
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses {@code groupNumber} into an {@code Integer} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified group number is invalid (not non-zero unsigned integer).
     */
    public static int parseGroupNumber(String groupNumber) throws ParseException {
        String trimmedIndex = groupNumber.trim();
        if (!Group.isValidGroupNumber(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_GROUP_NUMBER);
        }
        return Integer.parseInt(trimmedIndex);
    }

    /**
     * Parses a string into an integer task index.
     *
     * @param taskIndexString The string representation of the task index.
     * @return The parsed integer task index.
     * @throws ParseException if the specified task index is invalid (not a positive integer).
     */
    public static int parseTaskIndex(String taskIndexString) throws ParseException {
        String trimmedIndex = taskIndexString.trim();
        try {
            int taskIndex = Integer.parseInt(trimmedIndex);
            if (taskIndex <= 0) {
                throw new ParseException("Task index must be a positive integer.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid task index format.");
        }
    }
}
