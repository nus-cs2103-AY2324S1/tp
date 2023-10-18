package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

class RemarkCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Remark remark = new Remark(VALID_REMARK_AMY);
        RemarkCommand remarkCommand = new RemarkCommand(indexLastPerson, remark);

        Person editedPerson = new Person(
                lastPerson.getName(), lastPerson.getPhone(), lastPerson.getEmail(),
                lastPerson.getStatus(), remark, lastPerson.getTags());

        CommandResult commandResult = new CommandResult(String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS,
                Messages.format(editedPerson)));
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, commandResult, expectedModel);
    }

    @Test
    void execute_deleteRemarkUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Remark remark = new Remark(VALID_REMARK_BOB);
        RemarkCommand remarkCommand = new RemarkCommand(indexLastPerson, remark);

        Person editedPerson = new Person(
                lastPerson.getName(), lastPerson.getPhone(), lastPerson.getEmail(),
                lastPerson.getStatus(), remark, lastPerson.getTags());

        CommandResult commandResult = new CommandResult(String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                Messages.format(editedPerson)));
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, commandResult, expectedModel);
    }
}
