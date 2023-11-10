package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditCommand.MESSAGE_NO_CHANGE;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for the EditCommand.
 */
public class EditCommandTest {

    private final String nonExistentName = "NonExistentName";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person person = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(person);

        Person editedPerson = personInList.withPhone(VALID_PHONE_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(new Name(person.getName().toString()),
                new Id(person.getId().toString()), descriptor);

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PATIENT_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(person, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person lastPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withPhone(VALID_PHONE_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(new Name(lastPerson.getName().toString()), null, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PATIENT_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(new Name("Name"), null, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{name=Name, id=null" + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        assertFalse(descriptor.equals(null));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new Name(VALID_NAME_BOB), null, descriptor);

        assertFalse(editCommand.equals(5)); // Different type
    }

    @Test
    public void equals_equalEditPersonDescriptors_returnsTrue() {
        EditPersonDescriptor descriptor1 = new EditPersonDescriptor();
        EditPersonDescriptor descriptor2 = new EditPersonDescriptor();
        assertTrue(descriptor1.equals(descriptor2));
    }

    @Test
    public void equals_unequalEditPersonDescriptors_returnsFalse() {
        EditPersonDescriptor descriptor1 = new EditPersonDescriptor();
        EditPersonDescriptor descriptor2 = new EditPersonDescriptor();
        descriptor1.setName(new Name(VALID_NAME_BOB));
        assertFalse(descriptor1.equals(descriptor2)); // Different name
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(new Name(VALID_NAME_AMY), null, descriptor);

        assertTrue(editCommand.equals(editCommand));
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(nonExistentName).build();
        EditCommand editCommand = new EditCommand(new Name(nonExistentName), null, descriptor);

        // The expected CommandException should be thrown with the specified message
        assertThrows(CommandException.class, () -> editCommand.execute(model), EditCommand.MESSAGE_PATIENT_NOT_FOUND);
    }

    @Test
    public void execute_allNullFields_throwsCommandException() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        EditCommand editCommand = new EditCommand(null, null, descriptor);

        assertThrows(CommandException.class, () -> editCommand.execute(model), CommandUtil.MESSAGES_ALL_FIELDS_NULL);
    }

    @Test
    public void createEditedPerson_validInput_returnsEditedPerson() {
        Person personToEdit = ALICE;
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        assertEquals(editedPerson.getId(), personToEdit.getId());
        assertEquals(editedPerson.getPhone(), personToEdit.getPhone());
        assertEquals(editedPerson.getAddress(), personToEdit.getAddress());
        assertEquals(editedPerson.getMedicalHistories(), personToEdit.getMedicalHistories());
    }

    @Test
    public void execute_noChangeInEditFields_throwsCommandException() {
        Person person = model.getFilteredPersonList().get(0);

        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(person.getName(), null, descriptor);

        assertThrows(CommandException.class, () -> editCommand.execute(model), MESSAGE_NO_CHANGE);
    }

    @Test
    public void undo_successfulEditCommand() throws CommandException {
        Model model = new ModelManager();
        Person originalPerson = new PersonBuilder().build();
        Person editedPerson = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB)
               .build();

        model.addPerson(originalPerson);

        EditCommand editCommand = new EditCommand(originalPerson.getName(),
                null, new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build());

        editCommand.execute(model);

        Person personAfterEdit = model.getFilteredPersonList().get(0);
        assertEquals(editedPerson, personAfterEdit);

        // Undo the edit
        UndoableCommand undoCommand = model.popCommandFromHistory();
        undoCommand.undo(model);

        Person personAfterUndo = model.getFilteredPersonList().get(0);
        assertEquals(originalPerson, personAfterUndo);
    }
}
