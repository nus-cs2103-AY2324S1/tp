package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RecipeStepTest {
    @Test
    public void toStringMethod() {
        assertThrows(NullPointerException.class, () -> new RecipeStep(null, 1));

        RecipeStep someSteps = new RecipeStep("Test recipe steps", 1);
        assertEquals("1. Test recipe steps", someSteps.toString());
    }

    @Test
    public void modifySteps_nullStep_throwsNullPointerException() {
        RecipeStep someSteps = new RecipeStep("Test recipe steps", 1);
        assertThrows(NullPointerException.class, () -> someSteps.modifyStep(null));
    }
    @Test
    public void modifySteps_stepsHasDifferentIdentity_success() {
        RecipeStep someSteps = new RecipeStep("Test recipe steps", 1);
        RecipeStep modifiedRecipeSteps = someSteps.modifyStep(5);
        assertNotEquals(modifiedRecipeSteps, someSteps);

        RecipeStep otherModifiedRecipeSteps = someSteps.modifyStep("New recipe step");
        assertNotEquals(otherModifiedRecipeSteps, someSteps);
    }

    @Test
    public void modifyStepsNumber_stepsReflectChange_success() {
        RecipeStep someSteps = new RecipeStep("Test recipe steps", 1);

        assertEquals("5. Test recipe steps", someSteps.modifyStep(5).toString());
        assertEquals("1. New instruction", someSteps.modifyStep("New instruction").toString());
    }
}
