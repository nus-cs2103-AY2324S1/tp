package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static networkbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import networkbook.model.NetworkBook;
import org.junit.jupiter.api.Test;

import networkbook.logic.Messages;
import networkbook.model.Model;
import networkbook.model.ModelManager;
import networkbook.model.UserPrefs;
import networkbook.model.person.Person;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private final Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
    private static final EditAction VALID_EDIT_ACTION = editPersonDescriptor -> {};

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditCommand(null, VALID_EDIT_ACTION));
        assertThrows(NullPointerException.class, () -> new EditCommand(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, () -> new EditCommand(null, null));
    }

    @Test
    public void execute_editName_success() {
        showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person originalPerson = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new Person(
                EditCommandUtil.VALID_NAME,
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                editPersonDescriptor -> editPersonDescriptor.setName(EditCommandUtil.VALID_NAME));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
