package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDINTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;


import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TimeInterval;
import seedu.address.model.person.Name;


public class AddTimeCommandParser implements Parser<AddTimeCommand> {

    @Override
    public AddTimeCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_FREETIME, PREFIX_ENDINTERVAL);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_FREETIME) //find a way to separate error msg when ";" is missing
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTimeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        TimeInterval firstInterval = ParserUtil.parseEachInterval(argMultimap.getValue(PREFIX_FREETIME).get());
        ArrayList<TimeInterval> timeInterval = ParserUtil.parseInterval(argMultimap.getAllValues(PREFIX_ENDINTERVAL));
        timeInterval.add(0, firstInterval);

        if (!TimeInterval.isTimeIntervalOverlap(timeInterval)) {
            throw new ParseException(TimeInterval.MESSAGE_CONSTRAINTS_OVERLAP);
        }

        return new AddTimeCommand(name, timeInterval);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
