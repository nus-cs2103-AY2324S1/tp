package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Claim;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Money;
import seedu.address.model.person.Month;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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
     * Parses a {@code String dollar amount} into a {@code Money}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dollar amount} is invalid.
     */
    public static Money parseMoney(String money) throws ParseException {
        requireNonNull(money);
        String trimmed = money.trim();
        if (!Money.isValidMoney(trimmed)) {
            throw new ParseException(Money.MESSAGE_CONSTRAINTS);
        }
        return new Money(trimmed);
    }

    /**
     * Parses a {@code String department} into a {@code Department}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code department} is invalid.
     */
    public static Department parseDepartment(String department) throws ParseException {
        requireNonNull(department);
        String trimmed = department.trim();
        if (!Department.isValidDepartment(trimmed)) {
            throw new ParseException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(trimmed);
    }

    /**
     * Parses a {@code String dob} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dob} is invalid.
     */
    public static Birthday parseDob(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmed = dob.trim();
        if (!Birthday.isValidDob(trimmed)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmed);
    }

    /**
     * Returns a Claim Object upon successful checks.
     * Checks consist of ensuring user inputted either + or - before the claim amount.
     * Checks also consist of ensuring the claim amount contains only digits.
     *
     * @param claimAmount String Object which is parsed out from the user's command line input. Example: +500
     * @return Claim Object which stores claim amount as well as boolean  to indicate addition/subtraction.
     * @throws ParseException Exception when no symbols were inputted or amount consists of non-digits.
     */
    public static Claim parseClaim(String claimAmount) throws ParseException {
        requireNonNull(claimAmount);
        String trimmed = claimAmount.trim();
        if (trimmed.length() > 14) {
            throw new ParseException(Messages.TOO_LARGE_A_NUMBER);
        }
        if (!Claim.comtainsSymbol(trimmed)) {
            throw new ParseException(Claim.NO_SYMBOLS_ERROR);
        } else if (!Claim.isCorrectAmountType(trimmed)) {
            throw new ParseException(Claim.ALPHABETS_ERROR);
        }
        return new Claim(trimmed);
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
     * Parses a {@code String month} into a {@code int monthNumber}.
     * @param month String representation of the month number.
     * @return the integer representation of the given month.
     * @throws ParseException if the given {@code String month} is invalid.
     */
    public static int parseMonth(String month) throws ParseException {
        requireNonNull(month);
        String trimmed = month.trim();
        if (!trimmed.matches(Month.VALIDATION_REGEX)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS_BLANK_MONTH);
        } else if (Month.containsAlphabetsOrDecimals(trimmed)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS_MONTH_INVALID_CHARACTERS);
        } else if (Month.isZeroMonth(trimmed)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS_ZERO_MONTH);
        } else if (Month.isNegativeMonth(trimmed)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS_NEGATIVE_MONTH);
        } else if (!Month.isValidMonth(trimmed)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS_MONTH_OVER);
        } else {
            return Integer.parseInt(trimmed);
        }
    }
}
