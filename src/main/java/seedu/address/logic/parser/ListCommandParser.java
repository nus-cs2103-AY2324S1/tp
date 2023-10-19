package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new ListCommand object with sorting options.
 */
public class ListCommandParser implements Parser<ListCommand> {

    @Override
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_SORT);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String sortingAttribute = argMultimap.getValue(CliSyntax.PREFIX_SORT).orElse(null);
        System.out.println("sortingAttribute: " + sortingAttribute);

        if (sortingAttribute == null) {
            return new ListCommand();
        }

        // Create the sorting comparator based on the sorting attribute
        Comparator<Person> sortingComparator = createSortingComparator(sortingAttribute);


        return new ListCommand(sortingComparator);
    }

    private Comparator<Person> createSortingComparator(String sortingAttribute) {
        if ("name".equalsIgnoreCase(sortingAttribute)) {
            System.out.println("Sorting by name");
            return Comparator.comparing(Person::getName);
        } else {
            // Default: no sorting (you can change this behavior as needed)
            return null;
        }
    }
}
