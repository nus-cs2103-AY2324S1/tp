package seedu.address.logic.commands.internships;

import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.internship.ListInternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListInternshipCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_list_showsCorrectList() {
        assertCommandSuccess(
                new ListInternshipCommand(),
                model,
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getUnfilteredInternshipTaskList().size()
                                + model.getUnfilteredInternshipRoleList().size()),
                expectedModel
        );
    }

}
