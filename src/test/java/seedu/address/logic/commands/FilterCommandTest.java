package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalEmployees.getTypicalManageHr;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalManageHr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalManageHr(), new UserPrefs());

}
