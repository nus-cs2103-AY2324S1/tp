package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortCommandTest {

    private static final String SALARY_FIELD = "salary";
    private static final String NAME_FIELD = "name";
    private static final String OVERTIME_FIELD = "overtime";
    private static final String LEAVES_FIELD = "leaves";
    private static final String INVALID_FIELD = "invalid field";
    private static final String NO_FIELD = "";

    private static final String ASCENDING_ORDER = "ascending";
    private static final String DESCENDING_ORDER = "descending";
    private static final String INVALID_ORDER = "invalid order";
    private static final String NO_ORDER = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_sortListSalaryAscending_success() {
        SortCommand sortCommand = new SortCommand(SALARY_FIELD, ASCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SALARY_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListAscending(SALARY_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListNameAscending_success() {
        SortCommand sortCommand = new SortCommand(NAME_FIELD, ASCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, NAME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListAscending(NAME_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListOvertimeAscending_success() {
        SortCommand sortCommand = new SortCommand(OVERTIME_FIELD, ASCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, OVERTIME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListAscending(OVERTIME_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListLeavesAscending_success() {
        SortCommand sortCommand = new SortCommand(LEAVES_FIELD, ASCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, LEAVES_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListAscending(LEAVES_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListSalaryDescending_success() {
        SortCommand sortCommand = new SortCommand(SALARY_FIELD, DESCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SALARY_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListDescending(SALARY_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListNameDescending_success() {
        SortCommand sortCommand = new SortCommand(NAME_FIELD, DESCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, NAME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListDescending(NAME_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListOvertimeDescending_success() {
        SortCommand sortCommand = new SortCommand(OVERTIME_FIELD, DESCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, OVERTIME_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListDescending(OVERTIME_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortListLeavesDescending_success() {
        SortCommand sortCommand = new SortCommand(LEAVES_FIELD, DESCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, LEAVES_FIELD);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedEmployeeListDescending(LEAVES_FIELD);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidFieldSortList_failure() {
        SortCommand sortCommand = new SortCommand(INVALID_FIELD, ASCENDING_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_WRONG_FIELD, INVALID_FIELD);

        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    void execute_invalidOrderSortList_failure() {
        SortCommand sortCommand = new SortCommand(NAME_FIELD, INVALID_ORDER);

        String expectedMessage = String.format(SortCommand.MESSAGE_WRONG_ORDER, INVALID_ORDER);

        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    void execute_missingFieldSortList_failure() {
        SortCommand sortCommand = new SortCommand(NO_FIELD, ASCENDING_ORDER);

        String expectedMessage = SortCommand.MESSAGE_NO_FIELD;

        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    void execute_missingOrderSortList_failure() {
        SortCommand sortCommand = new SortCommand(NAME_FIELD, NO_ORDER);

        String expectedMessage = SortCommand.MESSAGE_NO_ORDER;

        assertCommandFailure(sortCommand, model, expectedMessage);
    }
}
