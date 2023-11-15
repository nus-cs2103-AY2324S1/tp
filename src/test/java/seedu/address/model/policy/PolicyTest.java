package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_BOB;

import org.junit.jupiter.api.Test;

public class PolicyTest {

    @Test
    public void equals() {
        // same values -> returns true
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        Policy amyPolicyCopy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertTrue(amyPolicy.equals(amyPolicyCopy));

        // same object -> returns true
        assertTrue(amyPolicy.equals(amyPolicy));

        // null -> returns false
        assertFalse(amyPolicy.equals(null));

        // different type -> returns false
        assertFalse(amyPolicy.equals(5));

        // different company -> returns false
        Policy p1 = new Policy(new Company(VALID_COMPANY_BOB), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertFalse(amyPolicy.equals(p1));

        // different policy number -> returns false
        Policy p2 = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_BOB),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertFalse(amyPolicy.equals(p2));

        // different policy issue date -> returns false
        Policy p3 = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_BOB), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertFalse(amyPolicy.equals(p3));

        // different policy expiry date -> returns false
        Policy p4 = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_BOB));
        assertFalse(amyPolicy.equals(p4));
    }

    @Test
    public void toDisplay_hasDefaultPolicyParameter_returnsNoPolicyFound() {
        Policy policyWithDefaultCompany = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(VALID_POLICY_NO_AMY), new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
                    new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        Policy policyWithDefaultPolicyNumber = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE), new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
                new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        Policy policyWithDefaultPolicyIssue = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE), new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        Policy policyWithDefaultPolicyExpiry = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE), new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        String expected = "No Policy Found";

        // Company parameter is default, boolean value don't matter -> returns No Policy Found
        assertEquals(expected, policyWithDefaultCompany.toDisplay(false));

        // Company parameter is default, boolean value don't matter -> returns No Policy Found
        assertEquals(expected, policyWithDefaultPolicyNumber.toDisplay(false));

        // Company parameter is default, boolean value don't matter -> returns No Policy Found
        assertEquals(expected, policyWithDefaultPolicyIssue.toDisplay(false));

        // Company parameter is default, boolean value don't matter -> returns No Policy Found
        assertEquals(expected, policyWithDefaultPolicyExpiry.toDisplay(false));
    }

    @Test
    public void toDisplay_isPersonCardIsTrue_returnsCustomisedString() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));

        String expected = "Company: " + VALID_COMPANY_AMY + "\n" + "Policy Number: " + VALID_POLICY_NO_AMY + "\n"
                + "Policy Issue Date: " + VALID_POLICY_ISSUE_DATE_AMY + "\n" + "Policy Expiry Date: "
                    + VALID_POLICY_EXPIRY_DATE_AMY;

        assertEquals(expected, amyPolicy.toDisplay(true));
    }

    @Test
    public void toDisplay_isPersonCardIsFalse_returnsCustomisedString() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));

        String expected = VALID_COMPANY_AMY + ", " + VALID_POLICY_NO_AMY + ", "
                + VALID_POLICY_ISSUE_DATE_AMY + ", " + VALID_POLICY_EXPIRY_DATE_AMY;

        assertEquals(expected, amyPolicy.toDisplay(false));
    }

    @Test
    public void policyHasDefaultParameter_policyWithDefaultValues_shouldReturnTrue() {
        Policy amyPolicy = new Policy(new Company(Company.DEFAULT_VALUE), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultParameter(), true);
    }

    @Test
    public void policyDoesNotHaveDefaultParameter_policyWithNonDefaultValues_shouldReturnFalse() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultParameter(), false);
    }

    @Test
    public void policyHasDefaultCompanyParameter_policyWithDefaultCompany_shouldReturnTrue() {
        Policy amyPolicy = new Policy(new Company(Company.DEFAULT_VALUE), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultCompanyParameter(), true);
    }

    @Test
    public void policyDoesNotHaveDefaultCompanyParameter_policyWithNonDefaultCompany_shouldReturnFalse() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultCompanyParameter(), false);
    }

    @Test
    public void policyHasDefaultNumberParameter_policyWithDefaultNumber_shouldReturnTrue() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultNumberParameter(), true);
    }

    @Test
    public void policyDoesNotHaveDefaultNumberParameter_policyWithNonDefaultNumber_shouldReturnFalse() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultCompanyParameter(), false);
    }

    @Test
    public void policyHasDefaultExpiryDateParameter_policyWithDefaultExpiryDate_shouldReturnTrue() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(PolicyDate.DEFAULT_VALUE));
        assertEquals(amyPolicy.hasDefaultExpiryDateParameter(), true);
    }

    @Test
    public void policyDoesNotHaveDefaultExpiryDateParameter_policyWithNonDefaultExpiryDate_shouldReturnFalse() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultExpiryDateParameter(), false);
    }

    @Test
    public void policyHasDefaultIssueDateParameter_policyWithDefaultIssueDate_shouldReturnTrue() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(PolicyDate.DEFAULT_VALUE), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultIssueDateParameter(), true);
    }

    @Test
    public void policyDoesNotHaveDefaultIssueDateParameter_policyWithNonDefaultIssueDate_shouldReturnFalse() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertEquals(amyPolicy.hasDefaultIssueDateParameter(), false);
    }

    @Test
    public void createsDefaultPolicy_newDefaultPolicy_shouldEqualExpected() {
        Policy expected = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE), new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Policy actual = Policy.createNewDefaultPolicy();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidIssueAndExpiryDate_policyWithInvalidDates_shouldReturnNegativeComparison() {
        Policy invalidAmyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY), new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY));
        assertTrue(invalidAmyPolicy.compareDates() < 0);
    }

    @Test
    public void validIssueAndExpiryDate_policyWithValidDates_shouldReturnPositiveComparison() {
        Policy amyPolicy = new Policy(new Company(VALID_COMPANY_AMY), new PolicyNumber(VALID_POLICY_NO_AMY),
                new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY), new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        assertTrue(amyPolicy.compareDates() > 0);
    }

}
