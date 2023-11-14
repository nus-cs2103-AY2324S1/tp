package networkbook.logic.commands.edit;

import static networkbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static networkbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static networkbook.testutil.Assert.assertThrowsAssertionError;
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
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Specialisation;
import networkbook.model.person.Tag;
import networkbook.model.util.UniqueList;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {
    private static final EditAction VALID_EDIT_ACTION = (editPersonDescriptor, index) ->
            editPersonDescriptor.setName(new Name("test"));
    private final Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());

    @Test
    public void constructor_null_throwsAssertionError() {
        assertThrowsAssertionError(() -> new EditCommand(null, VALID_EDIT_ACTION));
        assertThrowsAssertionError(() -> new EditCommand(EditCommandUtil.VALID_INDEX, null));
        assertThrowsAssertionError(() -> new EditCommand(null, null));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_commandFailure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getDisplayedPersonList().size() + 1);
        EditCommand editCommand = new EditCommand(outOfBoundIndex, VALID_EDIT_ACTION);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_changeNameToAnExistingContact_commandFailure() {
        Index lastIndex = Index.fromOneBased(model.getDisplayedPersonList().size());
        Person duplicateInModel = model.getDisplayedPersonList().get(0);
        EditCommand editCommand = new EditCommand(lastIndex, (editPersonDescriptor, index) ->
                editPersonDescriptor.setName(duplicateInModel.getName()));
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_validName_success() {
        Person originalPerson = model.getDisplayedPersonList()
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
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                        editPersonDescriptor.setName(EditCommandUtil.VALID_NAME));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(expectedModel.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPhoneAndIndex_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        UniqueList<Phone> newPhoneList = originalPerson.getPhones();
        newPhoneList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_PHONE);
        Person editedPerson = new Person(
                originalPerson.getName(),
                newPhoneList,
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setPhone(
                        EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE, TypicalIndexes.INDEX_FIRST_PERSON));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(expectedModel.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPhoneAndInvalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setPhone(
                        EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_PHONE, TypicalIndexes.INDEX_FIRST_PERSON));
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a phone",
                EditCommandUtil.INVALID_INDEX.getOneBased());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_validEmailAndIndex_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        UniqueList<Email> newEmailList = originalPerson.getEmails();
        newEmailList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_EMAIL);
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                newEmailList,
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setEmail(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL,
                        TypicalIndexes.INDEX_FIRST_PERSON));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validEmailAndInvalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setEmail(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_EMAIL,
                        TypicalIndexes.INDEX_FIRST_PERSON));
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "an email",
                EditCommandUtil.INVALID_INDEX.getOneBased());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_validLinkAndIndex_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        UniqueList<Link> newLinkList = originalPerson.getLinks();
        newLinkList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_LINK);
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                newLinkList,
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setLink(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK,
                        TypicalIndexes.INDEX_FIRST_PERSON));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validLinkAndInvalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setLink(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_LINK,
                        TypicalIndexes.INDEX_FIRST_PERSON));
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a link",
                EditCommandUtil.INVALID_INDEX.getOneBased());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_validGraduation_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                EditCommandUtil.VALID_GRADUATION,
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setGraduation(EditCommandUtil.VALID_GRADUATION));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCourseAndIndex_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        UniqueList<Course> newCourseList = originalPerson.getCourses();
        newCourseList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_COURSE);
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                newCourseList,
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setCourse(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE,
                        TypicalIndexes.INDEX_FIRST_PERSON));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCourseAndInvalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setCourse(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_COURSE,
                        TypicalIndexes.INDEX_FIRST_PERSON));
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a course",
                EditCommandUtil.INVALID_INDEX.getOneBased());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_validSpecialisationAndIndex_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        UniqueList<Specialisation> newSpecialisationList = originalPerson.getSpecialisations();
        newSpecialisationList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_SPECIALISATION);
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                newSpecialisationList,
                originalPerson.getTags(),
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setSpecialisation(EditCommandUtil.VALID_INDEX,
                        EditCommandUtil.VALID_SPECIALISATION, TypicalIndexes.INDEX_FIRST_PERSON));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSpecialisationAndInvalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setSpecialisation(EditCommandUtil.INVALID_INDEX,
                        EditCommandUtil.VALID_SPECIALISATION, TypicalIndexes.INDEX_FIRST_PERSON));
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a specialisation",
                EditCommandUtil.INVALID_INDEX.getOneBased());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_validTagAndIndex_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        UniqueList<Tag> newTagList = originalPerson.getTags();
        newTagList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_TAG);
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                newTagList,
                originalPerson.getPriority().orElse(null)
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setTag(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_TAG,
                        TypicalIndexes.INDEX_FIRST_PERSON));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTagAndInvalidIndex_throwsCommandException() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setTag(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_TAG,
                        TypicalIndexes.INDEX_FIRST_PERSON));
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a tag",
                EditCommandUtil.INVALID_INDEX.getOneBased());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_validPriority_success() {
        Person originalPerson = model.getDisplayedPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new Person(
                originalPerson.getName(),
                originalPerson.getPhones(),
                originalPerson.getEmails(),
                originalPerson.getLinks(),
                originalPerson.getGraduation().orElse(null),
                originalPerson.getCourses(),
                originalPerson.getSpecialisations(),
                originalPerson.getTags(),
                EditCommandUtil.VALID_PRIORITY
        );
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) ->
                editPersonDescriptor.setPriority(EditCommandUtil.VALID_PRIORITY));

        String expectedMessage =
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModel.setItem(model.getDisplayedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        EditAction editAction = (editPersonDescriptor, index) -> {};
        EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, editAction);
        EditCommand sameCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, editAction);
        EditCommand differentCommand = new EditCommand(
                TypicalIndexes.INDEX_FIRST_PERSON, (editPersonDescriptor, index) -> {});
        EditCommand differentIndexCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_PERSON, editAction);

        // same reference
        assertTrue(standardCommand.equals(standardCommand));

        // false for null
        assertFalse(standardCommand.equals(null));

        // different object type -> false
        assertFalse(standardCommand.equals(1));

        // equivalent commands
        assertTrue(standardCommand.equals(sameCommand));

        // different commands
        assertFalse(standardCommand.equals(differentCommand));

        // different indices
        assertFalse(standardCommand.equals(differentIndexCommand));
    }

    @Test
    public void toStringMethod() {
        EditAction editAction = (editPersonDescriptor, index) -> {};
        EditCommand command = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, editAction);
        String expectedString = command.getClass().getCanonicalName()
                + "{index=" + TypicalIndexes.INDEX_FIRST_PERSON
                + ", editAction=" + editAction + "}";
        assertEquals(expectedString, command.toString());
    }
}
