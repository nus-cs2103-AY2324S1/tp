package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class TeamTest {

    @Test
    public void equals() {
        TeamLeader aliceLeader =
                new TeamLeader(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        Team aliceTeam = new Team(aliceLeader);

        // same object -> returns true
        assertTrue(aliceTeam.equals(aliceTeam));
    }

    // Tests related to adding developers can be added here.
}
