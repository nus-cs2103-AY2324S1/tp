package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_OUTCOME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.task.EditInternshipTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.common.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.TaskOutcome;

/**
 * Parser to create an EditInternshipRoleCommand from the given input
 */
public class EditInternshipTaskParser implements Parser<EditInternshipTaskCommand> {
    @Override
    public EditInternshipTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_OUTCOME);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_INDEX, PREFIX_OUTCOME)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditInternshipTaskCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INDEX, PREFIX_OUTCOME);

        Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());

        TaskOutcome newOutcome = ParserUtil.parseTaskOutcome(
                argumentMultimap.getValue(PREFIX_OUTCOME).get());

        return new EditInternshipTaskCommand(index, newOutcome);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
