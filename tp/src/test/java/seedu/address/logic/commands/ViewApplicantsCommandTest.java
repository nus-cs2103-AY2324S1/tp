package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBookWithApplicants;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewApplicantsCommand.
 */
public class ViewApplicantsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithApplicants(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_applicantsIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewApplicantsCommand(), model, ViewApplicantsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_applicantsIsFiltered_showsEverything() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ViewApplicantsCommand(), model, ViewApplicantsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
