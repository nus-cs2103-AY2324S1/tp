package seedu.staffsnap.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.commons.util.StringUtil;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Descriptor;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;

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
     * Capitalizes the first letter of each word.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return the new content with the first letter of each word capitalized.
     */
    public static String standardizeCapitalization(String content) {
        String[] tokens = content.split(" ");
        StringBuilder newContent = new StringBuilder();
        for (String token : tokens) {
            char capLetter = Character.toUpperCase(token.charAt(0));
            newContent.append(" ");
            newContent.append(capLetter);
            newContent.append(token.substring(1).toLowerCase());
        }
        return newContent.toString().trim();
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
        trimmedName = standardizeCapitalization(trimmedName);
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
     * Parses a {@code String position} into an {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code position} is invalid.
     */
    public static Position parsePosition(String position) throws ParseException {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        if (!Position.isValidPosition(trimmedPosition)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }
        trimmedPosition = standardizeCapitalization(trimmedPosition);
        return new Position(trimmedPosition);
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
        return new Email(trimmedEmail.toLowerCase());
    }

    /**
     * Parses a {@code String interview} into a {@code Interview}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interview} is invalid.
     */
    public static Interview parseInterview(String interview) throws ParseException {
        requireNonNull(interview);
        String trimmedInterview = interview.trim();
        if (!Interview.isValidType(trimmedInterview)) {
            throw new ParseException(Interview.MESSAGE_CONSTRAINTS);
        }
        return new Interview(trimmedInterview);
    }

    /**
     * Parses {@code Collection<String> interviews} into a {@code List<Interview>}.
     */
    public static List<Interview> parseInterviews(Collection<String> interviews) throws ParseException {
        requireNonNull(interviews);
        final List<Interview> interviewList = new ArrayList<>();
        for (String interviewType : interviews) {
            interviewList.add(parseInterview(interviewType));
        }
        return interviewList;
    }

    /**
     * Parses a {@code String descriptor} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     * Case-insensitive
     *
     * @throws ParseException if the given {@code descriptor} is invalid.
     */
    public static Descriptor parseDescriptor(String descriptor) throws ParseException {
        requireNonNull(descriptor);
        String trimmedDescriptor = descriptor.trim();
        Descriptor result = Descriptor.findByName(trimmedDescriptor);
        if (result == null) {
            throw new ParseException(Descriptor.MESSAGE_CONSTRAINTS);
        }
        return result;
    }

    /**
     * Parses a {@code String type} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static String parseType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!Interview.isValidType(trimmedType)) {
            throw new ParseException(Interview.MESSAGE_CONSTRAINTS);
        }
        return trimmedType.toLowerCase();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
