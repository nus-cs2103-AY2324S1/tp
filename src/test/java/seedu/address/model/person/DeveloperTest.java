package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

public class DeveloperTest {

    @Test
    public void equals() {
        Developer aliceDeveloper =
                new Developer(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        Developer bobDeveloper =
                new Developer(BOB.getName(), BOB.getPhone(), BOB.getEmail(),
                        BOB.getAddress(), BOB.getRemark(), BOB.getTags());

        // same object -> returns true
        assertTrue(aliceDeveloper.equals(aliceDeveloper));

        // different developers -> returns false
        assertTrue(!aliceDeveloper.equals(bobDeveloper));
    }
}
