package seedu.ccacommander.model.member;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.TypicalMembers.ALICE;
import static seedu.ccacommander.testutil.TypicalMembers.BENSON;

import org.junit.jupiter.api.Test;

public class SameMemberPredicateTest {
    @Test
    public void testConstructor() {
        SameMemberPredicate predicate = new SameMemberPredicate(ALICE);
        assertNotNull(predicate);
    }

    @Test
    public void test_sameMember_returnsTrue() {
        // same member
        SameMemberPredicate predicate = new SameMemberPredicate(ALICE);
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_differentMember_returnsFalse() {
        // different members
        SameMemberPredicate predicate = new SameMemberPredicate(ALICE);
        assertFalse(predicate.test(BENSON));


        predicate = new SameMemberPredicate(null);

        assertFalse(predicate.test(ALICE));
    }

    @Test
    public void equals() {
        SameMemberPredicate firstPredicate = new SameMemberPredicate(ALICE);
        SameMemberPredicate secondPredicate = new SameMemberPredicate(BENSON);

        assertTrue(firstPredicate.equals(firstPredicate));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

}
