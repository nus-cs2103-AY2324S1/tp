package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVELIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_HOURS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BOB;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkList;
import seedu.address.testutil.EmployeeBuilder;

public class DeleteRemarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_remarkExist_success() {
        Remark remark = BOB.getRemarkList().getRemark(0);
        DeleteRemarkCommand addRemarkCommand = new DeleteRemarkCommand(BOB.getId(), remark);
        RemarkList editedRemarkList = new RemarkList(BOB.getRemarkList().remarkList);
        editedRemarkList.deleteRemark(remark);
        Employee editedEmployee = new EmployeeBuilder().withName(VALID_NAME_BOB)
                .withPosition(VALID_POSITION_BOB)
                .withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withDepartments(VALID_DEPARTMENT_FINANCE, VALID_DEPARTMENT_IT)
                .withSalary(VALID_SALARY_BOB)
                .withOvertimeHours(VALID_OVERTIME_HOURS_BOB)
                .withLeaveList(VALID_LEAVELIST_BOB)
                .withRemarkList(editedRemarkList.remarkList)
                .build();
        String expectedMessage = String.format(DeleteRemarkCommand.MESSAGE_SUCCESS,
                Messages.formatRemarks(editedEmployee));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        model.addEmployee(BOB);
        expectedModel.addEmployee(editedEmployee);

        assertCommandSuccess(addRemarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_remarkNotExist_failure() {
        Remark remark = new Remark("good worker");
        DeleteRemarkCommand deleteRemarkCommand = new DeleteRemarkCommand(BOB.getId(), remark);

        model.addEmployee(BOB);

        assertCommandFailure(deleteRemarkCommand, model, DeleteRemarkCommand.MESSAGE_NONEXISTENT_REMARK);
    }

    @Test
    void equals() {
        Remark remark = new Remark("good worker");
        Remark remarkUppercase = new Remark("GOOD WORKER");

        DeleteRemarkCommand firstCommand = new DeleteRemarkCommand(BOB.getId(), remark);
        DeleteRemarkCommand secondCommand = new DeleteRemarkCommand(BOB.getId(), remarkUppercase);
        DeleteRemarkCommand thirdCommand = new DeleteRemarkCommand(ALICE.getId(), remark);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeleteRemarkCommand copyCommand = new DeleteRemarkCommand(BOB.getId(), remark);
        assertTrue(firstCommand.equals(copyCommand));

        // different case -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different employee -> false
        assertFalse(firstCommand.equals(thirdCommand));
    }
}
