package seedu.address.logic.parser.assignment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseDateForList;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDFILTER;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_STARTFILTER;

import java.util.Arrays;

import seedu.address.logic.commands.assignment.ListAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentBetweenStartandEndPredicate;
import seedu.address.model.fields.IsoDate;

/**
 * Parses an input that starts with list-a
 */
public class ListAssignmentParser implements Parser<ListAssignmentCommand> {
    @Override
    public ListAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STARTFILTER, PREFIX_ENDFILTER);

        if (!argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAssignmentCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STARTFILTER, PREFIX_ENDFILTER);
        String startFilter = argumentMultimap.getValue(PREFIX_STARTFILTER).isEmpty()
                ? "1900-01-01 00:00"
                : argumentMultimap.getValue(PREFIX_STARTFILTER).get().trim();

        String endFilter = argumentMultimap.getValue(PREFIX_ENDFILTER).isEmpty()
                ? "9999-12-31 23:59"
                : argumentMultimap.getValue(PREFIX_ENDFILTER).get().trim();

        // if no timing input, include start time to be 00:00
        if (IsoDate.isValidIsoDateWithoutTime(startFilter)) {
            startFilter = startFilter + " 00:00";
        }

        // if no timing input, include end time to be 23:59
        if (IsoDate.isValidIsoDateWithoutTime(endFilter)) {
            endFilter = endFilter + " 23:59";
        }

        // To verify the date format is correct - if invalid, this will throw ParseException
        parseDateForList(startFilter, endFilter);

        String[] dates = new String[] {startFilter, endFilter};

        AssignmentBetweenStartandEndPredicate predicate =
                new AssignmentBetweenStartandEndPredicate(Arrays.asList(dates));

        return new ListAssignmentCommand(predicate);
    }
}
