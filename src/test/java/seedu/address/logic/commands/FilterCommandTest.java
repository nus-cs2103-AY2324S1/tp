package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil.FilterOperation;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addValidFilter_success() {
        Tag tag = new Tag("CS2103T");
        ContainsTagPredicate predicate = new ContainsTagPredicate(tag);
        FilterCommand filterCommand = new FilterCommand(FilterOperation.ADD, tag);

        String expectedMessage = String.format(FilterCommand.MESSAGE_ADD_SUCCESS, predicate);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addFilter(predicate);

        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeValidFilter_success() {
        Tag tag = new Tag("CS2103T");
        ContainsTagPredicate predicate = new ContainsTagPredicate(tag);

        model.addFilter(predicate);

        FilterCommand filterCommand = new FilterCommand(FilterOperation.DELETE, tag);

        String expectedMessage = String.format(FilterCommand.MESSAGE_DELETE_SUCCESS, predicate);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearFilters_success() {
        Tag firstTag = new Tag("CS2103T");
        Tag secondTag = new Tag("CS2101");
        ContainsTagPredicate firstPredicate = new ContainsTagPredicate(firstTag);
        ContainsTagPredicate secondPredicate = new ContainsTagPredicate(secondTag);

        model.addFilter(firstPredicate);
        model.addFilter(secondPredicate);

        FilterCommand filterCommand = new FilterCommand(FilterOperation.CLEAR, null);

        String expectedMessage = FilterCommand.MESSAGE_CLEAR_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidOperation_throwsCommandException() {
        FilterCommand filterCommand = new FilterCommand(null, new Tag("CS2103T"));

        assertCommandFailure(filterCommand, model, FilterCommand.MESSAGE_INVALID_OPERATION_FAILURE);
    }
}
