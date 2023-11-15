package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BRITISH;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.AUNTIES_COOKING;
import static seedu.address.testutil.TypicalStalls.BRITISH;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StallBuilder;

public class StallTest {
    @Test
    public void isSameStall() {
        // same object -> returns true
        assertTrue(AUNTIES_COOKING.isSameStall(AUNTIES_COOKING));

        // null -> returns false
        assertFalse(AUNTIES_COOKING.isSameStall(null));

        // same name, all other attributes different -> returns false
        Stall editedAuntiesCooking =
                new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BRITISH).build();
        assertFalse(AUNTIES_COOKING.isSameStall(editedAuntiesCooking));

        // different name, all other attributes same -> returns false
        editedAuntiesCooking =
                new StallBuilder(AUNTIES_COOKING).withName(VALID_NAME_BRITISH).build();
        assertFalse(AUNTIES_COOKING.isSameStall(editedAuntiesCooking));

        // name differs in case, all other attributes same -> returns true
        Stall editedBritish =
                new StallBuilder(BRITISH).withName(VALID_NAME_BRITISH.toLowerCase()).build();
        assertTrue(BRITISH.isSameStall(editedBritish));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BRITISH + " ";
        editedBritish = new StallBuilder(BRITISH).withName(nameWithTrailingSpaces).build();
        assertFalse(BRITISH.isSameStall(editedBritish));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Stall auntiesCookingCopy = new StallBuilder(AUNTIES_COOKING).build();
        assertTrue(AUNTIES_COOKING.equals(auntiesCookingCopy));

        // same object -> returns true
        assertTrue(AUNTIES_COOKING.equals(AUNTIES_COOKING));

        // null -> returns false
        assertFalse(AUNTIES_COOKING.equals(null));

        // different type -> returns false
        assertFalse(AUNTIES_COOKING.equals(5));

        // different stall -> returns false
        assertFalse(AUNTIES_COOKING.equals(BRITISH));

        // different name -> returns false
        Stall editedAuntiesCooking =
                new StallBuilder(AUNTIES_COOKING).withName(VALID_NAME_BRITISH).build();
        assertFalse(AUNTIES_COOKING.equals(editedAuntiesCooking));

        // different location -> returns false
        editedAuntiesCooking =
                new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BRITISH).build();
        assertFalse(AUNTIES_COOKING.equals(editedAuntiesCooking));

    }

    @Test
    public void toStringMethod() {
        String expected = Stall.class.getCanonicalName() + "{name=" + AUNTIES_COOKING.getName() + ", location="
                + AUNTIES_COOKING.getLocation() + ", menu=" + AUNTIES_COOKING.getMenu() + ", stallReview="
                + AUNTIES_COOKING.getStallReview()
                + "}";
        assertEquals(expected, AUNTIES_COOKING.toString());
    }
}
