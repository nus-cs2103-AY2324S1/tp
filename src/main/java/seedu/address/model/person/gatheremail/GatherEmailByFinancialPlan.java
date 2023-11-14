//@@author AlyssaPng
package seedu.address.model.person.gatheremail;

import seedu.address.model.person.Person;

/**
 * Gathers the email of {@code Person} if {@code Person}'s Financial Plans Names contains {@code promptFp}
 * as a substring.
 */
public class GatherEmailByFinancialPlan implements GatherEmailPrompt {
    private final String promptFp;

    /**
     * Constructs a new GatherEmailByFinancial Object
     */
    public GatherEmailByFinancialPlan(String promptFp) {
        this.promptFp = promptFp;
    }

    /**
     * Gathers the email of {@code person} if prompt is a substring of any {@code person}'s financial plan names.
     */
    @Override
    public String gatherEmails(Person person) {
        return person.gatherEmailsContainsFinancialPlan(promptFp);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GatherEmailByFinancialPlan)) {
            return false;
        }

        GatherEmailByFinancialPlan otherGatherByFinancialPlan = (GatherEmailByFinancialPlan) other;
        return promptFp.equals(otherGatherByFinancialPlan.promptFp);
    }

    /**
     * Returns the String representation of GatherEmailByFinancialPlan Object.
     */
    @Override
    public String toString() {
        return "Financial Plan: " + promptFp;
    }
}
