package seedu.ccacommander.model.member;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.TypicalMembers.ALICE;
import static seedu.ccacommander.testutil.TypicalMembers.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.model.shared.Name;
public class MemberInNameCollectionPredicateTest {
    @Test
    public void test_memberInCollection_returnsTrue() {
        MemberInNameCollectionPredicate predicate = new MemberInNameCollectionPredicate(
                Arrays.asList(ALICE.getName(), BENSON.getName()));

        assertTrue(predicate.test(ALICE));
        assertTrue(predicate.test(BENSON));
    }

    @Test
    public void test_memberNotInCollection_returnsFalse() {

        MemberInNameCollectionPredicate firstPredicate =
                new MemberInNameCollectionPredicate(Collections.emptyList());
        assertFalse(firstPredicate.test(ALICE));


        MemberInNameCollectionPredicate secondPredicate =
                new MemberInNameCollectionPredicate(Collections.singleton(BENSON.getName()));
        assertFalse(secondPredicate.test(ALICE));
    }

    @Test
    public void equals() {
        Set<Name> firstPredicateMemberNameSet = Collections.singleton(BENSON.getName());
        List<Name> secondPredicateMemberNameList = Collections.singletonList(BENSON.getName());
        List<Name> thirdPredicateMemberNameList = Collections.singletonList(ALICE.getName());

        MemberInNameCollectionPredicate firstPredicate =
                new MemberInNameCollectionPredicate(firstPredicateMemberNameSet);
        MemberInNameCollectionPredicate secondPredicate =
                new MemberInNameCollectionPredicate(secondPredicateMemberNameList);
        MemberInNameCollectionPredicate thirdPredicate =
                new MemberInNameCollectionPredicate(thirdPredicateMemberNameList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same members -> returns true
        MemberInNameCollectionPredicate firstPredicateCopy =
                new MemberInNameCollectionPredicate(firstPredicateMemberNameSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));


        // same members different collection type -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));


        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different members -> returns false
        assertFalse(secondPredicate.equals(thirdPredicate));
    }
}
