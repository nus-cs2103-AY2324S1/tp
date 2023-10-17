package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;


public class TeamTest {

    @Test
    public void constructor_validParameters_createsTeam() {
        TeamLeader aliceLeader =
                new TeamLeader(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        Team aliceTeam = new Team(aliceLeader);

        assertTrue(aliceTeam != null);
    }

    @Test
    public void equals() {
        TeamLeader aliceLeader =
                new TeamLeader(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        Team aliceTeam = new Team(aliceLeader);

        // same object -> returns true
        assertTrue(aliceTeam.equals(aliceTeam));

        // different object -> returns false
        TeamLeader bobLeader =
                new TeamLeader(BOB.getName(), BOB.getPhone(), BOB.getEmail(),
                        BOB.getAddress(), BOB.getRemark(), BOB.getTags());
        Team bobTeam = new Team(bobLeader);
        assertFalse(aliceTeam.equals(bobTeam));
    }

    @Test
    public void addDeveloper_addSingleDeveloper_successful() {
        TeamLeader aliceLeader =
                new TeamLeader(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        Team aliceTeam = new Team(aliceLeader);

        Developer bobDeveloper =
                new Developer(BOB.getName(), BOB.getPhone(), BOB.getEmail(),
                        BOB.getAddress(), BOB.getRemark(), BOB.getTags());

        aliceTeam.addDeveloper(bobDeveloper);
        assertTrue(aliceTeam.getDevelopers().contains(bobDeveloper.hashCode()));
    }
}
