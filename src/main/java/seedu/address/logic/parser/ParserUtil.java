package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attachment.Attachment;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.InterviewScore;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreviousGrade;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_PATH = "Please provide a valid file path.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String studentNo} into a {@code StudentNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static StudentNumber parseStudentNumber(String studentNo) throws ParseException {
        requireNonNull(studentNo);
        String trimmedStudentNo = studentNo.trim();
        if (!StudentNumber.isValidStudentNumber(trimmedStudentNo)) {
            throw new ParseException(StudentNumber.MESSAGE_CONSTRAINTS);
        }
        return new StudentNumber(trimmedStudentNo);
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
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String gpa} into a {@code Gpa}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gpa} is invalid.
     */
    public static Gpa parseGpa(String gpaString) throws ParseException {
        requireNonNull(gpaString);
        String trimmedGpaString = gpaString.trim();
        double gpa;
        try {
            gpa = Double.parseDouble(trimmedGpaString);
        } catch (NumberFormatException e) {
            throw new ParseException(Gpa.MESSAGE_CONSTRAINTS);
        }
        if (!Gpa.isValidGpa(gpa)) {
            throw new ParseException(Gpa.MESSAGE_CONSTRAINTS);
        }
        return new Gpa(gpa);
    }

    /**
     * Parses a {@code String previousGradeString} into a {@code PreviousGradeString}.
     *
     * @throws ParseException if the given {@code previousGradeString} is invalid.
     */
    public static PreviousGrade parsePreviousGrade(String previousGradeString) throws ParseException {
        requireNonNull(previousGradeString);
        if (!PreviousGrade.isValidGrade(previousGradeString)) {
            throw new ParseException(PreviousGrade.MESSAGE_CONSTRAINTS);
        }
        return new PreviousGrade(previousGradeString);
    }

    /**
     * Parses a {@code String interviewScoreString} into an {@code InterviewScore}.
     *
     * @throws ParseException if the given {@code interviewScoreString} is invalid.
     */
    public static InterviewScore parseInterviewScore(String interviewScoreString) throws ParseException {
        requireNonNull(interviewScoreString);
        String trimmedInterviewScoreString = interviewScoreString.trim();
        double interviewScore;
        try {
            interviewScore = Double.parseDouble(trimmedInterviewScoreString);
        } catch (NumberFormatException e) {
            throw new ParseException(InterviewScore.MESSAGE_CONSTRAINTS);
        }
        if (!InterviewScore.isValidInterviewScore(interviewScore)) {
            throw new ParseException(InterviewScore.MESSAGE_CONSTRAINTS);
        }
        return new InterviewScore(interviewScore);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Comment parseComment(String name) throws ParseException {
        requireNonNull(name);
        String trimmedComment = name.trim();
        if (!Comment.isValidComment(trimmedComment)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Comment(trimmedComment);
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String pathString} into a {@code Attachment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code pathString} is invalid.
     */
    public static Attachment parseAttachment(String pathString) throws ParseException {
        requireNonNull(pathString);
        String trimmedPathString = pathString.trim();
        if (!Attachment.isValidAttachment(pathString)) {
            throw new ParseException(MESSAGE_INVALID_PATH);
        }
        return new Attachment(trimmedPathString);
    }

    /**
     * Parses {@code Collection<String> pathStrings} into a
     * {@code List<Attachment>}.
     */
    public static List<Attachment> parseAttachments(Collection<String> pathStrings) throws ParseException {
        requireNonNull(pathStrings);
        final List<Attachment> pathList = new ArrayList<>();
        for (String pathString : pathStrings) {
            pathList.add(parseAttachment(pathString));
        }
        return pathList;
    }
}
