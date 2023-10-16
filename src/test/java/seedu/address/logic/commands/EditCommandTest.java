package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void findPersonToEdit_personFoundByName_returnPersonOptional() throws CommandException {
        List<Person> persons = new ArrayList<>();
        Person person = new PersonBuilder().withName("John Doe").build();
        persons.add(person);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        EditCommand editCommand = new EditCommand(new Name("John Doe"), null, descriptor);

        Optional<Person> personOptional = editCommand.findPersonToEdit(persons);

        assertEquals(person, personOptional.get());
    }

    @Test
    public void findPersonToEdit_personFoundByNric_returnPersonOptional() throws CommandException {
        List<Person> persons = new ArrayList<>();
        Person person = new PersonBuilder().withNric("S1223111B").build();
        persons.add(person);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        EditCommand editCommand = new EditCommand(null, new Nric("S1223111B"), descriptor);

        Optional<Person> personOptional = editCommand.findPersonToEdit(persons);

        assertEquals(person, personOptional.get());
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
    public void equals() {
        final EditCommand standardCommand = new EditCommand(new Name("Amy"), null, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(new Name("Amy"), null, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(new Name("John"), null, DESC_BOB)));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(new Name("John Doe"), null, descriptor);

        assertTrue(editCommand.equals(editCommand));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(new Name("John Doe"), null, descriptor);

        assertFalse(editCommand.equals(5)); // Different type
    }

    @Test
    public void findPersonToEdit_inconsistentNameAndID_throwsCommandException() {
        Person john = new PersonBuilder().withName("John Doe").withNric("S1234567A").build();
        Person jane = new PersonBuilder().withName("Jane Smith").withNric("S7654321B").build();

        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(new Name("John Doe"), new Nric("S7654321B"), descriptor);

        // Add persons to a list
        List<Person> persons = new ArrayList<>();
        persons.add(john);
        persons.add(jane);

        String expectedMessage = EditCommand.MESSAGE_INCONSISTENT_NAME_AND_ID;

        CommandException exception = assertThrows(CommandException.class, () -> editCommand.findPersonToEdit(persons));
        assertEquals(expectedMessage, exception.getMessage());
    }
}

