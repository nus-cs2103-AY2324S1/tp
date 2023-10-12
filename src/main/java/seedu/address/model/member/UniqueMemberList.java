package seedu.address.model.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.member.exceptions.DuplicateMemberException;
import seedu.address.model.member.exceptions.MemberNotFoundException;

/**
 * A list of members that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Member#isSameMember(Member)}. As such, adding and updating of
 * persons uses Member#isSameMember(Member) for equality as to ensure that the Member being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Member#equals(Object) as
 * to ensure that the person with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Member#isSameMember(Member)
 */
public class UniqueMemberList implements Iterable<Member> {

    private final ObservableList<Member> internalList = FXCollections.observableArrayList();
    private final ObservableList<Member> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent Member as the given argument.
     */
    public boolean contains(Member toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMember);
    }

    /**
     * Adds a Member to the list.
     * The person must not already exist in the list.
     */
    public void add(Member toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMemberException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the list.
     * The member identity of {@code editedMember} must not be the same as another existing member in the list.
     */
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MemberNotFoundException();
        }

        if (!target.isSamePerson(editedMember) && contains(editedMember)) {
            throw new DuplicateMemberException();
        }

        internalList.set(index, editedMember);
    }

    /**
     * Removes the equivalent Member from the list.
     * The Member must exist in the list.
     */
    public void remove(Member toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MemberNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Member> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Member> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.member.UniqueMemberList)) {
            return false;
        }

        seedu.address.model.member.UniqueMemberList otherUniquePersonList =
                (seedu.address.model.member.UniqueMemberList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }


    /**
     * Returns true if {@code members} contains only unique persons.
     */
    private boolean membersAreUnique(List<Member> members) {
        for (int i = 0; i < members.size() - 1; i++) {
            for (int j = i + 1; j < members.size(); j++) {
                if (members.get(i).isSamePerson(members.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
