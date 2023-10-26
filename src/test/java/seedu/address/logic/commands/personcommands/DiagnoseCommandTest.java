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



public class DiagnoseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexOneIllnessUnfilteredList_success() {
        Person personToDiagnose = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> existingTags = personToDiagnose.getTags();
        Set<Tag> tagToDiagnose = createTypicalIllnessSet();
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(existingTags);
        newTags.addAll(tagToDiagnose);

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person diagnosedPerson = new PersonBuilder(personToDiagnose).withTags(newTagsString).build();

        DiagnoseCommand diagnoseCommand = new DiagnoseCommand(INDEX_FIRST_PERSON, tagToDiagnose);
        String expectedMessage = String.format(DiagnoseCommand.MESSAGE_DIAGNOSE_PERSON_SUCCESS,
                Messages.format(diagnosedPerson));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), diagnosedPerson);

        assertCommandSuccess(diagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexTwoIllnessesUnfilteredList_success() {
        Person personToDiagnose = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Set<Tag> existingTags = personToDiagnose.getTags();
        Set<Tag> tagsToDiagnose = createTypicalIllnessesSet();
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(existingTags);
        newTags.addAll(tagsToDiagnose);

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person diagnosedPerson = new PersonBuilder(personToDiagnose).withTags(newTagsString).build();

        DiagnoseCommand diagnoseCommand = new DiagnoseCommand(INDEX_SECOND_PERSON, tagsToDiagnose);
        String expectedMessage = String.format(DiagnoseCommand.MESSAGE_DIAGNOSE_PERSON_SUCCESS,
                Messages.format(diagnosedPerson));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(1), diagnosedPerson);

        assertCommandSuccess(diagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexOneIllnessFilteredList_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showPersonAtIndex(model, INDEX_SECOND_PERSON);
        Person personToDiagnose = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> existingTags = personToDiagnose.getTags();
        Set<Tag> tagToDiagnose = createTypicalIllnessSet();
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(existingTags);
        newTags.addAll(tagToDiagnose);

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person diagnosedPerson = new PersonBuilder(personToDiagnose).withTags(newTagsString).build();

        DiagnoseCommand diagnoseCommand = new DiagnoseCommand(INDEX_FIRST_PERSON, tagToDiagnose);
        String expectedMessage = String.format(DiagnoseCommand.MESSAGE_DIAGNOSE_PERSON_SUCCESS,
                Messages.format(diagnosedPerson));
        //It should be editing the second person
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(1), diagnosedPerson);

        assertCommandSuccess(diagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexTwoIllnessesFilteredList_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showPersonAtIndex(model, INDEX_THIRD_PERSON);
        Person personToDiagnose = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> existingTags = personToDiagnose.getTags();
        Set<Tag> tagsToDiagnose = createTypicalIllnessesSet();
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(existingTags);
        newTags.addAll(tagsToDiagnose);

        String[] newTagsString = newTags.stream().map(x -> x.tagName).toArray(String[]::new);
        Person diagnosedPerson = new PersonBuilder(personToDiagnose).withTags(newTagsString).build();

        DiagnoseCommand diagnoseCommand = new DiagnoseCommand(INDEX_FIRST_PERSON, tagsToDiagnose);
        String expectedMessage = String.format(DiagnoseCommand.MESSAGE_DIAGNOSE_PERSON_SUCCESS,
                Messages.format(diagnosedPerson));

        //It should be editing the third person
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(2), diagnosedPerson);

        assertCommandSuccess(diagnoseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Tag> tagToDiagnose = createTypicalIllnessSet();
        DiagnoseCommand diagnoseCommand = new DiagnoseCommand(outOfBoundIndex, tagToDiagnose);

        assertCommandFailure(diagnoseCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Set<Tag> tagToDiagnose = createTypicalIllnessSet();
        DiagnoseCommand diagnoseCommand = new DiagnoseCommand(outOfBoundIndex, tagToDiagnose);

        assertCommandFailure(diagnoseCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Tag> typicalIllnessesSet = createTypicalIllnessesSet();
        final DiagnoseCommand standardCommand = new DiagnoseCommand(INDEX_FIRST_PERSON, typicalIllnessesSet);

        // same values -> returns true
        Set<Tag> illnessesWithSameValues = new HashSet<>();
        illnessesWithSameValues.add(new Tag(TYPICAL_ILLNESS_1));
        illnessesWithSameValues.add(new Tag(TYPICAL_ILLNESS_2));
        DiagnoseCommand commandWithSameValues = new DiagnoseCommand(INDEX_FIRST_PERSON, illnessesWithSameValues);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DiagnoseCommand(INDEX_SECOND_PERSON, typicalIllnessesSet)));

        // different illnesses set -> returns false
        assertFalse(standardCommand.equals(new DiagnoseCommand(INDEX_FIRST_PERSON, createTypicalIllnessSet())));
    }
}
