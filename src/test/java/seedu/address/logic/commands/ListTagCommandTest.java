package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagFrequencyTable;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTasks;

class ListTagCommandTest {

    @Test
    public void execute_noTagsInList_showsNoTagsMessage() {
        AddressBook noTagsAddressBook = new AddressBook();
        noTagsAddressBook.addPerson(new PersonBuilder(TypicalPersons.ALICE).withTags().build());
        noTagsAddressBook.addTask(new TaskBuilder(TypicalTasks.AGENDA).withTags().build());

        Model model = new ModelManager(noTagsAddressBook, new UserPrefs());

        assertCommandSuccess(new ListTagCommand(), model, ListTagCommand.MESSAGE_NO_TAGS_FOUND, model);
    }

    @Test
    public void execute_tagsInList_showsCorrectMessage() {
        AddressBook typicalAddressBook = new AddressBook();
        typicalAddressBook.setPersons(TypicalPersons.getTypicalPersons());
        typicalAddressBook.setTasks(TypicalTasks.getTypicalTasks());

        Model model = new ModelManager(typicalAddressBook, new UserPrefs());

        TagFrequencyTable tagFrequencyTable = model.getTagFrequencyTable();
        String expectedMessage = String.format(ListTagCommand.MESSAGE_SUCCESS, tagFrequencyTable.format());

        assertCommandSuccess(new ListTagCommand(), model, expectedMessage, model);
    }
}
