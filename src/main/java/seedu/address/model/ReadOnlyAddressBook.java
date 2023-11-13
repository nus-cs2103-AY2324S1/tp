package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the members list.
     * This list will not contain any duplicate members.
     */
    ObservableList<Member> getMemberList();

    /**
     * Returns an unmodifiable view of the applicants list.
     * This list will not contain any duplicate applicants.
     */
    ObservableList<Applicant> getApplicantList();

    /**
     * Returns an unmodifiable view of the tag list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();
}
