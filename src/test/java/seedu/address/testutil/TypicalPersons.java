package seedu.address.testutil;

import static seedu.address.testutil.TypicalApplicants.getTypicalApplicants;
import static seedu.address.testutil.TypicalMembers.getTypicalMembers;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical members.
     */
    public static AddressBook getTypicalAddressBookWithMembersApplicants() {
        AddressBook ab = new AddressBook();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }
        for (Member member : getTypicalMembers()) {
            ab.addMember(member);
        }
        return ab;
    }
}
