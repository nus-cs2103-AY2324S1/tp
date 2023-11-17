package seedu.address.logic.parser;


import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ADMISSION_DATE_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ADMISSION_DATE_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_BREED_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_BREED_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_DATE_OF_BIRTH_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_BREED;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_DATE_OF_ADMISSION;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_DATE_OF_BIRTH;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_NAME;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_SEX;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_INVALID_SPECIES;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_NAME_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SEX_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SPECIES_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SPECIES_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_ADMISSION_DATE_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_BREED_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_DATE_OF_BIRTH_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_NAME_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SEX_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SPECIES_POOKIE;
import static seedu.address.logic.commands.EditAnimalCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.EditAnimalCommand;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.testutil.EditAnimalDescriptorBuilder;


public class EditAnimalCommandParserTest {

    private static final String MESSAGE_NO_INDEX =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAnimalCommand.EXAMPLE_USAGE);

    private static final String MESSAGE_NO_FIELD =
            String.format(MESSAGE_NOT_EDITED, EditAnimalCommand.EXAMPLE_USAGE);
    private static final String MESSAGE_DO_NOT_CHANGE_ID =
            String.format(EditAnimalCommand.MESSAGE_DO_NOT_CHANGE_ID, EditAnimalCommand.EXAMPLE_USAGE);

    private final EditAnimalCommandParser parser = new EditAnimalCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_POOKIE, MESSAGE_NO_INDEX);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NO_FIELD);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_NO_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PREFIX_NAME_POOKIE, MESSAGE_NO_INDEX);

        // zero index
        assertParseFailure(parser, "0" + PREFIX_NAME_POOKIE, MESSAGE_NO_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_NO_INDEX);

    }

    @Test
    public void parse_invalidPrefix_failure() {
        // invalid prefix
        assertParseFailure(parser, "1 k/ string", MESSAGE_NO_INDEX);

        // prefix not allowed
        assertParseFailure(parser, "1 i/ string", MESSAGE_DO_NOT_CHANGE_ID);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + PREFIX_INVALID_NAME, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + PREFIX_INVALID_SEX, Sex.MESSAGE_CONSTRAINTS); // invalid sex
        assertParseFailure(parser, "1" + PREFIX_INVALID_SPECIES, Species.MESSAGE_CONSTRAINTS); // invalid species
        assertParseFailure(parser, "1" + PREFIX_INVALID_BREED, Breed.MESSAGE_CONSTRAINTS); // invalid breed
        assertParseFailure(parser, "1"
                + PREFIX_INVALID_DATE_OF_BIRTH, DateOfBirth.MESSAGE_CONSTRAINTS); // invalid date of birth
        assertParseFailure(parser, "1"
                + PREFIX_INVALID_DATE_OF_ADMISSION, AdmissionDate.MESSAGE_CONSTRAINTS); // invalid admission date

        // invalid sex followed by valid breed
        assertParseFailure(parser, "1" + PREFIX_INVALID_SEX + VALID_BREED_POOKIE, Sex.MESSAGE_CONSTRAINTS);


        // multiple invalid values with some valid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + PREFIX_INVALID_NAME + PREFIX_INVALID_DATE_OF_BIRTH
                        + PREFIX_INVALID_SPECIES + PREFIX_BREED_POOKIE + PREFIX_ADMISSION_DATE_POOKIE,
                    Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PREFIX_NAME_POOKIE + PREFIX_BREED_POOKIE
                + PREFIX_ADMISSION_DATE_POOKIE + PREFIX_SPECIES_POOKIE + PREFIX_SEX_POOKIE
                + PREFIX_DATE_OF_BIRTH_POOKIE;

        EditAnimalCommand.EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder()
                .withName(VALID_NAME_POOKIE)
                .withBreed(VALID_BREED_POOKIE)
                .withAdmissionDate(VALID_ADMISSION_DATE_POOKIE)
                .withSex(VALID_SEX_POOKIE)
                .withSpecies(VALID_SPECIES_POOKIE)
                .withDateOfBirth(VALID_DATE_OF_BIRTH_POOKIE)
                .build();
        EditAnimalCommand expectedCommand = new EditAnimalCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PREFIX_NAME_POOKIE + PREFIX_BREED_POOKIE;

        EditAnimalCommand.EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder()
                .withName(VALID_NAME_POOKIE)
                .withBreed(VALID_BREED_POOKIE).build();

        EditAnimalCommand expectedCommand = new EditAnimalCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + PREFIX_NAME_POOKIE;
        EditAnimalCommand.EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder()
                                                                .withName(VALID_NAME_POOKIE).build();
        EditAnimalCommand expectedCommand = new EditAnimalCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sex
        userInput = targetIndex.getOneBased() + PREFIX_SEX_POOKIE;
        descriptor = new EditAnimalDescriptorBuilder().withSex(VALID_SEX_POOKIE).build();
        expectedCommand = new EditAnimalCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // breed
        userInput = targetIndex.getOneBased() + PREFIX_BREED_POOKIE;
        descriptor = new EditAnimalDescriptorBuilder().withBreed(VALID_BREED_POOKIE).build();
        expectedCommand = new EditAnimalCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // admission date
        userInput = targetIndex.getOneBased() + PREFIX_ADMISSION_DATE_POOKIE;
        descriptor = new EditAnimalDescriptorBuilder().withAdmissionDate(VALID_ADMISSION_DATE_POOKIE).build();
        expectedCommand = new EditAnimalCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // species
        userInput = targetIndex.getOneBased() + PREFIX_SPECIES_POOKIE;
        descriptor = new EditAnimalDescriptorBuilder().withSpecies(VALID_SPECIES_POOKIE).build();
        expectedCommand = new EditAnimalCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date of birth
        userInput = targetIndex.getOneBased() + PREFIX_DATE_OF_BIRTH_POOKIE;
        descriptor = new EditAnimalDescriptorBuilder().withDateOfBirth(VALID_DATE_OF_BIRTH_POOKIE).build();
        expectedCommand = new EditAnimalCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_repeatedFields_failure() {

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PREFIX_INVALID_SPECIES + PREFIX_SPECIES_POOKIE;

        assertParseFailure(parser, userInput, AnimalMessages.getErrorMessageForDuplicatePrefixes(SPECIES.getPrefix()));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PREFIX_SPECIES_POOKIE + PREFIX_INVALID_SPECIES;

        assertParseFailure(parser, userInput, AnimalMessages.getErrorMessageForDuplicatePrefixes(SPECIES.getPrefix()));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + PREFIX_SPECIES_POOKIE + PREFIX_SPECIES_TOFU + PREFIX_BREED_POOKIE
                    + PREFIX_BREED_TOFU + PREFIX_ADMISSION_DATE_POOKIE + PREFIX_ADMISSION_DATE_TOFU;

        assertParseFailure(parser, userInput,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(SPECIES.getPrefix(), BREED.getPrefix(),
                        DATE_OF_ADMISSION.getPrefix()));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + PREFIX_INVALID_SPECIES + PREFIX_INVALID_BREED
                    + PREFIX_INVALID_DATE_OF_ADMISSION + PREFIX_INVALID_DATE_OF_BIRTH + PREFIX_INVALID_NAME
                    + PREFIX_INVALID_SPECIES + PREFIX_INVALID_BREED + PREFIX_INVALID_DATE_OF_ADMISSION
                    + PREFIX_INVALID_DATE_OF_BIRTH + PREFIX_INVALID_NAME;

        assertParseFailure(parser, userInput,
                AnimalMessages.getErrorMessageForDuplicatePrefixes(NAME.getPrefix(), SPECIES.getPrefix(),
                        BREED.getPrefix(), DATE_OF_ADMISSION.getPrefix(), DATE_OF_BIRTH.getPrefix()));
    }

}
