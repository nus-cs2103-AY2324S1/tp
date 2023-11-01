package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ListFreeTimeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void testCommandWordNotNull() {
        assertNotNull(ListFreeTimeCommand.COMMAND_WORD);
    }

    @Test
    void testCommandWordNotNull2() {
        assertNotNull(ListFreeTimeCommand.MESSAGE_LIST_FREETIME_SUCCESS);
    }

    @Test
    void testCommandWordNotNull3() {
        assertNotNull(ListFreeTimeCommand.MESSAGE_LIST_FREETIME_SUCCESS);
    }

    @Test
    void testFormatFreeTimeSuccess() {
        ListFreeTimeCommand listFreeTimeCommand = new ListFreeTimeCommand(LocalDateTime.now());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String expectedMessage = "Free times on 01/11/2023:\n"
                + "from: 09:00 to: 17:00\n";
        assertCommandSuccess(listFreeTimeCommand, model, expectedMessage, expectedModel);
    }
}
