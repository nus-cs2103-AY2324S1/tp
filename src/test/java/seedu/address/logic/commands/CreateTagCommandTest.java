package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.TEST_TAG;
import static seedu.address.testutil.TypicalTags.TEST_TAG_2;
import static seedu.address.testutil.TypicalTags.TEST_TAG_3;
import static seedu.address.testutil.TypicalTags.TEST_TAG_4;
import static seedu.address.testutil.TypicalTags.TEST_TAG_5;
import static seedu.address.testutil.TypicalTags.TEST_TAG_6;
import static seedu.address.testutil.TypicalTags.TEST_TAG_7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.UniqueTagList;

public class CreateTagCommandTest {

    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book!";
    public static final String MESSAGE_FAILURE = "Oops! You've reached the maximum limit for categories";
    private Model model = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());
    private UniqueTagList uniqueTagList = new UniqueTagList();
    @BeforeEach
    public void clearTestData() {
        if (uniqueTagList.contains(TEST_TAG)) {
            uniqueTagList.remove(TEST_TAG);
        }
        if (uniqueTagList.contains(TEST_TAG_2)) {
            uniqueTagList.remove(TEST_TAG_2);
        }
        if (uniqueTagList.contains(TEST_TAG_3)) {
            uniqueTagList.remove(TEST_TAG_3);
        }
        if (uniqueTagList.contains(TEST_TAG_4)) {
            uniqueTagList.remove(TEST_TAG_4);
        }
        if (uniqueTagList.contains(TEST_TAG_5)) {
            uniqueTagList.remove(TEST_TAG_5);
        }
        if (uniqueTagList.contains(TEST_TAG_6)) {
            uniqueTagList.remove(TEST_TAG_6);
        }
        if (uniqueTagList.contains(TEST_TAG_7)) {
            uniqueTagList.remove(TEST_TAG_7);
        }

    }

    @Test
    public void execute_createDuplicateTag_failure() {
        uniqueTagList.add(TEST_TAG);
        String[] tagParams = {"role developer"};
        CreateTagCommand createTagCommand = new CreateTagCommand(tagParams);
        assertCommandFailure(createTagCommand, model, MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_tagCategoriesExceedLimit_failure() {
        uniqueTagList.add(TEST_TAG);
        uniqueTagList.add(TEST_TAG_2);
        uniqueTagList.add(TEST_TAG_3);
        uniqueTagList.add(TEST_TAG_4);
        uniqueTagList.add(TEST_TAG_5);
        uniqueTagList.add(TEST_TAG_6);
        String[] tagParams = {"category7 test"};
        CreateTagCommand createTagCommand = new CreateTagCommand(tagParams);
        assertCommandFailure(createTagCommand, model, MESSAGE_FAILURE);
    }

    @Test
    public void execute_existingCategory_success() throws CommandException {
        uniqueTagList.add(TEST_TAG);
        uniqueTagList.add(TEST_TAG_2);
        uniqueTagList.add(TEST_TAG_3);
        uniqueTagList.add(TEST_TAG_4);
        uniqueTagList.add(TEST_TAG_5);
        uniqueTagList.add(TEST_TAG_6);
        String[] tagParams = {"employment fulltime"};
        CreateTagCommand createTagCommand = new CreateTagCommand(tagParams);

        createTagCommand.execute(model);
        assertEquals(7, uniqueTagList.asUnmodifiableObservableList().size());
    }

    @Test
    public void execute_newCategory_success() throws CommandException {
        String[] tagParams = {"employment intern"};
        CreateTagCommand createTagCommand = new CreateTagCommand(tagParams);
        createTagCommand.execute(model);
        assertEquals(1, uniqueTagList.asUnmodifiableObservableList().size());
    }

}
