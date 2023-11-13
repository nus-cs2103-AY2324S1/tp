package seedu.staffsnap.testutil;

import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.applicant.Applicant;

/**
 * A utility class to help with building ApplicantBook objects.
 * Example usage: <br>
 *     {@code ApplicantBook ab = new ApplicantBookBuilder().withApplicant("John", "Doe").build();}
 */
public class ApplicantBookBuilder {

    private ApplicantBook applicantBook;

    public ApplicantBookBuilder() {
        applicantBook = new ApplicantBook();
    }

    public ApplicantBookBuilder(ApplicantBook applicantBook) {
        this.applicantBook = applicantBook;
    }

    /**
     * Adds a new {@code Applicant} to the {@code ApplicantBook} that we are building.
     */
    public ApplicantBookBuilder withApplicant(Applicant applicant) {
        applicantBook.addApplicant(applicant);
        return this;
    }

    public ApplicantBook build() {
        return applicantBook;
    }
}
