package seedu.ccacommander.logic.parser;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.commands.EditEnrolmentCommand;
import seedu.ccacommander.logic.commands.EditEnrolmentCommand.EditEnrolmentDescriptor;
import seedu.ccacommander.logic.commands.EnrolCommand;
import seedu.ccacommander.logic.parser.exceptions.ParseException;
import seedu.ccacommander.model.tag.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_HOURS;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new EditEnrolmentCommand object
 */
public class EditEnrolmentCommandParser implements Parser<EditEnrolmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEnrolmentCommand
     * and returns an EditEnrolmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditEnrolmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_EVENT, PREFIX_HOURS, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEMBER, PREFIX_EVENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrolCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MEMBER, PREFIX_EVENT, PREFIX_HOURS, PREFIX_REMARK);

        EditEnrolmentDescriptor editEnrolmentDescriptor = new EditEnrolmentDescriptor();
        if (argMultimap.getValue(PREFIX_HOURS).isPresent()) {
            editEnrolmentDescriptor.setHours(ParserUtil.parseHours(argMultimap.getValue(PREFIX_HOURS).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editEnrolmentDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }

        if (!editEnrolmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEnrolmentCommand.MESSAGE_NOT_EDITED);
        }

        Index memberIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER).get());
        Index eventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT).get());

        return new EditEnrolmentCommand(memberIndex, eventIndex, editEnrolmentDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
