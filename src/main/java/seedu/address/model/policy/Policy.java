package seedu.address.model.policy;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a person's policy in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Policy {

    private final PolicyNumber policyNumber;
    private final PolicyDate policyIssueDate;
    private final PolicyDate policyExpiryDate;

    public Policy(PolicyNumber policyNumber, PolicyDate policyIssueDate, PolicyDate policyExpiryDate) {
        this.policyNumber = policyNumber;
        this.policyIssueDate = policyIssueDate;
        this.policyExpiryDate = policyExpiryDate;
    }

    public PolicyNumber getPolicyNumber() {
        return policyNumber;
    }

    public PolicyDate getPolicyIssueDate() {
        return policyIssueDate;
    }

    public PolicyDate getPolicyExpiryDate() {
        return policyExpiryDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        return policyNumber.equals(otherPolicy.policyNumber)
                && policyIssueDate.equals(otherPolicy.policyIssueDate)
                && policyExpiryDate.equals(otherPolicy.policyExpiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber, policyIssueDate, policyExpiryDate);
    }

    @Override
    public String toString() {
        if (policyNumber.equals(PolicyNumber.DEFAULT_VALUE)) {
            // Policy does not actually exist
            return "";
        }

        return new ToStringBuilder(this)
                .add("policy number", policyNumber)
                .add("policy issue date", policyIssueDate)
                .add("policy expiry date", policyExpiryDate)
                .toString();
    }

}
