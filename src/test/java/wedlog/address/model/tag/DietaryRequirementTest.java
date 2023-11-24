package wedlog.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DietaryRequirementTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DietaryRequirement(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "no milk, cheese";
        assertThrows(IllegalArgumentException.class, () -> new DietaryRequirement(invalidRemark));
    }

    @Test
    public void equals_notDietaryRequirement_returnsFalse() {
        assertNotEquals(new DietaryRequirement("vegan"), "vegan");
    }

    @Test
    public void equals_sameDietaryRequirement_returnsTrue() {
        assertEquals(new DietaryRequirement("vegan"), new DietaryRequirement("vegan"));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        DietaryRequirement testDietaryRequirement = new DietaryRequirement("vegan");
        assertTrue(testDietaryRequirement.equals(testDietaryRequirement));
    }

    @Test
    public void toString_returnsCorrectString() {
        assertEquals(new DietaryRequirement("vegan").toString(), "[vegan]");
    }
}
