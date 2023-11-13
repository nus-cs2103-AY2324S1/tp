package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CustomSet;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.ContainsAllPredicate;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;


/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    private static final Logger logger = LogsCenter.getLogger(ManageHrParser.class);

    /**
     * Parses the given {@code String} argument in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @param args FilterCommand argument.
     * @return FilterCommand object for execution.
     * @throws ParseException if the user inputs does not conform to the expected format.
     */
    //@@author kenvynKwek
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        //@@author
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        //@@author kenvynKwek
                        PREFIX_SALARY,
                        //@@author
                        PREFIX_LEAVE, PREFIX_ROLE, PREFIX_MANAGER,
                        //@@author kenvynKwek
                        PREFIX_DEPARTMENT);
        //@@author

        Set<EmployeeName> nameSet = parseNamesForFilter(argMultimap.getAllValues(PREFIX_NAME));
        Set<Phone> phoneSet = parsePhonesForFilter(argMultimap.getAllValues(PREFIX_PHONE));
        Set<Email> emailSet = parseEmailsForFilter(argMultimap.getAllValues(PREFIX_EMAIL));
        Set<Address> addressSet = parseAddressesForFilter(argMultimap.getAllValues(PREFIX_ADDRESS));
        Set<Salary> salarySet = parseSalariesForFilter(argMultimap.getAllValues(PREFIX_SALARY));
        Set<Leave> leaveSet = parseLeavesForFilter(argMultimap.getAllValues(PREFIX_LEAVE));
        Set<Role> roleSet = parseRolesForFilter(argMultimap.getAllValues(PREFIX_ROLE));

        Set<EmployeeName> supervisorNameSet = parseSupervisorsForFilter(argMultimap.getAllValues(PREFIX_MANAGER));
        Set<DepartmentName> departmentSet = parseDepartmentsForFilter(argMultimap.getAllValues(PREFIX_DEPARTMENT));

        //@@author kenvynKwek
        return new FilterCommand(
                //@@author
                new ContainsAllPredicate(nameSet, phoneSet, emailSet, addressSet, salarySet, leaveSet, roleSet,
                        supervisorNameSet, departmentSet));
    }

    private Set<EmployeeName> parseNamesForFilter(Collection<String> nameSet) throws ParseException {
        logger.info("Filter name(s) parsed: " + nameSet.toString());
        assert nameSet != null;
        Collection<String> names = nameSet.size() == 1 && nameSet.contains("")
                ? Collections.emptySet() : nameSet;
        Set<EmployeeName> parsedNameSet = new CustomSet<>();
        for (String name : names) {
            parsedNameSet.add(ParserUtil.parseName(name));
        }
        return parsedNameSet;
    }

    private Set<Phone> parsePhonesForFilter(Collection<String> phoneSet) throws ParseException {
        logger.info("Filter phone number(s) parsed: " + phoneSet.toString());
        assert phoneSet != null;
        Collection<String> phones = phoneSet.size() == 1 && phoneSet.contains("")
                ? Collections.emptySet() : phoneSet;
        Set<Phone> parsedPhoneSet = new CustomSet<>();
        for (String phone : phones) {
            parsedPhoneSet.add(ParserUtil.parsePhone(phone));
        }
        return parsedPhoneSet;
    }

    private Set<Email> parseEmailsForFilter(Collection<String> emailSet) throws ParseException {
        logger.info("Filter email(s) parsed: " + emailSet.toString());
        assert emailSet != null;
        Collection<String> emails = emailSet.size() == 1 && emailSet.contains("")
                ? Collections.emptySet() : emailSet;
        Set<Email> parsedEmailSet = new CustomSet<>();
        for (String email : emails) {
            parsedEmailSet.add(ParserUtil.parseEmail(email));
        }
        return parsedEmailSet;
    }

    private Set<Address> parseAddressesForFilter(Collection<String> addressSet) throws ParseException {
        logger.info("Filter address(s) parsed: " + addressSet.toString());
        assert addressSet != null;
        Collection<String> addresses = addressSet.size() == 1 && addressSet.contains("")
                ? Collections.emptySet() : addressSet;
        Set<Address> parsedAddressSet = new CustomSet<>();
        for (String address : addresses) {
            parsedAddressSet.add(ParserUtil.parseAddress(address));
        }
        return parsedAddressSet;
    }

    private Set<Salary> parseSalariesForFilter(Collection<String> salarySet) throws ParseException {
        logger.info("Filter salary(s) parsed: " + salarySet.toString());
        assert salarySet != null;
        Collection<String> salaries = salarySet.size() == 1 && salarySet.contains("")
                ? Collections.emptySet() : salarySet;
        Set<Salary> parsedSalarySet = new CustomSet<>();
        for (String salary : salaries) {
            parsedSalarySet.add(ParserUtil.parseSalary(salary));
        }
        return parsedSalarySet;
    }

    private Set<Leave> parseLeavesForFilter(Collection<String> leaveSet) throws ParseException {
        logger.info("Filter leave(s) parsed: " + leaveSet.toString());
        assert leaveSet != null;
        Collection<String> leaves = leaveSet.size() == 1 && leaveSet.contains("")
                ? Collections.emptySet() : leaveSet;
        Set<Leave> parsedLeaveSet = new CustomSet<>();
        for (String leave : leaves) {
            parsedLeaveSet.add(ParserUtil.parseLeave(leave));
        }
        return parsedLeaveSet;
    }

    private Set<Role> parseRolesForFilter(Collection<String> roleSet) throws ParseException {
        logger.info("Filter role(s) parsed: " + roleSet.toString());
        assert roleSet != null;
        Collection<String> roles = roleSet.size() == 1 && roleSet.contains("")
                ? Collections.emptySet() : roleSet;
        Set<Role> parsedRoleSet = new CustomSet<>();
        for (String role : roles) {
            parsedRoleSet.add(ParserUtil.parseRole(role));
        }
        return parsedRoleSet;
    }

    private Set<EmployeeName> parseSupervisorsForFilter(Collection<String> supervisors)
            throws ParseException {
        logger.info("Filter supervisor(s) parsed: " + supervisors.toString());
        assert supervisors != null;

        Collection<String> supervisorNameSet = supervisors.size() == 1 && supervisors.contains("")
                ? Collections.emptySet() : supervisors;
        CustomSet<EmployeeName> parsedSupervisorNameSet = new CustomSet<>();
        parsedSupervisorNameSet.addAll(ParserUtil.parseSupervisors(supervisorNameSet));
        return parsedSupervisorNameSet;
    }

    private Set<DepartmentName> parseDepartmentsForFilter(Collection<String> departments) throws ParseException {
        logger.info("Filter department(s) parsed: " + departments.toString());
        assert departments != null;

        Collection<String> departmentSet = departments.size() == 1 && departments.contains("")
                ? Collections.emptySet() : departments;
        CustomSet<DepartmentName> parsedDepartmentSet = new CustomSet<>();
        parsedDepartmentSet.addAll(ParserUtil.parseDepartments(departmentSet));
        return parsedDepartmentSet;
    }
}
