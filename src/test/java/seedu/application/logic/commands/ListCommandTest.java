package seedu.application.logic.commands;

import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.FieldComparator;
import seedu.application.model.job.Role;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(new FieldComparator(FieldComparator.EMPTY_COMPARATOR_SPECIFIER)),
            model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        assertCommandSuccess(new ListCommand(new FieldComparator(FieldComparator.EMPTY_COMPARATOR_SPECIFIER)),
            model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSorted_showsSortedList() {
        FieldComparator fieldComparator = new FieldComparator(Role.ROLE_SPECIFIER);
        expectedModel.sortJobs(fieldComparator);
        assertCommandSuccess(new ListCommand(fieldComparator), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
