package networkbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.model.Model;
import networkbook.model.ModelManager;
import networkbook.model.NetworkBook;
import networkbook.model.UserPrefs;
import networkbook.model.person.Person;
import networkbook.testutil.EditPersonDescriptorBuilder;
import networkbook.testutil.PersonBuilder;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class AddCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());

    @Test
    public void execute_addAnotherNameToPerson_commandFailure() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY).build();
        AddCommand addCommand = new AddCommand(indexLastPerson, descriptor);
        String expectedMessage = AddCommand.MESSAGE_MULTIPLE_NAMES;
        CommandTestUtil.assertCommandFailure(addCommand, model, expectedMessage);
    }
    @Test
    public void execute_callAddPhoneOnFilteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .addPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        AddCommand addCommand = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_BOB).build());

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_INFO_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_callAddEmailOnFilteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .addEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        AddCommand addCommand = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withEmail(CommandTestUtil.VALID_EMAIL_BOB).build());

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_INFO_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_callAddLinkOnFilteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .addLink(CommandTestUtil.VALID_LINK_BOB).build();
        AddCommand addCommand = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withLink(CommandTestUtil.VALID_LINK_BOB).build());

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_INFO_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleGradsBeingAdded_assertionError() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withGraduation(CommandTestUtil.VALID_GRADUATION_AMY).build();
        AddCommand addCommand = new AddCommand(indexLastPerson, descriptor);
        String expectedMessage = AddCommand.MESSAGE_MULTIPLE_UNIQUE_FIELD;
        CommandTestUtil.assertCommandFailure(addCommand, model, expectedMessage);
    }

    // TODO: Update the two tests below when multiple
    //  courses and specialisations can be added. See addLinkOnFilteredList for inspiration; will be similar.
    @Test
    public void execute_addAnotherCourseToPerson_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .addCourse(CommandTestUtil.VALID_COURSE_BOB).build();
        AddCommand addCommand = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withCourse(CommandTestUtil.VALID_COURSE_BOB).build());

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_INFO_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addAnotherSpecialisationToPerson_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .addSpecialisation(CommandTestUtil.VALID_SPECIALISATION_BOB).build();
        AddCommand addCommand = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withSpecialisation(CommandTestUtil.VALID_SPECIALISATION_BOB).build());

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_INFO_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getFilteredPersonList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedFilteredList_success() {
        AddCommand addCommand =
                new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON, new EditCommand.EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_INFO_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addPriorityTwiceToPersonWithoutPriority_succeedsOnlyOnce() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        AddCommand addCommand = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withPriority(CommandTestUtil.VALID_PRIORITY_AMY).build());

        String expectedMessage = AddCommand.MESSAGE_MULTIPLE_UNIQUE_FIELD;

        CommandTestUtil.assertCommandThrowsNothing(addCommand, model); // person would have priority
        CommandTestUtil.assertCommandFailure(addCommand, model, expectedMessage); // add priority to the person again
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_commandFailure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build();
        AddCommand addCommand = new AddCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of network book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of network book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNetworkBook().getPersonList().size());

        AddCommand addCommand = new AddCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddCommand standardCommand =
                new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditPersonDescriptor copyDescriptor =
                new EditCommand.EditPersonDescriptor(CommandTestUtil.DESC_AMY);
        AddCommand commandWithSameValues = new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new AddCommand(TypicalIndexes.INDEX_SECOND_PERSON, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new AddCommand(TypicalIndexes.INDEX_FIRST_PERSON, CommandTestUtil.DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        AddCommand addCommand = new AddCommand(index, editPersonDescriptor);
        String expected = AddCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, addCommand.toString());
    }

}
