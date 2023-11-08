package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.person.PersonType.PATIENT;
import static seedu.address.model.person.PersonType.SPECIALIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void executePatient_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(PATIENT), model, ListCommand.PATIENT_MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void executeSpecialist_listIsNotFiltered_showsSameList() {
        expectedModel.updateFilteredPersonList(SPECIALIST.getSearchPredicate());
        assertCommandSuccess(new ListCommand(SPECIALIST), model, ListCommand.SPECIALIST_MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void executePatient_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(PATIENT), model, ListCommand.PATIENT_MESSAGE_SUCCESS, expectedModel);
    }
}
