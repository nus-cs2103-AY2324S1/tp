package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ConsultationListBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ViewAssignmentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), new ConsultationListBook(), new GradedTestListBook());

    @Test
    public void execute_viewAssignments_success() throws CommandException {
        Index targetIndex = INDEX_FIRST_PERSON;
        List<Person> lastShownList = model.getFilteredPersonList();
        Person person = lastShownList.get(targetIndex.getZeroBased());

        ViewAssignmentsCommand viewAssignmentsCommand = new ViewAssignmentsCommand(targetIndex);
        CommandResult expectedResult = viewAssignmentsCommand.execute(model);
        String expectedMessage = String.format(ViewAssignmentsCommand.MESSAGE_SUCCESS, person.getName());
        assertTrue(expectedResult.equals(new CommandResult(expectedMessage, CommandType.VIEW_ASSIGNMENTS)));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewAssignmentsCommand viewAssignmentsCommand = new ViewAssignmentsCommand(outOfBoundIndex);

        assertCommandFailure(viewAssignmentsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        ViewAssignmentsCommand viewAssignmentsCommand = new ViewAssignmentsCommand(outOfBoundIndex);

        assertCommandFailure(viewAssignmentsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void getCommandType() {
        ViewAssignmentsCommand command = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);

        assertEquals(command.getCommandType(), CommandType.VIEW_ASSIGNMENTS);
    }

    @Test
    public void getIndexCommand() {
        ViewAssignmentsCommand viewAssignmentsCommand = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);
        assertEquals(viewAssignmentsCommand.getIndex(), INDEX_FIRST_PERSON);
    }

    @Test
    public void equals_sameObject_success() {
        ViewAssignmentsCommand viewAssignmentsCommand = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);
        assertEquals(viewAssignmentsCommand, viewAssignmentsCommand);
    }

    @Test
    public void equals_differentObjectType_failure() {
        ViewAssignmentsCommand viewAssignmentsCommand = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);
        assertFalse(viewAssignmentsCommand.equals(5));
    }

    @Test
    public void equals_differentObjectSameContent_success() {
        ViewAssignmentsCommand viewAssignmentsCommandFirst = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);
        ViewAssignmentsCommand viewAssignmentsCommandSecond = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);
        assertEquals(viewAssignmentsCommandFirst, viewAssignmentsCommandSecond);
    }

    @Test
    public void equals_differentObjectDifferentContent_failure() {
        ViewAssignmentsCommand viewAssignmentsCommandFirst = new ViewAssignmentsCommand(INDEX_FIRST_PERSON);
        ViewAssignmentsCommand viewAssignmentsCommandSecond = new ViewAssignmentsCommand(INDEX_SECOND_PERSON);
        assertNotEquals(viewAssignmentsCommandFirst, viewAssignmentsCommandSecond);
    }
}
