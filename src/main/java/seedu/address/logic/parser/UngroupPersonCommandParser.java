package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.UngroupPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Group;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new UngroupPersonCommand object
 */
public class UngroupPersonCommandParser implements Parser<UngroupPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UngroupPersonCommand
     * and returns a UngroupPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UngroupPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUPTAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_GROUPTAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UngroupPersonCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_GROUPTAG);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUPTAG).get());

        return new UngroupPersonCommand(new NameEqualsKeywordPredicate(name.toString()), group);
    }



    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
