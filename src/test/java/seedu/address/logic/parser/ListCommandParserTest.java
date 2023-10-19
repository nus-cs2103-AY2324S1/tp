package seedu.address.logic.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

public class ListCommandParserTest {

    private ListCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ListCommandParser();
    }

    @Test
    public void parse_noSortingAttribute_returnsListCommandWithDefaultComparator() throws ParseException {
        String args = "";

        ListCommand result = parser.parse(args);

        // Ensure the ListCommand uses the default sorting comparator
        assertEquals(result.getSortingComparator(), ListCommand.DEFAULT_COMPARATOR);
    }
}

