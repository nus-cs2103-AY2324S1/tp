package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMembers.ALAN_MEMBER;
import static seedu.address.testutil.TypicalMembers.BETTY_MEMBER;
import static seedu.address.testutil.TypicalMembers.CHARLES_MEMBER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithMembersApplicants;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MemberContainsKeywordsPredicate;

public class FindMemberCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());

    @Test
    public void equals() {
        MemberContainsKeywordsPredicate firstPredicate =
                new MemberContainsKeywordsPredicate(Collections.singletonList("Alice"));
        MemberContainsKeywordsPredicate secondPredicate =
                new MemberContainsKeywordsPredicate(Collections.singletonList("Benson"));

        FindMemberCommand findFirstCommand = new FindMemberCommand(firstPredicate);
        FindMemberCommand findSecondCommand = new FindMemberCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMemberCommand findFirstCommandCopy = new FindMemberCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
   public void execute_zeroKeywords_noMemberFound() {
        String expectedMessage = String.format(MESSAGE_MEMBERS_LISTED_OVERVIEW, 0);
        MemberContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindMemberCommand command = new FindMemberCommand(predicate);
        expectedModel.updateFilteredMemberList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMemberList());
    }

    @Test
    public void execute_multipleKeywords_multipleMemberFound() {
        String expectedMessage = String.format(MESSAGE_MEMBERS_LISTED_OVERVIEW, 3);
        MemberContainsKeywordsPredicate predicate = preparePredicate("Alan Betty Charles");
        FindMemberCommand command = new FindMemberCommand(predicate);
        expectedModel.updateFilteredMemberList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALAN_MEMBER, BETTY_MEMBER, CHARLES_MEMBER), model.getFilteredMemberList());
    }

    @Test
    public void toStringMethod() {
        MemberContainsKeywordsPredicate predicate = new MemberContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindMemberCommand findCommand = new FindMemberCommand(predicate);
        String expected = FindMemberCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code ApplicantContainsKeywordsPredicate}.
     */
    private MemberContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MemberContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
