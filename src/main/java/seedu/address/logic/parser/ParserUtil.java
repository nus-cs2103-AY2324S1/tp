package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.SortIn;
import seedu.address.model.person.Visual;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String secLevel} into an {@code SecLevel}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code secLevel} is invalid.
     */
    public static SecLevel parseSecLevel(String secLevel) throws ParseException {
        requireNonNull(secLevel);
        String trimmedSecLevel = secLevel.trim();
        if (!SecLevel.isValidSecLevel(trimmedSecLevel)) {
            throw new ParseException(SecLevel.MESSAGE_CONSTRAINTS);
        }
        return new SecLevel(trimmedSecLevel);
    }

    /**
     * Parses a {@code String mrtStation} into an {@code MrtStation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mrtStation} is invalid.
     */
    public static MrtStation parseMrtStation(String mrtStation) throws ParseException {
        requireNonNull(mrtStation);
        String trimmedMrtStation = mrtStation.trim();
        if (!MrtStation.isValidMrtStationName(trimmedMrtStation)) {
            throw new ParseException(MrtStation.MESSAGE_CONSTRAINTS);
        }
        return new MrtStation(trimmedMrtStation);
    }

    /**
     * Parses a {@code String tag} into a {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Subject parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Subject.isValidSubjectName(trimmedTag)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedTag);
    }

    /**
     * Parses a {@code String tag} and a {@code EnrolDate date} into a {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Subject parseTag(String tag, EnrolDate date) throws ParseException {
        requireNonNull(tag);
        requireNonNull(date);
        String trimmedTag = tag.trim();
        if (!Subject.isValidSubjectName(trimmedTag)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedTag, date);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Subject>}.
     */
    public static Set<Subject> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Subject> subjectSet = new HashSet<>();
        for (String tagName : tags) {
            subjectSet.add(parseTag(tagName));
        }
        return subjectSet;
    }

    /**
     * Parses {@code Collection<String> tags} and {@code EnrolDate date} into a {@code Set<Subject>}.
     */
    public static Set<Subject> parseTags(Collection<String> tags, EnrolDate date) throws ParseException {
        requireNonNull(tags);
        if (tags.size() < 1) {
            throw new ParseException(Messages.MESSAGE_DATE_NUMBER_NOT_MATCHING);
        }
        final Set<Subject> subjectSet = new HashSet<>();
        for (String tagName : tags) {
            subjectSet.add(parseTag(tagName, date));
        }
        return subjectSet;
    }

    /**
     * Parses {@code Collection<String> tags} and {@code Collection<EnrolDate> dates} into a {@code Set<Subject>}.
     */
    public static Set<Subject> parseTags(Collection<String> tags, Collection<EnrolDate> dates) throws ParseException {
        requireNonNull(tags);
        requireNonNull(dates);

        if (dates.isEmpty()) {
            return parseTags(tags);
        } else if (dates.size() == 1) {
            EnrolDate date = dates.iterator().next();
            return parseTags(tags, date);
        } else if (dates.size() != tags.size()) {
            throw new ParseException(Messages.MESSAGE_DATE_NUMBER_NOT_MATCHING);
        }

        final Set<Subject> subjectSet = new HashSet<>();
        Iterator<String> tagIterator = tags.iterator();
        Iterator<EnrolDate> dateIterator = dates.iterator();
        while (tagIterator.hasNext() && dateIterator.hasNext()) {
            String nextTag = tagIterator.next();
            EnrolDate nextDate = dateIterator.next();
            subjectSet.add(parseTag(nextTag, nextDate));
        }

        return subjectSet;
    }

    /**
     * Parses a {@code String date} into an {@code EnrolDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static EnrolDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!EnrolDate.isValidDate(trimmedDate)) {
            throw new ParseException(EnrolDate.MESSAGE_INVALID_DATE_FORMAT);
        }
        return new EnrolDate(trimmedDate);
    }

    /**
     * Parses {@code Collection<String> dates} into a {@code Collection<EnrolDate>}.
     */
    public static Collection<EnrolDate> parseDates(Collection<String> dates) throws ParseException {
        requireNonNull(dates);
        final Collection<EnrolDate> dateCollection = new ArrayList<>();
        for (String date : dates) {
            dateCollection.add(parseDate(date));
        }
        return dateCollection;
    }


    /**
     * Parses {@code String } into a {@code SortIn}.
     */
    public static SortIn parseSortIn(String sortIn) throws ParseException {
        requireNonNull(sortIn);
        String trimmedSortIn = sortIn.trim();
        if (!SortIn.isValidSortIn(trimmedSortIn)) {
            throw new ParseException(SortIn.MESSAGE_CONSTRAINTS);
        }
        return new SortIn(trimmedSortIn);
    }

    /**
     * Parses {@code String } into a {@code Visual}.
     */
    public static Visual parseVisual(String visual) throws ParseException {
        requireNonNull(visual);
        String trimmedVisual = visual.trim();
        if (!Visual.isValidVisual(trimmedVisual)) {
            throw new ParseException(Visual.MESSAGE_CONSTRAINTS);
        }
        return new Visual(trimmedVisual);
    }

    /**
     * Parses preamble of user input, preamble in edit command can either be index
     * or student's name
     *
     * @param preamble text before the first valid prefix in user input.
     * @return either Index object or Name object.
     * @throws ParseException
     */
    public static Object parsePreamble(String preamble) throws ParseException {
        requireNonNull(preamble);
        preamble = preamble.trim();
        // Preamble, if exist, can either be index or student's name
        if (isInteger(preamble)) {
            return parseIndex(preamble);
        }
        if (!preamble.matches("^[a-zA-Z ]+$")) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return parseName(preamble);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
