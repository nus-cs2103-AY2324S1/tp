package seedu.address.logic.parser.personparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESSES;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.personcommands.UndiagnoseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DiagnoseCommand object
 */
public class UndiagnoseCommandParser implements Parser<UndiagnoseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DiagnoseCommand
     * and returns a DiagnoseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndiagnoseCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ILLNESSES);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndiagnoseCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ILLNESSES);

        if (!argMultimap.getValue(PREFIX_ILLNESSES).isPresent()
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndiagnoseCommand.MESSAGE_USAGE));
        }

        Set<Tag> illnesses = ParserUtil.parseIllnesses(argMultimap.getValue(PREFIX_ILLNESSES).get());

        return new UndiagnoseCommand(index, illnesses);
    }
}
