package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.attendance.AttendanceType;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Benefit;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reason;
import seedu.address.model.person.Salary;

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
     * Parses a {@code String bankAccount} into an {@code BankAccount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bankAccount} is invalid.
     */
    public static BankAccount parseBankAccount(String bankAccount) throws ParseException {
        requireNonNull(bankAccount);
        String trimmedBankAccount = bankAccount.trim();
        if (!BankAccount.isValidBankAccount(trimmedBankAccount)) {
            throw new ParseException(BankAccount.MESSAGE_CONSTRAINTS);
        }
        return new BankAccount(trimmedBankAccount);
    }

    /**
     * Parses a {@code String joinDate} into an {@code JoinDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code joinDate} is invalid.
     */
    public static JoinDate parseJoinDate(String joinDate) throws ParseException {
        requireNonNull(joinDate);
        String trimmedJoinDate = joinDate.trim();
        if (!JoinDate.isValidJoinDate(trimmedJoinDate)) {
            throw new ParseException(JoinDate.MESSAGE_CONSTRAINTS);
        }
        return new JoinDate(trimmedJoinDate);
    }

    /**
     * Parses a {@code String salary} into an {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValid(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String annualLeave} into an {@code AnnualLeave}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code annualLeave} is invalid.
     */
    public static AnnualLeave parseAnnualLeave(String annualLeave) throws ParseException {
        requireNonNull(annualLeave);
        String trimmedAnnualLeave = annualLeave.trim();
        if (!AnnualLeave.isValidAnnualLeave(trimmedAnnualLeave)) {
            throw new ParseException(AnnualLeave.MESSAGE_CONSTRAINTS);
        }
        return new AnnualLeave(trimmedAnnualLeave);
    }

    /**
     * Parses a {@code String attendanceType} into an {@code AttendanceType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendanceType} is invalid.
     */
    public static AttendanceType parseAttendanceType(String attendanceType) throws ParseException {
        requireNonNull(attendanceType);
        String trimmedAttendanceType = attendanceType.trim();
        if (!AttendanceType.isValidAttendanceType(trimmedAttendanceType)) {
            throw new ParseException(AttendanceType.MESSAGE_CONSTRAINTS);
        }
        return AttendanceType.valueOf(attendanceType.toUpperCase());
    }
    /**
     * Returns LocalDate object from String
     * @param date The String containing date from user input
     * @return LocalDate
     * @throws DateTimeParseException if the format of String is wrong
     */
    public static LocalDate stringToDate(String date) throws DateTimeParseException {
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    /**
     * Returns String from LocalDate object.
     * @param date The LocalDate object
     * @return String format of LocalDate object
     */
    public static String dateToString(LocalDate date) {
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        String localDate = date.format(formatter);
        return localDate;
    }

    /**
     * Parses a {@code String reason} into an {@code Reason}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reason} is invalid.
     */
    public static Reason parseReason(String reason) throws ParseException {
        requireNonNull(reason);
        String trimmedReason = reason.trim();

        for (Reason r : Reason.values()) {
            String expected = String.join(" ", r.toString().split("_"));
            if (FuzzySearch.tokenSetRatio(trimmedReason.toLowerCase(), expected.toLowerCase()) > 50) {
                return r;
            }
        }
        throw new ParseException(Reason.MESSAGE_CONSTRAINTS);
    }

    /**
     * Parses a {@code String deduction} into an {@code Deduction}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deduction} is invalid.
     */
    public static Deduction parseDeduction(String value, Reason reason) throws ParseException {
        requireNonNull(value);
        requireNonNull(reason);
        String trimmedDeduction = value.trim();
        if (!Deduction.isValid(trimmedDeduction)) {
            throw new ParseException(Deduction.MESSAGE_CONSTRAINTS);
        }
        return new Deduction(trimmedDeduction, reason);
    }

    /**
     * Parses a {@code String benefit} into an {@code Benefit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code benefit} is invalid.
     */
    public static Benefit parseBenefit(String value, Reason reason) throws ParseException {
        requireNonNull(value);
        requireNonNull(reason);
        String trimmedBenefit = value.trim();
        if (!Benefit.isValid(trimmedBenefit)) {
            throw new ParseException(Benefit.MESSAGE_CONSTRAINTS);
        }
        return new Benefit(trimmedBenefit, reason);
    }
}
