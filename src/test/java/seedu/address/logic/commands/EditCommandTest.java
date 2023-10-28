package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
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

        Person editedPerson = personInList.withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(new Name(person.getName().toString()),
                new Nric(person.getNric().toString()), descriptor);

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(person, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person lastPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(new Name(lastPerson.getName().toString()), null, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(new Name("Name"), null, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{name=Name, nric=null" + ", editPersonDescriptor="
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
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(new Name(VALID_NAME_AMY), null, descriptor);

        assertTrue(editCommand.equals(editCommand));
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(nonExistentName).build();
        EditCommand editCommand = new EditCommand(new Name(nonExistentName), null, descriptor);

        // The expected CommandException should be thrown with the specified message
        assertThrows(CommandException.class, () -> editCommand.execute(model), EditCommand.MESSAGE_PERSON_NOT_FOUND);
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

        assertEquals(editedPerson.getNric(), personToEdit.getNric());
        assertEquals(editedPerson.getPhone(), personToEdit.getPhone());
        assertEquals(editedPerson.getAddress(), personToEdit.getAddress());
        assertEquals(editedPerson.getMedicalHistories(), personToEdit.getMedicalHistories());
        assertEquals(editedPerson.getTags(), personToEdit.getTags());
    }
}
