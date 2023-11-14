package seedu.address.logic.commands.tag;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalJobFestGo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewTagsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalJobFestGo(), new UserPrefs());
        expectedModel = new ModelManager(model.getJobFestGo(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        expectedModel.switchToTagsScreen(true);
        expectedModel.switchToContactsScreen(false);
        expectedModel.switchToEventsScreen(false);
        assertCommandSuccess(new ViewTagsCommand(), model, ViewTagsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
