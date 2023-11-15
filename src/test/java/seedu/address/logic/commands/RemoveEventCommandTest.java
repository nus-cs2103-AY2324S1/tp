package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.user.User;
import seedu.address.model.user.UserData;
import seedu.address.model.user.UserPrefs;
import seedu.address.testutil.UserBuilder;

public class RemoveEventCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    public void execute_validDated_success() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103 Meeting", null);
        String expectedMessage = "Dated Event 'CS2103 Meeting' deleted from your calendar!";
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);
        expectedModel.getUser().getSchedule().addDatedEvent(DatedEvent
                .newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDated_friendSuccess() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        Person friend = model.getFilteredPersonList().get(0);
        friend.getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103 Meeting",
                Index.fromZeroBased(0));
        String expectedMessage = "Dated Event 'CS2103 Meeting' deleted from Alice Pauline's calendar!";
        Model expectedModel = new ModelManager(model.getAddressBook(),
                new UserPrefs(), new UserData());
        expectedModel.setUser(newUser);
        Person expectedFriend = expectedModel.getFilteredPersonList().get(0);
        expectedFriend.getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));

        assertCommandSuccess(removeEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEventName_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2101", null);

        String expectedMessage = "Event " + "CS2101" + " does not exist!\n"
                + "Please check that you have entered the correct event name!\n";

        assertCommandFailure(removeEventCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_failure() {
        User newUser = new UserBuilder().build();
        model.setUser(newUser);
        model.getUser().getSchedule().addDatedEvent(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103 Meeting",
                Index.fromZeroBased(1000));

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX + "\n"
                + "Index can be max " + "7" + "!";

        assertCommandFailure(removeEventCommand, model, expectedMessage);
    }

    @Test
    public void listEvents_success() {
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103", null);
        ArrayList<DatedEvent> events = new ArrayList<>();
        events.add(DatedEvent.newDatedEvent("CS2103 Meeting 2023-10-10 1030 1130 y"));
        events.add(DatedEvent.newDatedEvent("Walk Dog 2023-10-10 1030 1130 n"));
        events.add(DatedEvent.newDatedEvent("Competitive sleeping 2023-10-10 1030 1130 y"));
        String expectedMessage = "DatedEvent: [CS2103 Meeting] on 2023-10-10 [Tuesday 1030 1130] Reminder: Yes\n"
                + "DatedEvent: [Walk Dog] on 2023-10-10 [Tuesday 1030 1130] Reminder: No\n"
                + "DatedEvent: [Competitive sleeping] on 2023-10-10 [Tuesday 1030 1130] Reminder: Yes\n";
        assertEquals(expectedMessage, removeEventCommand.listEvents(events));
    }

    @Test
    public void listEvents_empty() {
        RemoveEventCommand removeEventCommand = new RemoveEventCommand("CS2103", null);
        ArrayList<DatedEvent> events = new ArrayList<>();
        String expectedMessage = "";
        assertEquals(expectedMessage, removeEventCommand.listEvents(events));
    }

}
