package seedu.address.model.applicant.predicate;


import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;

/**
 * Tests that an {@code Applicant}'s fields matches the field predicates given.
 */
public class ApplicantPredicate implements Predicate<Applicant> {
    private final List<Predicate<Applicant>> predicateList;

    public ApplicantPredicate(List<Predicate<Applicant>> predicateList) {
        this.predicateList = predicateList;
    }
    @Override
    public boolean test(Applicant applicant) {
        return predicateList.stream().reduce(false, (accumulator, pred)
                -> accumulator || pred.test(applicant), (accumulator1, accumulator2) -> accumulator1 || accumulator2);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantPredicate)) {
            return false;
        }

        ApplicantPredicate otherApplicantPredicate = (ApplicantPredicate) other;
        if (predicateList.size() != otherApplicantPredicate.predicateList.size()) {
            return false;
        }

        for (int i = 0; i < predicateList.size(); i++) {
            if (!predicateList.get(i).equals(otherApplicantPredicate.predicateList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicateList", predicateList).toString();
    }
}
