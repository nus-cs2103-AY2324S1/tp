package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;


/**
 * Parses input arguments and create a new AddPlanCommand Object
 */
public class AddPlanCommandParser implements Parser<AddPlanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPlanCommand
     * and returns an AddPlanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPlanCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME, PREFIX_FRIEND);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATETIME, PREFIX_FRIEND)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException((String.format(MESSAGE_MISSING_ARGUMENTS, AddPlanCommand.MESSAGE_USAGE)));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATETIME, PREFIX_FRIEND);;
        PlanName planName = ParserUtil.parsePlanName(argMultimap.getValue(PREFIX_NAME).get());
        PlanDateTime planDateTime = ParserUtil.parsePlanDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        Name friendName = ParserUtil.parseName(argMultimap.getValue(PREFIX_FRIEND).get());

        return new AddPlanCommand(planName, planDateTime, friendName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
