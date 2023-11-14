package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.tab.Tab.CONTACTS_TAB_INDEX;
import static seedu.address.commons.core.tab.Tab.CONTACT_PARAMETER;
import static seedu.address.commons.core.tab.Tab.EVENTS_PARAMETER;
import static seedu.address.commons.core.tab.Tab.EVENTS_TAB_INDEX;
import static seedu.address.commons.core.tab.Tab.FINANCE_PARAMETER;
import static seedu.address.commons.core.tab.Tab.FINANCE_TAB_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.tab.Tab;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class TabCommandTest {
    private Model model = new ModelManager();

    private Model expectedModel = new ModelManager();

    @Test
    public void execute_tabContacts_success() {
        CommandResult expectedCommandResult = new CommandResult("", false, CONTACTS_TAB_INDEX, false);
        TabCommand command = new TabCommand(Tab.fromParameter(CONTACT_PARAMETER));
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tabFinance_success() {
        CommandResult expectedCommandResult = new CommandResult("", false, FINANCE_TAB_INDEX, false);
        TabCommand command = new TabCommand(Tab.fromParameter(FINANCE_PARAMETER));
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tabEvents_success() {
        CommandResult expectedCommandResult = new CommandResult("", false, EVENTS_TAB_INDEX, false);
        TabCommand command = new TabCommand(Tab.fromParameter(EVENTS_PARAMETER));
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tabInvalid_throwsIndexOutOfBoundsException() {
        CommandResult expectedCommandResult = new CommandResult("", false, null, false);
        assertThrows(RuntimeException.class, () -> {
            TabCommand command = new TabCommand(Tab.fromParameter(""));
        });
    }

    @Test
    public void equals() {
        TabCommand command1 = new TabCommand(Tab.fromParameter(CONTACT_PARAMETER));
        TabCommand command2 = new TabCommand(Tab.fromParameter(CONTACT_PARAMETER));
        TabCommand command3 = new TabCommand(Tab.fromParameter(FINANCE_PARAMETER));

        assertEquals(command1, command1);
        assertEquals(command1, command2);
        assertNotEquals(command1, 1);
        assertNotEquals(command1, null);
        assertNotEquals(command1, command3);
    }
}
