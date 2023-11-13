package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.statistics.StatisticMetric;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

//@@author
public class AddressBookParserTest {

    private static final UniqueTagList uniqueTagList = new UniqueTagList();
    private final AddressBookParser parser = new AddressBookParser();

    @AfterEach
    public void clearTestData() {
        Tag tag = new Tag("Interview", "assessment");
        if (uniqueTagList.contains(tag)) {
            uniqueTagList.remove(new Tag("Interview", "assessment"));
        }
    }

    //@@author
    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    //@@author
    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    //@@author
    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    //@@author
    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    //@@author
    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    //@@author
    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + "n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(Arrays.asList(new NameContainsKeywordsPredicate(keywords))), command);
    }

    //@@author
    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    //@@author
    @Test
    public void parseCommand_set() throws Exception {
        assertTrue(parser.parseCommand(SetCommand.COMMAND_WORD + " 5 Interviewed") instanceof SetCommand);
        assertTrue(parser.parseCommand(SetCommand.COMMAND_WORD + " 3 Interviewed") instanceof SetCommand);
    }

    //@@author
    @Test
    public void parseCommand_parseStatusType() throws Exception {
        assertTrue(ParserUtil.parseStatusType("Interviewed").toString().equals("Interviewed"));
    }

    //@@author
    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_export() throws Exception {
        assertTrue(parser.parseCommand(ExportCommand.COMMAND_WORD) instanceof ExportCommand);
    }

    //@@author sk2001git
    @Test
    public void parseCommand_remark() throws Exception {
        final String remark = "Some remark.";
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + remark);
        assertEquals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark(remark)), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_viewWrongIndex_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("view 0"));
    }

    @Test
    public void parseCommand_filter() throws Exception {
        Tag tag = new Tag("Interview", "assessment");
        uniqueTagList.add(tag);
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + "t/Interview met/MEAN");
        assertEquals(new FilterCommand(tag, StatisticMetric.MEAN, 0), command);
    }

    @Test
    public void parseCommand_filterInvalidTag_throwsParseException() {
        Tag tag = new Tag("Interview", "assessment");
        uniqueTagList.add(tag);
        String message = "val/VALUE is missing, it is compulsory. \n" + FilterCommand.MESSAGE_USAGE;
        assertThrows(ParseException.class, message, ()
            -> parser.parseCommand("filter t/Interview met/PERCENTILE"));
    }

    @Test
    public void parseCommand_createTag() throws ParseException {
        assertTrue(parser.parseCommand(CreateTagCommand.COMMAND_WORD + " t/dept software") instanceof CreateTagCommand);
        assertTrue(parser.parseCommand(CreateTagCommand.COMMAND_WORD + " t/role developer t/dept software")
                instanceof CreateTagCommand);
    }

    @Test
    public void parseCommand_listT() throws ParseException {
        assertTrue(parser.parseCommand(ListTCommand.COMMAND_WORD) instanceof ListTCommand);
        assertTrue(parser.parseCommand(ListTCommand.COMMAND_WORD + " 3") instanceof ListTCommand);
    }


    //@@author
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    //@@author
    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
