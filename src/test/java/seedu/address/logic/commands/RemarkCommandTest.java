package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    //    @Test
    //    public void execute_addRemarkUnfilteredList_success() {
    //        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();
    //
    //RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(editedPerson.getRemark().value));
    //
    //        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
    //
    //        Model expectedModel = new ModelManager(
    //                new AddressBook(model.getAddressBook()), new TeamBook(model.getTeamBook()), new UserPrefs());
    //        expectedModel.setPerson(firstPerson, editedPerson);
    //
    //        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    //    }
}
