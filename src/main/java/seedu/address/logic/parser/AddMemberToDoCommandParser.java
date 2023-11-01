package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMemberToDoCommand;
import seedu.address.logic.commands.AddMemberToDoCommand.AddMemberToDoDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.fields.Tasklist;

import java.util.HashSet;

public class AddMemberToDoCommandParser implements Parser<AddMemberToDoCommand> {
    @Override
    public AddMemberToDoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TODO);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMemberToDoCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TODO);

        AddMemberToDoDescriptor addMemberToDoDescriptor = new AddMemberToDoDescriptor();
        if (argMultimap.getValue(PREFIX_TODO).isPresent()) {
            System.out.println("LOOK EHRE" + (argMultimap.getValue(PREFIX_TODO).get()));
            addMemberToDoDescriptor.setTasks(new Tasklist(argMultimap.getValue(PREFIX_TODO).get()));
        }
        System.out.println(addMemberToDoDescriptor);
        return new AddMemberToDoCommand(index, addMemberToDoDescriptor);
    }
}
