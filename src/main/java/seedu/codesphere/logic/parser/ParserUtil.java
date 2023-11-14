package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.commons.util.StringUtil;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.model.student.Email;
import seedu.codesphere.model.student.Field;
import seedu.codesphere.model.student.Name;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.SortCriteria;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

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
     * Parses a {@code String courseName} into a {@code courseName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code courseName} is invalid.
     */
    public static CourseName parseCourseName(String courseName) throws ParseException {
        requireNonNull(courseName);
        String trimmedCourseName = courseName.trim();
        if (!CourseName.isValidCourseName(trimmedCourseName)) {
            throw new ParseException(CourseName.MESSAGE_CONSTRAINTS);
        }
        return new CourseName(trimmedCourseName);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String formattedEmail = email.trim().toLowerCase();
        if (!Email.isValidEmail(formattedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(formattedEmail);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (trimmedRemark.isEmpty()) {
            throw new ParseException(RemarkCommandParser.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String pendingQuestion} into an {@code pendingQuestion}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static PendingQuestion parsePendingQuestion(String pendingQuestion) throws ParseException {
        requireNonNull(pendingQuestion);
        String trimmedPendingQuestion = pendingQuestion.trim();
        if (trimmedPendingQuestion.isEmpty()) {
            throw new ParseException(PendingQuestionCommandParser.MESSAGE_CONSTRAINTS);
        }
        return new PendingQuestion(trimmedPendingQuestion);
    }

    /**
     * Parses a {@code StudentRank tag} into a {@code Tag}.
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
        if (trimmedTag.toUpperCase().equals(StudentRank.GOOD.toString())) {
            return new Tag(StudentRank.GOOD);
        } else if (trimmedTag.toUpperCase().equals(StudentRank.POOR.toString())) {
            return new Tag(StudentRank.POOR);
        } else if (trimmedTag.toUpperCase().equals(StudentRank.AVERAGE.toString())) {
            return new Tag(StudentRank.AVERAGE);
        } else {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS_ENUMS);
        }
    }

    /**
     * Parses a {@code String sortCriteria} into a {@code SortCriteria}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortCriteria} is invalid.
     */
    public static SortCriteria parseSortCriteria(String sortCriteria) throws ParseException {
        String trimmedSortCriteria = sortCriteria.trim();

        if (trimmedSortCriteria.toUpperCase().equals(Field.TAG.toString())) {
            return new SortCriteria(Field.TAG);
        } else if (trimmedSortCriteria.toUpperCase().equals(Field.NAME.toString())) {
            return new SortCriteria(Field.NAME);
        } else {
            throw new ParseException(SortCriteria.MESSAGE_CONSTRAINTS_ENUMS);
        }
    }
}
