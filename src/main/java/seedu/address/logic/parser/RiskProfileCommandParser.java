package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKPROFILE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RiskProfileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RiskProfile;

/**
 * Parses input arguments and creates a new {@code RiskProfileCommand} object
 */
public class RiskProfileCommandParser implements Parser<RiskProfileCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RiskProfileCommand}
     * and returns a {@code RiskProfileCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RiskProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RISKPROFILE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RiskProfileCommand.MESSAGE_USAGE),
                ive);
        }

        String result = argMultimap.getValue(PREFIX_RISKPROFILE).orElse("");

        if (result.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RiskProfileCommand.MESSAGE_USAGE));
        }

        return new RiskProfileCommand(index, new RiskProfile(result));
    }
}
