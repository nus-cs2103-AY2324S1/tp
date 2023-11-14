package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddSpecialisationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddSpecialisationCommand object
 */
public class AddSpecialisationCommandParser implements Parser<AddSpecialisationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSpecialisationCommand
     * and returns an AddSpecialisationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSpecialisationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddSpecialisationCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddSpecialisationCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG);
        Tag specialisation = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());

        return new AddSpecialisationCommand(index, specialisation);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
