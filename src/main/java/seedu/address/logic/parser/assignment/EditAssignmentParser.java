package seedu.address.logic.parser.assignment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.common.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.Description;

/**
 * Parses an input that starts with edit-a
 */
public class EditAssignmentParser implements Parser<EditAssignmentCommand> {

    /**
     * Parses the input command that starts with edit-a
     * @param args The input command from the user
     * @return The EditAssignmentCommand which can be executed
     * @throws ParseException
     */
    public EditAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DESCRIPTION);
        if (!arePrefixesPresent(argumentMultimap, PREFIX_INDEX, PREFIX_DESCRIPTION)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAssignmentCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INDEX, PREFIX_DESCRIPTION);

        Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());
        Description newDescription = ParserUtil.parseDescription(
                argumentMultimap.getValue(PREFIX_DESCRIPTION).get());
        return new EditAssignmentCommand(index, newDescription);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
