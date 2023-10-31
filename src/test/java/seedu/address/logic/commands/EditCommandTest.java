package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.MonthDay;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // Heuristic: Each Valid Input at Least Once in a Positive Test Case.
        Person editedPerson = new PersonBuilder().withName("Benjamin")
                .withAddress("311, Clementi Ave 1, #02-25")
                .withEmail("benjamin@example.com")
                .withPhone("98765431")
                .withBirthday(MonthDay.of(11, 10))
                .withLinkedin("benjamin")
                .withSecondaryEmail("benjamin@gmail.com")
                .withTelegram("@benjamin")
                .withTags("bestFriends")
                .build();
        EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setName(new Name("Benjamin"));
        descriptor.setAddress(new Address("311, Clementi Ave 1, #02-25"));
        descriptor.setEmail(new Email("benjamin@example.com"));
        descriptor.setPhone(new Phone("98765431"));
        descriptor.setBirthday(new Birthday(MonthDay.of(11, 10)));
        descriptor.setLinkedin(new Linkedin("benjamin"));
        descriptor.setSecondaryEmail(new Email("benjamin@gmail.com"));
        descriptor.setTelegram(new Telegram("@benjamin"));
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("bestFriends"));
        descriptor.setTags(tags);

        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        try {
            CommandResult commandResult = editCommand.execute(model);
            assertEquals(commandResult, new CommandResult(expectedMessage));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());

        Person personInFilteredList = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setName(new Name(VALID_NAME_BOB));
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personInFilteredList, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editEmptyFields_failure() {
        // Heuristic applied: No More Than One Invalid Input In A Test Case

        // Birthday of specified person is empty.
        EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setBirthday(new Birthday(MonthDay.of(6, 9)));
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_USE_ADDALT_COMMAND, "Birthday ");
        assertCommandFailure(editCommand, model, expectedMessage);

        // Telegram of specified person is empty.
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setTelegram(new Telegram("@alice"));
        editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        expectedMessage = String.format(EditCommand.MESSAGE_USE_ADDALT_COMMAND, "Telegram ");
        assertCommandFailure(editCommand, model, expectedMessage);

        // Linkedin of specified person is empty.
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("alice"));
        editCommand = new EditCommand(Index.fromOneBased(3), descriptor);
        expectedMessage = String.format(EditCommand.MESSAGE_USE_ADDALT_COMMAND, "Linkedin ");
        assertCommandFailure(editCommand, model, expectedMessage);

        // Secondary Email of specified person is empty.
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setSecondaryEmail(new Email("alice@email.com"));
        editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        expectedMessage = String.format(EditCommand.MESSAGE_USE_ADDALT_COMMAND, "Secondary Email ");
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_editResultsNoChange_failure() {
        // Heuristic applied: No More Than One Invalid Input In A Test Case

        // Invalid input refers to filling in the exact same field value of the specified person.

        // Name
        EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setName(new Name("Benson Meier"));
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Address
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setAddress(new Address("311, Clementi Ave 2, #02-25"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Email
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setEmail(new Email("johnd@example.com"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Phone
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setPhone(new Phone("98765432"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Birthday
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setBirthday(new Birthday(MonthDay.of(10, 10)));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Telegram
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setTelegram(new Telegram("@benson"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Linkedin
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("bensonmeier"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // SecondaryEmail
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setSecondaryEmail(new Email("ben10@gmail.com"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

        // Tags
        descriptor = new EditCommand.EditPersonDescriptor();
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("owesMoney"));
        tags.add(new Tag("friends"));
        descriptor.setTags(tags);
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        assertCommandFailure(editCommand, model, String.format(EditCommand.MESSAGE_NO_CHANGE, "Benson Meier"));

    }

    @Test
    public void execute_editEmail_failure() {
        // All pair strategy

        // Set secondary email same as primary email.
        EditPersonDescriptor descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setSecondaryEmail(new Email("johnd@example.com"));
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_DUPLICATE_EMAIL, "Benson Meier");
        assertCommandFailure(editCommand, model, expectedMessage);

        // Set primary email same as secondary email.
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setEmail(new Email("ben10@gmail.com"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        expectedMessage = String.format(EditCommand.MESSAGE_DUPLICATE_EMAIL, "Benson Meier");
        assertCommandFailure(editCommand, model, expectedMessage);

        // Set both primary and secondary email to be the same.
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setEmail(new Email("ben@gmail.com"));
        descriptor.setSecondaryEmail(new Email("ben@gmail.com"));
        editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
        expectedMessage = String.format(EditCommand.MESSAGE_DUPLICATE_EMAIL, "Benson Meier");
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(index, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
