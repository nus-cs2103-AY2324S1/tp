package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccessForListTeams;
import static seedu.address.logic.commands.CommandTestUtil.showTeamAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListTeamCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalTeamBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccessForListTeams(new ListTeamCommand(), model,
                ListTeamCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTeamAtIndex(model, INDEX_FIRST_TEAM);
        assertCommandSuccessForListTeams(new ListTeamCommand(), model,
                ListTeamCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
