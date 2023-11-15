package seedu.address.model.util;

import java.util.ArrayList;

import seedu.address.model.Inventory;
import seedu.address.model.Name;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyRecipeBook;
import seedu.address.model.RecipeBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeStep;

/**
 * Contains utility methods for populating {@code Inventory} with sample data.
 */
public class SampleDataUtil {
    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
            new Ingredient(new Name("Flour"), new Quantity(3, Unit.KILOGRAM)),
            new Ingredient(new Name("Butter"), new Quantity(50, Unit.GRAM)),
            new Ingredient(new Name("Baking Soda"), new Quantity(50, Unit.GRAM)),
            new Ingredient(new Name("Chocolate Chip"), new Quantity(300, Unit.GRAM)),
            new Ingredient(new Name("Vanilla Extract"), new Quantity(100, Unit.GRAM)),
            new Ingredient(new Name("Sugar"), new Quantity(2, Unit.KILOGRAM)),
            new Ingredient(new Name("Cocoa Powder"), new Quantity(200, Unit.GRAM)),
            new Ingredient(new Name("Corn Starch"), new Quantity(500, Unit.GRAM)),
        };
    }

    public static ReadOnlyInventory getSampleInventory() {
        Inventory sampleAb = new Inventory();
        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleAb.addIngredient(sampleIngredient);
        }
        return sampleAb;
    }

    public static Recipe[] getSampleRecipes() {
        // Recipe 1 (Cookie)
        Ingredient flour = new Ingredient(new Name("Flour"), new Quantity(200, Unit.GRAM));
        Ingredient milk = new Ingredient(new Name("Milk"), new Quantity(100, Unit.GRAM));
        Ingredient chocolateChips = new Ingredient(new Name("Chocolate Chip"), new Quantity(50, Unit.GRAM));
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
        ingredientList.add(flour);
        ingredientList.add(milk);
        ingredientList.add(chocolateChips);

        RecipeStep first = new RecipeStep("Mix flour with Milk", 1);
        RecipeStep second = new RecipeStep("Add Chocolate Chips", 2);
        RecipeStep third = new RecipeStep("Bake at 180C for 20 minutes", 3);
        ArrayList<RecipeStep> recipeSteps = new ArrayList<RecipeStep>();
        recipeSteps.add(first);
        recipeSteps.add(second);
        recipeSteps.add(third);

        // Recipe 2 (Cake)
        Ingredient cakeFlour = new Ingredient(new Name("Flour"), new Quantity(200, Unit.GRAM));
        Ingredient egg = new Ingredient(new Name("Egg"), new Quantity(2, Unit.PIECE));
        Ingredient cream = new Ingredient(new Name("Cream"), new Quantity(50, Unit.GRAM));
        ArrayList<Ingredient> ingredientListCake = new ArrayList<Ingredient>();
        ingredientListCake.add(cakeFlour);
        ingredientListCake.add(egg);
        ingredientListCake.add(cream);

        RecipeStep cakeFirst = new RecipeStep("Mix flour with Egg", 1);
        RecipeStep cakeSecond = new RecipeStep("Add Cream", 2);
        RecipeStep cakeThird = new RecipeStep("Bake at 180C for 20 minutes", 3);
        ArrayList<RecipeStep> cakeRecipeSteps = new ArrayList<RecipeStep>();
        cakeRecipeSteps.add(cakeFirst);
        cakeRecipeSteps.add(cakeSecond);
        cakeRecipeSteps.add(cakeThird);

        // Recipe 3 (Brioche)
        Ingredient briocheFlour = new Ingredient(new Name("Flour"), new Quantity(300, Unit.GRAM));
        Ingredient briocheEgg = new Ingredient(new Name("Egg"), new Quantity(3, Unit.PIECE));
        Ingredient briocheSugar = new Ingredient(new Name("Sugar"), new Quantity(80, Unit.GRAM));
        Ingredient briocheMilk = new Ingredient(new Name("Milk"), new Quantity(70, Unit.GRAM));
        ArrayList<Ingredient> ingredientListBrioche = new ArrayList<Ingredient>();
        ingredientListBrioche.add(briocheFlour);
        ingredientListBrioche.add(briocheEgg);
        ingredientListBrioche.add(briocheSugar);
        ingredientListBrioche.add(briocheMilk);

        RecipeStep briocheFirst = new RecipeStep("Mix flour with Egg", 1);
        RecipeStep briocheSecond = new RecipeStep("Add Sugar", 2);
        RecipeStep briocheThird = new RecipeStep("Pour in the milk", 3);
        RecipeStep briocheFourth = new RecipeStep("Bake at 350C for 1 hour", 4);
        ArrayList<RecipeStep> briocheRecipeSteps = new ArrayList<RecipeStep>();
        briocheRecipeSteps.add(briocheFirst);
        briocheRecipeSteps.add(briocheSecond);
        briocheRecipeSteps.add(briocheThird);
        briocheRecipeSteps.add(briocheFourth);

        // Recipe 4 (Croissant)
        Ingredient croissantFlour = new Ingredient(new Name("Flour"), new Quantity(300, Unit.GRAM));
        Ingredient croissantButter = new Ingredient(new Name("Butter"), new Quantity(100, Unit.GRAM));
        Ingredient croissantMilk = new Ingredient(new Name("Milk"), new Quantity(70, Unit.GRAM));
        Ingredient croissantSugar = new Ingredient(new Name("Sugar"), new Quantity(50, Unit.GRAM));
        ArrayList<Ingredient> ingredientListCroissant = new ArrayList<Ingredient>();
        ingredientListCroissant.add(croissantFlour);
        ingredientListCroissant.add(croissantButter);
        ingredientListCroissant.add(croissantMilk);
        ingredientListCroissant.add(croissantSugar);

        RecipeStep croissantFirst = new RecipeStep("Mix flour with Butter", 1);
        RecipeStep croissantSecond = new RecipeStep("Slowly mix milk in", 2);
        RecipeStep croissantThird = new RecipeStep("Pour sugar in", 3);
        RecipeStep croissantFourth = new RecipeStep("Bake at 270C for 40 minutes", 4);
        ArrayList<RecipeStep> croissantRecipeSteps = new ArrayList<RecipeStep>();
        croissantRecipeSteps.add(croissantFirst);
        croissantRecipeSteps.add(croissantSecond);
        croissantRecipeSteps.add(croissantThird);
        croissantRecipeSteps.add(croissantFourth);

        // Recipe 5 (Chocolate Muffin)
        Ingredient chocolateFlour = new Ingredient(new Name("Flour"), new Quantity(200, Unit.GRAM));
        Ingredient chocolateSugar = new Ingredient(new Name("Sugar"), new Quantity(100, Unit.GRAM));
        Ingredient chocolateVanilla = new Ingredient(new Name("Vanilla"), new Quantity(50, Unit.GRAM));
        Ingredient chocolateChocolate = new Ingredient(new Name("Chocolate Chip"), new Quantity(50, Unit.GRAM));
        ArrayList<Ingredient> ingredientListChocolate = new ArrayList<Ingredient>();
        ingredientListChocolate.add(chocolateFlour);
        ingredientListChocolate.add(chocolateSugar);
        ingredientListChocolate.add(chocolateVanilla);
        ingredientListChocolate.add(chocolateChocolate);

        RecipeStep chocolateFirst = new RecipeStep("Whisk flour and sugar in a bowl", 1);
        RecipeStep chocolateSecond = new RecipeStep("Slowly add the vanilla", 2);
        RecipeStep chocolateThird = new RecipeStep("Top the mixture with chocolate chips", 3);
        RecipeStep chocolateFourth = new RecipeStep("Bake at 300C for 35 minutes", 4);
        ArrayList<RecipeStep> chocolateRecipeSteps = new ArrayList<RecipeStep>();
        chocolateRecipeSteps.add(chocolateFirst);
        chocolateRecipeSteps.add(chocolateSecond);
        chocolateRecipeSteps.add(chocolateThird);
        chocolateRecipeSteps.add(chocolateFourth);

        // Recipe 6 (Blueberry Muffin)
        Ingredient blueberryFlour = new Ingredient(new Name("Flour"), new Quantity(200, Unit.GRAM));
        Ingredient blueberrySugar = new Ingredient(new Name("Sugar"), new Quantity(100, Unit.GRAM));
        Ingredient blueberryVanilla = new Ingredient(new Name("Vanilla"), new Quantity(50, Unit.GRAM));
        Ingredient blueberryBlueberry = new Ingredient(new Name("Blueberry"), new Quantity(50, Unit.GRAM));
        ArrayList<Ingredient> ingredientListBlueberry = new ArrayList<Ingredient>();
        ingredientListBlueberry.add(blueberryFlour);
        ingredientListBlueberry.add(blueberrySugar);
        ingredientListBlueberry.add(blueberryVanilla);
        ingredientListBlueberry.add(blueberryBlueberry);

        RecipeStep blueberryFirst = new RecipeStep("Whisk flour and sugar in a bowl", 1);
        RecipeStep blueberrySecond = new RecipeStep("Slowly add the vanilla", 2);
        RecipeStep blueberryThird = new RecipeStep("Top the mixture with blueberries", 3);
        RecipeStep blueberryFourth = new RecipeStep("Bake at 300C for 35 minutes", 4);
        ArrayList<RecipeStep> blueberryRecipeSteps = new ArrayList<RecipeStep>();
        blueberryRecipeSteps.add(blueberryFirst);
        blueberryRecipeSteps.add(blueberrySecond);
        blueberryRecipeSteps.add(blueberryThird);
        blueberryRecipeSteps.add(blueberryFourth);

        // Recipe 7 (Brownie)
        Ingredient brownieSugar = new Ingredient(new Name("Sugar"), new Quantity(100, Unit.GRAM));
        Ingredient brownieFlour = new Ingredient(new Name("Flour"), new Quantity(200, Unit.GRAM));
        Ingredient brownieCocoa = new Ingredient(new Name("Cocoa"), new Quantity(150, Unit.GRAM));
        ArrayList<Ingredient> ingredientListBrownie = new ArrayList<Ingredient>();
        ingredientListBrownie.add(brownieSugar);
        ingredientListBrownie.add(brownieFlour);
        ingredientListBrownie.add(brownieCocoa);

        RecipeStep brownieFirst = new RecipeStep("Mix ingredients in a bowl", 1);
        RecipeStep brownieSecond = new RecipeStep("Bake at 325C for 45 minutes", 2);
        ArrayList<RecipeStep> brownieRecipeSteps = new ArrayList<RecipeStep>();
        brownieRecipeSteps.add(brownieFirst);
        brownieRecipeSteps.add(brownieSecond);

        // Recipe 8 (Mee Jiang Kueh)
        Ingredient kuehEgg = new Ingredient(new Name("Egg"), new Quantity(1, Unit.PIECE));
        Ingredient kuehBakingSoda = new Ingredient(new Name("Baking Soda"), new Quantity(50, Unit.GRAM));
        Ingredient kuehSugar = new Ingredient(new Name("Sugar"), new Quantity(20, Unit.GRAM));
        ArrayList<Ingredient> ingredientListKueh = new ArrayList<Ingredient>();
        ingredientListKueh.add(kuehEgg);
        ingredientListKueh.add(kuehBakingSoda);
        ingredientListKueh.add(kuehSugar);

        RecipeStep kuehFirst = new RecipeStep("Mix egg and sugar until sugar dissolves", 1);
        RecipeStep kuehSecond = new RecipeStep("Sift in the baking soda", 2);
        RecipeStep kuehThird = new RecipeStep("Cook in skillet at 180C for 30 minutes", 3);
        ArrayList<RecipeStep> kuehRecipeSteps = new ArrayList<RecipeStep>();
        kuehRecipeSteps.add(kuehFirst);
        kuehRecipeSteps.add(kuehSecond);
        kuehRecipeSteps.add(kuehThird);

        return new Recipe[] {
            new Recipe(new Name("Cookie"), ingredientList, recipeSteps),
            new Recipe(new Name("Cake"), ingredientListCake, cakeRecipeSteps),
            new Recipe(new Name("Brioche"), ingredientListBrioche, briocheRecipeSteps),
            new Recipe(new Name("Croissant"), ingredientListCroissant, croissantRecipeSteps),
            new Recipe(new Name("Chocolate Muffin"), ingredientListChocolate, chocolateRecipeSteps),
            new Recipe(new Name("Blueberry Muffin"), ingredientListBlueberry, blueberryRecipeSteps),
            new Recipe(new Name("Brownie"), ingredientListBrownie, brownieRecipeSteps),
            new Recipe(new Name("Mee Jiang Kueh"), ingredientListKueh, kuehRecipeSteps),
        };
    }

    public static ReadOnlyRecipeBook getSampleRecipeBook() {
        RecipeBook sampleRb = new RecipeBook();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleRb.addRecipe(sampleRecipe);
        }
        return sampleRb;
    }
}
