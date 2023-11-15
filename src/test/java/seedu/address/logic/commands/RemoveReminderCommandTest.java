package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalUsers.JANE;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.UserBuilder;

public class RemoveReminderCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_validEvent_success() {
        User newUser = new UserBuilder(JANE).build();
        model.setUser(newUser);
        RemoveReminderCommand removeReminderCommand = new RemoveReminderCommand("CS2103 Meeting");
        String expectedMessage = RemoveReminderCommand.MESSAGE_REMOVE_REMINDER_SUCCESS + "CS2103 Meeting";
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(removeReminderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noSuchEvent_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        RemoveReminderCommand removeReminderCommand = new RemoveReminderCommand("CS2101 Meeting");
        String expectedMessage = RemoveReminderCommand.MESSAGE_NO_EVENT;

        assertCommandFailure(removeReminderCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        model.setUser(new UserBuilder().build());
        final RemoveReminderCommand standardCommand = new RemoveReminderCommand("CS2103 Meeting");

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new RemoveReminderCommand("Walk Dog"));
    }

    @Test
    public void toStringTest() {
        RemoveReminderCommand removeReminderCommand = new RemoveReminderCommand("CS2103 Meeting");
        String expected = RemoveReminderCommand.class.getCanonicalName() + "{" + "Remove Reminder for="
                + "CS2103 Meeting" + "}";
        assertEquals(expected, removeReminderCommand.toString());
    }
}
