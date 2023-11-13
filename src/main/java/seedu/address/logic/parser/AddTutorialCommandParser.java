package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TUTORIAL_TIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

/**
 * Parses input arguments and creates a new AddTutorialCommand object
 */
public class AddTutorialCommandParser implements Parser<AddTutorialCommand> {
    /* Methods */
    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorialCommand
     * and returns an AddTutorialCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTutorialCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_TUTORIAL_NAME, PREFIX_TUTORIAL_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_TUTORIAL_NAME, PREFIX_TUTORIAL_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MODULE, PREFIX_TUTORIAL_NAME, PREFIX_TUTORIAL_TIME);
        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        String tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL_NAME).get());
        String tutorialTime = ParserUtil.parseTutorialTime(argMultimap.getValue(PREFIX_TUTORIAL_TIME).get());

        try {
            tutorialTime = Tutorial.reformat(tutorialTime);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_TUTORIAL_TIME_FORMAT,
                    AddTutorialCommand.MESSAGE_USAGE));
        }

        Tutorial tutorial = new Tutorial(module, tutorialName, tutorialTime);

        return new AddTutorialCommand(tutorial);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


