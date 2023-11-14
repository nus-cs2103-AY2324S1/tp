package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Details;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Interaction.Outcome;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.lead.Lead;
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
     * Parses {@code String lead} into a {@code Lead}.
     */
    public static Lead parseLead(String lead) throws ParseException {
        requireNonNull(lead);
        String trimmedLead = lead.trim();
        if (!Lead.isValidLead(trimmedLead)) {
            throw new ParseException(Lead.MESSAGE_CONSTRAINTS);
        }
        return Lead.of(lead);
    }

    /**
     * Parses a {@code String telegram} into a {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static TelegramHandle parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedTelegram)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedTelegram);
    }

    /**
     * Parses a {@code String profession} into a {@code Profession}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code profession} is invalid.
     */
    public static Profession parseProfession(String profession) throws ParseException {
        requireNonNull(profession);
        String trimmedProfession = profession.trim();
        if (!Profession.isValidProfession(trimmedProfession)) {
            throw new ParseException(Profession.MESSAGE_CONSTRAINTS);
        }
        return new Profession(trimmedProfession);
    }

    /**
     * Parses a {@code String income} into a {@code Income}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code income} is invalid.
     */
    public static Income parseIncome(String income) throws ParseException {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!Income.isValidIncome(trimmedIncome)) {
            throw new ParseException(Income.MESSAGE_CONSTRAINTS);
        }
        return new Income(trimmedIncome);
    }

    /**
     * Parses a {@code String Details} into a {@code Details}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code details} is invalid.
     */
    public static Details parseDetails(String details) throws ParseException {
        requireNonNull(details);
        String trimmedDetails = details.trim();
        if (!Details.isValidDetails(trimmedDetails)) {
            throw new ParseException(Details.MESSAGE_CONSTRAINTS);
        }
        return new Details(trimmedDetails);
    }

    /**
     * Parses a {@code String outcome} into a {@code Outcome}.
     *
     * @throws ParseException if the given {@code outcome} is invalid.
     */
    public static Outcome parseOutcome(String outcome) throws ParseException {
        requireNonNull(outcome);
        String trimmedOutcome = outcome.trim();
        if (!Outcome.isValidOutcome(trimmedOutcome)) {
            throw new ParseException(Outcome.MESSAGE_CONSTRAINTS);
        }
        return Outcome.valueOf(trimmedOutcome.toUpperCase());
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
}
