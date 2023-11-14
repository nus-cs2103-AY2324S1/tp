package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.DESC_AMY;
import static connectify.logic.commands.CommandTestUtil.DESC_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static connectify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static connectify.logic.commands.CommandTestUtil.assertCommandFailure;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.logic.commands.EditPersonCommand.EditPersonDescriptor;
import connectify.model.AddressBook;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.testutil.EditPersonDescriptorBuilder;
import connectify.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        editedPerson.setParentCompany(model.getFilteredCompanyList().get(0));

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY,
                descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Company expectedCompanyToEdit = expectedModel.getFilteredCompanyList().get(0);
        Company expectedCompanyWithPersonDeleted = expectedCompanyToEdit.deletePersonFromCompany(
                                                    expectedCompanyToEdit.getPersonList().get(0));
        Company expectedEditedAffliatedCompany = expectedCompanyWithPersonDeleted.addPersonToCompany(editedPerson);
        expectedModel.setCompany(expectedCompanyToEdit, expectedEditedAffliatedCompany);
        editedPerson.setParentCompany(expectedEditedAffliatedCompany);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredCompanyList().get(0).getPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(indexLastPerson, INDEX_FIRST_COMPANY,
                descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                                                        Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Company expectedCompanyToEdit = expectedModel.getFilteredCompanyList().get(0);
        Company expectedCompanyWithPersonDeleted = expectedCompanyToEdit.deletePersonFromCompany(
                expectedCompanyToEdit.getPersonList().get(indexLastPerson.getZeroBased()));
        Company expectedEditedAffliatedCompany = expectedCompanyWithPersonDeleted.addPersonToCompany(editedPerson);
        expectedModel.setCompany(expectedCompanyToEdit, expectedEditedAffliatedCompany);
        editedPerson.setParentCompany(expectedEditedAffliatedCompany);
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY,
                new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                                                        Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY,
                descriptor);

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                                                                    Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Company expectedCompanyToEdit = expectedModel.getFilteredCompanyList().get(0);
        Company expectedCompanyWithPersonDeleted = expectedCompanyToEdit.deletePersonFromCompany(
                expectedCompanyToEdit.getPersonList().get(0));
        Company expectedEditedAffliatedCompany = expectedCompanyWithPersonDeleted.addPersonToCompany(editedPerson);
        expectedModel.setCompany(expectedCompanyToEdit, expectedEditedAffliatedCompany);
        editedPerson.setParentCompany(expectedEditedAffliatedCompany);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_SECOND_PERSON, INDEX_FIRST_COMPANY,
                descriptor);

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(personInList).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_SECOND_COMPANY,
                descriptor);

        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex, outOfBoundIndex, descriptor);

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_THIRD_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex, INDEX_FIRST_COMPANY,
                descriptor);
        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPersonCommand standardCommand = new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY,
                DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditPersonCommand commandWithSameValues = new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(INDEX_SECOND_PERSON, INDEX_FIRST_COMPANY,
                DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY,
                DESC_BOB)));
    }

    @Test
    public void execute_noCompanyProvided_failure() {
        EditPersonCommand editPersonCommand = new EditPersonCommand(INDEX_FIRST_PERSON, null, DESC_AMY);
        assertCommandFailure(editPersonCommand, model, EditPersonCommand.MESSAGE_NO_COMPANY_PROVIDED);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(
                model.getFilteredCompanyList().get(0).getPersonList().size() + 1);
        EditPersonCommand editPersonCommand = new EditPersonCommand(outOfBoundIndex, INDEX_FIRST_COMPANY, DESC_AMY);
        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditPersonCommand editPersonCommand = new EditPersonCommand(index, index, editPersonDescriptor);
        String expected = EditPersonCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editPersonCommand.toString());
    }

}
