package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ADMISSION_DATE_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ADMISSION_DATE_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_BREED_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_BREED_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_DATE_OF_BIRTH_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_DATE_OF_BIRTH_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ID_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ID_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_BREED;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_DATE_OF_ADMISSION;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_DATE_OF_BIRTH;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_ID;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_NAME;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_SEX;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_SPECIES;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_NAME_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_NAME_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SEX_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SEX_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SPECIES_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SPECIES_TOFU;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;
import static seedu.address.logic.parser.CliAnimalSyntax.getAnimalAttributePrefixes;
import static seedu.address.testutil.TypicalAnimals.TOFU;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.AddAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.testutil.AnimalBuilder;

public class AddAnimalCommandParserTest {
    private final AddAnimalCommandParser parser = new AddAnimalCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Animal expectedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of()).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PREFIX_NAME_TOFU + PREFIX_ID_TOFU
                + PREFIX_BREED_TOFU + PREFIX_DATE_OF_BIRTH_TOFU + PREFIX_ADMISSION_DATE_TOFU
                + PREFIX_SEX_TOFU + PREFIX_SPECIES_TOFU , new AddAnimalCommand(expectedAnimal));

    }

    @Test
    public void parse_repeatedValue_failure() {
        String validAnimalInput = PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE;

        // repeated name
        assertParseFailure(parser, validAnimalInput + PREFIX_NAME_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(NAME.getPrefix()));

        // repeated id
        assertParseFailure(parser, validAnimalInput + PREFIX_ID_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(PET_ID.getPrefix()));

        // repeated sex
        assertParseFailure(parser, validAnimalInput + PREFIX_SEX_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(SEX.getPrefix()));

        // repeated breed
        assertParseFailure(parser, validAnimalInput + PREFIX_BREED_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(BREED.getPrefix()));

        // repeated species
        assertParseFailure(parser, validAnimalInput + PREFIX_SPECIES_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(SPECIES.getPrefix()));

        // repeated date of birth
        assertParseFailure(parser, validAnimalInput + PREFIX_DATE_OF_BIRTH_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(DATE_OF_BIRTH.getPrefix()));

        // repeated date of admission
        assertParseFailure(parser, validAnimalInput + PREFIX_ADMISSION_DATE_POOKIE,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(DATE_OF_ADMISSION.getPrefix()));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing name prefix
        String missingNameExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(NAME.getUsage()));
        assertParseFailure(parser, PREFIX_ID_POOKIE + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE
            + PREFIX_ADMISSION_DATE_POOKIE + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, missingNameExpectedMessage);

        // missing id prefix
        String missingPetIdExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(PET_ID.getUsage()));
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE
            + PREFIX_ADMISSION_DATE_POOKIE + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, missingPetIdExpectedMessage);

        // missing sex prefix
        String missingSexExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(SEX.getUsage()));
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE + PREFIX_BREED_POOKIE
            + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
            + PREFIX_SPECIES_POOKIE, missingSexExpectedMessage);

        // missing species prefix
        String missingSpeciesExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(SPECIES.getUsage()));
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE + PREFIX_BREED_POOKIE
            + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
            + PREFIX_SEX_POOKIE, missingSpeciesExpectedMessage);

        // missing breed prefix
        String missingBreedExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(BREED.getUsage()));
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE + PREFIX_SPECIES_POOKIE
            + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
            + PREFIX_SEX_POOKIE, missingBreedExpectedMessage);

        // missing date of birth prefix
        String missingDobExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(DATE_OF_BIRTH.getUsage()));
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE + PREFIX_SPECIES_POOKIE + PREFIX_BREED_POOKIE
            + PREFIX_ADMISSION_DATE_POOKIE
            + PREFIX_SEX_POOKIE, missingDobExpectedMessage);

        // missing date of admission prefix
        String missingDoaExpectedMessage = AddAnimalCommandParser.getHelpMessage(List.of(DATE_OF_ADMISSION.getUsage()));
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE + PREFIX_SPECIES_POOKIE + PREFIX_BREED_POOKIE
            + PREFIX_DATE_OF_BIRTH_POOKIE
            + PREFIX_SEX_POOKIE, missingDoaExpectedMessage);

        // all prefixes missing
        String missingAllExpectedMessage = AddAnimalCommandParser.getHelpMessage(getAnimalAttributePrefixes().stream()
                .map(CliAnimalSyntax::getCliSyntaxFromPrefix)
                .map(CliAnimalSyntax::getUsage)
                .collect(Collectors.toList()));
        assertParseFailure(parser, "", missingAllExpectedMessage);
    }
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, PREFIX_INVALID_NAME + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_INVALID_ID
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, PetId.MESSAGE_CONSTRAINTS);

        // invalid breed
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_INVALID_BREED + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, Breed.MESSAGE_CONSTRAINTS);

        // invalid species
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_INVALID_SPECIES, Species.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_INVALID_DATE_OF_BIRTH + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid date of admission
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_INVALID_DATE_OF_ADMISSION
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, AdmissionDate.MESSAGE_CONSTRAINTS);

        // invalid sex
        assertParseFailure(parser, PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_INVALID_SEX + PREFIX_SPECIES_POOKIE, Sex.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported, invalid name and date of birth
        assertParseFailure(parser, PREFIX_INVALID_NAME + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_INVALID_DATE_OF_BIRTH + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
                + PREFIX_BREED_POOKIE + PREFIX_DATE_OF_BIRTH_POOKIE + PREFIX_ADMISSION_DATE_POOKIE
                + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE,
                AnimalMessages.MESSAGE_INVALID_PREAMBLE);

    }

    @Test
    public void parse_withDateOfBirthBeforeDateOfAdmission_throwsParseException() {
        String validAnimalInputWithoutDates = PREFIX_NAME_POOKIE + PREFIX_ID_POOKIE
            + PREFIX_BREED_POOKIE + PREFIX_SEX_POOKIE + PREFIX_SPECIES_POOKIE;

        // Date of birth after date of admission
        String dob = " db/ 2018-05-05";
        String doa = " da/ 2015-01-01";

        assertThrows(ParseException.class, () -> parser.parse(validAnimalInputWithoutDates + dob + doa));
    }
}
