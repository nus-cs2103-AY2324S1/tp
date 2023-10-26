package seedu.address.logic.commands.personcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_1;
import static seedu.address.logic.commands.CommandTestUtil.TYPICAL_ILLNESS_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.createTypicalIllnessSet;
import static seedu.address.logic.commands.CommandTestUtil.createTypicalIllnessesSet;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;



public class UndiagnoseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexOneIllnessUnfilteredList_success() {
        Person personToUndiagnose = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> existingTags = personToUndiagnose.getTags();
        Tag tagToUndiagnose = existingTags.stream().findFirst().orElseThrow();
        Set<Tag> tagToRemove = new HashSet<>();
        tagToRemove.add(tagToUndiagnose);
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(existingTags);
        newTags.remove(tagToUndiagnose);

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person undiagnosedPerson = new PersonBuilder(personToUndiagnose).withTags(newTagsString).build();

        UndiagnoseCommand undiagnoseCommand = new UndiagnoseCommand(INDEX_FIRST_PERSON, tagToRemove);
        String expectedMessage = String.format(UndiagnoseCommand.MESSAGE_UNDIAGNOSE_PERSON_SUCCESS,
                Messages.format(undiagnosedPerson));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), undiagnosedPerson);

        assertCommandSuccess(undiagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexAllIllnessesUnfilteredList_success() {
        Person personToUndiagnose = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Set<Tag> existingTags = personToUndiagnose.getTags();
        Set<Tag> newTags = new HashSet<>();

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person undiagnosedPerson = new PersonBuilder(personToUndiagnose).withTags(newTagsString).build();

        UndiagnoseCommand undiagnoseCommand = new UndiagnoseCommand(INDEX_SECOND_PERSON, existingTags);
        String expectedMessage = String.format(UndiagnoseCommand.MESSAGE_UNDIAGNOSE_PERSON_SUCCESS,
                Messages.format(undiagnosedPerson));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(1), undiagnosedPerson);

        assertCommandSuccess(undiagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexOneIllnessFilteredList_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person personToUndiagnose = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> existingTags = personToUndiagnose.getTags();
        Tag tagToUndiagnose = existingTags.stream().findFirst().orElseThrow();
        Set<Tag> tagToRemove = new HashSet<>();
        tagToRemove.add(tagToUndiagnose);
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(existingTags);
        newTags.remove(tagToUndiagnose);

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person undiagnosedPerson = new PersonBuilder(personToUndiagnose).withTags(newTagsString).build();

        UndiagnoseCommand undiagnoseCommand = new UndiagnoseCommand(INDEX_FIRST_PERSON, tagToRemove);
        String expectedMessage = String.format(UndiagnoseCommand.MESSAGE_UNDIAGNOSE_PERSON_SUCCESS,
                Messages.format(undiagnosedPerson));
        //It should be editing the second person
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(1), undiagnosedPerson);

        assertCommandSuccess(undiagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexTwoIllnessesFilteredList_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showPersonAtIndex(model, INDEX_THIRD_PERSON);
        Person personToUndiagnose = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> existingTags = personToUndiagnose.getTags();
        Set<Tag> newTags = new HashSet<>();

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person undiagnosedPerson = new PersonBuilder(personToUndiagnose).withTags(newTagsString).build();

        UndiagnoseCommand undiagnoseCommand = new UndiagnoseCommand(INDEX_FIRST_PERSON, existingTags);
        String expectedMessage = String.format(UndiagnoseCommand.MESSAGE_UNDIAGNOSE_PERSON_SUCCESS,
                Messages.format(undiagnosedPerson));

        //It should be editing the third person
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(2), undiagnosedPerson);

        assertCommandSuccess(undiagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Tag> tagToUndiagnose = createTypicalIllnessSet();
        UndiagnoseCommand undiagnoseCommand = new UndiagnoseCommand(outOfBoundIndex, tagToUndiagnose);

        assertCommandFailure(undiagnoseCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Set<Tag> tagToUndiagnose = createTypicalIllnessSet();
        UndiagnoseCommand undiagnoseCommand = new UndiagnoseCommand(outOfBoundIndex, tagToUndiagnose);

        assertCommandFailure(undiagnoseCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Tag> typicalIllnessesSet = createTypicalIllnessesSet();
        final UndiagnoseCommand standardCommand = new UndiagnoseCommand(INDEX_FIRST_PERSON, typicalIllnessesSet);

        // same values -> returns true
        Set<Tag> illnessesWithSameValues = new HashSet<>();
        illnessesWithSameValues.add(new Tag(TYPICAL_ILLNESS_1));
        illnessesWithSameValues.add(new Tag(TYPICAL_ILLNESS_2));
        UndiagnoseCommand commandWithSameValues = new UndiagnoseCommand(INDEX_FIRST_PERSON, illnessesWithSameValues);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UndiagnoseCommand(INDEX_SECOND_PERSON, typicalIllnessesSet)));

        // different illnesses set -> returns false
        assertFalse(standardCommand.equals(new UndiagnoseCommand(INDEX_FIRST_PERSON, createTypicalIllnessSet())));
    }
}
