package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class GroupParser implements Parser<GroupCommand> {

	/**
	 * Parses the given {@code String} of arguments in the context of the AddCommand
	 * and returns an AddCommand object for execution.
	 * @throws ParseException if the user input does not conform the expected format
	 */

	public GroupCommand parse(String args) throws ParseException {
		ArgumentMultimap argMultimap =
				ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUPTAG);


		if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_GROUPTAG)
				|| !argMultimap.getPreamble().isEmpty()) {
			throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
		}

		argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
		Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
		Group group = ParserUtil.parseName(argMultimap.getValue(PREFIX_GROUPTAG).get());
		Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
		//
		String groupName = ParserUtil.parseName(argMultimap.getValue(PREFIX_GROUPTAG).get()).toString();
		Group group = new Group(groupName);

		return new CreateGroupCommand(group);
		//

		Person person = new Person(name, phone, email, address, tagList);

		return new AddCommand(person);
	}

	/**
	 * Returns true if none of the prefixes contains empty {@code Optional} values in the given
	 * {@code ArgumentMultimap}.
	 */
	private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
		return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
	}


}
