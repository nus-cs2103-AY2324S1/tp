package seedu.address.logic.commands.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ListProjectCommand listProjectCommand = new ListProjectCommand();
        CommandResult commandResult = listProjectCommand.execute(model);

        assertEquals(ListProjectCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(model.getAddressBook().getProjectList(), model.getFilteredProjectList());
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        // Assume the list is filtered by some predicate
        model.updateFilteredProjectList(project -> project.getProjectDeadlines().equals("2023-01-01"));
        ListProjectCommand listProjectCommand = new ListProjectCommand();
        CommandResult commandResult = listProjectCommand.execute(model);

        assertEquals(ListProjectCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(model.getAddressBook().getProjectList(), model.getFilteredProjectList());
    }

    @Test
    public void execute_modelIsNull_throwsAssertionError() {
        ListProjectCommand listProjectCommand = new ListProjectCommand();
        assertThrows(AssertionError.class, () -> listProjectCommand.execute(null));
    }
}
