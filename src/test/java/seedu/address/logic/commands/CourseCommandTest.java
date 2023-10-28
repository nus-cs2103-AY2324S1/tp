package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil.CourseOperation;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;

public class CourseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());

    // @Test
    // public void execute_addValidFilter_success() {
    //     Optional<Tag> tag = Optional.of(new Tag("G02"));

    //     ContainsTagPredicate predicate = new ContainsTagPredicate(tag);
    //     CourseCommand filterCommand = new CourseCommand(CourseOperation.ADD, tag.get());

    //     String expectedMessage = String.format(CourseCommand.MESSAGE_ADD_SUCCESS, predicate);

    //     ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
    //     expectedModel.addFilter(predicate);

    //     assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    // }

    // @Test
    // public void execute_removeValidFilter_success() {
    //     Optional<Tag> tag = Optional.of(new Tag("T02"));

    //     ContainsTagPredicate predicate = new ContainsTagPredicate(tag);

    //     model.addFilter(predicate);

    //     CourseCommand filterCommand = new CourseCommand(CourseOperation.DELETE, tag.get());

    //     String expectedMessage = String.format(CourseCommand.MESSAGE_DELETE_SUCCESS, predicate);

    //     ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());

    //     assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    // }

    // @Test
    // public void execute_clearFilters_success() {
    //     Optional<Tag> firstTag = Optional.of(new Tag("G01"));
    //     Optional<Tag> secondTag = Optional.of(new Tag("G02"));

    //     ContainsTagPredicate firstPredicate = new ContainsTagPredicate(firstTag);
    //     ContainsTagPredicate secondPredicate = new ContainsTagPredicate(secondTag);

    //     model.addFilter(firstPredicate);
    //     model.addFilter(secondPredicate);

    //     CourseCommand filterCommand = new CourseCommand(CourseOperation.CLEAR, null);

    //     String expectedMessage = CourseCommand.MESSAGE_CLEAR_SUCCESS;

    //     ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());

    //     assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    // }

    // @Test
    // public void execute_invalidOperation_throwsCommandException() {
    //     CourseCommand filterCommand = new CourseCommand(null, new Tag("CS2103T"));

    //     assertCommandFailure(filterCommand, model, CourseCommand.MESSAGE_INVALID_OPERATION_FAILURE);
    // }
}
