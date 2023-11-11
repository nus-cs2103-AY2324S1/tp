package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModuleData;
import static seedu.address.testutil.TypicalModules.getTypicalModulePlan;
import static seedu.address.testutil.TypicalModules.getTypicalModules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModulePlan(), new UserPrefs(), getTypicalModuleData());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = TypicalModules.CS2106;

        Model expectedModel = new ModelManager((getTypicalModulePlan()), new UserPrefs(), getTypicalModuleData());
        expectedModel.addModule(validModule);

        AddCommand actualCommand = new AddCommand(validModule.getModuleCode(), validModule.getYearTaken(),
            validModule.getSemesterTaken(), validModule.getGrade());

        assertCommandSuccess(actualCommand, model,
            String.format(AddCommand.MESSAGE_ADD_MODULE_SUCCESS, Messages.format(validModule)),
            expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module moduleInList = getTypicalModules().get(0);

        AddCommand actualCommand = new AddCommand(moduleInList.getModuleCode(), moduleInList.getYearTaken(),
            moduleInList.getSemesterTaken(), moduleInList.getGrade());

        assertCommandFailure(actualCommand, model,
            String.format(AddCommand.MESSAGE_DUPLICATE_MODULE, moduleInList.getModuleCode()));
    }

}
