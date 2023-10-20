package transact.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static transact.logic.commands.CommandTestUtil.DESC_AMY;
import static transact.logic.commands.CommandTestUtil.DESC_BOB;
import static transact.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static transact.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static transact.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static transact.logic.commands.CommandTestUtil.assertCommandFailure;
import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.testutil.TypicalIndexes.ID_FIRST_PERSON;
import static transact.testutil.TypicalIndexes.ID_SECOND_PERSON;
import static transact.testutil.TypicalPersons.getTypicalAddressBook;
import static transact.testutil.TypicalTransactions.getTypicalTransactionBook;

import org.junit.jupiter.api.Test;

import transact.logic.Messages;
import transact.logic.commands.EditStaffCommand.EditPersonDescriptor;
import transact.model.AddressBook;
import transact.model.Model;
import transact.model.ModelManager;
import transact.model.TransactionBook;
import transact.model.UserPrefs;
import transact.model.person.Person;
import transact.testutil.EditPersonDescriptorBuilder;
import transact.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditStaffCommand.
 */
public class EditStaffCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTransactionBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder()
                .withId(model.getFilteredPersonList().get(ID_FIRST_PERSON).getPersonId()).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(ID_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TransactionBook(model.getTransactionBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(ID_FIRST_PERSON).getPersonId(), editedPerson);

        assertCommandSuccess(editStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Integer lastPersonId = ID_SECOND_PERSON;
        Person secondPerson = model.getFilteredPersonList().get(lastPersonId);

        PersonBuilder personInList = new PersonBuilder(secondPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(lastPersonId, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TransactionBook(model.getTransactionBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson.getPersonId(), editedPerson);

        assertCommandSuccess(editStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditStaffCommand editStaffCommand = new EditStaffCommand(ID_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(ID_FIRST_PERSON);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TransactionBook(model.getTransactionBook()), new UserPrefs());

        assertCommandSuccess(editStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        /*
         * Not needed for now, since we do not identify a person based on their position
         * in the list
         * showPersonAtId(model, ID_FIRST_PERSON);
         * 
         * Person personInFilteredList =
         * model.getFilteredPersonList().get(ID_FIRST_PERSON);
         * Person editedPerson = new
         * PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
         * EditStaffCommand editStaffCommand = new EditStaffCommand(ID_FIRST_PERSON,
         * new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
         * 
         * String expectedMessage =
         * String.format(EditStaffCommand.MESSAGE_EDIT_PERSON_SUCCESS,
         * Messages.format(editedPerson));
         * 
         * Model expectedModel = new ModelManager(new
         * AddressBook(model.getAddressBook()),
         * new TransactionBook(model.getTransactionBook()), new UserPrefs());
         * expectedModel.setPerson(model.getFilteredPersonList().get(0).getPersonId(),
         * editedPerson);
         * 
         * assertCommandSuccess(editStaffCommand, model, expectedMessage,
         * expectedModel);
         */
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        /*
         * Not needed, since we are not able to edit person ID which is the only way to
         * create a duplicate person
         * Person firstPerson = model.getFilteredPersonList().get(ID_FIRST_PERSON);
         * EditPersonDescriptor descriptor = new
         * EditPersonDescriptorBuilder(firstPerson).build();
         * EditStaffCommand editStaffCommand = new EditStaffCommand(ID_SECOND_PERSON,
         * descriptor);
         * 
         * assertCommandFailure(editStaffCommand, model,
         * EditStaffCommand.MESSAGE_DUPLICATE_PERSON);
         */
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        /*
         * Not needed, since we are not able to edit person ID which is the only way to
         * create a duplicate person
         * showPersonAtId(model, ID_FIRST_PERSON);
         * 
         * // edit person in filtered list into a duplicate in address book
         * Person personInList =
         * model.getAddressBook().getPersonList().get(ID_SECOND_PERSON);
         * EditStaffCommand editStaffCommand = new EditStaffCommand(ID_FIRST_PERSON,
         * new EditPersonDescriptorBuilder(personInList).build());
         * 
         * assertCommandFailure(editStaffCommand, model,
         * EditStaffCommand.MESSAGE_DUPLICATE_PERSON);
         */
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Integer outOfBoundId = model.getFilteredPersonList().size() + 1;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(outOfBoundId, descriptor);

        assertCommandFailure(editStaffCommand, model, String.format(Messages.MESSAGE_INVALID_PERSON_ID, outOfBoundId));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        /*
         * Not needed for now, since we do not identify a person based on their position
         * in the list
         * showPersonAtId(model, ID_FIRST_PERSON);
         * Integer outOfBoundId = ID_SECOND_PERSON;
         * // ensures that outOfBoundIndex is still in bounds of address book list
         * assertTrue(outOfBoundId < model.getAddressBook().getPersonList().size());
         * 
         * EditStaffCommand editStaffCommand = new EditStaffCommand(outOfBoundId,
         * new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
         * 
         * assertCommandFailure(editStaffCommand, model,
         * Messages.getInvalidPersonIndexMessageForId(outOfBoundId));
         */
    }

    @Test
    public void equals() {
        final EditStaffCommand standardCommand = new EditStaffCommand(ID_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditStaffCommand commandWithSameValues = new EditStaffCommand(ID_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(ID_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(ID_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Integer id = 1;
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditStaffCommand editStaffCommand = new EditStaffCommand(id, editPersonDescriptor);
        String expected = EditStaffCommand.class.getCanonicalName() + "{personId=" + id + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editStaffCommand.toString());
    }

}
