package swe.context.logic.commands;

import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.logic.commands.CommandTestUtil.showContactAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());
        expectedModel = new ModelManager(model.getContacts(), new Settings());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, Messages.LIST_COMMAND_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);
        assertCommandSuccess(new ListCommand(), model, Messages.LIST_COMMAND_SUCCESS, expectedModel);
    }
}
