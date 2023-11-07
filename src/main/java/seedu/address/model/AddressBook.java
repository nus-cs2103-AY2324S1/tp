package seedu.address.model;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList<Member> members;
    private final UniquePersonList<Applicant> applicants;
    private final ObservableList<Tag> tags = FXCollections.observableArrayList();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        members = new UniquePersonList<>();
        applicants = new UniquePersonList<>();
    }

    /**
     * Default constructor.
     */
    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     *
     * @param toBeCopied The ReadOnlyAddressBook to copy from.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setMembers(List<Member> members) {
        this.members.setPersons(members);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setPersons(applicants);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     *
     * @param newData The new data.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setMembers(newData.getMemberList());
        setApplicants(newData.getApplicantList());
    }

    /// member-level methods

    /**
     * Returns true if a member with the same identity as {@code member} exists in the address book.
     *
     * @param member The member to check.
     * @return True if the member exists, false otherwise.
     */
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return members.contains(member);
    }

    /**
     * Adds a member to the address book.
     * The member must not already exist in the address book.
     *
     * @param m The member to add.
     */
    public void addMember(Member m) {
        members.add(m);
    }

    /**
     * Replaces the given member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the address book.
     *
     * @param target       The member to replace.
     * @param editedMember The member to replace with.
     */
    public void setMember(Member target, Member editedMember) {
        requireNonNull(editedMember);
        members.setPerson(target, editedMember);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key The key of the member to remove.
     */
    public void removeMember(Member key) {
        members.remove(key);
    }

    //// applicant-level methods

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the address book.
     *
     * @param applicant The applicant to check.
     * @return True if the applicant exists, false otherwise.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds an applicant to the address book.
     * The applicant must not already exist in the address book.
     *
     * @param a The applicant to add.
     */
    public void addApplicant(Applicant a) {
        applicants.add(a);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the
     * address book.
     *
     * @param target          The applicant to replace.
     * @param editedApplicant The applicant to replace with.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);
        applicants.setPerson(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key The key of the applicant to remove.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("members", members)
            .add("applicants", applicants)
            .toString();
    }

    @Override
    public ObservableList<Member> getMemberList() {
        return members.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        updateTags();
        return tags;
    }

    /**
     * Updates the tags list to contain all tags in the address book.
     */
    public void updateTags() {
        ObservableList<Member> allMembers = getMemberList();
        HashSet<Tag> allTags = new HashSet<>();
        for (Member member : allMembers) {
            allTags.addAll(member.getTags());
        }
        tags.setAll(allTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return members.equals(otherAddressBook.members)
            && applicants.equals(otherAddressBook.applicants);
    }

    @Override
    public int hashCode() {
        return hash(members.hashCode(), applicants.hashCode());
    }
}
