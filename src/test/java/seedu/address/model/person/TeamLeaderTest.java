package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class TeamLeaderTest {

    @Test
    public void equals() {
        TeamLeader aliceLeader =
                new TeamLeader(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        TeamLeader bobLeader =
                new TeamLeader(BOB.getName(), BOB.getPhone(), BOB.getEmail(),
                        BOB.getAddress(), BOB.getRemark(), BOB.getTags());

        // same object -> returns true
        assertTrue(aliceLeader.equals(aliceLeader));

        // different team leaders -> returns false
        assertTrue(!aliceLeader.equals(bobLeader));
    }
}
