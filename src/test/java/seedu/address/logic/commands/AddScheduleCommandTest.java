package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddEventCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.UserBuilder;

public class AddScheduleCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_validCCa_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand("Basketball",
                "cca", "Monday 1030 1130");
        String expectedMessage = MESSAGE_SUCCESS + ("\nCCA:\n" + "Basketball"
                + " " + "Monday 1030 1130" + " to " + newUser.getName());
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCca_friendSuccess() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand("Basketball",
                "cca", Index.fromZeroBased(0), "Monday 1030 1130");
        String expectedMessage = MESSAGE_SUCCESS + ("\nCCA:\n" + "Basketball"
                + " " + "Monday 1030 1130" + " to " + friend.getName());
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModule_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand("CS2103",
                "module", "Monday 1030 1130");
        String expectedMessage = MESSAGE_SUCCESS + "\nModule:\n" + "CS2103"
                + " " + "Monday 1030 1130" + " to " + newUser.getName();

        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addScheduleCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_validModuleFriend_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand("CS2103",
                "module", Index.fromZeroBased(0), "Saturday 0000 0100");
        String expectedMessage = MESSAGE_SUCCESS + "\nModule:\n" + "CS2103"
                + " " + "Saturday 0000 0100" + " to " + friend.getName();

        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);

        assertCommandSuccess(addScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEventType_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand("CS2103",
                "sleep", "Monday 1030 1130");

        String expectedMessage = "Invalid event type!"
                + "\n Event type can only be 'Module' or 'CCA'";

        assertCommandFailure(addScheduleCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand("CS2103",
                "module", Index.fromZeroBased(100), "Monday 1030 1130");

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX + "\n"
                + "Index can be max " + "7" + "!";

        assertCommandFailure(addScheduleCommand, model, expectedMessage);
    }

}
