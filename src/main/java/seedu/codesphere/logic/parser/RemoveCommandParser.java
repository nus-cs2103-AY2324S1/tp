//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.commands.RemoveCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {
    public static final String MESSAGE_CONSTRAINTS =
            "Remark/ Pending Question should be blank." + "\nExample: remove 1 r/ pq/";


    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REMARK, PREFIX_PENDING_QUESTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_REMARK, PREFIX_PENDING_QUESTION);

        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");
        String pendingQuestion = argMultimap.getValue(PREFIX_PENDING_QUESTION).orElse("");
        if (!remark.isEmpty() || !pendingQuestion.isEmpty()) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editStudentDescriptor.setRemark(new Remark(""));
        }
        if (argMultimap.getValue(PREFIX_PENDING_QUESTION).isPresent()) {
            editStudentDescriptor.setPendingQuestion(new PendingQuestion(""));
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(RemoveCommand.MESSAGE_NOT_EDITED);
        }

        return new RemoveCommand(index, editStudentDescriptor);
    }

}
