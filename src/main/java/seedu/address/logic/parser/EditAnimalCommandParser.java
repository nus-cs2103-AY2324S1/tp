package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.EditAnimalCommand;
import seedu.address.logic.commands.EditAnimalCommand.EditAnimalDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAnimalCommand object
 */
public class EditAnimalCommandParser implements AnimalParser<EditAnimalCommand> {
    private static final Prefix[] OPTIONAL_PREFIXES =
            CliAnimalSyntax.getAnimalAttributePrefixes().toArray(Prefix[]::new);

    /**
     * Parses the given {@code String} of arguments in the context of the EditAnimalCommand
     * and returns an EditAnimalCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAnimalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, OPTIONAL_PREFIXES);

        Index index;

        try {
            index = AnimalParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAnimalCommand.EXAMPLE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(OPTIONAL_PREFIXES);

        EditAnimalDescriptor editAnimalDescriptor = new EditAnimalDescriptor();

        if (argMultimap.getValue(CliAnimalSyntax.NAME).isPresent()) {
            editAnimalDescriptor.setName(AnimalParserUtil.parseName(argMultimap.getValue(NAME).get()));
        }

        if (argMultimap.getValue(SEX).isPresent()) {
            editAnimalDescriptor.setSex(AnimalParserUtil.parseSex(argMultimap.getValue(SEX).get()));
        }

        if (argMultimap.getValue(DATE_OF_BIRTH).isPresent()) {
            editAnimalDescriptor.setDateOfBirth(AnimalParserUtil.parseDateOfBirth(argMultimap.getValue(DATE_OF_BIRTH)
                    .get()));
        }

        if (argMultimap.getValue(DATE_OF_ADMISSION).isPresent()) {
            editAnimalDescriptor.setAdmissionDate(AnimalParserUtil.parseAdmissionDate(argMultimap.getValue(
                    DATE_OF_ADMISSION).get()));
        }

        if (argMultimap.getValue(SPECIES).isPresent()) {
            editAnimalDescriptor.setSpecies(AnimalParserUtil.parseSpecies(argMultimap.getValue(SPECIES).get()));
        }

        if (argMultimap.getValue(BREED).isPresent()) {
            editAnimalDescriptor.setBreed(AnimalParserUtil.parseBreed(argMultimap.getValue(BREED).get()));
        }

        if (argMultimap.getValue(PET_ID).isPresent()) {
            throw new ParseException(String.format(EditAnimalCommand.MESSAGE_DO_NOT_CHANGE_ID,
                    EditAnimalCommand.EXAMPLE_USAGE));
        }

        if (!editAnimalDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(EditAnimalCommand.MESSAGE_NOT_EDITED,
                    EditAnimalCommand.EXAMPLE_USAGE));
        }

        return new EditAnimalCommand(index, editAnimalDescriptor);

    }
}
