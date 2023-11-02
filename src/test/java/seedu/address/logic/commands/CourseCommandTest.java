package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil.CourseOperation;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CourseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());

    @Test
    public void execute_createOperation_success() {
        CourseCommand courseCommand = new CourseCommand(CourseOperation.CREATE, "CS2103T");

        String expectedMessage = String.format(CourseCommand.MESSAGE_CREATE_SUCCESS, "CS2103T");

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.addAddressBook(new AddressBook("CS2103T"));

        assertCommandSuccess(courseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_createOperationDuplicate_throwsCommandException() {
        CourseCommand courseCommand = new CourseCommand(CourseOperation.CREATE, "CS2103T");
        model.addAddressBook(new AddressBook("CS2103T"));

        String expectedMessage = String.format(CourseCommand.MESSAGE_DUPLICATE_ADDRESS_BOOK_FAILURE, "CS2103T");

        assertCommandFailure(courseCommand, model, expectedMessage);
    }

    @Test
    public void execute_switchOperation_success() {
        model.addAddressBook(new AddressBook("CS2103T"));
        model.addAddressBook(new AddressBook("CS2101"));
        model.setActiveAddressBook("CS2101");

        CourseCommand courseCommand = new CourseCommand(CourseOperation.SWITCH, "CS2103T");
        String expectedMessage = String.format(CourseCommand.MESSAGE_SWITCH_SUCCESS, "CS2103T");

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.setActiveAddressBook("CS2103T");

        assertCommandSuccess(courseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_switchOperationInvalid_throwsCommandException() {
        CourseCommand courseCommand = new CourseCommand(CourseOperation.SWITCH, "CS2103T");

        String expectedMessage = String.format(CourseCommand.MESSAGE_NO_EXIST_FAILURE, "CS2103T");

        assertCommandFailure(courseCommand, model, expectedMessage);
    }

    @Test
    public void execute_deleteOperation_success() {
        model.setAddressBook(new AddressBook("CS2103T"));
        model.addAddressBook(new AddressBook("CS2101"));
        model.setActiveAddressBook("CS2103T");

        CourseCommand courseCommand = new CourseCommand(CourseOperation.DELETE, "CS2103T");
        String expectedMessage = String.format(CourseCommand.MESSAGE_DELETE_SUCCESS, "CS2103T");

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.setActiveAddressBook("CS2101");
        expectedModel.deleteAddressBook("CS2103T");

        assertCommandSuccess(courseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteOperationInvalid_throwsCommandException() {
        CourseCommand courseCommand = new CourseCommand(CourseOperation.DELETE, "CS2103T");

        String expectedMessage = String.format(CourseCommand.MESSAGE_NO_EXIST_FAILURE, "CS2103T");

        assertCommandFailure(courseCommand, model, expectedMessage);
    }

    @Test
    public void execute_editOperation_success() {
        model.setAddressBook(new AddressBook("CS2101"));

        CourseCommand courseCommand = new CourseCommand(CourseOperation.EDIT, "CS2103T");
        String expectedMessage = String.format(CourseCommand.MESSAGE_EDIT_SUCCESS, "CS2103T");

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook("CS2103T", expectedModel.getAddressBook()));

        assertCommandSuccess(courseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editOperationInvalid_throwsCommandException() {
        CourseCommand courseCommand = new CourseCommand(CourseOperation.EDIT, "CS2103T");
        model.addAddressBook(new AddressBook("CS2103T"));

        String expectedMessage = String.format(CourseCommand.MESSAGE_DUPLICATE_ADDRESS_BOOK_FAILURE, "CS2103T");

        assertCommandFailure(courseCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        CourseCommand firstCourseCommand = new CourseCommand(CourseOperation.CREATE, "CS2103T");
        CourseCommand secondCourseCommand = new CourseCommand(CourseOperation.CREATE, "CS2101");
        CourseCommand thirdCourseCommand = new CourseCommand(CourseOperation.SWITCH, "CS2103T");

        // same object -> returns true
        assertTrue(firstCourseCommand.equals(firstCourseCommand));

        // same values -> returns true
        CourseCommand firstCourseCommandCopy = new CourseCommand(CourseOperation.CREATE, "CS2103T");
        assertTrue(firstCourseCommand.equals(firstCourseCommandCopy));

        // different types -> returns false
        assertFalse(firstCourseCommand.equals(1));

        // null -> returns false
        assertFalse(firstCourseCommand.equals(null));

        // different course code -> returns false
        assertFalse(firstCourseCommand.equals(secondCourseCommand));

        // different operation -> returns false
        assertFalse(firstCourseCommand.equals(thirdCourseCommand));
    }
}
