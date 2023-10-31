package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.ShortcutAlias;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Theme;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialty;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX =
            "The provided indexes must be positive whole numbers (integers greater than zero).";

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
     * Parses {@code oneBasedIndexes} into a list of sorted {@code Index} in ascending order and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if any of the specified index is invalid (not non-zero unsigned integer)
     */
    public static List<Index> parseIndexes(String oneBasedIndexes) throws ParseException {
        requireNonNull(oneBasedIndexes);
        String trimmedIndexes = oneBasedIndexes.trim();
        List<Index> indexList = new ArrayList<>();
        String[] args = trimmedIndexes.split("\\s+");
        for (String arg : args) {
            indexList.add(parseIndex(arg));
        }
        verifyNoDuplicateIndexes(args);
        Collections.sort(indexList);
        return indexList;
    }

    /**
     * Verifies that no duplicated indexes are present in user input argument.
     */
    private static void verifyNoDuplicateIndexes(String[] args) throws ParseException {
        String[] duplicatedIndexes = Arrays.stream(args)
                .filter(i -> Collections.frequency(Arrays.asList(args), i) > 1)
                .distinct()
                .toArray(String[]::new);
        if (duplicatedIndexes.length > 0) {
            throw new ParseException(Messages.getErrorMessageForDuplicateIndexes(duplicatedIndexes));
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
     * Parses a {@code String specialty} into a {@code Specialty}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code specialty} is invalid.
     */
    public static Specialty parseSpecialty(String specialty) throws ParseException {
        requireNonNull(specialty);
        String trimmedSpecialty = specialty.trim();
        if (!Specialty.isValidSpecialty(trimmedSpecialty)) {
            throw new ParseException(Specialty.MESSAGE_CONSTRAINTS);
        }
        return new Specialty(specialty);
    }

    /**
     * Parses a {@code String medicalHistory} into a {@code MedicalHistory}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code medicalHistory} is invalid.
     */
    public static MedicalHistory parseMedicalHistory(String medicalHistory) throws ParseException {
        requireNonNull(medicalHistory);
        String trimmedSpecialty = medicalHistory.trim();
        if (!Specialty.isValidSpecialty(trimmedSpecialty)) {
            throw new ParseException(MedicalHistory.MESSAGE_CONSTRAINTS);
        }
        return new MedicalHistory(medicalHistory);
    }

    /**
     * Parses {@code Collection<String> medicalHistories} into a {@code Set<MedicalHistory>}.
     */
    public static Set<MedicalHistory> parseMedicalHistories(Collection<String> medicalHistories) throws ParseException {
        requireNonNull(medicalHistories);
        final Set<MedicalHistory> medHistSet = new HashSet<>();
        for (String medicalhistory : medicalHistories) {
            medHistSet.add(parseMedicalHistory(medicalhistory));
        }
        return medHistSet;
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
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedAddress = location.trim();
        if (!Location.isValidLocation(trimmedAddress)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedAddress);
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
     * Parses {@code String age} into a {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }

    /**
     * Parses {@code String commandWord} into a {@code CommandWord}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code commandWord} is invalid.
     */
    public static CommandWord parseCommandWord(String commandWord) throws ParseException {
        requireNonNull(commandWord);
        String trimmedCommandWord = commandWord.trim();
        if (!CommandWord.isValidCommandWord(trimmedCommandWord)) {
            throw new ParseException(CommandWord.MESSAGE_CONSTRAINTS);
        }
        return new CommandWord(trimmedCommandWord);
    }

    /**
     * Parses {@code String alias} into a {@code ShortcutAlias}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code alias} is invalid.
     */
    public static ShortcutAlias parseShortcutAlias(String alias) throws ParseException {
        requireNonNull(alias);
        String trimmedAlias = alias.trim();
        if (!ShortcutAlias.isValidShortcutAlias(trimmedAlias)) {
            throw new ParseException(ShortcutAlias.MESSAGE_CONSTRAINTS);
        }
        return new ShortcutAlias(trimmedAlias);
    }

    /**
     * Parses {@code String themeString} into a {@code Theme}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code themeString} is invalid.
     */
    public static Theme parseTheme(String themeString) throws ParseException {
        requireNonNull(themeString);
        String trimmedTheme = themeString.trim();
        try {
            return Theme.getThemeValue(trimmedTheme);
        } catch (IllegalArgumentException exception) {
            throw new ParseException(Theme.MESSAGE_CONSTRAINTS);
        }
    }
}
