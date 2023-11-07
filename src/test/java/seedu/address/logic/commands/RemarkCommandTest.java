package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.index.Index.fromZeroBased;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    private final String messageAddRemarkSuccess = "Added remark to Person: seedu.address.model.person."
            + "Person{name=Alice Pauline, phone=94351253, email=alice@example.com, address=123, "
            + "Jurong West Ave 6, #08-111, remark=Likes to swim, tags=[[friends]], identitycode=1}";
    private final String messageRemoveRemarkSuccess = "Removed remark from Person: seedu.address.model."
            + "person.Person{name=Alice Pauline, phone=94351253, email=alice@example.com, address=123, "
            + "Jurong West Ave 6, #08-111, remark=, tags=[[friends]], identitycode=1}";

    @Test
    public void execute_addRemark_success() throws CommandException {
        Person personToEdit = new PersonBuilder().build();
        Remark remark = new Remark("Likes to swim");
        RemarkCommand remarkCommand = new RemarkCommand(fromZeroBased(0), remark);

        CommandResult commandResult = remarkCommand.execute(model);

        assertEquals(messageAddRemarkSuccess, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_deleteRemark_success() throws CommandException {
        Person personToEdit = new PersonBuilder().withRemark("Likes to swim").build();
        Remark remark = new Remark(""); // empty string to indicate deleting the remark
        RemarkCommand remarkCommand = new RemarkCommand(fromZeroBased(0), remark);

        CommandResult commandResult = remarkCommand.execute(model);

        assertEquals(messageRemoveRemarkSuccess, commandResult.getFeedbackToUser());
    }
}
