package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.StallBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddStallCommand}.
 */
public class AddStallCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_newStall_success() {
        Stall validStall = new StallBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStall(validStall);

        assertCommandSuccess(new AddStallCommand(validStall), model,
                String.format(AddStallCommand.MESSAGE_SUCCESS, Messages.format(validStall)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStall_throwsCommandException() {
        Stall stallInList = model.getAddressBook().getStallList().get(0);
        assertCommandFailure(new AddStallCommand(stallInList), model,
                AddStallCommand.MESSAGE_DUPLICATE_STALL);
    }

}
