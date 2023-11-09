package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.comparer.AddressComparator;
import seedu.address.model.person.comparer.EmailComparator;
import seedu.address.model.person.comparer.NameComparator;
import seedu.address.model.person.comparer.PhoneComparator;
import seedu.address.model.person.comparer.SortComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String PARSE_EXCEPTION_MESSAGE = "Invalid syntax for sort. Please use the "
            + "following syntax: sort {/byattribute} {/reverse (optional)}. Example: sort /byname /reverse."
            + " Possible attributes: /byname, /byemail, /byaddress, /byphone ";
    private static final Prefix PREFIX_DELIMITER = new Prefix("/");
    private static final String SORT_BY_ADDRESS_KEYWORD = "byaddress";
    private static final String SORT_BY_EMAIL_KEYWORD = "byemail";
    private static final String SORT_BY_NAME_KEYWORD = "byname";
    private static final String SORT_BY_PHONE_KEYWORD = "byphone";
    private static final String REVERSE_KEYWORD = "reverse";
    private static final int MAX_SECONDARY_SORT = 0;
    private static final int MAX_SORTS = MAX_SECONDARY_SORT + 1;
    private static final int MAX_SORT_ARGS = 2 + MAX_SECONDARY_SORT * 2;
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DELIMITER);
        argMultimap.verifyNoDuplicatePrefixesFor();

        List<String> sortValues = argMultimap.getAllValues(PREFIX_DELIMITER);

        ArrayList<SortComparator> sortComparatorList = new ArrayList<>();
        boolean isEmptyArray = sortValues.isEmpty();
        boolean isTooManyArgs = sortValues.size() > MAX_SORT_ARGS;
        if (isEmptyArray || isTooManyArgs) {
            throw new ParseException(PARSE_EXCEPTION_MESSAGE);
        }

        for (String command: sortValues) {
            String commandLower = command.toLowerCase();
            SortComparator sortComparator;

            if (!commandLower.equals(REVERSE_KEYWORD) && sortComparatorList.size() > MAX_SECONDARY_SORT) {
                throw new ParseException(PARSE_EXCEPTION_MESSAGE);
            }

            switch (commandLower) {
            case SORT_BY_ADDRESS_KEYWORD:
                sortComparator = new AddressComparator(true, false, 1);
                sortComparatorList.add(sortComparator);
                break;

            case SORT_BY_EMAIL_KEYWORD:
                sortComparator = new EmailComparator(true, false, 1);
                sortComparatorList.add(sortComparator);
                break;

            case SORT_BY_NAME_KEYWORD:
                sortComparator = new NameComparator(true, false, 1);
                sortComparatorList.add(sortComparator);
                break;

            case SORT_BY_PHONE_KEYWORD:
                sortComparator = new PhoneComparator(true, false, 1);
                sortComparatorList.add(sortComparator);
                break;

            case REVERSE_KEYWORD:
                if (sortComparatorList.isEmpty()) {
                    throw new ParseException(PARSE_EXCEPTION_MESSAGE);
                }
                int lastIdx = sortComparatorList.size() - 1;
                sortComparatorList.get(lastIdx).setIsReverse(true);
                break;

            default:
                throw new ParseException(PARSE_EXCEPTION_MESSAGE);
            }
        }

        return new SortCommand(sortComparatorList);
    }

}
