package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

public class TypicalAddressBook {
    private TypicalAddressBook() {} // prevents instantiation
    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Applicant applicant : TypicalApplicants.getTypicalApplicants()) {
            ab.addPerson(applicant);
        }
        for (Interview interview : TypicalInterviews.getTypicalInterviews()) {
            ab.addInterview(interview);
        }
        return ab;
    }
}
