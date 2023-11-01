package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithNoInterviews;

class ListInterviewsTodayCommandTest {
    private Model model;
    private Model modelWithNoInterviews;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithNoInterviews(), new UserPrefs());
        modelWithNoInterviews = new ModelManager(getTypicalAddressBookWithNoInterviews(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListInterviewsTodayCommand(), model,
                ListInterviewsTodayCommand.MESSAGE_SUCCESS, modelWithNoInterviews);
    }
}