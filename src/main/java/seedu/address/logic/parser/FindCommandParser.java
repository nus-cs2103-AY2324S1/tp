package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.SubjectContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Logger logger = LogsCenter.getLogger(FindCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Parsing FindCommand with arguments" + args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_SUBJECT);

        if (!argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_NAME).isPresent()) {
            logger.log(Level.WARNING, "Invalid input format: Both preamble and name specified.");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (!argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            logger.log(Level.WARNING, "No name or subject specified in FindCommand.");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        logger.log(Level.INFO, "Successfully parsed FindCommand.");
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            assert ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()) != null
                    : "Parsed name cannot be null";
            String nameKeywords = argMultimap.getValue(PREFIX_NAME).get();
            if (nameKeywords.split("\\s+").length > 1) {
                throw new ParseException("Name can only take one word.");
            }
            ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }

        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            assert ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()) != null
                    : "Parsed subject cannot be null";
            String subjectKeywords = argMultimap.getValue(PREFIX_SUBJECT).get();
            if (subjectKeywords.split("\\s+").length > 1) {
                throw new ParseException("Subject can only take one word.");
            }
            ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        }

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        List<String> subjectKeywords = argMultimap.getAllValues(PREFIX_SUBJECT);

        if (nameKeywords.isEmpty() && subjectKeywords.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(
                new NameContainsKeywordsPredicate(nameKeywords),
                new SubjectContainsKeywordsPredicate(subjectKeywords));
    }
}
