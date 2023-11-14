package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.tab.Tab;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.FinanceListType;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_TAB_NAME = "Tab name is not a valid tab name. ";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
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
     * Parses {@code tab} into an {@code Tab} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified tab name is invalid.
     */
    public static Tab parseTab(String tabParameter) throws ParseException {
        String trimmedTabParameter = tabParameter.trim();
        if (!Tab.isValidTabParameter(trimmedTabParameter)) {
            throw new ParseException(MESSAGE_INVALID_TAB_NAME);
        }

        return Tab.fromParameter(trimmedTabParameter);
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
        if (!EventName.isValidEventName(trimmedName)) {
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
     * Parses a {@code String company} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String telegramName} into an {@code TelegramName}.
     * @throws ParseException if the given {@code telegramName} is invalid.
     */
    public static TelegramName parseTelegramName(String telegramName) throws ParseException {
        String trimmedTelegramName = telegramName.trim();
        if (!TelegramName.isValidTelegramName(trimmedTelegramName)) {
            throw new ParseException(TelegramName.MESSAGE_CONSTRAINTS);
        }
        return new TelegramName(trimmedTelegramName);
    }

    /**
     * Parses a {@code String eventName} into an {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidEventName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return new EventName(trimmedEventName);
    }


    /**
     * Parses a {@code String timeDue} into an {@code TimeDue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeDue} is invalid.
     */
    public static TimeDue parseTimeDue(String timeDue) throws ParseException {
        String trimmedTimeDue = timeDue.trim();
        if (!DateTimeParser.isValidDateTimeFormat(trimmedTimeDue)) {
            throw new ParseException(DateTimeParser.INVALID_DATETIME_FORMAT);
        }
        return new TimeDue(trimmedTimeDue);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static EventDescription parseEventDescription(String description) throws ParseException {
        String trimmedDescription = description.trim();
        if (!EventDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new EventDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a collection of client names into a set of {@code Person} objects.
     *
     * @throws ParseException if a client name is invalid.
     */
    public static Set<Person> parseClients(Collection<String> clients) throws ParseException {
        requireNonNull(clients);
        final Set<Person> clientSet = new HashSet<>();
        for (String clientName : clients) {
            clientSet.add(parseClient(clientName));
        }
        return clientSet;
    }

    /**
     * Parses a {@code String clientName} into a {@code Person} object.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientName} is invalid.
     */
    public static Person parseClient(String clientName) throws ParseException {
        requireNonNull(clientName);
        String trimmedClientName = clientName.trim();
        if (!Name.isValidName(trimmedClientName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return Person.makeDummyWithName(trimmedClientName);
    }

    /**
     * Similar to parseClient method, but returns null if client name is empty.
     * Reason: Edit command has different parsing requirements for client than the add command.
     *
     * @throws ParseException if the given {@code clientName} is invalid.
     */
    public static Person parseClientForEdit(String clientName) throws ParseException {
        requireNonNull(clientName);
        String trimmedClientName = clientName.trim();
        if (trimmedClientName.isEmpty()) {
            return null;
        } else if (!Name.isValidName(trimmedClientName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return Person.makeDummyWithName(trimmedClientName);
    }

    /**
     * Parses a {@code String amount} into an {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(trimmedAmount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(trimmedAmount);
    }

    /**
     * Parses a {@code String financeListType} into a {@code FinanceListType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code financeListType} is invalid.
     */
    public static FinanceListType parseFinanceListType(String financeListType) throws ParseException {
        requireNonNull(financeListType);
        String trimmedFinanceType = financeListType.trim();
        switch (trimmedFinanceType) {
        case "expense":
            return FinanceListType.EXPENSE;
        case "commission":
            return FinanceListType.COMMISSION;
        case "":
            return FinanceListType.ALL;
        default:
            throw new ParseException("Allowed values: expense, commission, <blank>");
        }

    }
}
