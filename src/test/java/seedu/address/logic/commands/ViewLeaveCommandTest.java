package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


class ViewLeaveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validDateWithNoEmployeeOnLeave_throwCommandException() throws CommandException {
        LocalDate validDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = validDate.format(formatter);

        ViewLeaveCommand viewLeaveCommand = new ViewLeaveCommand(validDate);

        assertCommandFailure(viewLeaveCommand, model,
                String.format(Messages.MESSAGE_NO_EMPLOYEE_ON_LEAVE, formattedDate));
    }

    @Test
    public void execute_validDateWithEmployeeOnLeave_success() throws CommandException {
        LocalDate validDate = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = validDate.format(formatter);

        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(INDEX_FIRST_PERSON, validDate);
        addLeaveCommand.execute(model);
        ViewLeaveCommand viewLeaveCommand = new ViewLeaveCommand(validDate);

        List<String> nameList = viewLeaveCommand.getNameList(model.getFilteredPersonList());
        String nameListInString = viewLeaveCommand.nameListToString(nameList);

        String expectedMessage = String.format(viewLeaveCommand.MESSAGE_SUCCESS, formattedDate, nameListInString);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(viewLeaveCommand, model, expectedMessage, expectedModel);
    }
}
