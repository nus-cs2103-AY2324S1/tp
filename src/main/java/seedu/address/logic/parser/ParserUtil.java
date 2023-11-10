package seedu.address.logic.parser;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.OvertimeCommand.MESSAGE_EMPTY_OPERATION;
import static seedu.address.logic.commands.OvertimeCommand.MESSAGE_OPERATION_USAGE;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.OvertimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.Salary;
import seedu.address.model.remark.Remark;

/**
 * Contains utility methods used for parsing strings in the various Parser classes.
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
     * Parses a {@code String department} into a {@code Department}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code department} is invalid.
     */
    public static Department parseDepartment(String department) throws ParseException {
        requireNonNull(department);
        String trimmedDepartment = department.trim();
        if (!Department.isValidDepartmentName(trimmedDepartment)) {
            throw new ParseException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(trimmedDepartment);
    }

    /**
     * Parses a {@code String position} into a {@code Position}.
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
        return new Position(trimmedPosition);
    }

    /**
     * Parses a {@code String id} into an {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses {@code Collection<String> departments} into a {@code Set<Department>}.
     */
    public static Set<Department> parseDepartments(Collection<String> departments) throws ParseException {
        requireNonNull(departments);
        final Set<Department> departmentSet = new HashSet<>();
        for (String departmentName : departments) {
            departmentSet.add(parseDepartment(departmentName));
        }
        return departmentSet;
    }

    /**
     * Parses a {@code String salary} into a {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        String parsedSalary = String.valueOf(Integer.parseInt(trimmedSalary));
        return new Salary(parsedSalary);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();

        if (trimmedDate.isEmpty()) {
            throw new ParseException(Messages.MESSAGE_MISSING_DATE);
        }
        try {
            return LocalDate.parse(trimmedDate, ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Parses a {@code String hours} into an {@code OvertimeHours}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code hours} is invalid.
     */
    public static OvertimeHours parseOvertimeHours(String hours) throws ParseException {
        requireNonNull(hours);
        String trimmedHours = hours.trim();
        if (trimmedHours.isEmpty()) {
            throw new ParseException(OvertimeCommand.MESSAGE_MISSING_AMOUNT);
        }
        int overtimeHours;
        try {
            overtimeHours = Integer.parseInt(trimmedHours);
        } catch (NumberFormatException e) {
            throw new ParseException(OvertimeCommand.MESSAGE_INVALID_AMOUNT);
        }
        if (overtimeHours <= 0) {
            throw new ParseException(OvertimeCommand.MESSAGE_INVALID_AMOUNT);
        }
        if (!OvertimeHours.isValidOvertimeHours(overtimeHours)) {
            throw new ParseException(OvertimeHours.MESSAGE_CONSTRAINTS);
        }
        return new OvertimeHours(overtimeHours);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (trimmedRemark.isEmpty()) {
            throw new ParseException(Messages.MESSAGE_MISSING_REMARK);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String operation} into a {@code boolean}.
     *
     * @throws ParseException if the given {@code operation} is invalid.
     */
    public static boolean parseOperation(String operation) throws ParseException {
        if (operation.equals("inc")) {
            return true;
        } else if (operation.equals("dec")) {
            return false;
        } else if (operation.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_OPERATION);
        } else {
            throw new ParseException(MESSAGE_OPERATION_USAGE);
        }
    }
}
