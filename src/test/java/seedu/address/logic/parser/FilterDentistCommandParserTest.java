package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterDentistCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

public class FilterDentistCommandParserTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final FilterDentistCommandParser parser = new FilterDentistCommandParser();

    @Test
    public void parse_validArguments_returnsFilterDentistCommand() throws ParseException {
        String args = "a/phone k/98765432";
        FilterDentistCommand expectedCommand = new FilterDentistCommand("phone", "98765432");

        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validArgumentsWithWhitespace_returnsFilterDentistCommand() throws ParseException {
        String args = "a/  phone k/  98765432";
        FilterDentistCommand expectedCommand = new FilterDentistCommand("phone", "98765432");

        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyKeywords_throwsParseException() {
        String args = "a/attribute k/";
        String expectedMessage = "Please follow the valid filter command format, filter-dentist a/(attribute) "
            + "k/(keywords).";
        assertParseFailure(parser, args, String.format(expectedMessage));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        assertParseFailure(parser, args, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FilterDentistCommand.MESSAGE_USAGE));
    }
}
