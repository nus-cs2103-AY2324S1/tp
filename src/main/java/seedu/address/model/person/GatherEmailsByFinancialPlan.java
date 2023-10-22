package seedu.address.model.person;

/**
 * Gather the email of {@code Person} if {@code Person}'s Financial Plans Names contains {@code promptFp}
 * as a substring.
 */
public class GatherEmailsByFinancialPlan implements GatherEmails {
    private final String promptFp;

    public GatherEmailsByFinancialPlan(String promptFp) {
        this.promptFp = promptFp;
    }
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
        if (!(other instanceof GatherEmailsByFinancialPlan)) {
            return false;
        }
        GatherEmailsByFinancialPlan otherGatherCommand = (GatherEmailsByFinancialPlan) other;
        return promptFp.equals(otherGatherCommand.promptFp);
    }

    @Override
    public String toString() {
        return "Financial Plan: " + promptFp;
    }
}
