package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.member.Member;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Member> getMemberList();

    ObservableList<Applicant> getApplicantList();
}
