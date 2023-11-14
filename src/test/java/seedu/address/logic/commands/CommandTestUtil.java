package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ManageHr;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.name.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_SALARY_AMY = "10000";
    public static final String VALID_SALARY_BOB = "12000";
    public static final String VALID_LEAVE_AMY = "21";
    public static final String VALID_LEAVE_BOB = "28";
    public static final String VALID_ROLE_AMY = "manager";
    public static final String VALID_ROLE_BOB = "subordinate";
    public static final String VALID_MANAGER_AMY = "Amy Bee";
    public static final String VALID_MANAGER_BOB = "Bob Choo";
    public static final String VALID_DEPARTMENT_LOGISTIC = "logistics";
    public static final String VALID_DEPARTMENT_INVESTMENT = "investment";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String LEAVE_DESC_AMY = " " + PREFIX_LEAVE + VALID_LEAVE_AMY;
    public static final String LEAVE_DESC_BOB = " " + PREFIX_LEAVE + VALID_LEAVE_BOB;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String MANAGER_DESC_AMY = " " + PREFIX_MANAGER + VALID_MANAGER_AMY;
    public static final String MANAGER_DESC_BOB = " " + PREFIX_MANAGER + VALID_MANAGER_BOB;
    public static final String DEPARTMENT_DESC_INVESTMENT = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_INVESTMENT;
    public static final String DEPARTMENT_DESC_LOGISTIC = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_LOGISTIC;
    public static final String DEPARTMENT_COMMAND_TYPE_ADD = " " + PREFIX_TYPE + "add";
    public static final String DEPARTMENT_COMMAND_TYPE_DELETE = " " + PREFIX_TYPE + "delete";
    public static final String DEPARTMENT_NAME_INVESTMENT = " " + PREFIX_NAME + VALID_DEPARTMENT_INVESTMENT;
    public static final String DEPARTMENT_NAME_LOGISTIC = " " + PREFIX_NAME + VALID_DEPARTMENT_LOGISTIC;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "10thou"; // alphabets not allowed for salary
    public static final String INVALID_LEAVE_DESC = " " + PREFIX_LEAVE + "100 days"; // spaces not allowed for leave
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "worker"; // 'worker' not a valid role
    public static final String INVALID_DEPARTMENT_COMMAND_TYPE = " " + PREFIX_TYPE + "idk";
    public static final String INVALID_DEPARTMENT_NAME_DESC = " " + PREFIX_NAME + "日本人";
    public static final String INVALID_DEPARTMENT_NAME_DESC_EMPTY = " " + PREFIX_NAME;
    public static final String INVALID_MANAGER_DESC = " " + PREFIX_MANAGER + "James"; // 'James' is not a manager
    public static final String INVALID_DEPARTMENT_DESC = " "
            + PREFIX_DEPARTMENT + "hubby111*"; // '111' not allowed in departments

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEmployeeDescriptor DESC_AMY;
    public static final EditCommand.EditEmployeeDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSalary(VALID_SALARY_AMY).withLeave(VALID_LEAVE_AMY).withRole(VALID_ROLE_AMY)
                .withSupervisors().withDepartments(VALID_DEPARTMENT_INVESTMENT).build();
        DESC_BOB = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withSalary(VALID_SALARY_BOB)
                .withLeave(VALID_LEAVE_BOB).withRole(VALID_ROLE_BOB).withSupervisors(VALID_MANAGER_AMY)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC, VALID_DEPARTMENT_INVESTMENT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(actualModel.getManageHr().getEmployeeList(), expectedModel.getManageHr().getEmployeeList());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the ManageHR, filtered employee list and selected employee in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ManageHr expectedManageHr = new ManageHr(actualModel.getManageHr());
        List<Employee> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEmployeeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedManageHr, actualModel.getManageHr());
        assertEquals(expectedFilteredList, actualModel.getFilteredEmployeeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the employee at the given {@code targetIndex} in the
     * {@code model}'s ManageHR.
     */
    public static void showEmployeeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEmployeeList().size());

        Employee employee = model.getFilteredEmployeeList().get(targetIndex.getZeroBased());
        final String[] splitName = employee.getName().fullName.split("\\s+");
        model.updateFilteredEmployeeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEmployeeList().size());
    }

}
