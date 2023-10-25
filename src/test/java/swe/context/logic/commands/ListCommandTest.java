package swe.context.logic.commands;

import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.logic.commands.CommandTestUtil.showContactAtIndex;
import static swe.context.testutil.TestData.IndexContact.FIRST_CONTACT;
import static swe.context.testutil.TestData.Valid.Contact.getTypicalContacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContacts(), new Settings());
        expectedModel = new ModelManager(model.getContacts(), new Settings());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, Messages.LIST_COMMAND_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showContactAtIndex(model, FIRST_CONTACT);
        assertCommandSuccess(new ListCommand(), model, Messages.LIST_COMMAND_SUCCESS, expectedModel);
    }
}
