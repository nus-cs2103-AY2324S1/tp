package seedu.lovebook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;

import seedu.lovebook.logic.commands.EditPrefCommand;
import seedu.lovebook.logic.commands.EditPrefCommand.EditPreferenceDescriptor;
import seedu.lovebook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPrefCommand object
 */
public class EditPrefCommandParser implements Parser<EditPrefCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPrefCommand
     * and returns an EditPrefCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPrefCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME);

        EditPreferenceDescriptor editPersonDescriptor = new EditPreferenceDescriptor();

        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            editPersonDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            editPersonDescriptor.setHeight(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_HEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_INCOME).isPresent()) {
            editPersonDescriptor.setIncome(ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPrefCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPrefCommand(editPersonDescriptor);
    }
}
