package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.TypicalApplicants.getUnsortedApplicantBook;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;

class ConfirmationCommandTest {

    @Test
    void execute() {
        Model model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        ConfirmationCommand command = new ConfirmationCommand();
        assertTrue(Objects.equals(command.execute(model), new CommandResult(ConfirmationCommand.CONFIRM)));
    }
}
