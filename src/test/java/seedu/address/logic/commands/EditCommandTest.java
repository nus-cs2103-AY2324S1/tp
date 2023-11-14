package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_LINKEDIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EMAIL_NOT_FOUND;
import static seedu.address.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.address.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        Email firstPersonEmail = model.getFilteredPersonList().get(0).getEmail();
        EditCommand editCommand = new EditCommand(firstPersonEmail, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, false);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withYear(VALID_YEAR_BOB)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withYear(VALID_YEAR_BOB)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB).build();

        Email firstPersonEmail = model.getFilteredPersonList().get(0).getEmail();
        EditCommand editCommand = new EditCommand(firstPersonEmail, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, false);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Email firstPersonEmail = model.getFilteredPersonList().get(0).getEmail();
        EditCommand editCommand = new EditCommand(firstPersonEmail, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(0);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, false);
    }

    @Test
    public void execute_filteredList_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        Email firstPersonEmail = model.getFilteredPersonList().get(0).getEmail();
        EditCommand editCommand = new EditCommand(firstPersonEmail,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel, false);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(0);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        Email secondPersonEmail = model.getFilteredPersonList().get(1).getEmail();
        EditCommand editCommand = new EditCommand(secondPersonEmail, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        // edit person in filtered list into a duplicate in address book
        Email firstPersonEmail = model.getFilteredPersonList().get(0).getEmail();
        Person personInList = model.getAddressBook().getPersonList().get(1);
        EditCommand editCommand = new EditCommand(firstPersonEmail,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonEmailUnfilteredList_failure() {
        Email invalidEmail = new Email("invalid@u.nus.edu");
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(invalidEmail, descriptor);

        assertCommandFailure(editCommand, model, MESSAGE_EMAIL_NOT_FOUND);
    }

    @Test
    public void equals() {
        Email amyEmail = new Email("amy@u.nus.edu");

        final EditCommand standardCommand = new EditCommand(amyEmail, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(amyEmail, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different email -> returns false
        assertFalse(standardCommand.equals(new EditCommand(EMAIL_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(EMAIL_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(EMAIL_FIRST_PERSON, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{email=" + EMAIL_FIRST_PERSON
                + ", editPersonDescriptor=" + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

    @Test
    public void equals_sameTutorials_returnsTrue() {
        // Create two edit descriptors with the same tutorials
        EditPersonDescriptor descriptorWithTutorials = new EditPersonDescriptorBuilder()
            .withTutorials("01", "02").build();
        EditPersonDescriptor descriptorCopyWithTutorials = new EditPersonDescriptorBuilder()
            .withTutorials("01", "02").build();

        // They should be considered equal even if other fields are different
        assertTrue(descriptorWithTutorials.equals(descriptorCopyWithTutorials));
    }

}
