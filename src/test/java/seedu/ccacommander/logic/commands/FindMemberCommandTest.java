package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW;
import static seedu.ccacommander.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;
import static seedu.ccacommander.testutil.TypicalMembers.CARL;
import static seedu.ccacommander.testutil.TypicalMembers.ELLE;
import static seedu.ccacommander.testutil.TypicalMembers.FIONA;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.model.Model;
import seedu.ccacommander.model.ModelManager;
import seedu.ccacommander.model.UserPrefs;
import seedu.ccacommander.model.member.MemberNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindMemberCommand}.
 */
public class FindMemberCommandTest {
    private Model model = new ModelManager(getTypicalCcaCommander(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCcaCommander(), new UserPrefs());

    @Test
    public void equals() {
        MemberNameContainsKeywordsPredicate firstPredicate =
                new MemberNameContainsKeywordsPredicate(Collections.singletonList("first"));
        MemberNameContainsKeywordsPredicate secondPredicate =
                new MemberNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindMemberCommand findFirstMemberCommand = new FindMemberCommand(firstPredicate);
        FindMemberCommand findSecondMemberCommand = new FindMemberCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstMemberCommand.equals(findFirstMemberCommand));

        // same values -> returns true
        FindMemberCommand findFirstMemberCommandCopy = new FindMemberCommand(firstPredicate);
        assertTrue(findFirstMemberCommand.equals(findFirstMemberCommandCopy));

        // different types -> returns false
        assertFalse(findFirstMemberCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstMemberCommand.equals(null));

        // different member -> returns false
        assertFalse(findFirstMemberCommand.equals(findSecondMemberCommand));
    }

    @Test
    public void execute_zeroKeywords_noMemberFound() {
        String expectedMessage = String.format(MESSAGE_MEMBERS_LISTED_OVERVIEW, 0);
        MemberNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindMemberCommand command = new FindMemberCommand(predicate);
        expectedModel.updateFilteredMemberList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMemberList());
    }

    @Test
    public void execute_multipleKeywords_multipleMembersFound() {
        String expectedMessage = String.format(MESSAGE_MEMBERS_LISTED_OVERVIEW, 3);
        MemberNameContainsKeywordsPredicate predicate = preparePredicate("Carl Elle Fiona");
        FindMemberCommand command = new FindMemberCommand(predicate);
        expectedModel.updateFilteredMemberList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredMemberList());
    }

    @Test
    public void toStringMethod() {
        MemberNameContainsKeywordsPredicate predicate =
                new MemberNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindMemberCommand findMemberCommand = new FindMemberCommand(predicate);
        String expected = FindMemberCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findMemberCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code MemberNameContainsKeywordsPredicate}.
     */
    private MemberNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MemberNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
