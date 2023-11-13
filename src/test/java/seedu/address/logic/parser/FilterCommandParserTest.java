//@@author kenvynKwek
package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.LEAVE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LEAVE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MANAGER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MANAGER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANAGER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MANAGER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CustomSet;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.ContainsAllPredicate;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;


public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    public ContainsAllPredicate createContainsAllPredicateHelper(String prefixes, String keywords) {
        Set<EmployeeName> nameSet = new CustomSet<>();
        Set<Phone> phoneSet = new CustomSet<>();
        Set<Email> emailSet = new CustomSet<>();
        Set<Address> addressSet = new CustomSet<>();
        Set<Salary> salarySet = new CustomSet<>();
        Set<Leave> leaveSet = new CustomSet<>();
        Set<Role> roleSet = new CustomSet<>();
        Set<EmployeeName> supervisorSet = new CustomSet<>();
        Set<DepartmentName> departmentSet = new CustomSet<>();

        String[] prefix = prefixes.split("_");
        String[] keyword = keywords.split("_");
        for (int i = 0; i < prefix.length; i++) {
            switch (prefix[i]) {
            case "name":
                nameSet.add(new EmployeeName(keyword[i]));
                break;
            case "phone":
                phoneSet.add(new Phone(keyword[i]));
                break;
            case "email":
                emailSet.add(new Email(keyword[i]));
                break;
            case "address":
                addressSet.add(new Address(keyword[i]));
                break;
            case "salary":
                salarySet.add(new Salary(keyword[i]));
                break;
            case "leave":
                leaveSet.add(new Leave(keyword[i]));
                break;
            case "role":
                roleSet.add(new Role(keyword[i]));
                break;
            case "supervisor":
                supervisorSet.add(new EmployeeName(keyword[i]));
                break;
            case "department":
                departmentSet.add(new DepartmentName(keyword[i]));
                break;
            default:
                break;
            }
        }

        ContainsAllPredicate predicate = new ContainsAllPredicate(nameSet, phoneSet, emailSet, addressSet, salarySet,
                leaveSet, roleSet, supervisorSet, departmentSet);
        return predicate;
    }

    @Test
    public void parse_emptyArg_returnsFilterFunction() {
        // empty department parameter
        String userInput = " " + PREFIX_DEPARTMENT;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("", "");
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // empty name parameter
        userInput = " " + PREFIX_NAME;
        predicate = createContainsAllPredicateHelper("", "");
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validName_returnFilterCommand() {
        // parse n/Amy Bee
        String userInput = NAME_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("name", VALID_NAME_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse n/Bob Choo
        userInput = NAME_DESC_BOB;
        predicate = createContainsAllPredicateHelper("name", VALID_NAME_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validPhone_returnFilterCommand() {
        // parse p/11111111
        String userInput = PHONE_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("phone", VALID_PHONE_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse p/22222222
        userInput = PHONE_DESC_BOB;
        predicate = createContainsAllPredicateHelper("phone", VALID_PHONE_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validEmail_returnFilterCommand() {
        // parse e/amy@example.com
        String userInput = EMAIL_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("email", VALID_EMAIL_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse e/bob@example.com
        userInput = EMAIL_DESC_BOB;
        predicate = createContainsAllPredicateHelper("email", VALID_EMAIL_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validAddress_returnsFilterCommand() {
        // parse a/Block 312, Amy Street 1
        String userInput = ADDRESS_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("address", VALID_ADDRESS_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse a/Block 123, Bobby Street 3
        userInput = ADDRESS_DESC_BOB;
        predicate = createContainsAllPredicateHelper("address", VALID_ADDRESS_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validSalary_returnsFilterCommand() {
        // parse s/10000
        String userInput = SALARY_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("salary", VALID_SALARY_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse s/12000
        userInput = SALARY_DESC_BOB;
        predicate = createContainsAllPredicateHelper("salary", VALID_SALARY_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validLeave_returnsFilterCommand() {
        // parse l/21
        String userInput = LEAVE_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("leave", VALID_LEAVE_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse s/28
        userInput = LEAVE_DESC_BOB;
        predicate = createContainsAllPredicateHelper("leave", VALID_LEAVE_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validRole_returnsFilterCommand() {
        // parse r/manager
        String userInput = ROLE_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("role", VALID_ROLE_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse r/subordinate
        userInput = ROLE_DESC_BOB;
        predicate = createContainsAllPredicateHelper("role", VALID_ROLE_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validSupervisor_returnsFilterCommand() {
        // parse m/Amy Bee
        String userInput = MANAGER_DESC_AMY;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("supervisor", VALID_MANAGER_AMY);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse m/Bob Choo
        userInput = MANAGER_DESC_BOB;
        predicate = createContainsAllPredicateHelper("supervisor", VALID_MANAGER_BOB);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_validDepartment_returnsFilterCommand() {
        // parse d/investment
        String userInput = DEPARTMENT_DESC_INVESTMENT;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("department",
                VALID_DEPARTMENT_INVESTMENT);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // parse d/logistics
        userInput = DEPARTMENT_DESC_LOGISTIC;
        predicate = createContainsAllPredicateHelper("department", VALID_DEPARTMENT_LOGISTIC);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_moreThanOneArg_returnsFilterCommand() { // combination tests
        // filter n/Amy Bee n/Bob Choo
        String userInput = NAME_DESC_AMY + NAME_DESC_BOB;
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("name_name", VALID_NAME_AMY
                + "_" + VALID_NAME_BOB);
        FilterCommand expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // filter l/28 s/10000
        userInput = LEAVE_DESC_BOB + SALARY_DESC_AMY;
        predicate = createContainsAllPredicateHelper("leave_salary", VALID_LEAVE_BOB + "_"
                + VALID_SALARY_AMY);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // filter r/subordinate a/Block 312, Amy Street 1
        userInput = ROLE_DESC_BOB + ADDRESS_DESC_AMY;
        predicate = createContainsAllPredicateHelper("role_address", VALID_ROLE_BOB + "_"
                + VALID_ADDRESS_AMY);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        // filter n/Bob choo m/Amy Bee d/investment
        userInput = NAME_DESC_BOB + MANAGER_DESC_AMY + DEPARTMENT_DESC_INVESTMENT;
        predicate = createContainsAllPredicateHelper("name_supervisor_department", VALID_NAME_BOB + "_"
                + VALID_MANAGER_AMY + "_" + VALID_DEPARTMENT_INVESTMENT);
        expectedFilterCommand = new FilterCommand(predicate);
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }
}
//@@author
