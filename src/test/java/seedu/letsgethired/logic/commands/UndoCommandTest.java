package seedu.letsgethired.logic.commands;

import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.InternApplicationBuilder;


public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_afterAddCommand_success() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS_UNDONE;
        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());

        model.addInternApplication(validInternApplication);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_alreadyAtLatestChange_success() {
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS_AT_LATEST_CHANGE;
        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterDeleteCommand_success() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS_UNDONE;
        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.addInternApplication(validInternApplication);

        model.addInternApplication(validInternApplication);
        model.deleteInternApplication(validInternApplication);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterEditCommand_success() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();
        InternApplicationBuilder internApplicationInList =
                new InternApplicationBuilder(validInternApplication);
        InternApplication editedInternApplication = internApplicationInList
                .withCompany(VALID_COMPANY_BYTEDANCE)
                .withRole(VALID_ROLE_BACK_END).build();
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS_UNDONE;
        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.addInternApplication(validInternApplication);
        model.addInternApplication(validInternApplication);
        model.setInternApplication(validInternApplication, editedInternApplication);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_afterClearCommand_success() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS_UNDONE;
        ModelManager expectedModel = new ModelManager(model.getInternTracker(), new UserPrefs());
        expectedModel.addInternApplication(validInternApplication);
        model.addInternApplication(validInternApplication);
        model.clearInternshipApplications();
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }
}

