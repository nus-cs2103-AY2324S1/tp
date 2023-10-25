package seedu.address.model.applicant.predicate;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

/**
 * Tests that a {@code Person}'s {@code object} matches any of the interviews' applicant given.
 */
public class ApplicantContainsInterviewPredicate implements Predicate<Applicant> {
    private final ObservableList<Interview> interviews;

    public ApplicantContainsInterviewPredicate(ObservableList<Interview> interviews) {
        this.interviews = interviews;
    }

    @Override
    public boolean test(Applicant applicant) {
        return interviews.stream()
                .anyMatch(interview -> interview.getInterviewApplicant().equals(applicant));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantContainsInterviewPredicate)) {
            return false;
        }

        ApplicantContainsInterviewPredicate otherPredicate = (ApplicantContainsInterviewPredicate) other;
        return interviews.equals(otherPredicate.interviews);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", interviews).toString();
    }
}
