package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Birthday.FORMATTER;

import java.time.MonthDay;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_NOT_A_INDEX = "Index should be an integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        try {
            boolean isValidIndex = StringUtil.isNonZeroUnsignedInteger(trimmedIndex);
            if (!isValidIndex) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            return Index.fromOneBased(Integer.parseInt(trimmedIndex));
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_NOT_A_INDEX);
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
     * Parses a {@code String birthday} into an {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} is invalid or doesn't follow the specified format.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        try {
            return new Birthday(MonthDay.parse(trimmedBirthday, FORMATTER));
        } catch (DateTimeParseException e) {
            throw new ParseException(Birthday.MESSAGE_INVALID);
        }
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
     * Parses a {@code String linkedin} into an {@code Linkedin}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code linkedin} is invalid.
     */
    public static Linkedin parseLinkedin(String linkedin) throws ParseException {
        requireNonNull(linkedin);
        String trimmedLinkedin = linkedin.trim();
        if (!Linkedin.isValidLinkedin(trimmedLinkedin)) {
            throw new ParseException(Linkedin.MESSAGE_CONSTRAINTS);
        }
        return new Linkedin(trimmedLinkedin);
    }

    /**
     * Parses a {@code String telegram} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
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
            Tag tagToAdd = parseTag(tagName);
            if (!tagSet.contains(tagToAdd)) {
                tagSet.add(tagToAdd);
            }
        }
        return tagSet;
    }

    /**
     * Parses a {@code String balance} into a {@code Balance}.
     * Handles the conversion of a dollar amount into cents.
     */
    public static Balance parseBalance(String balance) throws ParseException {
        requireNonNull(balance);
        String trimmedBalance = balance.trim();


        if (!Balance.isValidDollarString(trimmedBalance)) {
            throw new ParseException(Balance.MESSAGE_CONSTRAINTS);
        }

        // Remove the dollar sign if it exists
        String dollarAmount = trimmedBalance.replace("$", "");

        // Split the string at the decimal point
        String[] parts = dollarAmount.split("\\.");

        // Strip leading zeros from the dollar part
        parts[0] = parts[0].replaceFirst("^0+(?!$)", "");

        // Check that the dollar amount does not clearly exceed 5 digits.
        // This prevents integer overflow when converting to cents and when
        // performing validation in subsequent pay / owe operations.
        if (parts[0].length() > 5) {
            throw new ParseException(Balance.MESSAGE_TRANSACTION_LIMIT_EXCEEDED);
        }

        // Convert the dollar part to cents
        int cents = Integer.parseInt(parts[0]) * 100;

        // Add the cents part if it exists
        if (parts.length > 1) {
            if (parts[1].length() == 1) {
                // If there's only one decimal place, multiply by 10 to get correct cents
                cents += Integer.parseInt(parts[1]) * 10;
            } else {
                cents += Integer.parseInt(parts[1]);
            }
        }

        if (!Balance.isWithinTransactionLimit(cents)) {
            throw new ParseException(Balance.MESSAGE_TRANSACTION_LIMIT_EXCEEDED);
        }

        return new Balance(cents);
    }

}
