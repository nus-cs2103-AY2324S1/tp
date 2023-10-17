package seedu.lovebook.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.lovebook.commons.core.index.Index;
import seedu.lovebook.commons.util.StringUtil;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.Name;
import seedu.lovebook.model.person.horoscope.Horoscope;

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
     * Parses a {@code String age} into a {@code Age}.
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
     * Parses a {@code String height} into an {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code height} is invalid.
     */
    public static Height parseAddress(String height) throws ParseException {
        requireNonNull(height);
        String trimmedAddress = height.trim();
        if (!Height.isValidHeight(trimmedAddress)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(trimmedAddress);
    }

    /**
     * Parses a {@code String income} into an {@code Income}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code income} is invalid.
     */
    public static Income parseIncome(String income) throws ParseException {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!Height.isValidHeight(trimmedIncome)) {
            throw new ParseException(Income.MESSAGE_CONSTRAINTS);
        }
        return new Income(income);
    }

    /**
     * Parses a {@code String horoscope} into an {@code Horoscope}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code horoscope} is invalid.
     */
    public static Horoscope parseHoroscope(String horoscope) throws ParseException {
        requireNonNull(horoscope);
        String upperHoroscope = horoscope.trim().toUpperCase();
        if (!Horoscope.isValidHoroscope(upperHoroscope)) {
            throw new ParseException(Horoscope.MESSAGE_CONSTRAINTS);
        }
        return new Horoscope(upperHoroscope);
    }
}
