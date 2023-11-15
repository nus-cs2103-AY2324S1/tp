package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.review.StallReview;
import seedu.address.testutil.StallBuilder;
import seedu.address.testutil.StallReviewBuilder;

public class AddStallReviewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validReview_success() {

        Stall stallToAddReview = new StallBuilder().build();
        StallReview stallReviewToAdd = new StallReviewBuilder().build();
        AddStallReviewCommand command = new AddStallReviewCommand(stallReviewToAdd, INDEX_FIRST_STALL);

        String expectedMessage = String.format(AddStallReviewCommand.MESSAGE_ADD_STALL_REVIEW_SUCCESS,
                Messages.format(stallReviewToAdd));


        assertCommandSuccess(command, model, expectedMessage, CommandResult.ViewType.STALL_DETAIL, expectedModel);
    }

    @Test
    public void execute_addReviewToStallWithExistingReviews_failure() {

        StallReview stallReviewToAdd = new StallReviewBuilder().build();
        Stall stallWithReview = new StallBuilder().withStallReview(stallReviewToAdd).build();
        model.addStall(stallWithReview);
        assertTrue(stallWithReview.hasStallReview());
    }

    @Test
    public void execute_addReviewToInvalidStallIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        StallReview stallReviewToAdd = new StallReviewBuilder().build();
        AddStallReviewCommand addStallReviewCommand = new AddStallReviewCommand(stallReviewToAdd, outOfBoundIndex);
        assertCommandFailure(addStallReviewCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addReviewToNonExistentStall_throwsCommandException() {
        Index nonExistentStallIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        StallReview stallReviewToAdd = new StallReviewBuilder().build();
        AddStallReviewCommand command = new AddStallReviewCommand(stallReviewToAdd, nonExistentStallIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    // Test equality with the same object
    @Test
    public void equals_sameObject_returnsTrue() {
        StallReview stallReview = new StallReviewBuilder().build();
        AddStallReviewCommand command = new AddStallReviewCommand(stallReview, INDEX_FIRST_STALL);
        assertEquals(command, command);
    }

    // Test equality with objects with same values
    @Test
    public void equals_sameValues_returnsTrue() {
        StallReview stallReview = new StallReviewBuilder().build();
        AddStallReviewCommand command1 = new AddStallReviewCommand(stallReview, INDEX_FIRST_STALL);
        AddStallReviewCommand command2 = new AddStallReviewCommand(stallReview, INDEX_FIRST_STALL);
        assertEquals(command1, command2);
    }

    // Test non-equality with a different type of object
    @Test
    public void equals_differentTypes_returnsFalse() {
        StallReview stallReview = new StallReviewBuilder().build();
        AddStallReviewCommand command = new AddStallReviewCommand(stallReview, INDEX_FIRST_STALL);
        assertFalse(command.equals(new Object()));
    }

    // Test non-equality with null
    @Test
    public void equals_null_returnsFalse() {
        StallReview stallReview = new StallReviewBuilder().build();
        AddStallReviewCommand command = new AddStallReviewCommand(stallReview, INDEX_FIRST_STALL);
        assertFalse(command.equals(null));
    }

    // Test non-equality with different StallReview
    @Test
    public void equals_differentStallReview_returnsFalse() {
        StallReview stallReview1 = new StallReviewBuilder().withRating("4").build();
        StallReview stallReview2 = new StallReviewBuilder().withRating("5").build();
        AddStallReviewCommand command1 = new AddStallReviewCommand(stallReview1, INDEX_FIRST_STALL);
        AddStallReviewCommand command2 = new AddStallReviewCommand(stallReview2, INDEX_FIRST_STALL);
        assertNotEquals(command1, command2);
    }

    // Test non-equality with different Index
    @Test
    public void equals_differentIndex_returnsFalse() {
        StallReview stallReview = new StallReviewBuilder().build();
        AddStallReviewCommand command1 = new AddStallReviewCommand(stallReview, INDEX_FIRST_STALL);
        AddStallReviewCommand command2 = new AddStallReviewCommand(stallReview, INDEX_SECOND_STALL);
        assertNotEquals(command1, command2);
    }


}
