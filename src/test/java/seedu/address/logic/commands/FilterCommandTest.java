//@@author kenvynKwek
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.CARL;
import static seedu.address.testutil.TypicalEmployees.DANIEL;
import static seedu.address.testutil.TypicalEmployees.ELLE;
import static seedu.address.testutil.TypicalEmployees.FIONA;
import static seedu.address.testutil.TypicalEmployees.GEORGE;
import static seedu.address.testutil.TypicalEmployees.getTypicalManageHr;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CustomSet;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.ContainsAllPredicate;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

public class FilterCommandTest {
    private static final String INVALID_DEPARTMENT = "A";
    private static final String INVALID_PHONE = "12345678";
    private static final String INVALID_NAME = "Sam Sulek";
    private static final String VALID_SALARY_2000 = "2000";
    private static final String VALID_SALARY_3000 = "3000";
    private static final String VALID_SALARY_4300 = "4300";
    private static final String VALID_LEAVE = "14";
    private Model model = new ModelManager(getTypicalManageHr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalManageHr(), new UserPrefs());

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

        String[] prefix = prefixes.split(",");
        String[] keyword = keywords.split(",");
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
    public void execute_noEmployeesFound() {
        // invalid d/DEPARTMENT
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 0);
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("department", INVALID_DEPARTMENT);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());

        // non-existent phone number in typical employees
        predicate = createContainsAllPredicateHelper("phone", INVALID_PHONE);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());

        // wrong name
        predicate = createContainsAllPredicateHelper("name", INVALID_NAME);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_multipleEmployeesFound() {
        // filter n/Benson Meier from typical employees
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1);
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("name", BENSON.getName().toString());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredEmployeeList());

        // filter d/investment from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        predicate = createContainsAllPredicateHelper("department", VALID_DEPARTMENT_INVESTMENT);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredEmployeeList());

        // filter d/logistics from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1);
        predicate = createContainsAllPredicateHelper("department", VALID_DEPARTMENT_LOGISTIC);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredEmployeeList());

        // filter r/subordinate from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        predicate = createContainsAllPredicateHelper("role", VALID_ROLE_BOB);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA, GEORGE), model.getFilteredEmployeeList());

        // filter s/3000 (salary <= 3000) from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 4);
        predicate = createContainsAllPredicateHelper("salary", VALID_SALARY_3000);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, FIONA, GEORGE), model.getFilteredEmployeeList());

        // filter l/14 (leave == 14) from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 2);
        predicate = createContainsAllPredicateHelper("leave", VALID_LEAVE);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL), model.getFilteredEmployeeList());
    }

    @Test
    public void multiplePrefixFilterTest() {
        // combination tests

        // filter r/manager and d/investment from typical employees
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("role,department",
                VALID_ROLE_AMY + "," + VALID_DEPARTMENT_INVESTMENT);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredEmployeeList());

        // filter s/4300 and l/14 from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1);
        predicate = createContainsAllPredicateHelper("salary,leave", VALID_SALARY_4300 + ","
                + VALID_LEAVE);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredEmployeeList());

        // filter r/subordinate s/2000 from typical employees
        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 2);
        predicate = createContainsAllPredicateHelper("role,salary", VALID_ROLE_BOB + ","
                + VALID_SALARY_2000);
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, GEORGE), model.getFilteredEmployeeList());
    }

    @Test
    public void equals() {
        ContainsAllPredicate firstPredicate = createContainsAllPredicateHelper("department", "first");
        ContainsAllPredicate secondPredicate = createContainsAllPredicateHelper("department", "second");

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ContainsAllPredicate predicate = createContainsAllPredicateHelper("department", "keyword");
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate="
                + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}
//@@author
