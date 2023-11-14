package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.EditPersonDescriptorBuilder.DUMMY_TIME_INTERVAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditFreeTimeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Edits a person's freetime while other fields are unchanged.
     */
    @Test
    public void execute_success() {

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setTimeInterval(DUMMY_TIME_INTERVAL);
        EditFreeTimeCommand editFreeTimeCommand = new EditFreeTimeCommand(INDEX_SECOND_PERSON,
                                                                        1, editPersonDescriptor);
        TimeInterval bensonUsualTime = new TimeInterval(LocalTime.of(21, 15),
                LocalTime.of(22, 30));
        FreeTime updatedFreeTime = new FreeTime(List.of(DUMMY_TIME_INTERVAL, bensonUsualTime,
                bensonUsualTime, bensonUsualTime, bensonUsualTime));
        Person bensonWithNewFreeTime = new PersonBuilder().withName("Benson Meier")
                .withTelegram("@bensonnn123")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withTags("owesMoney", "friends").withFreeTime(updatedFreeTime).withCourses("CS2103T")
                .withHour(14).build();
        String expectedMessage = String.format(EditFreeTimeCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(bensonWithNewFreeTime));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setPerson(model.getFilteredPersonList().get(1), bensonWithNewFreeTime);

        assertCommandSuccess(editFreeTimeCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditFreeTimeCommand editCommand = new EditFreeTimeCommand(outOfBoundIndex, 1, descriptor);

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

        EditFreeTimeCommand editCommand = new EditFreeTimeCommand(outOfBoundIndex, 2,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Integer day = 2;
        final EditFreeTimeCommand standardCommand = new EditFreeTimeCommand(INDEX_FIRST_PERSON, day, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditFreeTimeCommand commandWithSameValues = new EditFreeTimeCommand(INDEX_FIRST_PERSON, day, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));


        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different object -> returns false
        assertFalse(standardCommand.equals(new Object()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditFreeTimeCommand(INDEX_SECOND_PERSON, 2, DESC_AMY)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditFreeTimeCommand(INDEX_SECOND_PERSON, 3, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditFreeTimeCommand(INDEX_FIRST_PERSON, 2, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        Integer day = 2;
        EditFreeTimeCommand editCommand = new EditFreeTimeCommand(index, day, editPersonDescriptor);
        String expected = EditFreeTimeCommand.class.getCanonicalName() + "{index=" + index + ", day=" + day
                + ", editPersonDescriptor=" + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
