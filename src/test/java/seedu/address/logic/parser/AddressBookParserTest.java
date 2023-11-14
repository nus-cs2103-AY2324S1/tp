package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.addcommands.AddCompanyCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.commands.clearcommands.ClearAllCommand;
import seedu.address.logic.commands.clearcommands.ClearInternshipCommand;
import seedu.address.logic.commands.deletecommands.DeleteCompanyCommand;
import seedu.address.logic.commands.deletecommands.DeletePersonCommand;
import seedu.address.logic.commands.editcommands.EditPersonCommand;
import seedu.address.logic.commands.editcommands.editdescriptors.EditPersonDescriptor;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.findcommands.FindCompanyCommand;
import seedu.address.logic.commands.findcommands.FindPersonCommand;
import seedu.address.logic.commands.listcommands.ListCommand;
import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.commands.viewcommands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyNameAndTagContainKeywordsPredicate;
import seedu.address.model.person.NameAndTagContainKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.CompanyUtil;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand personCommand = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPersonCommand(person), personCommand);
        Company company = new CompanyBuilder().build();
        AddCompanyCommand companyCommand = (AddCompanyCommand) parser.parseCommand(CompanyUtil.getAddCommand(company));
        assertEquals(new AddCompanyCommand(company), companyCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD) instanceof ClearAllCommand);
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD + " i 1") instanceof ClearInternshipCommand);
    }

    @Test
    public void parseCommand_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " p " + INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY), command);
    }

    @Test
    public void parseCommand_deleteCompany() throws Exception {
        DeleteCompanyCommand command = (DeleteCompanyCommand) parser.parseCommand(
                DeleteCompanyCommand.COMMAND_WORD + " c " + INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getOneBased());
        assertEquals(new DeleteCompanyCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD
                + " p " + INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        // Example keywords to search for
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        List<String> tagKeywords = Arrays.asList("tag1", "tag2");

        String nameKeywordsString = nameKeywords.stream()
            .map(keyword -> PREFIX_NAME + keyword)
            .collect(Collectors.joining(" "));
        String tagKeywordsString = tagKeywords.stream()
            .map(keyword -> PREFIX_TAG + keyword)
            .collect(Collectors.joining(" "));

        // Example for FindPersonCommand
        FindPersonCommand personCommand = (FindPersonCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + FindCommandParser.FIND_PERSONS_ARG_WORD + " "
                + nameKeywordsString + " " + tagKeywordsString);
        assertEquals(new FindPersonCommand(
            new NameAndTagContainKeywordsPredicate(nameKeywords, tagKeywords)), personCommand);

        // Example for FindCompanyCommand
        FindCompanyCommand companyCommand = (FindCompanyCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + FindCommandParser.FIND_COMPANIES_ARG_WORD + " "
                + nameKeywordsString + " " + tagKeywordsString);
        assertEquals(new FindCompanyCommand(
            new CompanyNameAndTagContainKeywordsPredicate(nameKeywords, tagKeywords)), companyCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " p") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " c") instanceof ListCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " p" + " 1") instanceof ViewCommand);
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " c" + " 1") instanceof ViewCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " c") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " c"
            + " start/14-05-2012 10:51") instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, (
        ) -> parser.parseCommand("unknownCommand"));
    }
}
