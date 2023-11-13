package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BarChartCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.TableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordPredicate;
import seedu.address.model.person.SortIn;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentPredicateList;
import seedu.address.model.person.StudentTakesSubjectPredicate;
import seedu.address.model.person.Visual;
import seedu.address.model.tag.Subject;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Student person = new PersonBuilder().build();
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PersonBuilder.DEFAULT_NAME);
        assertEquals(new DeleteCommand(person.getName()), command);
    }

    @Test
    public void parseCommand_deleteIndex() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_table() throws Exception {
        TableCommand command1 = (TableCommand) parser.parseCommand(TableCommand.COMMAND_WORD + " g/");
        TableCommand command2 = (TableCommand) parser.parseCommand(TableCommand.COMMAND_WORD + " l/");
        TableCommand command3 = (TableCommand) parser.parseCommand(TableCommand.COMMAND_WORD + " s/");

        assertEquals(new TableCommand("g/"), command1);
        assertEquals(new TableCommand("l/"), command2);
        assertEquals(new TableCommand("s/"), command3);
    }

    @Test
    public void parseCommand_barChart() throws Exception {
        BarChartCommand command1 = (BarChartCommand) parser.parseCommand(BarChartCommand.COMMAND_WORD + " g/");
        BarChartCommand command2 = (BarChartCommand) parser.parseCommand(BarChartCommand.COMMAND_WORD + " l/");
        BarChartCommand command3 = (BarChartCommand) parser.parseCommand(BarChartCommand.COMMAND_WORD + " s/");

        assertEquals(new BarChartCommand("g/"), command1);
        assertEquals(new BarChartCommand("l/"), command2);
        assertEquals(new BarChartCommand("s/"), command3);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        String sequence = "ASC";
        SortIn sortIn = new SortIn(sequence);
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " in/" + sequence);
        assertEquals(new SortCommand(sortIn), command);
    }
    @Test
    public void parseCommand_export() throws Exception {
        String visualType = "Bar";
        Visual visual = new Visual(visualType);
        ExportCommand command = (ExportCommand) parser.parseCommand(
                                            ExportCommand.COMMAND_WORD + " v/" + visualType);
        assertEquals(new ExportCommand(visual), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_search() throws Exception {
        String keyword = "foo";
        SearchCommand command = (SearchCommand) parser.parseCommand(
                SearchCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new SearchCommand(new NameContainsKeywordPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        String condition = "s/English";
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + condition);
        StudentTakesSubjectPredicate predicate = new StudentTakesSubjectPredicate(new Subject("English"));
        StudentPredicateList newList = new StudentPredicateList();
        newList.add(predicate);
        assertEquals(new FilterCommand(newList), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
