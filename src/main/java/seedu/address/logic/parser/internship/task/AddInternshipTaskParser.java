package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseTags;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_OUTCOME;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.internship.task.CliSyntax.PREFIX_TASK_NAME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.task.AddInternshipTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.common.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.tag.Tag;

/**
 * Parses user input for adding internship tasks
 */
public class AddInternshipTaskParser implements Parser<AddInternshipTaskCommand> {
    @Override
    public AddInternshipTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_INDEX,
                PREFIX_STATUS, PREFIX_DEADLINE, PREFIX_OUTCOME, PREFIX_TAG);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_TASK_NAME, PREFIX_INDEX, PREFIX_DEADLINE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInternshipTaskCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK_NAME, PREFIX_INDEX, PREFIX_STATUS,
                PREFIX_DEADLINE, PREFIX_OUTCOME);

        Name name = ParserUtil.parseName(argumentMultimap.getValue(PREFIX_TASK_NAME).get());

        Status status = argumentMultimap.getValue(PREFIX_STATUS).isEmpty()
                ? new Status(false)
                : ParserUtil.parseStatus(argumentMultimap.getValue(PREFIX_STATUS).get());

        // Can cast it as IsoDate because already verified there is going to be a PREFIX_DEADLINE
        IsoDate deadline = (IsoDate) ParserUtil.parseDateForAdd(argumentMultimap.getValue(PREFIX_DEADLINE).get());

        Index intershipRoleIndex = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());

        TaskOutcome outcome = argumentMultimap.getValue(PREFIX_OUTCOME).isEmpty()
                ? new TaskOutcome(Outcome.AWAITING)
                : ParserUtil.parseTaskOutcome(argumentMultimap.getValue(PREFIX_OUTCOME).get());


        Set<Tag> tagList = parseTags(argumentMultimap.getAllValues(PREFIX_TAG));

        return new AddInternshipTaskCommand(intershipRoleIndex, name, deadline, status, outcome, tagList);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
