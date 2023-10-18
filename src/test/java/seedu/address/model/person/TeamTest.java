package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TeamTest {

    @Test
    public void constructor_validParameters_createsTeam() {
        Team aliceTeam = new Team(ALICE.getIdentityCode(), "Alice's Team");

        assertTrue(aliceTeam != null);
    }

    @Test
    public void equals() {
        Team aliceTeam = new Team(ALICE.getIdentityCode(), "Alice's Team");

        // same object -> returns true
        assertTrue(aliceTeam.equals(aliceTeam));

        // different object -> returns false
        Team bobTeam = new Team(BOB.getIdentityCode(), "Bob's Team");
        assertFalse(aliceTeam.equals(bobTeam));
    }

    @Test
    public void addAndRemoveDeveloper_addAndRemoveSingleDeveloper_successful() {
        Team aliceTeam = new Team(ALICE.getIdentityCode(), "Alice's Team");

        aliceTeam.addDeveloper(BOB.getIdentityCode());
        assertTrue(aliceTeam.getDeveloperIdentityCodes().contains(BOB.getIdentityCode()));

        aliceTeam.removeDeveloper(BOB.getIdentityCode());
        assertFalse(aliceTeam.getDeveloperIdentityCodes().contains(BOB.getIdentityCode()));
    }

    @Test
    public void setTeamLeader_updateTeamLeader_successful() {
        Team aliceTeam = new Team(ALICE.getIdentityCode(), "Alice's Team");

        aliceTeam.setTeamLeader(BOB.getIdentityCode());
        assertEquals(BOB.getIdentityCode(), aliceTeam.getTeamLeaderIdentityCode());
    }

    @Test
    public void toStringMethod() {
        Team aliceTeam = new Team(ALICE.getIdentityCode(), "Alice's Team");

        Set<IdentityCode> developers = new HashSet<>();
        developers.add(BOB.getIdentityCode());
        aliceTeam.addDeveloper(BOB.getIdentityCode());

        String expected = Team.class.getCanonicalName() + "{Team Name=Alice's Team, Team Leader=" + ALICE.getIdentityCode() + ", Developer List=" + developers + "}";
        assertEquals(expected, aliceTeam.toString());
    }
}
