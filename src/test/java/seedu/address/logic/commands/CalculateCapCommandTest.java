package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_ZERO_MC;
import static seedu.address.testutil.TypicalModules.getTypicalModuleData;
import static seedu.address.testutil.TypicalModules.getTypicalModulePlan;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.moduleplan.ModulePlan;

/**
 * Contains integration tests (interaction with the Model) for CalculateCapCommand.
 */
public class CalculateCapCommandTest {

    @Test
    public void execute_calculateCap_success() {
        // Both actual and expected models should be identical
        Model actualmodel = new ModelManager(getTypicalModulePlan(), new UserPrefs(), getTypicalModuleData());
        Model expectedModel = new ModelManager(getTypicalModulePlan(), new UserPrefs(), getTypicalModuleData());

        String expectedMessage = String.format(CalculateCapCommand.MESSAGE_CALCULATION_SUCCESS, actualmodel.getCap());

        assertCommandSuccess(new CalculateCapCommand(), actualmodel, expectedMessage, expectedModel);

        // Test for zero MC modules as well (same expected message as above)
        actualmodel.addModule(MODULE_ZERO_MC);
        expectedModel.addModule(MODULE_ZERO_MC);
        assertCommandSuccess(new CalculateCapCommand(), actualmodel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModulePlan_success() {
        // Model with empty modulePlan
        Model actualmodel = new ModelManager(new ModulePlan(), new UserPrefs(), getTypicalModuleData());
        Model expectedModel = new ModelManager(new ModulePlan(), new UserPrefs(), getTypicalModuleData());

        // Expected CAP should be 0.0
        String expectedMessage = String.format(CalculateCapCommand.MESSAGE_CALCULATION_SUCCESS, 0.0);

        assertCommandSuccess(new CalculateCapCommand(), actualmodel, expectedMessage, expectedModel);
    }
}
