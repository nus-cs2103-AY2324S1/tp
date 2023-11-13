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
import seedu.address.model.person.predicates.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

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
                new NameContainsKeywordsPredicate("");
        LicenceContainsKeywordsPredicate firstLicencePredicate =
                new LicenceContainsKeywordsPredicate("SAA1A");
        LicenceContainsKeywordsPredicate secondLicencePredicate =
                new LicenceContainsKeywordsPredicate("");
        NricContainsKeywordsPredicate firstNricPredicate =
                new NricContainsKeywordsPredicate("000A");
        NricContainsKeywordsPredicate secondNricPredicate =
                new NricContainsKeywordsPredicate("");
        PhoneContainsKeywordsPredicate firstPhonePredicate =
                new PhoneContainsKeywordsPredicate("12345678");
        PhoneContainsKeywordsPredicate secondPhonePredicate =
                new PhoneContainsKeywordsPredicate("");
        PolicyNumberContainsKeywordsPredicate firstPolicyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("A1234");
        PolicyNumberContainsKeywordsPredicate secondPolicyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("");
        TagContainsKeywordsPredicate firstTagPredicate =
                new TagContainsKeywordsPredicate("friends");
        TagContainsKeywordsPredicate secondTagPredicate =
                new TagContainsKeywordsPredicate("");
        PolicyExpiryContainsKeywordsPredicate firstPolicyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("25-12-2023");
        PolicyExpiryContainsKeywordsPredicate secondPolicyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("");
        EmailContainsKeywordsPredicate firstEmailPredicate =
                new EmailContainsKeywordsPredicate("weewooowaa@example.com");
        EmailContainsKeywordsPredicate secondEmailPredicate =
                new EmailContainsKeywordsPredicate("");
        PolicyIssueContainsKeywordsPredicate firstPolicyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("12-12-2022");
        PolicyIssueContainsKeywordsPredicate secondPolicyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("");
        CompanyContainsKeywordsPredicate firstCompanyPredicate =
                new CompanyContainsKeywordsPredicate("NTUC");
        CompanyContainsKeywordsPredicate secondCompanyPredicate =
                new CompanyContainsKeywordsPredicate("");


        FindCommand findFirstCommand = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        FindCommand findSecondCommand = new FindCommand(secondNamePredicate, secondLicencePredicate,
                secondNricPredicate, secondPhonePredicate, secondPolicyNumPredicate, secondTagPredicate,
                secondPolicyExpiryPredicate, secondEmailPredicate, secondPolicyIssuePredicate,
                secondCompanyPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // One field different means different person -> return false
        FindCommand findFirstCommandOneDiffField = new FindCommand(secondNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, secondLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                secondNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, secondPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, secondPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, secondTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
               secondPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, secondEmailPredicate, firstPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, secondPolicyIssuePredicate,
                firstCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

        findFirstCommandOneDiffField = new FindCommand(firstNamePredicate, firstLicencePredicate,
                firstNricPredicate, firstPhonePredicate, firstPolicyNumPredicate, firstTagPredicate,
                firstPolicyExpiryPredicate, firstEmailPredicate, firstPolicyIssuePredicate,
                secondCompanyPredicate);
        assertFalse(findFirstCommand.equals(findFirstCommandOneDiffField));

    }

    @Test
    public void execute_noMatchingKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("test");
        LicenceContainsKeywordsPredicate licencePredicate =
                new LicenceContainsKeywordsPredicate("SAA1B");
        NricContainsKeywordsPredicate nricPredicate =
                new NricContainsKeywordsPredicate("111A");
        PhoneContainsKeywordsPredicate phonePredicate =
                new PhoneContainsKeywordsPredicate("12345678");
        PolicyNumberContainsKeywordsPredicate policyNumPredicate =
                new PolicyNumberContainsKeywordsPredicate("A1234");
        TagContainsKeywordsPredicate tagPredicate =
                new TagContainsKeywordsPredicate("stranger");
        PolicyExpiryContainsKeywordsPredicate policyExpiryPredicate =
                new PolicyExpiryContainsKeywordsPredicate("25-12-2023");
        EmailContainsKeywordsPredicate emailPredicate =
                new EmailContainsKeywordsPredicate("weewooowaa@example.com");
        PolicyIssueContainsKeywordsPredicate policyIssuePredicate =
                new PolicyIssueContainsKeywordsPredicate("12-12-2022");
        CompanyContainsKeywordsPredicate companyPredicate =
                new CompanyContainsKeywordsPredicate("AXA");
        FindCommand command = new FindCommand(predicate, licencePredicate, nricPredicate, phonePredicate,
                policyNumPredicate, tagPredicate, policyExpiryPredicate, emailPredicate, policyIssuePredicate,
                companyPredicate);

        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(licencePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(nricPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(phonePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(policyNumPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(policyExpiryPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(emailPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(policyIssuePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        expectedModel.updateFilteredPersonList(companyPredicate);
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
        CompanyContainsKeywordsPredicate companyPredicate =
                new CompanyContainsKeywordsPredicate("NTUC");
        FindCommand findCommand = new FindCommand(predicate, licencePredicate, nricPredicate, phonePredicate,
                policyNumPredicate, tagPredicate, policyExpiryPredicate, emailPredicate, policyIssuePredicate,
                companyPredicate);
        String expected = FindCommand.class.getCanonicalName() + "{name predicate=" + predicate
                + ", licence predicate=" + licencePredicate + ", nric predicate=" + nricPredicate
                + ", phone predicate=" + phonePredicate + ", policy number predicate=" + policyNumPredicate
                + ", tag predicate=" + tagPredicate + ", policy expiry predicate=" + policyExpiryPredicate
                + ", email predicate=" + emailPredicate + ", policy issue predicate=" + policyIssuePredicate
                + ", company predicate=" + companyPredicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(userInput);
    }
}
