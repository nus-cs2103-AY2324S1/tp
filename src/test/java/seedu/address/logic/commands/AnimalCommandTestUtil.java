package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class AnimalCommandTestUtil {

    public static final String VALID_NAME_POOKIE = "Pookie";
    public static final String VALID_NAME_TOFU = "Tofu";
    public static final String VALID_ID_POOKIE = "1111";
    public static final String VALID_ID_TOFU = "1234";
    public static final String VALID_DATE_OF_BIRTH_POOKIE = "2020-12-12";
    public static final String VALID_DATE_OF_BIRTH_TOFU = "2019-10-10";
    public static final String VALID_ADMISSION_DATE_POOKIE = "2023-05-05";
    public static final String VALID_ADMISSION_DATE_TOFU = "2019-11-11";
    public static final String VALID_SEX_POOKIE = "MALE";
    public static final String VALID_SEX_TOFU = "FEMALE";
    public static final String VALID_SPECIES_POOKIE = "Dog";
    public static final String VALID_SPECIES_TOFU = "Cat";
    public static final String VALID_BREED_POOKIE = "Poodle";
    public static final String VALID_BREED_TOFU = "British Shorthair";
    public static final String VALID_TASK_DESCRIPTION = "Feed dog";



    public static final String PREFIX_NAME_POOKIE = " " + NAME.getPrefix() + VALID_NAME_POOKIE;
    public static final String PREFIX_NAME_TOFU = " " + NAME.getPrefix() + VALID_NAME_TOFU;
    public static final String PREFIX_ID_POOKIE = " " + PET_ID.getPrefix() + VALID_ID_POOKIE;
    public static final String PREFIX_ID_TOFU = " " + PET_ID.getPrefix() + VALID_ID_TOFU;
    public static final String PREFIX_DATE_OF_BIRTH_POOKIE = " " + DATE_OF_BIRTH.getPrefix()
        + VALID_DATE_OF_BIRTH_POOKIE;
    public static final String PREFIX_DATE_OF_BIRTH_TOFU = " " + DATE_OF_BIRTH.getPrefix()
        + VALID_DATE_OF_BIRTH_TOFU;
    public static final String PREFIX_ADMISSION_DATE_POOKIE = " " + DATE_OF_ADMISSION.getPrefix()
            + VALID_ADMISSION_DATE_POOKIE;
    public static final String PREFIX_ADMISSION_DATE_TOFU =
        " " + DATE_OF_ADMISSION.getPrefix() + VALID_ADMISSION_DATE_TOFU;
    public static final String PREFIX_SEX_POOKIE = " " + SEX.getPrefix() + VALID_SEX_POOKIE;
    public static final String PREFIX_SEX_TOFU = " " + SEX.getPrefix() + VALID_SEX_TOFU;
    public static final String PREFIX_SPECIES_POOKIE = " " + SPECIES.getPrefix() + VALID_SPECIES_POOKIE;
    public static final String PREFIX_SPECIES_TOFU = " " + SPECIES.getPrefix() + VALID_SPECIES_TOFU;
    public static final String PREFIX_BREED_POOKIE = " " + BREED.getPrefix() + VALID_BREED_POOKIE;
    public static final String PREFIX_BREED_TOFU = " " + BREED.getPrefix() + VALID_BREED_TOFU;

    public static final String PREFIX_INVALID_NAME = " " + NAME.getPrefix() + "James&"; // '&' not allowed in names
    public static final String PREFIX_INVALID_ID = " " + PET_ID.getPrefix() + "911a"; // 'a' not allowed in ID
    public static final String PREFIX_INVALID_DATE_OF_BIRTH =
        " " + DATE_OF_BIRTH.getPrefix() + "2022-13-10"; // no such date
    public static final String PREFIX_INVALID_DATE_OF_ADMISSION = " "
            + DATE_OF_ADMISSION.getPrefix() + "22-12-2022"; // wrong date format dd-mm-yyyy
    public static final String PREFIX_INVALID_SEX = " " + SEX.getPrefix() + "others"; // Can only be male or female
    public static final String PREFIX_INVALID_SPECIES = " " + SPECIES.getPrefix()
            + "Waterboard2"; // '2' not allowed in species
    public static final String PREFIX_INVALID_BREED = " " + BREED.getPrefix() + "lil-bear"; // '-' not allowed in breed
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditAnimalCommand.EditAnimalDescriptor DESC_POOKIE =
            new EditAnimalDescriptorBuilder().withName(VALID_NAME_POOKIE)
                    .withBreed(VALID_BREED_POOKIE)
                    .withDateOfBirth(VALID_DATE_OF_BIRTH_POOKIE)
                    .withSex(VALID_SEX_POOKIE)
                    .withSpecies(VALID_SPECIES_POOKIE)
                    .withAdmissionDate(VALID_ADMISSION_DATE_POOKIE)
                    .build();
    public static final EditAnimalCommand.EditAnimalDescriptor DESC_TOFU =
            new EditAnimalDescriptorBuilder().withName(VALID_NAME_TOFU)
                    .withBreed(VALID_BREED_TOFU)
                    .withDateOfBirth(VALID_DATE_OF_BIRTH_TOFU)
                    .withSex(VALID_SEX_TOFU)
                    .withSpecies(VALID_SPECIES_TOFU)
                    .withAdmissionDate(VALID_ADMISSION_DATE_TOFU)
                    .build();


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(AnimalCommand command, AnimalModel actualModel,
                                            CommandResult expectedCommandResult, AnimalModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(AnimalCommand, AnimalModel, CommandResult, AnimalModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(AnimalCommand command, AnimalModel actualModel, String expectedMessage,
                                            AnimalModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the animal catalog, filtered animal list and selected animal in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(AnimalCommand command, AnimalModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AnimalCatalog animalCatalog = new AnimalCatalog(actualModel.getAnimalCatalog());
        List<Animal> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAnimalList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(animalCatalog, actualModel.getAnimalCatalog());
        assertEquals(expectedFilteredList, actualModel.getFilteredAnimalList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the animal at the given {@code targetIndex} in the
     * {@code model}'s animal catalog.
     */
    public static void showAnimalAtIndex(AnimalModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAnimalList().size());

        Animal animal = model.getFilteredAnimalList().get(targetIndex.getZeroBased());
        final String[] splitName = animal.getName().fullName.split("\\s+");
        model.updateFilteredAnimalList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredAnimalList().size());
    }

}
