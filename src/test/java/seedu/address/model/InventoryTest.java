package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.EGG;
import static seedu.address.testutil.TypicalIngredients.FLOUR;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.ingredient.exceptions.DuplicateIngredientException;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;
import seedu.address.testutil.IngredientBuilder;

public class InventoryTest {

    private final Inventory inventory = new Inventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), inventory.getIngredientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInventory_replacesData() {
        Inventory newData = getTypicalInventory();
        inventory.resetData(newData);
        assertEquals(newData, inventory);
    }

    @Test
    public void resetData_withDuplicateIngredients_throwsDuplicateIngredientException() {
        // Two ingredients with the same identity fields
        Ingredient editedAlice = new IngredientBuilder(FLOUR).build();
        List<Ingredient> newIngredients = Arrays.asList(FLOUR, editedAlice);
        InventoryStub newData = new InventoryStub(newIngredients);

        assertThrows(DuplicateIngredientException.class, () -> inventory.resetData(newData));
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.hasIngredient(null));
    }

    @Test
    public void hasIngredient_ingredientNotInInventory_returnsFalse() {
        assertFalse(inventory.hasIngredient(FLOUR.getName()));
    }

    @Test
    public void hasIngredient_ingredientInInventory_returnsTrue() {
        inventory.addIngredient(FLOUR);
        assertTrue(inventory.hasIngredient(FLOUR.getName()));
    }

    @Test
    public void hasIngredient_ingredientWithSameIdentityFieldsInInventory_returnsTrue() {
        inventory.addIngredient(FLOUR);
        Ingredient editedAlice = new IngredientBuilder(FLOUR).build();
        assertTrue(inventory.hasIngredient(editedAlice.getName()));
    }

    @Test
    public void getIngredientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> inventory.getIngredientList().remove(0));
    }

    @Test
    public void useIngredient_sufficientIngredientInInventory_returnsTrue() {
        Name testIngredient = new Name("Flour");
        Quantity testQuantity = new Quantity(50, Unit.GRAM);
        inventory.addIngredient(FLOUR);
        inventory.useIngredient(testIngredient, testQuantity);
        assertEquals(inventory.getQuantityOf(testIngredient), testQuantity);
    }

    @Test
    public void useIngredient_insufficientIngredientInInventory_removesIngredient() {
        Name testIngredient = new Name("Flour");
        Quantity testQuantity = new Quantity(150, Unit.GRAM);
        inventory.addIngredient(FLOUR);
        inventory.useIngredient(testIngredient, testQuantity);
        assertEquals(inventory.hasIngredient(testIngredient), false);
    }

    @Test
    public void useIngredient_noIngredientInInventory_throwsIngredientNotFoundException() {
        Name testIngredient = new Name("Flour");
        Quantity testQuantity = new Quantity(150, Unit.GRAM);
        assertThrows(IngredientNotFoundException.class, () -> inventory.useIngredient(testIngredient, testQuantity));
    }

    @Test
    public void clear_ingredientInInventory_returnsEmptyList() {
        inventory.addIngredient(FLOUR);
        inventory.addIngredient(EGG);
        ObservableList tmpLst = inventory.getIngredientList();
        assertEquals(tmpLst.size(), 2);
        inventory.clear();
        assertEquals(tmpLst.size(), 0);
    }

    @Test
    public void toStringMethod() {
        String expected = Inventory.class.getCanonicalName() + "{ingredients=" + inventory.getIngredientList() + "}";
        assertEquals(expected, inventory.toString());
    }

    /**
     * A stub ReadOnlyInventory whose ingredients list can violate interface constraints.
     */
    private static class InventoryStub implements ReadOnlyInventory {
        private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

        InventoryStub(Collection<Ingredient> ingredients) {
            this.ingredients.setAll(ingredients);
        }

        @Override
        public ObservableList<Ingredient> getIngredientList() {
            return ingredients;
        }
    }

}
