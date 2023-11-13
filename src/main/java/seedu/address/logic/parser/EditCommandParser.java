package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.IllegalArgumentException;
import seedu.address.logic.parser.exceptions.InvalidFormatException;
import seedu.address.logic.parser.exceptions.NoRecordedModificationException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Member;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_NOTE,
                        PREFIX_MEMBER, PREFIX_DEADLINE, PREFIX_PRIORITY);

        // check if the preamble is empty, if it is, then it must be malformed
        if (argMultimap.getPreamble().isEmpty()) {
            throw new InvalidFormatException(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD,
                    EditCommand.MESSAGE_USAGE);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DESCRIPTION, PREFIX_NOTE, PREFIX_DEADLINE, PREFIX_PRIORITY);

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        setDescriptorFields(argMultimap, editTaskDescriptor);

        // now validate the index
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalArgumentException e) {
            throw new InvalidFormatException(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD,
                    EditCommand.MESSAGE_USAGE);
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new NoRecordedModificationException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> members} into a {@code Set<Member>} if {@code members} is non-empty.
     * If {@code members} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Member>} containing zero members.
     */
    private Optional<Set<Member>> parseMembersForEdit(Collection<String> members) throws ParseException {
        requireNonNull(members);

        if (members.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> memberSet = members.size() == 1 && members.contains("") ? Collections.emptySet() : members;
        return Optional.of(ParserUtil.parseMembers(memberSet));
    }

    private void setDescriptorFields(ArgumentMultimap argMultimap, EditTaskDescriptor editTaskDescriptor)
            throws ParseException {
        assert argMultimap != null;
        assert editTaskDescriptor != null;

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setDescription(ParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editTaskDescriptor.setNote(ParserUtil.parseNote(argMultimap
                    .getValue(PREFIX_NOTE).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap
                    .getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editTaskDescriptor.setPriority(ParserUtil.parsePriority(argMultimap
                    .getValue(PREFIX_PRIORITY).get()));
        }

        /*
         * If the bottom calls to parse*() fail, then the resulting help-string, along with an
         * IllegalArgumentException will be thrown and floated to the caller, without continuing
         * with the parsing
         */
        parseMembersForEdit(argMultimap.getAllValues(PREFIX_MEMBER)).ifPresent(editTaskDescriptor::setMembers);
    }

}
