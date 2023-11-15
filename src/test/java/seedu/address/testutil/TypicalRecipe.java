package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Name;
import seedu.address.model.RecipeBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class containing a list of {@code Recipe} to be used in tests.
 */
public class TypicalRecipe {

    public static final Recipe COOKIES = new RecipeBuilder().withName("Cookies")
            .withIngredient(new Ingredient(new Name("Flour"), new Quantity(200, Unit.GRAM)))
            .withIngredient(new Ingredient(new Name("Milk"), new Quantity(100, Unit.GRAM)))
            .withIngredient(new Ingredient(new Name("Chocolate Chips"), new Quantity(50, Unit.GRAM)))
            .withSteps("Mix flour with milk", 1)
            .withSteps("Put chocolate chips in mixture", 2)
            .withSteps("Shape dough into cookie shape", 3)
            .withSteps("Bake in oven at 180C for 20 minutes", 4)
            .withId(1)
            .build();

    public static final Recipe SPONGECAKE = new RecipeBuilder().withName("Spongecake")
            .withIngredient(new Ingredient(new Name("Flour"), new Quantity(500, Unit.GRAM)))
            .withIngredient(new Ingredient(new Name("Butter"), new Quantity(250, Unit.GRAM)))
            .withIngredient(new Ingredient(new Name("Eggs"), new Quantity(4, Unit.PIECE)))
            .withIngredient(new Ingredient(new Name("Yeast"), new Quantity(5, Unit.GRAM)))
            .withIngredient(new Ingredient(new Name("Baking Soda"), new Quantity(10, Unit.GRAM)))
            .withSteps("Mix flour with butter", 1)
            .withSteps("Mix eggs into dough", 2)
            .withSteps("Put yeast in warm water and mix it in dough", 3)
            .withSteps("Put baking soda in dough", 4)
            .withSteps("Let dough rest for 30 minutes", 5)
            .withSteps("Bake in oven at 220C for 25 minutes", 6)
            .withId(2)
            .build();

    public static final Recipe DUMMY_RECIPE = new RecipeBuilder().withName("Dummy")
            .withIngredient(new Ingredient(new Name("Flour"), new Quantity(500, Unit.GRAM)))
            .withSteps("Mix flour with air", 1)
            .withId(3)
            .build();

    public static final String COOKIES_STRING = "1. Cookies\nFlour 200.0 GRAM\nMilk 100.0 GRAM\n"
            + "Chocolate Chips 50.0 GRAM\n1. Mix flour with milk\n2. Put chocolate chips in mixture\n"
            + "3. Shape dough into cookie shape\n4. Bake in oven at 180C for 20 minutes";

    public static final String SPONGECAKE_STRING = "2. Spongecake\nFlour 500.0 GRAM\nButter 250.0 GRAM\n"
            + "Eggs 4.0 PIECE\nYeast 5.0 GRAM\nBaking Soda 10.0 GRAM\n1. Mix flour with butter\n"
            + "2. Mix eggs into dough\n3. Put yeast in warm water and mix it in dough\n"
            + "4. Put baking soda in dough\n5. Let dough rest for 30 minutes\n6. Bake in oven at 220C for 25 minutes";

    private TypicalRecipe() {}

    public static RecipeBook getTypicalRecipeBook() {
        RecipeBook rb = new RecipeBook();
        for (Recipe recipe : getTypicalRecipes()) {
            rb.addRecipe(recipe);
        }
        return rb;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(COOKIES, SPONGECAKE));
    }

}
