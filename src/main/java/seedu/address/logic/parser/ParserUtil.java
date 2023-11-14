package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.Comment;
import seedu.address.model.fields.Tag;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;

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
     * Parses a {@code tutorial} into an {@code int} and returns it. Leading and trailing whitespaces will be trimmed.
     * @param tutorial String value of tut
     * @return int value of tut if valid
     * @throws ParseException if the given {@code tutorial} is invalid
     */
    public static int parseTutorial(String tutorial) throws ParseException {
        requireNonNull(tutorial);
        String trimmedTutorial = tutorial.trim();
        if (!Attendance.isValidTutorial(trimmedTutorial)) {
            throw new ParseException(Attendance.TUTORIAL_ERROR_MSG);
        }
        return Attendance.convertToIntegerWeek(trimmedTutorial);
    }

    /**
     * Parses a {@code pp} into an {@code String} and returns it. Leading and trailing whitespaces will be trimmed.
     * @param status String value of status
     * @return String value of status if valid
     * @throws ParseException if the given {@code status} is invalid
     */
    public static String parseParticipationStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Attendance.isValidStatus(trimmedStatus)) {
            throw new ParseException(Attendance.STATUS_ERROR_MSG);
        }
        return trimmedStatus;
    }

    /**
     * Parses a {@code pp} into an {@code int} and returns it. Leading and trailing whitespaces will be trimmed.
     * @param pp String value of participation score
     * @return int value of pp if valid
     * @throws ParseException if the given {@code pp} is invalid
     */
    public static int parseParticipationPoints(String pp) throws ParseException {
        requireNonNull(pp);
        String trimmedPP = pp.trim();
        if (!Attendance.isValidParticipation(trimmedPP)) {
            throw new ParseException(Attendance.PARTICIPATION_ERROR_MSG);
        }
        return Integer.parseInt(trimmedPP);
    }

    /**
     * Parses the specified string into an integer value, removing leading and trailing whitespaces.
     *
     * @param number A string representing an integer. Must not be null.
     * @return The parsed integer value.
     * @throws ParseException If the given {@code number} is not a valid integer format.
     */
    public static int parseInt(String number) throws ParseException {
        requireNonNull(number);
        String trimmedNumber = number.trim();
        try {
            return Integer.parseInt(trimmedNumber);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid number format.");
        }
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
     * Parses a {@code String address} into an {@code Address}.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String telegramHandle) throws ParseException {
        requireNonNull(telegramHandle);
        String trimmedTelegramHandle = telegramHandle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedTelegramHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedTelegramHandle);
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
     * Parses a {@code String tag} into a {@code Comment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code comment} is invalid.
     */
    public static Comment parseComment(String comment) throws ParseException {
        requireNonNull(comment);
        String trimmedComment = comment.trim();
        if (!Comment.isValidCommentName(trimmedComment)) {
            throw new ParseException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(trimmedComment);
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
     * Parses {@code Collection<String> comments} into a {@code Set<Comment>}.
     */
    public static Set<Comment> parseComments(Collection<String> comments) throws ParseException {
        requireNonNull(comments);
        final Set<Comment> commentSet = new HashSet<>();
        for (String commentName : comments) {
            commentSet.add(parseComment(commentName));
        }
        return commentSet;
    }

    /**
     * Parses {@code String group} into a {@code Group}.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        String trimmedGroup = group.trim();
        if (!Group.isValidGroup(trimmedGroup)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedGroup);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
