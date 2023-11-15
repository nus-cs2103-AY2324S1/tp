package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.EGG;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.ingredient.exceptions.UnitFormatException;

public class JsonAdaptedIngredientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_QUANTITY = "1.0 MILES";
    private static final String VALID_NAME = EGG.getName().toString();
    private static final String VALID_QUANTITY = "1.0 GRAM";

    @Test
    public void toModelType_validIngredientDetails_returnIngredient() throws Exception {
        JsonAdaptedIngredient ingredient = new JsonAdaptedIngredient(EGG);
        assertEquals(EGG, ingredient.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(INVALID_NAME, VALID_QUANTITY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    /*
    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient = new JsonAdaptedIngredient(null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }
    */

    @Test
    public void toModelType_invalidQuantity_throwsUnitFormatException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(VALID_NAME, INVALID_QUANTITY);
        String expectedMessage = String.format("Unit MILES is not a valid unit! %s", Unit.MESSAGE_CONSTRAINTS);
        assertThrows(UnitFormatException.class, expectedMessage, ingredient::toModelType);
    }
}
