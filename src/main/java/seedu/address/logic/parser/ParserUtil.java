package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.IllegalArgumentException;
import seedu.address.logic.sort.enums.SortOrderEnum;
import seedu.address.logic.sort.enums.SortTypeEnum;
import seedu.address.model.member.Member;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Note;
import seedu.address.model.task.Priority;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "The index you enter in should be a positive number "
            + "starting from 1!";
    private static final String SPLIT_WHITESPACE = " ";
    private static final String SPLIT_DATE = "\\/|-";
    private static final String SPLIT_TIME = "-|:";
    private static final String REGEX_TIME = "\\d{4}";
    private static final String DEFAULT_TIME = "T00:00:00";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws IllegalArgumentException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws IllegalArgumentException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IllegalArgumentException(MESSAGE_INVALID_INDEX);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws IllegalArgumentException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new IllegalArgumentException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws IllegalArgumentException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new IllegalArgumentException(seedu.address.model.task.Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     * @param deadline A string containing the deadline.
     * @throws IllegalArgumentException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws IllegalArgumentException {
        requireNonNull(deadline);

        deadline = deadline.strip();
        if (Deadline.isValidDateTime(deadline)) {
            return parseDateTime(deadline);
        } else if (Deadline.isValidDate(deadline)) {
            return parseDate(deadline);
        }

        throw new IllegalArgumentException(Deadline.INVALID_DATE);
    }

    /**
     * Parses a {@code String deadline} specified to be in a date and time format into a {@code Deadline}.
     * @param deadline A string containing the date and time of the deadline.
     * @return A Deadline with the specified date and time.
     * @throws IllegalArgumentException
     */
    public static Deadline parseDateTime(String deadline) throws IllegalArgumentException {
        //@@author asdfghjkxd-reused
        // Regex strings are reused with major modification from ChatGPT, and is built and tested with
        // https://regex101.com/.
        String[] dateTimeSplit = deadline.split(SPLIT_WHITESPACE);
        String[] parsedDate = dateTimeSplit[0].split(SPLIT_DATE);
        boolean isMatchingRegex = Pattern.matches(REGEX_TIME, dateTimeSplit[1]);
        //@@author

        String[] parsedTime = isMatchingRegex
                ? new String[]{dateTimeSplit[1].substring(0, 2), dateTimeSplit[1].substring(2, 4)}
                : dateTimeSplit[1].split(SPLIT_TIME);
        if (parsedDate[1].length() < 2 || parsedDate[0].length() < 2
                || parsedTime[0].length() < 2 || parsedTime[1].length() < 2) {
            throw new IllegalArgumentException(Deadline.MESSAGE_CONSTRAINTS);
        }

        try {
            LocalDate.parse(parsedDate[2] + "-" + parsedDate[1] + "-" + parsedDate[0]);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(Deadline.INVALID_DATE);
        }

        return Deadline.of(LocalDateTime.parse(parsedDate[2] + "-" + parsedDate[1] + "-" + parsedDate[0] + "T"
                + parsedTime[0] + ":" + parsedTime[1] + ":00"));
    }

    /**
     * Parses a {@code String deadline} specified to be in a date only format into a {@code Deadline}.
     * @param deadline A string containing the date of the deadline.
     * @return A Deadline with the specified date and time
     * @throws IllegalArgumentException
     */
    public static Deadline parseDate(String deadline) throws IllegalArgumentException {
        String[] date = deadline.split(SPLIT_DATE);
        if (date[1].length() < 2 || date[0].length() < 2) {
            throw new IllegalArgumentException(Deadline.MESSAGE_CONSTRAINTS);
        }

        try {
            LocalDate.parse(date[2] + "-" + date[1] + "-" + date[0]);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(Deadline.INVALID_DATE);
        }

        return Deadline.of(LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + DEFAULT_TIME));
    }

    /**
     * Parses a {@code String memberName} into a {@code Member}.
     * Leading and trailing whitespaces will be trimmed.
     * @param memberName A string containing the name of the member.
     * @throws IllegalArgumentException if the given {@code memberName} is invalid.
     */
    public static Member parseMember(String memberName) throws IllegalArgumentException {
        requireNonNull(memberName);
        String trimmedName = memberName.trim();
        if (!Member.isValidName(trimmedName)) {
            throw new IllegalArgumentException(Member.MESSAGE_CONSTRAINTS);
        }
        return new Member(trimmedName);
    }

    /**
     * Parses {@code Collection<String> members} into a {@code Set<Member>}.
     * @param members A collection of strings containing the names of the members.
     * @return A set of members to be added to the task.
     * @throws IllegalArgumentException if the given {@code members} is invalid.
     */
    public static Set<Member> parseMembers(Collection<String> members) throws IllegalArgumentException {
        requireNonNull(members);

        final Set<Member> memberSet = new HashSet<>();
        for (String memberName : members) {
            memberSet.add(parseMember(memberName));
        }
        return memberSet;
    }
    /**
     * Parses {@code String} into a {@code SortOrderEnum}.
     * @param enumValue String enum value to parse.
     * @return Parsed {@code SortOrderEnum} value.
     */
    public static SortOrderEnum parseSortOrder(String enumValue) throws IllegalArgumentException {
        try {
            return SortOrderEnum.of(enumValue);
        } catch (java.lang.IllegalArgumentException exception) {
            throw new IllegalArgumentException(SortOrderEnum.MESSAGE_CONSTRAINTS + enumValue + "!");
        }
    }

    /**
     * Parses {@code String} into a {@code SortTypeEnum}.
     * @param enumValue String enum value to parse.
     * @return Parsed {@code SortTypeEnum} value.
     */
    public static SortTypeEnum parseSortType(String enumValue) throws IllegalArgumentException {
        try {
            return SortTypeEnum.of(enumValue);
        } catch (java.lang.IllegalArgumentException exception) {
            throw new IllegalArgumentException(SortTypeEnum.MESSAGE_CONSTRAINTS + enumValue + "!");
        }
    }

    /**
     * Parses {@code String} into a {@code Priority}.
     * @param priority String priority level to parse.
     * @return Parsed {@code Priority} value.
     * @throws IllegalArgumentException if {@code String} is not recognized as a priority.
     */
    public static Priority parsePriority(String priority) throws IllegalArgumentException {
        requireNonNull(priority);
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalArgumentException(Priority.MESSAGE_CONSTRAINTS);
        }
        return Priority.of(priority);
    }
}
