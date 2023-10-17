package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate("second");
        LicenceContainsKeywordsPredicate firstLicencePredicate =
                new LicenceContainsKeywordsPredicate("SAA1A");
        LicenceContainsKeywordsPredicate secondLicencePredicate =
                new LicenceContainsKeywordsPredicate("SAA1B");
        NricContainsKeywordsPredicate firstNricPredicate =
                new NricContainsKeywordsPredicate("000A");
        NricContainsKeywordsPredicate secondNricPredicate =
                new NricContainsKeywordsPredicate("000B");
        PhoneContainsKeywordsPredicate firstPhonePredicate =
                new PhoneContainsKeywordsPredicate("12345678");
        PhoneContainsKeywordsPredicate secondPhonePredicate =
                new PhoneContainsKeywordsPredicate("87654321");
        PolicyNumberContainsKeywordsPredicate firstPolicyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("A1234");
        PolicyNumberContainsKeywordsPredicate secondPolicyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("B4321");
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate("friends");
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate("colleagues");
        PolicyExpiryContainsKeywordsPredicate firstPolicyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("25-12-2023");
        PolicyExpiryContainsKeywordsPredicate secondPolicyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("14-02-2023");
        EmailContainsKeywordsPredicate firstEmailPredicate =
                new EmailContainsKeywordsPredicate("weewooowaa@example.com");
        EmailContainsKeywordsPredicate secondEmailPredicate =
                new EmailContainsKeywordsPredicate("heehoohaa@example.com");
        PolicyIssueContainsKeywordsPredicate firstPolicyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("12-12-2022");
        PolicyIssueContainsKeywordsPredicate secondPolicyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("11-11-2022");


        FindCommand findFirstCommand = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate);
        FindCommand findSecondCommand = new FindCommand(secondNamePredicate, secondLicencePredicate,
                secondNricPredicate, secondPhonePredicate, secondPolicyNumPredicate, secondTagPredicate,
                secondPolicyExpiryPredicate, secondEmailPredicate, secondPolicyIssuePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noMatchingKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("test");
        LicenceContainsKeywordsPredicate licencePredicate =
                new LicenceContainsKeywordsPredicate("SAA1B");
        NricContainsKeywordsPredicate nricPredicate =
                new NricContainsKeywordsPredicate("000A");
        PhoneContainsKeywordsPredicate phonePredicate =
                new PhoneContainsKeywordsPredicate("12345678");
        PolicyNumberContainsKeywordsPredicate policyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("A1234");
        TagContainsKeywordsPredicate tagPredicate =
                new TagContainsKeywordsPredicate("friends");
        PolicyExpiryContainsKeywordsPredicate policyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("25-12-2023");
        EmailContainsKeywordsPredicate emailPredicate =
                new EmailContainsKeywordsPredicate("weewooowaa@example.com");
        PolicyIssueContainsKeywordsPredicate policyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("12-12-2022");
        FindCommand command = new FindCommand(predicate, licencePredicate, nricPredicate, phonePredicate,
                policyNumPredicate, tagPredicate, policyExpiryPredicate, emailPredicate, policyIssuePredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("keyword");
        LicenceContainsKeywordsPredicate licencePredicate =
                new LicenceContainsKeywordsPredicate("SAA1B");
        NricContainsKeywordsPredicate nricPredicate =
                new NricContainsKeywordsPredicate("000A");
        PhoneContainsKeywordsPredicate phonePredicate =
                new PhoneContainsKeywordsPredicate("12345678");
        PolicyNumberContainsKeywordsPredicate policyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("A1234");
        TagContainsKeywordsPredicate tagPredicate =
                new TagContainsKeywordsPredicate("friends");
        PolicyExpiryContainsKeywordsPredicate policyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("25-12-2023");
        EmailContainsKeywordsPredicate emailPredicate =
                new EmailContainsKeywordsPredicate("weewooowaa@example.com");
        PolicyIssueContainsKeywordsPredicate policyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("12-12-2022");
        FindCommand findCommand = new FindCommand(predicate, licencePredicate, nricPredicate, phonePredicate,
                policyNumPredicate, tagPredicate, policyExpiryPredicate, emailPredicate, policyIssuePredicate);
        String expected = FindCommand.class.getCanonicalName() + "{name predicate=" + predicate
                + ", licence predicate=" + licencePredicate + ", nric predicate=" + nricPredicate
                + ", phone predicate=" + phonePredicate + ", policy number predicate=" + policyNumPredicate
                + ", tag predicate=" + tagPredicate + ", policy expiry predicate=" + policyExpiryPredicate
                + ", email predicate=" + emailPredicate + ", policy issue predicate=" + policyIssuePredicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(userInput);
    }
}
