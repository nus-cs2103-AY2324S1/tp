package seedu.lovebook.logic.commands;

import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalDates.getTypicalLoveBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;

class ShowPrefCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
        expectedModel = new ModelManager(model.getLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    }
    @Test
    public void execute_showPreference() {
        assertCommandSuccess(new ShowPrefCommand(), model, ShowPrefCommand.MESSAGE_SUCCESS
                + "Age: 21; Height: 170; Income: 10000; Horoscope: ARIES", expectedModel);
    }
}
