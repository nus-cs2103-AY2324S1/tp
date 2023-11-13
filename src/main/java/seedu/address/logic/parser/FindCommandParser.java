package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import seedu.address.Main;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GeneralPersonPredicate;
import seedu.address.model.person.LastContactedTime;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static Logger logger = LogsCenter.getLogger(Main.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        try {
            logger.info("Begin FindCommand parse");
            requireNonNull(args);

            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_LASTTIME, PREFIX_STATUS, PREFIX_TAG);
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_LASTTIME, PREFIX_STATUS, PREFIX_TAG);

            logger.info("Begin creation of Meeting predicates");
            String[] nameKeyWords = argMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
            String[] phoneValues = argMultimap.getValue(PREFIX_PHONE).orElse("").split("\\s+");
            String[] emailKeyWords = argMultimap.getValue(PREFIX_EMAIL).orElse("").split("\\s+");
            String[] statusKeyWords = argMultimap.getValue(PREFIX_STATUS).orElse("").split("\\s+");
            String[] tagKeyWords = argMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

            LocalDateTime lastContacted = LocalDateTime.MIN;
            if (argMultimap.getValue(PREFIX_LASTTIME).isPresent()) {
                lastContacted = ParserUtil.parseContactTime(argMultimap.getValue(PREFIX_LASTTIME).get());
                if (!LastContactedTime.isValidLastContactedTime(lastContacted)) {
                    throw new ParseException(LastContactedTime.MESSAGE_CONSTRAINTS);
                }
            }

            GeneralPersonPredicate generalPersonPredicate = new GeneralPersonPredicate(
                    nameKeyWords,
                    phoneValues,
                    emailKeyWords,
                    lastContacted,
                    statusKeyWords,
                    tagKeyWords);

            logger.info("All Person predicates created");

            return new FindCommand(generalPersonPredicate);
        } catch (ParseException pe) {
            throw new ParseException(FindCommand.MESSAGE_USAGE, pe);
        }
    }

}
