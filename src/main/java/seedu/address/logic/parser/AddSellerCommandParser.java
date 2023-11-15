package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLING_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.displayable.Address.DEFAULT_ADDRESS_STRING;
import static seedu.address.model.displayable.Email.DEFAULT_EMAIL_STRING;
import static seedu.address.model.displayable.HouseInfo.DEFAULT_HOUSE_INFO;
import static seedu.address.model.displayable.Phone.DEFAULT_PHONE_STRING;
import static seedu.address.model.displayable.Priority.DEFAULT_PRIO_LVL;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddSellerCommand object
 */
public class AddSellerCommandParser implements Parser<AddSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSellerCommand
     * and returns an AddSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSellerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SELLING_ADDRESS, PREFIX_HOUSE_INFO, PREFIX_TAG, PREFIX_PRIORITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_SELLING_ADDRESS, PREFIX_PRIORITY);
        Name name = ParserUtil.parseName(commandWarnings, argMultimap.getValueOrDefault(PREFIX_NAME, ""));
        assert name != null && !name.toString().trim().isEmpty() : "All sellers must have names!";
        Phone phone = ParserUtil.parsePhone(commandWarnings, argMultimap.getValueOrDefault(PREFIX_PHONE,
                DEFAULT_PHONE_STRING));
        Email email = ParserUtil.parseEmail(commandWarnings, argMultimap.getValueOrDefault(PREFIX_EMAIL,
                DEFAULT_EMAIL_STRING));
        Address address = ParserUtil.parseAddress(commandWarnings, argMultimap.getValueOrDefault(PREFIX_ADDRESS,
                DEFAULT_ADDRESS_STRING));
        Address sellingAddress = ParserUtil.parseAddress(commandWarnings, argMultimap.getValueOrDefault(
                PREFIX_SELLING_ADDRESS, DEFAULT_ADDRESS_STRING));
        HouseInfo houseInfo = ParserUtil.parseHouseInfo(commandWarnings, argMultimap.getValueOrDefault(
                PREFIX_HOUSE_INFO, DEFAULT_HOUSE_INFO));
        Set<Tag> tagList = ParserUtil.parseTags(commandWarnings, argMultimap.getAllValues(PREFIX_TAG));
        Priority priority = ParserUtil.parsePriority(
                commandWarnings,
                argMultimap.getValueOrDefault(PREFIX_PRIORITY, DEFAULT_PRIO_LVL)
        );

        Seller seller = new Seller(name, phone, email, address, sellingAddress, houseInfo, tagList, priority);

        return new AddSellerCommand(seller, commandWarnings);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> !argumentMultimap.getValueOrDefault(prefix, "").isEmpty());
    }

}
