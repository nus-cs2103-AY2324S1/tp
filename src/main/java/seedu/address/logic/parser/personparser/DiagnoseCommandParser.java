package seedu.address.logic.parser.personparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESSES;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.personcommands.DiagnoseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DiagnoseCommand object
 */
public class DiagnoseCommandParser implements Parser<DiagnoseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DiagnoseCommand
     * and returns a DiagnoseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DiagnoseCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ILLNESSES);

        Index index;

        if (argMultimap.getValue(PREFIX_ILLNESSES).isEmpty()
                || argMultimap.getPreamble().isEmpty() || argMultimap.checkPreambleIsNotNumber()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DiagnoseCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ILLNESSES);

        Set<Tag> illnesses = ParserUtil.parseIllnesses(argMultimap.getValue(PREFIX_ILLNESSES).get());

        return new DiagnoseCommand(index, illnesses);
    }
}
