package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.recipe.exceptions.RecipeStepFormatException;

/**
 * An abstraction class for a recipe step.
 * Wraps a step number and a string instruction.
 */
public class RecipeStep {
    private static final Pattern recipeStepPattern =
            Pattern.compile("(?<stepNumber>[0-9]+)\\. (?<stepInstruction>\\S.*)");
    private final String instruction;
    private final int stepNumber;

    /** Creates a new recipe step with the specified {@code stepNumber} and {@code instruction}. */
    public RecipeStep(String instruction, int stepNumber) {
        requireAllNonNull(instruction, stepNumber);
        this.instruction = instruction;
        this.stepNumber = stepNumber;
    }

    /** Modifies the current recipe step with the specified {@code newInstruction}. */
    public RecipeStep modifyStep(String newInstruction) {
        requireNonNull(newInstruction);
        return new RecipeStep(newInstruction, this.stepNumber);
    }

    /** Modifies the current recipe step with the specified {@code newStepNumber} */
    public RecipeStep modifyStep(int newStepNumber) {
        return new RecipeStep(this.instruction, newStepNumber);
    }

    /** Parses the {@code str} as a {@code RecipeStep}. */
    public static RecipeStep parseRecipeStep(String str) {
        Matcher matcher = recipeStepPattern.matcher(str);
        if (matcher.find()) {
            int stepNumber = Integer.parseInt(matcher.group("stepNumber"));
            String stepInstruction = matcher.group("stepInstruction");
            return new RecipeStep(stepInstruction, stepNumber);
        } else {
            throw new RecipeStepFormatException();
        }
    }

    /** Returns the step number. */
    public int getStepNumber() {
        return stepNumber;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", this.stepNumber, this.instruction);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecipeStep)) {
            return false;
        }

        RecipeStep otherRecipeStep = (RecipeStep) other;
        return this.stepNumber == otherRecipeStep.stepNumber
                && this.instruction.equals(otherRecipeStep.instruction);
    }

}
